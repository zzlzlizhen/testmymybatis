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
	}
}
