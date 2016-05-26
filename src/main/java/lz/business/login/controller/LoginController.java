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
	 * 描述：登录成功跳转界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:29:29
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",user.getId());
		map.put("pid",ConstantInfo.ONE_RESOURCE_PID);
		List<Resource> listResourceMenu = getResourceMenu(map);
		request.setAttribute("listResourceMenu",listResourceMenu);
		return "/main";
	}
	@RequestMapping("/homePage")
	public String homePage(HttpServletRequest request){
		return "/homePage";
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
