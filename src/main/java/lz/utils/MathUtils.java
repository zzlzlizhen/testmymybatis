package lz.utils;

import java.util.Random;

public class MathUtils {

	/**
	 * 生成随机数的工具
	 * @param lenght
	 * @return
	 */
	public static String getCharAndNum(int lenght){
		String val="";
		Random random = new Random();
		for(int i=0;i<lenght;i++){
			String charOrNum = random.nextInt(2)%2==0?"char":"num";
			if("char".equalsIgnoreCase(charOrNum)){
				int choice = random.nextInt(2)%2==0?65:97;
				val += (char)(choice+random.nextInt(26));
			}else if("num".equalsIgnoreCase(charOrNum)){
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	/**
	 * 生成随机数的工具
	 * @param lenght
	 * @return
	 */
	public static String getNum(int lenght){
		String val="";
		Random random = new Random();
		for(int i=0;i<lenght;i++){
			String numValue = random.nextInt(10)+"";
			val += numValue;
		}
		return val;
	}
}
