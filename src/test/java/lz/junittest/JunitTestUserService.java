package lz.junittest;

import lz.business.authManage.service.UserService;
import lz.model.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JunitTestUserService {

	@Test
	public void test01(){
		try {
			test02();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("扑捉test03的异常");
		}
	}
	
	public void test02(){
		test03();
	}
	public void test03(){
		int i = 10/0;
	}

}
