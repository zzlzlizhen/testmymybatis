package lz.business.login.service;

import java.util.Map;

import lz.model.Security;


public interface SecurityService {
	
	Security getSecurity(Map<String,Object> map);
	
	int getSecurityIsEffective(Map<String,Object> map);
	
	int insertSecurity(Security security);
	
	int updateSecurity(Security security);
}
