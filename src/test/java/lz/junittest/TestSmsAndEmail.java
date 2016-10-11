package lz.junittest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import lz.mail.MailInfo;
import lz.mail.SimpleMail;
import lz.sms.SimpleSMS;

import org.junit.Test;

public class TestSmsAndEmail {

	@Test
	public void test01() throws IOException{
		Properties prop = new Properties();
		String filePath = Class.class.getResource("/").getPath()
				+ "sms-info.properties";
		FileInputStream fis = new FileInputStream(filePath);
		BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
		prop.load(bf);
		System.out.println(prop.getProperty("content1"));
	}
	
	@Test
	public void testSms(){
		SimpleSMS.sendSms("15810669164");
	}
	
	@Test
	public void testEmail(){
		MailInfo mailInfo = new MailInfo();  
        mailInfo.setToAddress("275290501@qq.com");
        mailInfo.setContent("测试普通文本文件");
        mailInfo.setSubject("普通文本文件");
        SimpleMail.sendTextMail(mailInfo);
	}
}
