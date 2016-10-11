/**
 * 
 */
package lz.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author lizhen_pc
 *123
 */
public class TimeClient {

	/**
	 * 描述：BIO客户端，
	 * 作者：李震
	 * 时间：2016年9月1日 上午10:07:04
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
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1",port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			System.out.println("发送到服务器成功");
			String resp = in.readLine();
			System.out.println("返回："+resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
				out = null;
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in=null;
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket=null;
			}
		}
	}

}
