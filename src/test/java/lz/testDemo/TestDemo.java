/**
 * 
 */
package lz.testDemo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

class A {    
    
    @Override    
    public boolean equals(Object obj) {    
        System.out.println("判断equals");    
        return false;    
    }    
    
    @Override    
    public int hashCode() {    
        System.out.println("判断hashcode");    
        return 1;    
    }    
}    

/**
 * @author lizhen_pc
 *123
 */
public class TestDemo {

	@Test
	public void test() {
		String a = "abc";
		String b = "abc";
		String c = new String("abc");
		String d = "ab"+"c";
		String e = new String("abc");
		System.out.println(a==b);//true
		System.out.println(a==c);//false
		System.out.println(a==d);//true
		System.out.println(a==e);//false
		
		System.out.println(b==c);//false
		System.out.println(b==d);//true
		System.out.println(b==e);//false
		
		System.out.println(c==d);//false
		System.out.println(c==e);//false
		
		System.out.println(d==e);//false
	}

	
	
	@Test
	public void test01(){
		Map<A,Object> map = new HashMap<A,Object>();
		map.put(new A(),new Object());
		map.put(new A(),new Object());
		map.put(new A(),new Object());
		System.out.println(map.size());
	}
	
	
}

