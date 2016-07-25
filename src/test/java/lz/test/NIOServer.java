/**
 * 
 */
package lz.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * @author lizhen_pc
 *123
 */
public class NIOServer {

	//通道管理器
	private Selector selector;
	
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月18日 下午6:11:34
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		NIOServer server = new NIOServer();
		server.initServer(8888);
		server.listen();
	}
	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月18日 下午6:13:25
	 * @param port
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException{
		//获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		//设置通道为非阻塞
		serverChannel.configureBlocking(false);
		//将该通道对应的ServerSocket绑定到port端口
		serverChannel.socket().bind(new InetSocketAddress(port));
		//获得一个通道的管理器
		this.selector=Selector.open();
		//将通道管理器和该通道绑定，并为该通道注册SelectKey.OP_ACCEPT事件
		//当该事件到达时，selector.select()会返回，否则selector.select()会一直阻塞
		serverChannel.register(selector,SelectionKey.OP_ACCEPT);
	}
	/**
	 * 采用监听轮训的方式监听selector上是否有需要处理的事件，如果有则进行处理
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月18日 下午6:20:55
	 * @throws IOException
	 */
	public void listen() throws IOException{
		System.out.println("服务器启动成功");
		//轮训访问selector
		while(true){
			selector.select();
			//获取selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
			while(ite.hasNext()){
				SelectionKey key = ite.next();
				//删除已选中的key，以防止重复处理
				ite.remove();
				//客户端请求连接事件
				if(key.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel)key.channel();
					//获得和客户端连接的通道
					SocketChannel channel = server.accept();
					//设置成非阻塞
					channel.configureBlocking(false);
					//给客户端发送信息
					Date date = new Date();
					channel.write(ByteBuffer.wrap(new String("给客户端发送信息"+date.getTime()).getBytes()));
					//在和客户端连接成功之后，为了可以接受客户端的信息，需要给通道设置读的权限
					channel.register(this.selector,SelectionKey.OP_READ);
				}else if(key.isReadable()){
					read(key);
				}
			}
		}
	}
	/**
	 * 处理客户端发来的信息的事件
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月18日 下午6:32:34
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException{
		//服务器可读取消息，得到事件发生的socket通道
		SocketChannel channel = (SocketChannel)key.channel();
		//创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务器收到信息："+msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		//将消息回送给客户端
		channel.write(outBuffer);
	}
}
