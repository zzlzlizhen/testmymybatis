package lz.business.systemManage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.business.systemManage.service.ParamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/visitorController")
public class VisitorController {
	@Autowired
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
