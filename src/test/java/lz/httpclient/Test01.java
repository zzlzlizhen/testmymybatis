/**
 * 
 */
package lz.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @author lizhen_pc
 *123
 */
public class Test01 {
	@Test
	public void test01(){
		String str = null;
		if(StringUtils.isNotEmpty(str)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
	
	@Test
	public void test03(){
		String str = "str";
		String str1 = "str";
		System.out.println(str.hashCode());
		System.out.println(str1.hashCode());
		str="str1";
		System.out.println(str.hashCode());
		System.out.println(str1.hashCode());
		System.out.println(str.toString());
		Object o = null;
	}
	
	@Test
	public void testPost() {
		String url = "http://localhost:8080/testmybatis/otherController/index";
		HttpClient hc = new HttpClient();
		HttpMethodParams params = new HttpMethodParams();
		HttpConnectionManagerParams managerParams = hc.getHttpConnectionManager().getParams();
		managerParams.setConnectionTimeout(3000);
		managerParams.setSoTimeout(15000);
		params.setContentCharset("UTF-8");
		PostMethod post = new PostMethod(url);
		//post.addRequestHeader("content-type","application/json");
		post.setParams(params);
		post.setParameter("name","张三");
		post.setParameter("age","24");
		String form = "";
		
		try {
			hc.executeMethod(post);
			form = post.getResponseBodyAsString();
			System.out.println(form);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			post.releaseConnection();
		}
	}
	
	@Test
	public void test02(){
		HttpClient client = new HttpClient();  

		HttpMethod method = new GetMethod("http://test.com");     

		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000); 
		
		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
