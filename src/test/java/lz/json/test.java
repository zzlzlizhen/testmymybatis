/**
 * 
 */
package lz.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lizhen_pc
 *123
 */
public class test {

	public static void main(String args[]){
		String str = "{id:'1'}";
		JSONObject jo = JSONObject.parseObject(str);
		System.out.println(jo.toJSONString());
	}
}
