package lz.quartz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lz.business.authManage.service.ResourceService;
import lz.model.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestJob01{

	@javax.annotation.Resource
	private ResourceService resourceService;
	
	public void execute01(){
	}
	public void execute02(){
		Map<String,Object> map = new HashMap<String,Object>();
		Date today = new Date();
		Date yesterDay = DateUtils.addDays(today,-1);
		map.put("startCreateTime",DateFormatUtils.format(yesterDay,"yyyy-MM-dd")+" 00:00:00");
		map.put("endCreateTime",DateFormatUtils.format(yesterDay,"yyyy-MM-dd")+" 23:59:59");
		List<Resource> yesterCreateResource = resourceService.getResources(map);
		writeExcel(yesterCreateResource);
	}
	
	public void writeExcel(List<Resource> listResource){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("学生表一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式 
        HSSFCell cell = row.createCell(0) ;
        cell.setCellValue("权限名字");  
        cell.setCellStyle(style);  
        cell = row.createCell(1);  
        cell.setCellValue("权限地址");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("父权限");  
        cell.setCellStyle(style);  
        cell = row.createCell(3);  
        cell.setCellValue("图标");
        cell.setCellStyle(style);  
        cell = row.createCell(4);  
        cell.setCellValue("创建时间");
        cell.setCellStyle(style); 
        cell = row.createCell(5);  
        cell.setCellValue("备注");
        cell.setCellStyle(style); 
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < listResource.size(); i++)  
        {  
            row = sheet.createRow(i + 1);  
            Resource resource = listResource.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell(0).setCellValue(resource.getResourceName());
            row.createCell(1).setCellValue(resource.getResourceUrl());
            row.createCell(2).setCellValue(resource.getPid());
            row.createCell(3).setCellValue(resource.getIcons());
            row.createCell(4).setCellValue(resource.getCreateTime());
            row.createCell(5).setCellValue(resource.getRemark());
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
        	Date today = new Date();
    		Date yesterDay = DateUtils.addDays(today,-1);
        	String fileName = DateFormatUtils.format(yesterDay,"yyyy-MM-dd");
        	String filePath = "F:/"+fileName+".xls";
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  finally{
        	try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
