package lz.business.authManage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lz.business.authManage.service.RoleService;
import lz.dao.RoleMapper;
import lz.dao.RoleResourceMapper;
import lz.model.Role;
import lz.model.RoleExample;
import lz.model.RoleResource;
import lz.model.RoleResourceExample;
import lz.utils.IdGenerateUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private RoleMapper roleMapper;
	private RoleResourceMapper roleResourceMapper;
	public RoleMapper getRoleMapper() {
		return roleMapper;
	}
	@Autowired
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	
	public RoleResourceMapper getRoleResourceMapper() {
		return roleResourceMapper;
	}
	@Autowired
	public void setRoleResourceMapper(RoleResourceMapper roleResourceMapper) {
		this.roleResourceMapper = roleResourceMapper;
	}
	@Override
	public int insertRole(Role role) {
		role.setId(IdGenerateUtils.getId());
		role.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return roleMapper.insertSelective(role);
	}
	@Override
	public int updateRole(Role role) {
		role.setUpdateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return roleMapper.updateByPrimaryKeySelective(role);
	}
	@Override
	public int delRole(Role role) {
		RoleResourceExample rre = new RoleResourceExample();
		rre.createCriteria().andRoleIdEqualTo(role.getId());
		roleResourceMapper.deleteByExample(rre);
		return roleMapper.deleteByPrimaryKey(role.getId());
	}
	@Override
	public int batchDelRole(String batchDelId) {
		List<String> batchDelIds = new ArrayList<String>();
		for(String id:batchDelId.split(",")){
			batchDelIds.add(id);
		}
		return roleMapper.batchDelRole(batchDelIds);
	}
	@Override
	public Role getRoleById(String id) {
		return roleMapper.selectById(id);
	}
	/**
	 * 角色列表分页
	 */
	
	@Override
	public PageInfo<Role> getRolePage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		List<Role> list = roleMapper.selectRoleByPage(map);
		PageInfo<Role> page = new PageInfo<Role>(list);
		return page;
	}
	/**
	 * 获取所有的角色
	 */
	@Override
	public List<Role> getRoles(Map<String, Object> map) {
		RoleExample example = new RoleExample();
		List<Role> list = roleMapper.selectByExample(example);
		return list;
	}
	/**
	 * 角色授权
	 */
	@Override
	public int insertRoleResource(Role role) {
		String resources = role.getRemark();
		RoleResourceExample rre = new RoleResourceExample();
		rre.createCriteria().andRoleIdEqualTo(role.getId());
		roleResourceMapper.deleteByExample(rre);
		for(String resourceId:resources.split(",")){
			RoleResource rr = new RoleResource();
			rr.setId(IdGenerateUtils.getId());
			rr.setResourceId(resourceId);
			rr.setRoleId(role.getId());
			roleResourceMapper.insertSelective(rr);
		}
		return 0;
	}
}
