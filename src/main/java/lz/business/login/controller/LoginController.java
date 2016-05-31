package lz.business.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lz.business.authManage.service.ResourceService;
import lz.business.authManage.service.UserService;
import lz.constant.ConstantInfo;
import lz.model.User;
import lz.utils.MathUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/loginController")
public class LoginController {
	@javax.annotation.Resource
	UserService userService;
	@javax.annotation.Resource
	private ResourceService resourceService;
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
	public Map<String,Object> register(@RequestBody User user,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String security = (String)request.getSession().getAttribute("registerSecurityCode");
		try {
			if(user.getSecurityCode().equals(security)){
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
				map.put("result","securityError");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
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
		try {
			String securityCode = MathUtils.getNum(6);
			request.getSession().setAttribute("registerSecurityCode",securityCode);
			map.put("result","success");
			map.put("securityCode", securityCode);
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
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
		try {
			List<User> users = userService.getUsers(args);
			if(users!=null&&users.size()>0){
				String securityCode = MathUtils.getNum(6);
				request.getSession().setAttribute("getPwdSecurityCode",securityCode);
				map.put("result","success");
				map.put("securityCode", securityCode);
				map.put("resetPswUserName",args.get("name"));
			}else{
				map.put("result","infoError");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
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
		try {
			String security = (String)request.getSession().getAttribute("getPwdSecurityCode");
			String securityCode = (String)args.get("securityCode");
			if(security.equals(securityCode)){
				map.put("result","success");
			}else{
				map.put("result","securityError");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 重置密码
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月31日 下午5:16:01
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPwdValidateResetPsw")
	@ResponseBody
	public Map<String,Object> getPwdValidateResetPsw(@RequestBody User user,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			userService.updatePswByName(user);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	
}
