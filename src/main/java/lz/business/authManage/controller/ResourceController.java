package lz.business.authManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;
import lz.business.authManage.service.ResourceService;
import lz.business.authManage.service.RoleService;
import lz.constant.ConstantInfo;
import lz.exception.ControllerException;
import lz.model.Resource;
import lz.model.Role;
import lz.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/resourceController")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RoleService roleService;
	/**
	 * 描述：跳转资源首页列表
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:18:29
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceIndex")
	public String resourceIndex(HttpServletRequest request){
		return "/authManage/resource/resourceIndex";
	}
	/**
	 * 描述：跳转到资源新增页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:18:09
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resourceUrl","#");
		//查询所有的资源url是#的信息（表示是父菜单）
		List<Resource> listResource = resourceService.getResources(map);
		request.setAttribute("listResource",listResource);
		return "/authManage/resource/addResource";
	}
	/**
	 * 描述：跳转到资源查看页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:17:53
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		Resource resource = resourceService.getResourceById(id);
		request.setAttribute("resource",resource);
		return "/authManage/resource/viewResource";
	}
	/**
	 * 描述：跳转到资源修改页面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:17:36
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id,HttpServletRequest request){
		Resource resource = resourceService.getResourceById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resourceUrl","#");
		//查询所有的资源url是#的信息（表示是父菜单）
		List<Resource> listResource = resourceService.getResources(map);
		request.setAttribute("resource",resource);
		request.setAttribute("listResource",listResource);
		return "/authManage/resource/editResource";
	}
	/**
	 * 描述：保存资源信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:17:23
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	@LogAspectAnnotation(logDesc="保存资源信息",logBusiness="资源管理")
	public Map<String,Object> add(@RequestBody Resource resource){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			resourceService.insertResource(resource);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"保存资源信息失败","资源管理","/resourceController/add");
		}
		return map;
	}
	/**
	 * 描述：编辑资源信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:17:07
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	@LogAspectAnnotation(logDesc="修改资源信息",logBusiness="资源管理")
	public Map<String,Object> edit(@RequestBody Resource resource){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			resourceService.updateResource(resource);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"修改资源信息失败","资源管理","/resourceController/edit");
		}
		return map;
	}
	/**
	 * 描述：删除资源信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:15:09
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	@LogAspectAnnotation(logDesc="删除资源信息",logBusiness="资源管理")
	public Map<String,Object> del(@RequestBody Resource resource){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			resourceService.delResource(resource);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"删除资源信息失败","资源管理","/resourceController/del");
		}
		return map;
	}
	/**
	 * 描述：批量删除资源信息
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:15:31
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/batchDel")
	@ResponseBody
	@LogAspectAnnotation(logDesc="批量删除资源信息",logBusiness="资源管理")
	public Map<String,Object> batchDel(@RequestBody Resource resource){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//这里用remark熟悉来传递需要删除的id串，格式是：id1，id2，id3
			String batchDelIds = resource.getRemark();
			int delSize = resourceService.batchDelResource(batchDelIds);
			map.put("delSize",delSize);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"批量删除资源信息失败","资源管理","/resourceController/batchDel");
		}
		return map;
	}
	/**
	 * 描述：返回资源信息分页数据
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:16:01
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getResources")
	public void getResources(HttpServletRequest request,HttpServletResponse response){
		try {
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String searchId = request.getParameter("resourceName");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			if(searchId!=null && !"".equals(searchId)){
				map.put("resourceName","%"+searchId+"%");
			}
			PageInfo<Resource> page = resourceService.getResourcePage(map);
			List<Resource> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][6];
			for(int j=0;j<list.size();j++){ 
				Resource resource = list.get(j); 
				data[j][0]=resource.getId();
				data[j][1]=resource.getResourceName();
				data[j][2]=resource.getResourceUrl();
				//remark存放是父资源的名字
				data[j][3]=resource.getRemark();
				data[j][4]=resource.getIcons();
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取资源分页列表信息失败","资源管理","/resourceController/getResources");
		}
	}
	/**
	 * 描述：根据角色id，获取资源信息树
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:16:27
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getCheckedResourceByRoleId/{id}")
	@ResponseBody
	public JSONArray getCheckedResourceByRoleId(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		List<Resource> allList = new ArrayList<Resource>();
		List<Resource> checkedList = new ArrayList<Resource>();
		JSONArray ja = new JSONArray();
		try {
			allList = resourceService.getResources(null);
			Role role = roleService.getRoleById(id);
			if(role!=null){
				checkedList = role.getResources();
			}
			for(Resource resource:allList){
				JSONObject jo = new JSONObject();
				jo.put("id",resource.getId());
				jo.put("pId",resource.getPid());
				jo.put("name",resource.getResourceName());
				//遍历集合的同时，删除集合的元素
				for(Iterator<Resource> iter = checkedList.iterator();iter.hasNext();){
					if(iter.next().getId().equals(resource.getId())){
						iter.remove();
						jo.put("checked",true);
						break;
					}
				}
				jo.put("open",true);
				ja.add(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();			
			throw new ControllerException(e,"角色授权时获取资源树信息失败","资源管理","/resourceController/getCheckedResourceByRoleId/"+id);
		}
		return ja;
	}
	@RequestMapping(value="/getResourceMenuByUserId")
	@ResponseBody
	public List<Resource> getResourceMenuByUserId(HttpServletRequest request,HttpServletResponse response){
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
}
