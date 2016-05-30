package lz.business.authManage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lz.business.authManage.service.UserService;
import lz.constant.ConstantInfo;
import lz.dao.UserMapper;
import lz.dao.UserRoleMapper;
import lz.model.Role;
import lz.model.User;
import lz.model.UserExample;
import lz.model.UserRole;
import lz.model.UserRoleExample;
import lz.utils.IdGenerateUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	private UserRoleMapper userRoleMapper;
	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Autowired
	public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
		this.userRoleMapper = userRoleMapper;
	}
	@Override
	public User getUserById(String id) {
		User user = userMapper.selectById(id);
		return user;
	}
	@Override
	public int insertUser(User user) {
		user.setId(IdGenerateUtils.getId());
		String userRoleIds = user.getPwd();
		for(String userRoleId:userRoleIds.split(",")){
			UserRole ur = new UserRole();
			ur.setId(IdGenerateUtils.getId());
			ur.setRoleId(userRoleId);
			ur.setUserId(user.getId());
			userRoleMapper.insertSelective(ur);
		}
		user.setPwd(ConstantInfo.INIT_PASSWORD);
		user.setStatus("1");
		user.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return userMapper.insertSelective(user);
	}
	
	/**
	 * 注册用户
	 */
	@Override
	public int insertRegisterUser(User user) {
		user.setId(IdGenerateUtils.getId());
		user.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		user.setStatus("1");
		return userMapper.insertSelective(user);
	}
	
	@Override
	public List<User> getUsers(Map<String,Object> map) {
		UserExample ue = new UserExample();
		if(map.get("username")!=null){
			ue.createCriteria().andNameEqualTo((String)map.get("username"));
		}else if(map.get("telephone")!=null){
			ue.createCriteria().andPhoneEqualTo((String)map.get("telephone"));
		}
		return userMapper.selectByExample(ue);
	}
	@Override
	public User getUserByNameAndPwd(User user) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andNameEqualTo(user.getName()).andPwdEqualTo(user.getPwd());
		List<User> list = userMapper.selectByExample(userExample);
		if(list!=null&&list.size()>0) return list.get(0);
		else return null;
	}
	@Override
	public int updateUser(User user) {
		String userRoleIds = user.getPwd();
		UserRoleExample ure = new UserRoleExample();
		ure.createCriteria().andUserIdEqualTo(user.getId());
		userRoleMapper.deleteByExample(ure);
		for(String userRoleId:userRoleIds.split(",")){
			UserRole ur = new UserRole();
			ur.setId(IdGenerateUtils.getId());
			ur.setRoleId(userRoleId);
			ur.setUserId(user.getId());
			userRoleMapper.insertSelective(ur);
		}
		user.setPwd(null);
		user.setUpdateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return userMapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public int updateUserStatus(User user) {
		user.setUpdateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return userMapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public int delUser(User user) {
		UserRoleExample ure = new UserRoleExample();
		ure.createCriteria().andUserIdEqualTo(user.getId());
		userRoleMapper.deleteByExample(ure);
		return userMapper.deleteByPrimaryKey(user.getId());
	}

	@Override
	public PageInfo<User> getUserPage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		PageInfo<User> page = null;
		try {
			List<User> list = userMapper.selectUserByPage(map);
			page = new PageInfo<User>(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
}
