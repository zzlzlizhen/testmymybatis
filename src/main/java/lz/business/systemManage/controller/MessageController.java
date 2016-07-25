package lz.business.systemManage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lz.annotation.LogAspectAnnotation;
import lz.business.systemManage.service.MessageService;
import lz.constant.ConstantInfo;
import lz.exception.ControllerException;
import lz.model.Message;
import lz.model.User;
import lz.webSocket.WebSocketEndPoint;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/messageController")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private WebSocketEndPoint websocket;
	@RequestMapping("/messageIndex")
	public String messageIndex(HttpServletRequest request){
		return "/systemManage/message/messageIndex";
	}
	@RequestMapping("/personMessageIndex")
	public String personMessageIndex(HttpServletRequest request){
		return "/personCore/personMessage/personMessageIndex";
	}
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request){
		return "/systemManage/message/addMessage";
	}
	@RequestMapping("/viewPage/{id}")
	public String viewPage(@PathVariable String id,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(ConstantInfo.LOGIN_USER);
		Message message = messageService.getMessageById(id);
		messageService.readMessage(message,user);
		request.setAttribute("message",message);
		return "/systemManage/message/viewMessage";
	}
	@RequestMapping("/personViewPage/{id}")
	public String personViewPage(@PathVariable String id,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(ConstantInfo.LOGIN_USER);
		Message message = messageService.getMessageById(id);
		messageService.readMessage(message,user);
		request.setAttribute("message",message);
		return "/personCore/personMessage/personViewMessage";
	}
	@RequestMapping("/editPage/{id}")
	public String editPage(@PathVariable String id,HttpServletRequest request){
		Message message = messageService.getMessageById(id);
		request.setAttribute("message",message);
		return "/systemManage/message/editMessage";
	}
	@RequestMapping(value="/add")
	@ResponseBody
	@LogAspectAnnotation(logDesc="添加系统通知消息",logBusiness="消息管理")
	public Map<String,Object> add(@RequestBody Message message,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = (User)request.getSession().getAttribute(ConstantInfo.LOGIN_USER);
		try {
			message.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			message.setCreatedUser(user.getName());
			message.setMessageType(ConstantInfo.SYSTEM_MESSAGE);
			message.setMessageStatus(ConstantInfo.MESSAGE_SAVE);
			messageService.insertMessage(message);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"添加系统通知消息失败","消息管理","/messageController/add");
		}
		return map;
	}
	@RequestMapping(value="/edit")
	@ResponseBody
	@LogAspectAnnotation(logDesc="修改系统通知消息",logBusiness="消息管理")
	public Map<String,Object> edit(@RequestBody Message message){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			messageService.updateMessage(message);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"修改系统通知消息失败","消息管理","/messageController/edit");
		}
		return map;
	}
	@RequestMapping(value="/del")
	@ResponseBody
	@LogAspectAnnotation(logDesc="删除系统通知消息",logBusiness="消息管理")
	public Map<String,Object> del(@RequestBody Message message){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			messageService.delMessage(message.getId());
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"删除系统通知消息失败","消息管理","/messageController/del");
		}
		return map;
	}
	@RequestMapping(value="/publish")
	@ResponseBody
	@LogAspectAnnotation(logDesc="发布系统通知消息",logBusiness="消息管理")
	public Map<String,Object> publish(@RequestBody Message message){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Message m = messageService.getMessageById(message.getId());
			m.setMessageStatus(ConstantInfo.MESSAGE_PUBLISH);
			m.setPublishTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			messageService.updateMessage(m);
			websocket.sendMessageToUsers(m);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"发布系统通知消息失败","消息管理","/messageController/del");
		}
		return map;
	}
	@RequestMapping(value="/destory")
	@ResponseBody
	@LogAspectAnnotation(logDesc="销毁系统通知消息",logBusiness="消息管理")
	public Map<String,Object> destory(@RequestBody Message message){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Message m = messageService.getMessageById(message.getId());
			m.setMessageStatus(ConstantInfo.MESSAGE_DESTORY);
			m.setDestoryTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			messageService.updateMessage(m);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"销毁系统通知消息失败","消息管理","/messageController/del");
		}
		return map;
	}
	@RequestMapping(value="/batchDel")
	@ResponseBody
	@LogAspectAnnotation(logDesc="批量删除系统通知消息",logBusiness="消息管理")
	public Map<String,Object> batchDel(@RequestBody Message message){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//用messageContent属性接受批量删除的信息id
			int delSize = messageService.batchDelMessage(message.getMessageContent());
			map.put("delSize",delSize);
			map.put("result","success");
		} catch (Exception e) {
			map.put("result","error");
			e.printStackTrace();
			throw new ControllerException(e,"批量删除系统通知消息失败","消息管理","/messageController/batchDel");

		}
		return map;
	}
	@RequestMapping("/getMessages")
	public void getMessages(HttpServletRequest request,HttpServletResponse response){
		try {
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String messageHead = request.getParameter("messageHead");
			String messageType = request.getParameter("messageType");
			String messageStatus = request.getParameter("messageStatus");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			if(messageHead!=null && !"".equals(messageHead)){
				map.put("messageHead","%"+messageHead+"%");
			}
			if(!"-1".equals(messageType)){
				map.put("messageType",messageType);
			}
			if(!"-1".equals(messageStatus)){
				map.put("messageStatus",messageStatus);
			}
			PageInfo<Message> page = messageService.getMessageByPage(map);
			List<Message> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][9];
			for(int j=0;j<list.size();j++){ 
				Message message = list.get(j); 
				data[j][0]=message.getId();
				data[j][1]=message.getMessageHead();
				data[j][2]=message.getMessageContent().length()>30?message.getMessageContent().substring(0,30)+"...":message.getMessageContent();
				data[j][3]=message.getMessageType();
				data[j][4]=message.getMessageStatus();
				data[j][5]=message.getCreateTime();
				data[j][6]=message.getPublishTime();
				data[j][7]=message.getDestoryTime();
				
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);	
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取系统通知消息分页列表失败","消息管理","/messageController/getMessages");

		}
	}
	@RequestMapping("/getPersonMessages")
	public void getPersonMessages(HttpServletRequest request,HttpServletResponse response){
		try {
			User user = (User)request.getSession().getAttribute(ConstantInfo.LOGIN_USER);
			String sEcho = request.getParameter("sEcho");
			Long iDisplayStart = Long.parseLong(request.getParameter("iDisplayStart"));
			int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			String messageHead = request.getParameter("messageHead");
			String messageType = request.getParameter("messageType");
			String messageStatus = request.getParameter("messageStatus");
			Map<String,Object> map = new HashMap<String,Object>();
			int pageNum = ((Long)(iDisplayStart/iDisplayLength)).intValue()+1;
			map.put("pageNum",pageNum);
			map.put("pageSize",iDisplayLength);
			map.put("userId",user.getId());
			map.put("userName",user.getName());
			if(messageHead!=null && !"".equals(messageHead)){
				map.put("messageHead","%"+messageHead+"%");
			}
			if(!"-1".equals(messageType)){
				map.put("messageType",messageType);
			}
			if(!"-1".equals(messageStatus)){
				map.put("messageStatus",messageStatus);
			}
			PageInfo<Message> page = messageService.getPersonMessageByPage(map);
			List<Message> list = page.getList();
			long total = page.getTotal();
			Object[][] data=new Object[list.size()][7];
			for(int j=0;j<list.size();j++){ 
				Message message = list.get(j); 
				data[j][0]=message.getId();
				data[j][1]=message.getMessageHead().length()>30?message.getMessageHead().substring(0,30)+"...":message.getMessageHead();
				data[j][2]=message.getMessageContent().length()>100?message.getMessageContent().substring(0,100)+"...":message.getMessageContent();
				data[j][3]=message.getMessageType();
				//表示是否已读
				data[j][4]=message.getMessageStatus();
				data[j][5]=message.getPublishTime();
				
			}
			JSONObject jo = new JSONObject();
			jo.put("iTotalDisplayRecords",total);
			jo.put("iTotalRecords",total);
			jo.put("sEcho",sEcho);
			jo.put("aaData",data);	
			response.getWriter().print(jo.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e,"获取个人消息分页列表信息失败","个人消息","/messageController/getMessages");

		}
	}
}
