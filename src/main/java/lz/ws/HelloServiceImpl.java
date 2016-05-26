package lz.ws;

import javax.jws.WebService;
/**
 * 
 * @author lizhen_pc
 *
 */
@WebService
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String username) {
        return "hello, " + username;
    }

	@Override
	public String sayBay(String username) {
		return "bay,"+username;
	}

}