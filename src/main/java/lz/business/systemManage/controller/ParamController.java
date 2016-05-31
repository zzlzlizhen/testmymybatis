package lz.business.systemManage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.business.systemManage.service.ParamService;
import lz.model.SystemParam;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/paramController")
public class ParamController {
	@Resource
	private ParamService paramService;
	@RequestMapping("/paramIndex")
	public String paramIndex(HttpServletRequest request){
		return "/systemManage/param/paramIndex";
	}
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		return "/systemManage/param/addParam";
	}
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		SystemParam systemParam = paramService.getParamById(id);
		request.setAttribute("systemParam",systemParam);
		return "/systemManage/param/viewParam";
	}
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id,HttpServletRequest request){
		SystemParam systemParam = paramService.getParamById(id);
		request.setAttribute("systemParam",systemParam);
		return "/systemManage/param/editParam";
	}
	@RequestMapping("/testIn/{key}")
	public void editPage(@PathVariable String key,HttpServletRequest request,HttpServletResponse response){
		SystemParam systemParam = paramService.getParamByParamKey(key);
		String str = JSON.toJSONStringWithDateFormat(systemParam,"yyyy-MM-dd HH:mm:ss");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(paramService.getParamCountByParamKey(sp.getParamKey())>0){
				map.put("result","paramKeyIsExist");
			}else{
				sp.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				paramService.insertParam(sp);
				map.put("result","success");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			SystemParam systemParam = paramService.getParamById(sp.getId());
			if(!sp.getParamKey().equals(systemParam.getParamKey())){
				if(paramService.getParamCountByParamKey(sp.getParamKey())>0){
					map.put("result","paramKeyIsExist");
				}else{
					paramService.updateParam(sp);
					map.put("result","success");
				}
			}else{
				paramService.updateParam(sp);
				map.put("result","success");
			}
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String,Object> del(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			paramService.delSystemParam(sp.getId());
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String,Object> batchDel(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int delSize = paramService.batchDelSystemParam(sp.getParamValue());
			map.put("delSize",delSize);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping("/getParams")
	public void getParams(HttpServletRequest request,HttpServletResponse response){
		String sEcho = request.getParameter("sEcho");
		Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		String searchId = request.getParameter("searchId");
		Map<String,Object> map = new HashMap<String,Object>();
		int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
		map.put("pageNum",pageNum);
		map.put("pageSize",iDisplayLength);
		if(searchId!=null && !"".equals(searchId)){
			map.put("searchId","%"+searchId+"%");
		}
		/*List<SystemParam> list = paramService.getParamByPage(map);
		int total = paramService.getParamCount(map);*/
		PageInfo<SystemParam> page = paramService.getParamByPage(map);
		List<SystemParam> list = page.getList();
		long total = page.getTotal();
		Object[][] data=new Object[list.size()][5];
		for(int j=0;j<list.size();j++){ 
			SystemParam sp = list.get(j); 
			data[j][0]=sp.getId();
			data[j][1]=sp.getParamKey();
			data[j][2]=sp.getParamValue();
			data[j][3]=sp.getCreateTime();
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
