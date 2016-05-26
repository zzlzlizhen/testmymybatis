package lz.junittest;

import javax.xml.namespace.QName;

import lz.ws.IHelloService;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WebServiceTest {

	private ApplicationContext ac;
	private IHelloService helloService;
	@Before
	public void before(){
		ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml","spring-cxf-client.xml"});
		helloService = (IHelloService)ac.getBean("client");
	}
	@Test
	public void test01(){
		String response = helloService.sayHello("happy");  
        System.out.println("Response: " + response);  
        System.exit(0);
	}
	@Test
	public void webServiceCXF() throws Exception{
    	//这个是用cxf 客户端访问cxf部署的webservice服务  
        //千万记住，访问cxf的webservice必须加上namespace ,否则通不过  
        //现在又另外一个问题，传递过去的参数服务端接收不到  
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:8080/testmybatis/ws/helloService?wsdl");  
        //url为调用webService的wsdl地址  
        QName name=new QName("http://ws.lz/","sayBay");  
        //namespace是命名空间，methodName是方法名 
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"  
                 + "     <facelook>"  
                 + "        <condition>"  
                 + "            <name>家</name>"  
                 + "            <description></description>"  
                 + "            <pageno></pageno>"  
                 + "            <pagesize></pagesize>"  
                 + "        </condition>"  
                 + "     </facelook>";  
        //paramvalue为参数值  
        Object[] objects=client.invoke(name,xmlStr);   
        //调用web Service//输出调用结果  
        System.out.println("test success");
        System.out.println(objects[0].toString());  
    }
}
