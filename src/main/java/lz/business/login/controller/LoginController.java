package lz.business.login.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lz.annotation.LogAspectAnnotation;
import lz.business.authManage.service.ResourceService;
import lz.business.authManage.service.UserService;
import lz.business.login.service.SecurityService;
import lz.constant.ConstantInfo;
import lz.exception.ControllerException;
import lz.model.Security;
import lz.model.User;
import lz.utils.MathUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/loginController")
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private SecurityService securityService;
	/**
	 * 
	 * 描述：登录用户名密码校验
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:29:03
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginValid")
	@ResponseBody
	@LogAspectAnnotation(logDesc="登录成功",logBusiness="登录")
	public Map<String,Object> add(@RequestBody User user,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			User u = userService.getUserByNameAndPwd(user);
			if(u==null){
				map.put("result","infoError");
			}else{
				if(ConstantInfo.USER_STOP.equals(u.getStatus())){
					map.put("result","userStop");
				}else{
					map.put("result","success");
					request.getSession().setAttribute("loginUser",u);
				}
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"登录失败","登录","/loginController/loginValid");
		}
		return map;
	}
	
	/**
	 * 跳转到注册页面
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月30日 下午3:11:04
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerPage")
	public String registerPage(HttpServletRequest request){
		return "/registerPage";
	}
	/**
	 * 注册用户
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月31日 下午5:17:04
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	@LogAspectAnnotation(logDesc="注册成功",logBusiness="注册")
	public Map<String,Object> register(@RequestBody User user,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		String security = (String)request.getSession().getAttribute("registerSecurityCode");
		try {
			if(user.getSecurityCode().equals(security)){
				Date currentDate = new Date();
				String createTime = DateFormatUtils.format(DateUtils.addMinutes(currentDate,-10),"yyyy-MM-dd HH:mm:ss");
				queryMap.put("createTime",createTime);
				queryMap.put("securityType",ConstantInfo.SECURITY_CODE_PHONE);
				queryMap.put("phone",user.getPhone());
				//验证注册的验证码是否超过10分钟有效期
				if(securityService.getSecurityIsEffective(queryMap)>0){
					Map<String,Object> args = new HashMap<String,Object>();
					args.put("username", user.getName());
					List<User> users1 = userService.getUsers(args);
					if(users1!=null&&users1.size()>0){
						map.put("result","nameIsExist");
					}else{
						args.clear();
						args.put("telephone",user.getPhone());
						List<User> users2 = userService.getUsers(args);
						if(users2!=null&&users2.size()>0){
							map.put("result","phoneIsExist");
						}else{
							userService.insertRegisterUser(user);
							map.put("result","success");
						}
					}
				}else{
					map.put("result","securityTimeOut");
				}
			}else{
				map.put("result","securityError");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"注册失败","注册","/loginController/register");
		}
		return map;
	}
	/**
	 * 跳转到找回密码页面
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月30日 下午3:11:14
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPswPage")
	public String getPswPage(HttpServletRequest request){
		return "/getPswPage";
	}
	/**
	 * 注册时发送验证码
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月31日 下午5:16:50
	 * @param args
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sendSecurityCode")
	@ResponseBody
	public Map<String,Object> sendSecurityCode(@RequestBody Map<String,Object> args,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		Security security = null;
		try {
			List<User> users2 = userService.getUsers(args);
			//验证该手机号是否已经注册过
			if(users2!=null&&users2.size()>0){
				map.put("result","phoneIsExist");
			}else{
				queryMap.put("securityType",ConstantInfo.SECURITY_CODE_PHONE);
				queryMap.put("phone",args.get("telephone"));
				security = securityService.getSecurity(queryMap);
				String securityCode = MathUtils.getNum(6);
				//该手机号首次发送验证码
				if(security==null){
					security = new Security();
					security.setType(ConstantInfo.SECURITY_CODE_PHONE);
					security.setSecurityCode(securityCode);
					security.setPhone((String)args.get("telephone"));
					securityService.insertSecurity(security);
					request.getSession().setAttribute("registerSecurityCode",securityCode);
					map.put("result","success");
					map.put("securityCode", securityCode);
				}else{
					Date currentDate = new Date();
					String createTime = DateFormatUtils.format(DateUtils.addMinutes(currentDate,-1),"yyyy-MM-dd HH:mm:ss");
					queryMap.put("createTime",createTime);
					//距离上次发送验证码不到一分钟，提示发送验证码频繁，请稍等会重新发送
					if(securityService.getSecurityIsEffective(queryMap)>0){
						map.put("result","timeTooShort");
					}else{
						security.setSecurityCode(securityCode);
						securityService.updateSecurity(security);
						request.getSession().setAttribute("registerSecurityCode",securityCode);
						map.put("result","success");
						map.put("securityCode", securityCode);
					}
				}
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"注册时发送验证码失败","发送验证码","/loginController/sendSecurityCode");
		}
		return map;
	}
	/**
	 * 找回密码验证用户名和手机号信息
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月31日 下午5:16:35
	 * @param args
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPwdValidateUserNameAndPhone")
	@ResponseBody
	public Map<String,Object> getPwdValidateUserNameAndPhone(@RequestBody Map<String,Object> args,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		Security security = null;
		try {
			List<User> users = userService.getUsers(args);
			if(users!=null&&users.size()>0){
				String securityCode = MathUtils.getNum(6);
				queryMap.put("securityType",ConstantInfo.SECURITY_CODE_PHONE);
				queryMap.put("phone",args.get("phone"));
				security = securityService.getSecurity(queryMap);
				if(security==null){
					security = new Security();
					security.setType(ConstantInfo.SECURITY_CODE_PHONE);
					security.setSecurityCode(securityCode);
					security.setPhone((String)args.get("phone"));
					securityService.insertSecurity(security);
					request.getSession().setAttribute("getPwdSecurityCode",securityCode);
					request.getSession().setAttribute("getPwdName",args.get("name"));
					map.put("result","success");
					map.put("securityCode", securityCode);
					map.put("resetPswUserName",args.get("name"));
					map.put("resetPswPhone",args.get("phone"));
				}else{
					Date currentDate = new Date();
					String createTime = DateFormatUtils.format(DateUtils.addMinutes(currentDate,-1),"yyyy-MM-dd HH:mm:ss");
					queryMap.put("createTime",createTime);
					//距离上次找回密码发送验证码不到一分钟，提示发送验证码频繁，请稍等会重新发送
					if(securityService.getSecurityIsEffective(queryMap)>0){
						map.put("result","timeTooShort");
					}else{
						security.setSecurityCode(securityCode);
						securityService.updateSecurity(security);
						request.getSession().setAttribute("getPwdSecurityCode",securityCode);
						map.put("result","success");
						map.put("securityCode", securityCode);
						map.put("resetPswUserName",args.get("name"));
						map.put("resetPswPhone",args.get("phone"));
					}
				}
				
			}else{
				map.put("result","infoError");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"找回密码验证用户名和密码失败","找回密码","/loginController/getPwdValidateUserNameAndPhone");
		}
		return map;
	}
	/**
	 * 找回密码验证验证码
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月31日 下午5:16:12
	 * @param args
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPwdValidateSecurity")
	@ResponseBody
	public Map<String,Object> getPwdValidateSecurity(@RequestBody Map<String,Object> args,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		try {
			String security = (String)request.getSession().getAttribute("getPwdSecurityCode");
			String securityCode = (String)args.get("securityCode");
			if(security.equals(securityCode)){
				Date currentDate = new Date();
				String createTime = DateFormatUtils.format(DateUtils.addMinutes(currentDate,-10),"yyyy-MM-dd HH:mm:ss");
				queryMap.put("createTime",createTime);
				queryMap.put("securityType",ConstantInfo.SECURITY_CODE_PHONE);
				queryMap.put("phone",args.get("phone"));
				if(securityService.getSecurityIsEffective(queryMap)>0){
					map.put("result","success");
					map.put("resetPswSecurity",securityCode);
				}else{
					map.put("result","securityTimeOut");
				}
			}else{
				map.put("result","securityError");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"找回密码验证验证码失败","找回密码","/loginController/getPwdValidateSecurity");
		}
		return map;
	}
	/**
	 * 重置密码,验证
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月31日 下午5:16:01
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPwdValidateResetPsw")
	@ResponseBody
	@LogAspectAnnotation(logDesc="找回密码成功",logBusiness="找回密码")
	public Map<String,Object> getPwdValidateResetPsw(@RequestBody User user,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		String getPswName = (String)request.getSession().getAttribute("getPswName");
		try {
			String security = (String)request.getSession().getAttribute("getPwdSecurityCode");
			String securityCode = user.getSecurityCode();
			if(security.equals(securityCode)){
				Date currentDate = new Date();
				String createTime = DateFormatUtils.format(DateUtils.addMinutes(currentDate,-10),"yyyy-MM-dd HH:mm:ss");
				queryMap.put("createTime",createTime);
				queryMap.put("securityType",ConstantInfo.SECURITY_CODE_PHONE);
				queryMap.put("phone",user.getPhone());
				if(securityService.getSecurityIsEffective(queryMap)>0){
					if(getPswName.equals(user.getName())){
						if(userService.getUserByNameAndPwd(user)!=null){
							map.put("result","pwdIsExsit");
						}else{
							userService.updatePswByName(user,null);
							map.put("result","success");
						}
					}else{
						map.put("result","error");
					}
					
				}else{
					map.put("result","securityTimeOut");
				}
			}else{
				map.put("result","securityTimeOut");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"找回密码重置密码失败","找回密码","/loginController/getPwdValidateResetPsw");
		}
		return map;
	}
	
}
