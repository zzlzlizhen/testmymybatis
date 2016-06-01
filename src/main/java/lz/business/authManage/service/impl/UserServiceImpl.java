package lz.business.authManage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lz.business.authManage.service.UserService;
import lz.business.systemManage.service.ParamService;
import lz.constant.ConstantInfo;
import lz.dao.RoleMapper;
import lz.dao.UserMapper;
import lz.dao.UserRoleMapper;
import lz.model.Role;
import lz.model.RoleExample;
import lz.model.SystemParam;
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
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Resource(name="paramService")
	private ParamService paramService;
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
		String userId = IdGenerateUtils.getId();
		user.setId(userId);
		user.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		user.setStatus("1");
		//给注册的用户添加默认的角色
		SystemParam param = paramService.getParamByParamKey("defaultRole");
		if(param!=null){
			RoleExample roleExample = new RoleExample();
			roleExample.createCriteria().andRoleTypeEqualTo(param.getParamValue());
			List<Role> roles = roleMapper.selectByExample(roleExample);
			UserRole ur = new UserRole();
			ur.setId(IdGenerateUtils.getId());
			ur.setUserId(userId);
			ur.setRoleId((roles!=null&&roles.size()>0)?roles.get(0).getId():"");
			userRoleMapper.insertSelective(ur);
		}
		return userMapper.insertSelective(user);
	}
	
	@Override
	public List<User> getUsers(Map<String,Object> map) {
		UserExample ue = new UserExample();
		if(map.get("username")!=null){
			ue.createCriteria().andNameEqualTo((String)map.get("username"));
		}else if(map.get("telephone")!=null){
			ue.createCriteria().andPhoneEqualTo((String)map.get("telephone"));
		}else if(map.get("name")!=null&&map.get("phone")!=null){
			ue.createCriteria().andNameEqualTo((String)map.get("name")).andPhoneEqualTo((String)map.get("phone"));
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
	/**
	 * 更改密码
	 */
	@Override
	public int updatePswByName(User user) {
		User record = new User();
		record.setPwd(user.getPwd());
		UserExample ue = new UserExample();
		ue.createCriteria().andNameEqualTo(user.getName());
		return userMapper.updateByExampleSelective(record, ue);
	}
	
}
