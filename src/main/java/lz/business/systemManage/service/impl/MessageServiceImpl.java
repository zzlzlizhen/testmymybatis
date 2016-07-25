package lz.business.systemManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lz.business.systemManage.service.MessageService;
import lz.constant.ConstantInfo;
import lz.dao.MessageMapper;
import lz.dao.MessageUserMapper;
import lz.model.Message;
import lz.model.MessageUser;
import lz.model.MessageUserExample;
import lz.model.User;
import lz.utils.IdGenerateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageUserMapper messageUserMapper;
	
	@Override
	public PageInfo<Message> getMessageByPage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		List<Message> list = messageMapper.selectMessageByPage(map);
		PageInfo<Message> page = new PageInfo<Message>(list);
		return page;
	}
	@Override
	public PageInfo<Message> getPersonMessageByPage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		List<Message> list = messageMapper.selectPersonMessageByPage(map);
		PageInfo<Message> page = new PageInfo<Message>(list);
		return page;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int insertMessage(Message systemMessage) {
		systemMessage.setId(IdGenerateUtils.getId());
		return messageMapper.insertSelective(systemMessage);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int updateMessage(Message systemMessage) {
		return messageMapper.updateByPrimaryKeySelective(systemMessage);
	}
	
	@Override
	public Message getMessageById(String id) {
		Message systemParem = messageMapper.selectByPrimaryKey(id);
		return systemParem;
	}

	

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int delMessage(String id) {
		MessageUserExample mue = new MessageUserExample();
		mue.createCriteria().andMessageIdEqualTo(id);
		messageUserMapper.deleteByExample(mue);
		return messageMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int batchDelMessage(String batchDelId) {
		List<String> batchDelIds = new ArrayList<String>();
		for(String id:batchDelId.split(",")){
			batchDelIds.add(id);
		}
		return messageMapper.batchDelMessage(batchDelIds);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void readMessage(Message message,User user) {
		MessageUserExample mue = new MessageUserExample();
		mue.createCriteria().andMessageIdEqualTo(message.getId()).andUserIdEqualTo(user.getId());
		List<MessageUser> list = messageUserMapper.selectByExample(mue);
		//如果没有记录，直接保存该信息已读，如果有记录信息状态是未读则更新为已读
		if(list!=null&&list.size()>0){
			MessageUser messageUser = list.get(0);
			if(messageUser.getMessageType().equals(ConstantInfo.MESSAGE_NOT_READ)){
				messageUser.setMessageType(ConstantInfo.MESSAGE_READ);
				messageUserMapper.updateByPrimaryKeySelective(messageUser);
			}
		}else{
			MessageUser messageUser = new MessageUser();
			messageUser.setId(IdGenerateUtils.getId());
			messageUser.setUserId(user.getId());
			messageUser.setMessageId(message.getId());
			messageUser.setMessageType(ConstantInfo.MESSAGE_READ);
			messageUserMapper.insertSelective(messageUser);
		}
	}
	
}
