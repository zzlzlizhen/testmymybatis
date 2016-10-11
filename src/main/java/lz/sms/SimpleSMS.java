package lz.sms;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SimpleSMS {

	public static boolean sendSms(String toPhone){
		try {
			Properties prop = new Properties();
			String filePath = Class.class.getResource("/").getPath()
					+ "sms-info.properties";
			FileInputStream fis = new FileInputStream(filePath);
			BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
			prop.load(bf);
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
			post.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=utf8");// 在头文件中设置转码
			NameValuePair[] data = {
					new NameValuePair("Uid", prop.getProperty("uid")),
					new NameValuePair("Key", prop.getProperty("key")),
					new NameValuePair("smsMob",toPhone),
					new NameValuePair("smsText",prop.getProperty("content1")) };
			post.setRequestBody(data);
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes(
					"utf8"));
			System.out.println(result);
			post.releaseConnection();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}