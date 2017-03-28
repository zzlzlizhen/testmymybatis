package lz.junittest;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import lz.business.authManage.service.UserService;
import lz.business.systemManage.service.ParamService;
import lz.model.SystemParam;
import lz.model.User;
import lz.utils.MD5Utils;
import lz.utils.MathUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-Mybatis.xml"})  
public class SpringTestUserService {
	private static final Logger log = Logger.getLogger(SpringTestUserService.class);
	//private ApplicationContext ac;
	@Resource
	private ParamService paramService;
	@Resource
	private UserService us;
	/*@Before
	public void before(){
		ac = new ClassPathXmlApplicationContext(new String[]{"/spring*.xml"});
		//paramService = (ParamService)ac.getBean("paramService");
		us = (UserService)ac.getBean("userService");
	}*/
	@Test
	public void TestInsertUser(){
		for(int i=0;i<10;i++){
			Date date = new Date();
			String uuid = UUID.randomUUID().toString();
			String name = MathUtils.getCharAndNum(8);
			User user = new User();
			//user.setId(1);
			user.setName(name);
			user.setPwd(MD5Utils.getMd5(name));
			user.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			us.insertUser(user);
		}
	}
	@Test
	public void TestInsertParam(){
		for(int i=1;i<25;i++){
			Date date = new Date();
			String uuid = UUID.randomUUID().toString();
			String name = MathUtils.getCharAndNum(8);
			SystemParam sp = new SystemParam();
			sp.setParamKey(i+"");
			sp.setParamValue(name);
			sp.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			paramService.insertParam(sp);
		}
	}
	@Test
	public void testRandom(){
		System.out.println(MathUtils.getCharAndNum(10));
	}
	
	
	@Test
	public void TestRegex(){
		String str = "12345_67890";
		if(str==null||"".equals(str)){
		}else{
			if(str.length()>15||str.length()<3){
				System.out.println("");
			}else{
				if(!str.matches("^[a-zA-Z0-9_]*$")){
					System.out.println("");
				}
				if(str.matches("^[0-9]*$")||str.matches("^[a-zA-Z]*$")||str.matches("^[_]*$")){
					System.out.println("");
				}
			}
		}
		
	}

}
