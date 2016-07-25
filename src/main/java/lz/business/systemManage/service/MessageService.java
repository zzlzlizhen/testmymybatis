package lz.business.systemManage.service;

import java.util.Map;

import lz.model.Message;
import lz.model.User;

import com.github.pagehelper.PageInfo;

public interface MessageService {

	
	
	int insertMessage(Message systemMessage);
	
	int updateMessage(Message systemMessage);
	
	Message getMessageById(String id);
	
	int delMessage(String id);
	
	int batchDelMessage(String batchDelId);
	
	PageInfo<Message> getMessageByPage(Map<String,Object> map);
	
	PageInfo<Message> getPersonMessageByPage(Map<String,Object> map);
	
	void readMessage(Message message,User user);
}
