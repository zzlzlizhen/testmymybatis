package lz.business.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("/logout/{name}")
	@LogAspectAnnotation(logDesc="退出系统",logBusiness="登录")
	public void logout(@PathVariable String name,HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("loginUser");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	@RequestMapping(value="/index/{map}")
	public void index(@PathVariable String map,HttpServletResponse resp) throws IOException{
		if(map!=null){
			System.out.println("接收到请求信息name："+map);
			
			try {
				System.out.println("开始休眠12秒");
				Thread.sleep(12000);
				System.out.println("休眠12秒完成");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		resp.getWriter().print("success");
	}
}
