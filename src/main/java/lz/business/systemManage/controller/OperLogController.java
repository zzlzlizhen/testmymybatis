package lz.business.systemManage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;
import lz.business.systemManage.service.OperLogService;
import lz.exception.ControllerException;
import lz.model.OperLog;
import lz.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/operLogController")
public class OperLogController {
	@Resource
	private OperLogService operLogService;
	@RequestMapping("/operLogIndex")
	public String operLogIndex(HttpServletRequest request){
		return "/systemManage/operLog/operLogIndex";
	}
	@RequestMapping("/personOperLogIndex")
	public String personOperLogIndex(HttpServletRequest request){
		return "/personCore/personOperLog/personOperLogIndex";
	}
	@RequestMapping(value="/batchDel")
	@ResponseBody
	@LogAspectAnnotation(logDesc="批量删除系统操作日志信息",logBusiness="操作日志")
	public Map<String,Object> batchDel(@RequestBody OperLog ol){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int delSize = operLogService.batchDelOperLog(ol.getLogDesc());
			map.put("delSize",delSize);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"批量删除系统操作日志信息失败","操作日志","/operLogController/batchDel");
		}
		return map;
	}
	@RequestMapping("/getOperLogs")
	public void getParams(HttpServletRequest request,HttpServletResponse response){
		try {
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String name = request.getParameter("name");
			String logDesc = request.getParameter("logDesc");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			if(name!=null && !"".equals(name)){
				map.put("name","%"+name+"%");
			}if(logDesc!=null && !"".equals(logDesc)){
				map.put("logDesc","%"+logDesc+"%");
			}if(startTime!=null && !"".equals(startTime)){
				map.put("startTime",startTime+" 00:00:00");
			}if(endTime!=null && !"".equals(endTime)){
				map.put("endTime",endTime+" 23:59:59");
			}
			PageInfo<OperLog> page = operLogService.getOperLogByPage(map);
			List<OperLog> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][7];
			for(int j=0;j<list.size();j++){ 
				OperLog ol = list.get(j); 
				data[j][0]=ol.getId();
				data[j][1]=ol.getName();
				data[j][2]=ol.getLogBusiness();
				data[j][3]=ol.getLogDesc();
				data[j][4]=ol.getOperIp();
				data[j][5]=ol.getCreateTime();
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取操作日志分页列表信息失败","操作日志","/operLogController/getOperLogs");
		}
	}
	@RequestMapping("/getPersonOperLogs")
	public void getPersonOperLogs(HttpServletRequest request,HttpServletResponse response){
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String logDesc = request.getParameter("logDesc");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			map.put("loginUserName",user.getName());
			if(logDesc!=null && !"".equals(logDesc)){
				map.put("logDesc","%"+logDesc+"%");
			}if(startTime!=null && !"".equals(startTime)){
				map.put("startTime",startTime+" 00:00:00");
			}if(endTime!=null && !"".equals(endTime)){
				map.put("endTime",endTime+" 23:59:59");
			}
			PageInfo<OperLog> page = operLogService.getOperLogByPage(map);
			List<OperLog> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][7];
			for(int j=0;j<list.size();j++){ 
				OperLog ol = list.get(j); 
				data[j][0]=ol.getId();
				data[j][1]=ol.getName();
				data[j][2]=ol.getLogBusiness();
				data[j][3]=ol.getLogDesc();
				data[j][4]=ol.getOperIp();
				data[j][5]=ol.getCreateTime();
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取操作日志分页列表信息失败","操作日志","/operLogController/getOperLogs");
		}
	}
}
