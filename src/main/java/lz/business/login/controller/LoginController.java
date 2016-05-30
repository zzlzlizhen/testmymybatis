package lz.business.login.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.business.authManage.service.ResourceService;
import lz.business.authManage.service.UserService;
import lz.constant.ConstantInfo;
import lz.model.Resource;
import lz.model.SystemParam;
import lz.model.User;
import lz.utils.MathUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * 描述：登录成功跳转界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:29:29
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpServletRequest request){
		return "/main";
	}
	@RequestMapping("/homePage")
	public String homePage(HttpServletRequest request){
		return "/homePage";
	}
	@RequestMapping("/getMenus")
	@ResponseBody
	public List<Resource> getMenus(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",user.getId());
		map.put("pid",ConstantInfo.ONE_RESOURCE_PID);
		List<Resource> listResourceMenu = getResourceMenu(map);
		return listResourceMenu;
	}
	public List<Resource> getResourceMenu(Map<String,Object> map){
		List<Resource> rootResource = resourceService.getResourceMenuByUserId(map);
		for(Resource resource:rootResource){
			map.put("pid",resource.getId());
			List<Resource> childResource = getResourceMenu(map);
			if(childResource!=null&&childResource.size()>0){
				resource.setChildResource(childResource);
				resource.setHasChild(true);
			}
		}
		return rootResource;
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
	 * 描述：退出
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:29:47
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("loginUser");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
