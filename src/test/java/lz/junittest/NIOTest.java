/**
 * 
 */
package lz.junittest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author lizhen_pc
 *123
 */
public class NIOTest {

	@Test
	public void test() throws Exception{
		String infile = "F:"+File.separator+"2016-05-25.xls";
		String outfile = "F:"+File.separator+"2016-05-25-beifen.xls";
		//获取源文件和目标文件的输入输出流
		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);
		//获取输入输出通道
		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();
		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocateDirect(2014);
		while(true){
			//clear方法重设缓冲区，使它可以接受读入的数据
			buffer.clear();
			//从输入通道中将数据读到缓冲区
			int r = fcin.read(buffer);
			//read方法返回读取的字节数，可能是零，如果该通道已经到达流的尾末，则返回-1
			if(r==-1) break;
			//flip方法让缓冲区可以将新读入的数据写入到另一个通道
			buffer.flip();
			//从输出通道中将数据写入到缓冲区
			fcout.write(buffer);
		}
		
		fin.close();
		fout.close();
		fcin.close();
		fcout.close();
	
	}

}
