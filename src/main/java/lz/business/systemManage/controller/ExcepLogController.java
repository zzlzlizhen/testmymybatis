package lz.business.systemManage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;
import lz.business.systemManage.service.ExcepLogService;
import lz.exception.ControllerException;
import lz.model.ExcepLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/excepLogController")
public class ExcepLogController {
	@Autowired
	private ExcepLogService excepLogService;
	@RequestMapping("/excepLogIndex")
	public String excepLogIndex(HttpServletRequest request){
		return "/systemManage/excepLog/excepLogIndex";
	}
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		ExcepLog excepLog = excepLogService.getExcepLogById(id);
		request.setAttribute("excepLog",excepLog);
		return "/systemManage/excepLog/viewExcepLog";
	}
	@RequestMapping(value="/batchDel")
	@ResponseBody
	@LogAspectAnnotation(logDesc="批量删除系统异常日志信息",logBusiness="异常日志")
	public Map<String,Object> batchDel(@RequestBody ExcepLog el){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int delSize = excepLogService.batchDelExcepLog(el.getExceptionDesc());
			map.put("delSize",delSize);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"批量删除系统异常日志信息失败","异常日志","/excepLogController/batchDel");
		}
		return map;
	}
	@RequestMapping("/getExcepLogs")
	public void getParams(HttpServletRequest request,HttpServletResponse response){
		try {
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String name = request.getParameter("name");
			String ExceptionDesc = request.getParameter("ExceptionDesc");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			if(name!=null && !"".equals(name)){
				map.put("name","%"+name+"%");
			}if(ExceptionDesc!=null && !"".equals(ExceptionDesc)){
				map.put("ExceptionDesc","%"+ExceptionDesc+"%");
			}if(startTime!=null && !"".equals(startTime)){
				map.put("startTime",startTime+" 00:00:00");
			}if(endTime!=null && !"".equals(endTime)){
				map.put("endTime",endTime+" 23:59:59");
			}
			PageInfo<ExcepLog> page = excepLogService.getExcepLogByPage(map);
			List<ExcepLog> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][7];
			for(int j=0;j<list.size();j++){ 
				ExcepLog el = list.get(j); 
				data[j][0]=el.getId();
				data[j][1]=el.getName();
				data[j][2]=el.getExceptionBusiness();
				data[j][3]=el.getExceptionDesc();
				data[j][4]=el.getExceptionTarget();
				data[j][5]=el.getCreateTime();
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取异常日志分页列表信息失败","异常日志","/excepLogController/getExcepLogs");
		}
	}
}
