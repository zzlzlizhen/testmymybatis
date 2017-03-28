/**
 * 
 */
package lz.business.other.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lizhen_pc
 *123
 */
@Controller
@RequestMapping("/otherController")
public class OtherController {

	@RequestMapping(value="/testDownload")
	public void testDownload(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		OutputStream out = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			HSSFSheet sheet = wb.createSheet("测试下载");
			HSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("测试1");
			row.createCell(1).setCellValue("测试2");
			wb.write(os);
			byte[] ba = os.toByteArray();
			resp.reset();
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/msexcel;charset=UTF-8");
			resp.setHeader("Content-Disposition","attachment;filename=\""+URLEncoder.encode("测试.xls","UTF-8"));
			out = resp.getOutputStream();
			out.write(ba);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out!=null) out.close();
			wb.close();
		}
	}
	
	@RequestMapping(value="/index")
	public void index(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String name = req.getParameter("name");
		if(name!=null){
			System.out.println("接收到请求信息name："+name);
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
	
	/**
	 * 修改密码
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月15日 下午4:55:46
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/testClient")
	@ResponseBody
	public Map<String,Object> testClient(@RequestBody Map<String,Object> map,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		if(map.get("name")!=null){
			System.out.println("接收到请求信息name："+map.get("name"));
			try {
				System.out.println("开始休眠12秒");
				Thread.sleep(12000);
				System.out.println("休眠12秒完成");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
