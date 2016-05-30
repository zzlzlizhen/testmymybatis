package lz.business.authManage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.business.authManage.service.RoleService;
import lz.business.authManage.service.UserService;
import lz.model.Role;
import lz.model.User;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	/**
	 * 描述：用户列表首页
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:39
	 * @param request
	 * @return
	 */
	@RequestMapping("/userIndex")
	public String userIndex(HttpServletRequest request){
		return "/authManage/user/userIndex";
	}
	/**
	 * 描述：跳转到新增用户信息页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:42
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		List<Role> listRoles = roleService.getRoles(null);
		request.setAttribute("listRoles",listRoles);
		return "/authManage/user/addUser";
	}
	/**
	 * 描述：跳转到查看用户信息页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:47
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		User user = userService.getUserById(id);
		List<Role> allRoles = roleService.getRoles(null);
		List<Role> checkedListRoles = user.getRoleLists();
		for(Role role:allRoles){
			//遍历集合的同时，删除集合的元素
			for(Iterator<Role> iter = checkedListRoles.iterator();iter.hasNext();){
				if(iter.next().getId().equals(role.getId())){
					//这里用updateTime时间来作为前台的显示标志，如果是1，说明是已经选中的角色
					role.setUpdateTime("1");
					iter.remove();
					break;
				}
			}
		}
		request.setAttribute("user",user);
		request.setAttribute("allRoles",allRoles);
		return "/authManage/user/viewUser";
	}
	/**
	 * 描述：跳转到修改用户页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:52
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id,HttpServletRequest request){
		User user = userService.getUserById(id);
		List<Role> allRoles = roleService.getRoles(null);
		List<Role> checkedListRoles = user.getRoleLists();
		for(Role role:allRoles){
			//遍历集合的同时，删除集合的元素
			for(Iterator<Role> iter = checkedListRoles.iterator();iter.hasNext();){
				if(iter.next().getId().equals(role.getId())){
					//这里用updateTime时间来作为前台的显示标志，如果是1，说明是已经选中的角色
					role.setUpdateTime("1");
					iter.remove();
					break;
				}
			}
		}
		request.setAttribute("user",user);
		request.setAttribute("allRoles",allRoles);
		return "/authManage/user/editUser";
	}
	/**
	 * 描述：保存用户信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:56
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(@RequestBody User user){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//此处用pwd来接收用户所选择的权限
			userService.insertUser(user);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 描述：修改用户信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:21:01
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(@RequestBody User user){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//此处用pwd来接收用户所选择的权限
			userService.updateUser(user);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 描述：删除用户信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:21:11
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String,Object> del(@RequestBody User user){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			userService.delUser(user);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 暂定或者恢复用户
	 * 描述：
	 * 作者：李震
	 * 时间：2016年5月30日 下午2:09:44
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/stopOrRecover")
	@ResponseBody
	public Map<String,Object> stopOrRecover(@RequestBody User user){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			userService.updateUserStatus(user);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/*
	 * 
	@RequestMapping("/authPage/{id}")
	public String authPage(@PathVariable int id,HttpServletRequest request){
		request.setAttribute("id",id);
		return "/authManage/user/authUser";
	} 
    @RequestMapping(value="/auth")
	@ResponseBody
	public Map<String,Object> auth(@RequestBody User user){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			userService.insertUserResource(user);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}*/
	/**
	 * 描述：获取用户信息的页面数据
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:21:17
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getUsers")
	public void getusers(HttpServletRequest request,HttpServletResponse response){
		String sEcho = request.getParameter("sEcho");
		Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		Map<String,Object> map = new HashMap<String,Object>();
		int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
		map.put("pageNum",pageNum);
		map.put("pageSize",iDisplayLength);
		if(userName!=null && !"".equals(userName)){
			map.put("name","%"+userName+"%");
		}if(phone!=null && !"".equals(phone)){
			map.put("phone",phone);
		}if(status!=null && !"".equals(status)){
			map.put("status",status);
		}
		PageInfo<User> page = userService.getUserPage(map);
		List<User> list = page.getList();
		long total = page.getTotal();
		Object[][] data=new Object[list.size()][7];
		for(int j=0;j<list.size();j++){ 
			User user = list.get(j); 
			data[j][0]=user.getId();
			data[j][1]=user.getName();
			data[j][2]=user.getPhone();
			data[j][3]=user.getEmail();
			data[j][4]=user.getCreateTime();
			data[j][5]=user.getStatus();
			
		}
		JSONObject jo = new JSONObject();
		jo.put("iTotalDisplayRecords",total);
		jo.put("iTotalRecords",total);
		jo.put("sEcho",sEcho);
		jo.put("aaData",data);
		try {
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
