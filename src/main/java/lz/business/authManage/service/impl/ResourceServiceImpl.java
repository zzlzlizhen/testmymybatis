package lz.business.authManage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lz.business.authManage.service.ResourceService;
import lz.dao.ResourceMapper;
import lz.model.Resource;
import lz.model.ResourceExample;
import lz.utils.IdGenerateUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int insertResource(Resource resource) {
		resource.setId(IdGenerateUtils.getId());
		resource.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return resourceMapper.insertSelective(resource);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int updateResource(Resource resource) {
		resource.setUpdateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int delResource(Resource resource) {
		return resourceMapper.deleteByPrimaryKey(resource.getId());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
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
	public PageInfo<Resource> getResourcePage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		List<Resource> list = resourceMapper.selectResourceByPage(map);
		PageInfo<Resource> page = new PageInfo<Resource>(list);
		return page;
	}
	@Override
	public List<Resource> getResources(Map<String, Object> map) {
		ResourceExample example = new ResourceExample();
		if(map!=null&&map.get("resourceUrl")!=null){
			example.createCriteria().andResourceUrlEqualTo((String)map.get("resourceUrl"));
		}
		List<Resource> list = resourceMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<Resource> getResourceMenuByUserId(Map<String, Object> map) {
		return resourceMapper.getResourceMenuByUserId(map);
	}
}
