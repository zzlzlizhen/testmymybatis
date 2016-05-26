package lz.ws;

import javax.jws.WebService;

/**
 * 
 * @author lizhen_pc
 *
 */
@WebService
public interface IHelloService {

    public String sayHello(String username);
    
    public String sayBay(String username);
    
}