package lz.utils;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class IdGenerateUtils {

	/**
	 * 获取生成的唯一标识id
	 * @return
	 */
	public static String getId(){
		Date date = new Date();
		String id = DateFormatUtils.format(date,"yyyyMMddHHmmssSSS")+MathUtils.getCharAndNum(8);
		return id;
	}
	public static void main(String[] args){
		System.out.println(getId());
	}
}
