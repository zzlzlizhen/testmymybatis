/**
 * 
 */
package lz.outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhen_pc
 *123
 *  测试堆内存溢出
 */
public class HeapOOM {

	static class OOMObject{
		
	}
	public static void main(String [] args){
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
