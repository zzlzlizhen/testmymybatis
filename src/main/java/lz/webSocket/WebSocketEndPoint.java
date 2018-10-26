/**
 * 
 */
package lz.webSocket;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import lz.constant.ConstantInfo;
import lz.dao.MessageMapper;
import lz.dao.MessageUserMapper;
import lz.model.Message;
import lz.model.MessageUser;
import lz.model.User;
import lz.utils.IdGenerateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;

/**
 * @author lizhen_pc
 *123
 */
public class WebSocketEndPoint extends TextWebSocketHandler{
	@Autowired
	private MessageUserMapper messageUserMapper;
	@Autowired
	private MessageMapper messageMapper;
	private static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<WebSocketSession>();
    }
    /**
     * 接受前端发来的信息
     */
	@Override  
    protected void handleTextMessage(WebSocketSession session,  
            TextMessage message) throws Exception {  
		if(!session.isOpen()){
            return;
        }
        super.handleTextMessage(session, message);  
        TextMessage returnMessage = new TextMessage(message.getPayload());  
        session.sendMessage(returnMessage);  
    }  
	/**
	 * socket握手完成之后执行,如果收到的最后一条信息是未读，则推送提醒
	 */
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		users.add(session);
		User user = (User)session.getHandshakeAttributes().get(ConstantInfo.LOGIN_USER);
		if(session.isOpen()){
			map.put("userId",user.getId());
			map.put("userName",user.getName());
			Message message = messageMapper.selectLatestMessage(map);
			if(!ConstantInfo.MESSAGE_READ.equals(message.getMessageStatus())){
				session.sendMessage(new TextMessage(JSON.toJSONString(message)));
			}
		}
	}
	/**
     * 给所有在线用户发送消息,并记录未读
     *
     * @param message
     */
    public void sendMessageToUsers(Message message) {
    	String messageJson = JSON.toJSONString(message);
        for (WebSocketSession userSession : users) {
            try {
            	User user = (User)userSession.getHandshakeAttributes().get(ConstantInfo.LOGIN_USER);
                if (userSession.isOpen()) {
                	userSession.sendMessage(new TextMessage(messageJson));
                    MessageUser messageUser = new MessageUser();
        			messageUser.setId(IdGenerateUtils.getId());
        			messageUser.setUserId(user.getId());
        			messageUser.setMessageId(message.getId());
        			messageUser.setMessageType(ConstantInfo.MESSAGE_NOT_READ);
        			messageUserMapper.insertSelective(messageUser);
                }
            } catch (IOException e) {
            }
        }
    }
 
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(User user,Message message) {
    	String messageJson = JSON.toJSONString(message);
        for (WebSocketSession userSession : users) {
        	User u = (User)userSession.getHandshakeAttributes().get(ConstantInfo.LOGIN_USER);
            if (u!=null&&u.getName().equals(user.getName())) {
                try {
                    if (userSession.isOpen()) {
                    	//此推送
                    	userSession.sendMessage(new TextMessage(messageJson));
                        MessageUser messageUser = new MessageUser();
            			messageUser.setId(IdGenerateUtils.getId());
            			messageUser.setUserId(user.getId());
            			messageUser.setMessageId(message.getId());
            			messageUser.setMessageType(ConstantInfo.MESSAGE_NOT_READ);
            			messageUserMapper.insertSelective(messageUser);
                    }
                } catch (IOException e) {
                }
                break;
            }
        }
    }
	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1)
			throws Exception {
		if(session.isOpen()){
            session.close();
        }
        users.remove(session);
	}
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	users.remove(session);
    }
}
