package lz.business.authManage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import lz.model.Role;

public interface RoleService {

	int insertRole(Role role);
	
	int updateRole(Role role);
	
	int delRole(Role role);
	
	int batchDelRole(String batchDelId);
	
	Role getRoleById(String id);
	
	PageInfo<Role> getRolePage(Map<String,Object> map);
	
	List<Role> getRoles(Map<String,Object> map);
	
	int insertRoleResource(Role role);
}
