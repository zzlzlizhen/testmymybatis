package lz.business.authManage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import lz.model.Role;
import lz.model.User;

public interface UserService {
	
    int insertUser(User user);
	
	int updateUser(User user);
	
	int delUser(User user);
	
	User getUserByNameAndPwd(User user);

	User getUserById(String id);
	
	List<User> getUsers(Map<String,Object> map);
	
	PageInfo<User> getUserPage(Map<String,Object> map);
	
}
