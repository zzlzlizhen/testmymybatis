package lz.business.clothesManager.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;
import lz.business.systemManage.service.ParamService;
import lz.constant.ConstantInfo;
import lz.exception.ControllerException;
import lz.model.SystemParam;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/clothesManagerController")
public class ClothesManagerController {
	@Autowired
	private ParamService paramService;
	@RequestMapping("/clothesIndex")
	public String parmaIndex(HttpServletRequest request){
		return "/yznz/clothesManager/clothesIndex";
	}
	//此路径与addFun一致
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		return "/yznz/clothesManager/addClothes";
	}
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		SystemParam systemParam = paramService.getParamById(id);
		request.setAttribute("systemParam",systemParam);
		return "/yznz/clothesManager/viewClothes";
	}
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id, HttpServletRequest request){
		SystemParam systemParam = paramService.getParamById(id);
		request.setAttribute("systemParam", systemParam);
		return "/yznz/clothesManager/editClothes";
	}
	@RequestMapping(value="/add")
	@ResponseBody
	@LogAspectAnnotation(logDesc="添加衣服属性管理系统参数信息",logBusiness="衣服属性管理")
	public Map<String,Object> add(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			sp.setParamKey(sp.getParamKey()+"_"+ System.currentTimeMillis());
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
			throw new ControllerException(e,"添加衣服属性系统参数信息失败","衣服属性管理","/clothesManagerController/add");
		}
		return map;
	}
	@RequestMapping(value="/edit")
	@ResponseBody
	@LogAspectAnnotation(logDesc="衣服属性系统参数信息",logBusiness="衣服属性管理")
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
			throw new ControllerException(e,"修改衣服属性系统参数信息失败","衣服属性管理","/clothesManagerController/edit");
		}
		return map;
	}
	@RequestMapping(value="/del")
	@ResponseBody
	@LogAspectAnnotation(logDesc="衣服属性系统参数删除",logBusiness="衣服属性管理")
	public Map<String,Object> del(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			paramService.delSystemParam(sp.getId());
			map.put("result", "success");
		}catch(Exception e){
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"删除衣服属性关系系统参数信息失败","衣服属性管理","/clothesManagerController/del");
		}
		return map;
	}
	@RequestMapping(value="batchDel")
	@ResponseBody
	@LogAspectAnnotation(logDesc="批量删除衣服属性管理系统参数",logBusiness="衣服属性管理")
	public Map<String,Object> batchDel(@RequestBody SystemParam sp){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			int delSize = paramService.batchDelSystemParam(sp.getParamValue());
			map.put("delSize", delSize);
			map.put("result", "success");
		}catch(Exception e){
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"批量删除衣服属性关系系统参数信息失败","衣服属性管理","/clothesManagerController/batchdel");
		}
		return map;
	}
	@RequestMapping("/getParams")
	public void getParms(HttpServletRequest request,HttpServletResponse response){
		try {
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String searchId = request.getParameter("searchId");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			map.put("yznzClothes", "%" + ConstantInfo.YZNZ_CLOUTHES + "%");
			if(searchId!=null && !"".equals(searchId)){
				map.put("searchId","%"+searchId+"%");
			}
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
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取衣服管理系统参数分页列表信息失败","参数管理","/clothesManagerController/getParams");

		}		
	}
}
