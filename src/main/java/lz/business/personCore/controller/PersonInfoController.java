/**
 * 
 */
package lz.business.personCore.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lz.annotation.LogAspectAnnotation;
import lz.business.authManage.service.UserService;
import lz.constant.ConstantInfo;
import lz.exception.ControllerException;
import lz.model.Message;
import lz.model.User;
import lz.utils.IdGenerateUtils;
import lz.webSocket.WebSocketEndPoint;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

/**
 * @author lizhen_pc
 *123
 */
@Controller
@RequestMapping("/personInfoController")
public class PersonInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	private WebSocketEndPoint websocket;
	@RequestMapping("/setPasswordPage")
	public String setPasswordPage(HttpServletRequest request){
		return "/personCore/personInfo/setPassword";
	}
	@RequestMapping("/setPersonInfoPage")
	public String userIndex(HttpServletRequest request){
		return "/personCore/personInfo/setPersonInfo";
	}
	/**
	 * 修改密码
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月15日 下午4:55:46
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePersonPassword")
	@ResponseBody
	@LogAspectAnnotation(logDesc="修改密码成功",logBusiness="个人中心")
	public Map<String,Object> getPwdValidateResetPsw(@RequestBody User user,HttpServletRequest request){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//用手机号这个属性接受页面传递过来的旧密码
			String oldPws = user.getPhone();
			if(!oldPws.equals(loginUser.getPwd())){
				map.put("result","oldPwsError");
			}else if(loginUser.getPwd().equals(user.getPwd())){
				map.put("result","pswIsExist");
			}else{
				Message message = new Message();
				message.setId(IdGenerateUtils.getId());
				message.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				message.setPublishTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				message.setCreatedUser(user.getName());
				message.setMessageType(ConstantInfo.USER_MESSAGE);
				message.setMessageStatus(ConstantInfo.MESSAGE_PUBLISH);
				message.setMessageHead("修改密码成功");
				message.setMessageContent("恭喜您的密码在"+DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"时间修改成功");
				user.setName(loginUser.getName());
				userService.updatePswByName(user,message);
				map.put("result","success");
				loginUser.setPwd(user.getPwd());
				request.getSession().setAttribute("loginUser",loginUser);
				websocket.sendMessageToUser(user,message);
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"修改密码失败","个人中心","/personInfoController/updatePersonPassword");
		}
		return map;
	}
	/**
	 * 更新用户信息
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月15日 下午5:29:53
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePersonInfo")
	@ResponseBody
	@LogAspectAnnotation(logDesc="修改信息成功",logBusiness="个人中心")
	public Map<String,Object> updatePersonInfo(@RequestBody User user,HttpServletRequest request){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> args = new HashMap<String,Object>();
		try {
			String phone = user.getPhone();
			if((phone!=null)&&(!"".equals(phone))&&(!phone.equals(loginUser.getPhone()))){
				args.put("telephone",user.getPhone());
				List<User> users2 = userService.getUsers(args);
				if(users2!=null&&users2.size()>0){
					map.put("result","phoneIsExist");
				}else{
					loginUser.setPhone(user.getPhone());
					loginUser.setEmail(user.getEmail());
					userService.updatePersonInfo(loginUser);
					map.put("result","success");
					request.getSession().setAttribute("loginUser",loginUser);
				}
			}else{
				loginUser.setPhone(user.getPhone());
				loginUser.setEmail(user.getEmail());
				userService.updatePersonInfo(loginUser);
				map.put("result","success");
				request.getSession().setAttribute("loginUser",loginUser);
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"修改信息失败","个人中心","/personInfoController/updatePersonInfo");
		}
		return map;
	}
}
