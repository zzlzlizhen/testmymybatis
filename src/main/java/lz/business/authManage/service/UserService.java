package lz.business.authManage.service;

import java.util.List;
import java.util.Map;

import lz.model.Message;
import lz.model.User;

import com.github.pagehelper.PageInfo;

public interface UserService {
	
    int insertUser(User user);
	
    int insertRegisterUser(User user);
    
	int updateUser(User user);
	
	int updatePersonInfo(User user);
	
	int updateUserStatus(User user);
	
	int delUser(User user);
	
	int updatePswByName(User user,Message message);

	//获取用户对象
	User getUserByNameAndPwd(User user);

	User getUserById(String id);
	
	List<User> getUsers(Map<String,Object> map);
	
	PageInfo<User> getUserPage(Map<String,Object> map);
	
	
}
