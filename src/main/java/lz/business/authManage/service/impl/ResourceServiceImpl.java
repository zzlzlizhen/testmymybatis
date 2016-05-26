package lz.business.authManage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lz.business.authManage.service.ResourceService;
import lz.dao.ResourceMapper;
import lz.dao.UserMapper;
import lz.model.Resource;
import lz.model.ResourceExample;
import lz.model.SystemParam;
import lz.utils.IdGenerateUtils;
@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	private ResourceMapper resourceMapper;
	
	public ResourceMapper getResourceMapper() {
		return resourceMapper;
	}
	@Autowired
	public void setResourceMapper(ResourceMapper resourceMapper) {
		this.resourceMapper = resourceMapper;
	}
	@Override
	public int insertResource(Resource resource) {
		resource.setId(IdGenerateUtils.getId());
		resource.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return resourceMapper.insertSelective(resource);
	}

	@Override
	public int updateResource(Resource resource) {
		resource.setUpdateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public int delResource(Resource resource) {
		return resourceMapper.deleteByPrimaryKey(resource.getId());
	}

	@Override
	public int batchDelResource(String batchDelId) {
		List<String> batchDelIds = new ArrayList<String>();
		for(String id:batchDelId.split(",")){
			batchDelIds.add(id);
		}
		return resourceMapper.batchDelResource(batchDelIds);
	}

	@Override
	public Resource getResourceById(String id) {
		return resourceMapper.selectById(id);
	}
	@Override
	public int getCount(Map<String, Object> map) {
		return resourceMapper.getCountByPage(map);
	}
	@Override
	public List<Resource> getResourcePage(Map<String, Object> map) {
		List<Resource> list = null;
		try {
			list = resourceMapper.selectResourceByPage(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Resource> getResources(Map<String, Object> map) {
		List<Resource> list = null;
		ResourceExample example = new ResourceExample();
		if(map!=null&&map.get("resourceUrl")!=null){
			example.createCriteria().andResourceUrlEqualTo((String)map.get("resourceUrl"));
		}
		try {
			list = resourceMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Resource> getResourceMenuByUserId(Map<String, Object> map) {
		return resourceMapper.getResourceMenuByUserId(map);
	}

}
