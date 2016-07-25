/**
 * 
 */
package lz.webSocket;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import lz.constant.ConstantInfo;
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
	private Timer timer;
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
            timer.cancel();
            return;
        }
        super.handleTextMessage(session, message);  
        TextMessage returnMessage = new TextMessage(message.getPayload());  
        session.sendMessage(returnMessage);  
    }  
	/**
	 * socket握手完成之后执行
	 */
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		//TextMessage textMessage = new TextMessage(new Date().getTime()+"");
        //handleMessage(session,textMessage);
        /*timer = new Timer(true);
        long delay = 0;
        OrderTimeTask orderTimeTask = new OrderTimeTask(session);
        timer.schedule(orderTimeTask,delay, 3000);// 设定指定的时间time,此处为1分钟
*/    }
	/*class OrderTimeTask extends TimerTask{
        private WebSocketSession session;
        public OrderTimeTask(WebSocketSession session){
            this.session = session;
        }
        @Override
        public void run() {
            try {
            	User user = (User)session.getHandshakeAttributes().get("currentUser");
                TextMessage textMessage = new TextMessage(new Date().getTime()+""+user.getName());
                handleMessage(session,textMessage);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }*/
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
    	System.out.println("Connection Closed！");
    }
}
