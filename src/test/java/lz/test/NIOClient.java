/**
 * 
 */
package lz.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * @author lizhen_pc
 *123
 */
public class NIOClient {

	private Selector selector;
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月18日 下午6:11:48
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		NIOClient client = new NIOClient();  
        client.initClient("localhost",8888);  
        client.listen();  
	}

	/**
	 * 获取一个通道，并为该通道初始化
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月18日 下午6:44:37
	 * @param ip
	 * @param port
	 * @throws IOException
	 */
	public void initClient(String ip,int port) throws IOException{
		//获取一个socket通道
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		this.selector = Selector.open();
		//客户端连接服务器，其中方法执行并没有连接，需要在listen方法中执行channel.finishConnect()，才能完成连接
		channel.connect(new InetSocketAddress(ip,port));
		//将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件
		channel.register(selector,SelectionKey.OP_CONNECT);
	}
	public void listen() throws IOException{
		while(true){
			selector.select();
			Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
			while(ite.hasNext()){
				SelectionKey key = ite.next();
				ite.remove();
				if(key.isConnectable()){
					SocketChannel channel = (SocketChannel)key.channel();
					//如果正在连接，则完成连接
					if(channel.isConnectionPending()){
						channel.finishConnect();
					}
					Date date = new Date();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("向服务器发送信息"+date.getTime()).getBytes()));
					channel.register(this.selector,SelectionKey.OP_READ);
				}else if(key.isReadable()){
					read(key);
				}
			}
		}
	}
	public void read(SelectionKey key) throws IOException{
		//服务器可读取消息，得到事件发生的socket通道
		SocketChannel channel = (SocketChannel)key.channel();
		//创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("客户端收到信息："+msg);
		/*ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		//将消息回送给客户端
		channel.write(outBuffer);*/
	}
}
