package lz.business.authManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.business.authManage.service.ResourceService;
import lz.business.authManage.service.RoleService;
import lz.model.Resource;
import lz.model.Role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/roleController")
public class RoleController {
	@javax.annotation.Resource
	private RoleService roleService;
	@javax.annotation.Resource
	private ResourceService resourceService;
	/**
	 * 描述：角色列表界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:28
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleIndex")
	public String roleIndex(HttpServletRequest request){
		return "/authManage/role/roleIndex";
	}
	/**
	 * 描述：跳转到角色新增界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:34
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		List<Role> listRole = roleService.getRoles(null);
		request.setAttribute("listRole",listRole);
		return "/authManage/role/addRole";
	}
	/**
	 * 描述：跳转到角色详情界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:38
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		Role role = roleService.getRoleById(id);
		request.setAttribute("role",role);
		return "/authManage/role/viewRole";
	}
	/**
	 * 描述：跳转到角色编辑页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:43
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id,HttpServletRequest request){
		Role role = roleService.getRoleById(id);
		request.setAttribute("role",role);
		return "/authManage/role/editRole";
	}
	/**
	 * 描述：跳转到角色授权界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:47
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/authPage/{id}")
	public String authPage(@PathVariable String id,HttpServletRequest request){
		request.setAttribute("id",id);
		return "/authManage/role/authRole";
	}
	/**
	 * 描述：保存角色
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:52
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(@RequestBody Role role){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			roleService.insertRole(role);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 描述：修改角色
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:19:56
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(@RequestBody Role role){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			roleService.updateRole(role);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 描述:删除角色
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:00
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String,Object> del(@RequestBody Role role){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			roleService.delRole(role);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 描述：角色授权
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:04
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/auth")
	@ResponseBody
	public Map<String,Object> auth(@RequestBody Role role){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			roleService.insertRoleResource(role);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 描述：获取角色的分页数据
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:20:09
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getRoles")
	public void getroles(HttpServletRequest request,HttpServletResponse response){
		String sEcho = request.getParameter("sEcho");
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		String searchId = request.getParameter("roleName");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",iDisplayStart);
		map.put("end",iDisplayLength);
		if(searchId!=null && !"".equals(searchId)){
			map.put("roleName","%"+searchId+"%");
		}
		List<Role> list = roleService.getRolePage(map);
		int total = roleService.getCount(map);
		Object[][] data=new Object[list.size()][4];
		for(int j=0;j<list.size();j++){ 
			Role role = list.get(j); 
			data[j][0]=role.getId();
			data[j][1]=role.getRoleName();
			data[j][2]=role.getRemark();
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
