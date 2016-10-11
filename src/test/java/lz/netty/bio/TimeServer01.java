/**
 * 
 */
package lz.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lizhen_pc
 *123
 */
public class TimeServer01 {

	/**
	 * 描述：BIO服务端（伪服务）
	 * 作者：李震
	 * 时间：2016年9月1日 上午9:46:46
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("服务开启，端口："+port);
			Socket socket = null;
			//创建io线程池
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
			while(true){
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(server !=null){
				System.out.println("服务关闭");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server = null;
			}
		}
	}

}
