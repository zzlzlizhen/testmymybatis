package lz.webSocket;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lz.business.authManage.service.UserService;
 
@ServerEndpoint("/mywebsocket")
public class MyWebSocket {
	
	@Resource
	private UserService userService;
	
    private static int onlineCount = 0;
     
    //private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    private static ConcurrentMap<String,MyWebSocket> simpleWebSocket = new ConcurrentHashMap<String,MyWebSocket>();
    private Session session;
     
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        simpleWebSocket.put(session.getId(),this);
        addOnlineCount();           
        System.out.println(session.getId()+"--" + getOnlineCount());
    }
    @OnClose
    public void onClose(){
        //webSocketSet.remove(this);  
        simpleWebSocket.remove(this);
        subOnlineCount();           
        System.out.println(this.session.getId()+"--" + getOnlineCount());
    }
    /**
     * 
     * @param message 
     * @param session 
     *//*
    @OnMessage
    public void onMessage(String message, Session session) {
        for(MyWebSocket item: webSocketSet){             
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	try {
			simpleWebSocket.get(session.getId()).sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
     
    /**
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}