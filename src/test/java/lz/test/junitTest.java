/**
 * 
 */
package lz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * @author lizhen_pc
 *123
 */
public class junitTest {

	@Test
	public void test01(){
		List<String> list = new ArrayList<String>();
		list.add("AbCd");
		list.add("Abce");
		list.add("AbcF");
		list.add("AbCG");
		list.add("AbCD");
		list.add("AbcE");
		list.add("Abcf");
		list.add("AbCg");
		System.out.println("排序前："+list);
		System.out.println("排序后："+junitTest.sortString(list));
	}

	/**
	 * 排序字符串，ABcD>ABCE
	 * 描述：
	 * 作者：李震
	 * 时间：2016年10月26日 下午4:08:33
	 * @param list
	 * @return
	 */
	public static List<String> sortString(List<String> list){
		Collections.sort(list,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				for(int i=0;i < o1.length(); i++){
					if(i<o2.length()){
						char firstChar = 0;
						char secondChar = 0;
						if(o1.charAt(i)>='A' && o1.charAt(i)<='Z'){
							firstChar = (char) (32+o1.charAt(i));
						}
						if(o2.charAt(i)>='A' && o2.charAt(i)<='Z'){
							secondChar = (char) (32+o2.charAt(i));
						}
						if((firstChar ==0 && secondChar==0)||(firstChar !=0 && secondChar!=0)) {
							if(o1.charAt(i)>o2.charAt(i)){
								return 1;
							}else if(o1.charAt(i)<o2.charAt(i)){
								return -1;
							}else{
								continue;
							}
						}else if(firstChar !=0 && secondChar==0){
							if(firstChar>o2.charAt(i)){
								return 1;
							}else if(firstChar<=o2.charAt(i)){
								return -1;
							}
						}else{
							if(o1.charAt(i)>=secondChar){
								return 1;
							}else if(o1.charAt(i)<secondChar){
								return -1;
							}
						}
					}else{
						return -1;
					}
				}
				return -1;
			}
		});
		return list;
	}
}
