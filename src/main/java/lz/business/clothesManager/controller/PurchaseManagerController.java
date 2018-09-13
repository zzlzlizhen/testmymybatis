package lz.business.clothesManager.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;
import lz.business.clothesManager.service.ClothesPurchaseManagerService;
import lz.exception.ControllerException;
import lz.model.SystemParam;
import lz.model.YznzColthes;







import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/purchaseManagerController")
public class PurchaseManagerController {
	@Autowired
	private ClothesPurchaseManagerService cpmService;
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
		return "/yznz/purchaseManager/purchaseIndex";
	}
	
	
	@RequestMapping("/purchaseIndex")
	public String purchaseIndex(HttpServletRequest request){
		return "/yznz/purchaseManager/purchaseIndex";
	}
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		YznzColthes yznzCloth = cpmService.getParamById(id);
		request.setAttribute("yznzCloth",yznzCloth);
		return "/yznz/purchaseManager/viewPurchase";
	}
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		return "/yznz/purchaseManager/addPurchase";
	}
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id,HttpServletRequest request){
		YznzColthes yznzCloth = cpmService.getParamById(id);
		request.setAttribute("yznzCloth",yznzCloth);
		return "/yznz/purchaseManager/editPurchase";
	}

	@RequestMapping("/getPages")
	public void getPages(HttpServletRequest request,HttpServletResponse response){
		try {
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String searchId = request.getParameter("searchId");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			if(searchId!=null && !"".equals(searchId)){
				map.put("searchId","%"+searchId+"%");
			}
			PageInfo<YznzColthes> page = cpmService.getPurchaseByPage(map);
			List<YznzColthes> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][6];
			for(int j=0;j<list.size();j++){ 
				YznzColthes sp = list.get(j); 
				data[j][0]=sp.getId();
				data[j][1]=sp.getPicUrl();
				data[j][2]=sp.getName();
				data[j][3]=sp.getCategoryType();
				data[j][4]=sp.getPurchaseCount();
				data[j][5]=sp.getSaleCount();
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);	
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取进货管理分页列表信息失败","参数管理","/purchaseManagerController/getParams");

		}		
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	@LogAspectAnnotation(logDesc="添加进货管理参数信息",logBusiness="进货参数管理")
	public Map<String,Object> add(@RequestBody YznzColthes yznz){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			cpmService.insertYznzClother(yznz);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"添加进货管理失败","进货管理管理","/paramController/add");
		}
		return map;
	}
	@RequestMapping(value="/edit")
	@ResponseBody
	@LogAspectAnnotation(logDesc="进货管理信息修改",logBusiness="进货管理")
	public Map<String,Object> edit(@RequestBody YznzColthes yznz){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
//			YznzColthes yznzCloth = cpmService.getParamById(yznz.getId());
			
			cpmService.updateYznzClother(yznz);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"修改进货管理信息失败","进货管理","/purchaseManagerController/edit");
		}
		return map;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	@LogAspectAnnotation(logDesc="进货管理删除",logBusiness="进货管理")
	public Map<String,Object> del(@RequestBody YznzColthes yznz){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			cpmService.delYznzClother(yznz.getId());
			map.put("result", "success");
		}catch(Exception e){
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"删除进货管理信息失败","进货管理","/purchaseManagerController/del");
		}
		return map;
	}
	
	@RequestMapping(value="batchDel")
	@ResponseBody
	@LogAspectAnnotation(logDesc="批量删除进货管理参数",logBusiness="进货管理")
	public Map<String,Object> batchDel(@RequestBody YznzColthes yznz){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			int delSize = cpmService.batchDelYznzClothes(yznz.getId());
			map.put("delSize", delSize);
			map.put("result", "success");
		}catch(Exception e){
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"批量删除进货管理信息失败","进货管理","/purchaseManagerController/batchdel");
		}
		return map;
	}
	// uploadFile
	@RequestMapping(value="uploadFile")
	@ResponseBody
	public Map<String, Object> uploadFile(MultipartFile myfile)
			throws IllegalStateException, IOException {
		// 原始名称
		String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
		// 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
		String saveFilePath = "F://picture";
		// 上传图片
		if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
			// 新的图片名称
			String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			// 新图片
			File newFile = new File(saveFilePath + "\\" + newFileName);
			// 将内存中的数据写入磁盘
			myfile.transferTo(newFile);
			// 将新图片名称返回到前端
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "成功啦");
			map.put("url", newFileName);
			return map;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "图片不合法");
			return map;
		}
	}
}
