package lz.business.boostrap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bootstrapController")
public class BootstrapController {
	
	@RequestMapping("/dashboard")
	public String dashboardMethod(HttpServletRequest request){
		return "/bootstrap/dashboard";
	}
	@RequestMapping("/ui")
	public String uiMethod(HttpServletRequest request){
		return "/bootstrap/ui";
	}
	@RequestMapping("/form")
	public String formMethod(HttpServletRequest request){
		return "/bootstrap/form";
	}
	@RequestMapping("/chart")
	public String chartMethod(HttpServletRequest request){
		return "/bootstrap/chart";
	}
	@RequestMapping("/typography")
	public String typographyMethod(HttpServletRequest request){
		return "/bootstrap/typography";
	}
	@RequestMapping("/gallery")
	public String galleryMethod(HttpServletRequest request){
		return "/bootstrap/gallery";
	}
	@RequestMapping("/table")
	public String tableMethod(HttpServletRequest request){
		return "/bootstrap/table";
	}
	@RequestMapping("/calendar")
	public String calendarMethod(HttpServletRequest request){
		return "/bootstrap/calendar";
	}
	@RequestMapping("/grid")
	public String grid(HttpServletRequest request){
		return "/bootstrap/grid";
	}
	@RequestMapping("/tour")
	public String tourMethod(HttpServletRequest request){
		return "/bootstrap/tour";
	}
	@RequestMapping("/icon")
	public String iconMethod(HttpServletRequest request){
		return "/bootstrap/icon";
	}
	@RequestMapping("/error")
	public String errorMethod(HttpServletRequest request){
		return "/bootstrap/error";
	}
	@RequestMapping("/loginPage")
	public String loginPageMethod(HttpServletRequest request){
		return "/bootstrap/login";
	}
}
