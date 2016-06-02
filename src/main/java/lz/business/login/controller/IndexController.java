package lz.business.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/indexController")
public class IndexController {
	/**
	 * 描述：登录成功跳转界面
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:29:29
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpServletRequest request){
		return "/main";
	}
	@RequestMapping("/homePage")
	public String homePage(HttpServletRequest request){
		return "/homePage";
	}
	/**
	 * 描述：退出
	 * 作者：李震
	 * 时间：2016年5月17日 下午6:29:47
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	@LogAspectAnnotation(logDesc="退出系统",logBusiness="登录")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("loginUser");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
