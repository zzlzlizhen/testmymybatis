package lz.business.systemManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.business.systemManage.service.ParamService;
import lz.model.SystemParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/visitorController")
public class VisitorController {
	@Resource
	private ParamService paramService;
	@RequestMapping("/visitorIndex")
	public String showUser(HttpServletRequest request){
		return "/systemManage/visitor/visitorIndex";
	}
	@RequestMapping("/getVisitors")
	@ResponseBody
	public void getVisitors(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String sEcho = request.getParameter("sEcho");
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",iDisplayStart);
		map.put("end",iDisplayLength);
		List<SystemParam> list = paramService.getParamByPage(map);
		int total = paramService.getParamCount(null);
		Object[][] data=new Object[list.size()][5];
		for(int j=0;j<list.size();j++){ 
			SystemParam sp = list.get(j); 
			data[j][0]=sp.getId();
			data[j][1]=sp.getParamKey();
			data[j][2]=sp.getParamValue();
			data[j][3]=sp.getCreateTime();
			data[j][4]=sp.getId();
		}
		JSONObject jo = new JSONObject();
		jo.put("iTotalDisplayRecords",total);
		jo.put("iTotalRecords",total);
		jo.put("sEcho",sEcho);
		jo.put("aaData",data);
		response.getWriter().print(jo.toJSONString());
	}
}
