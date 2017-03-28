/**
 * 
 */
package lz.fx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author lizhen_pc
 *123
 */
public class Test01 {

	@Test
	public void Test001(){
		List<String> a = new ArrayList<String>();  
		a.add("CSDN_SEU_Cavin");  
		Class c = a.getClass();  
		try{  
		    Method method = c.getMethod("add",Object.class);  
		    method.invoke(a,100);  
		    System.out.println(a);  
		}catch(Exception e){  
		    e.printStackTrace();  
		}  
	}
}
