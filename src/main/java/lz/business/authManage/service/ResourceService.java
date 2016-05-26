package lz.business.authManage.service;

import java.util.List;
import java.util.Map;

import lz.model.Resource;

public interface ResourceService {

	int insertResource(Resource resource);
	
	int updateResource(Resource resource);
	
	int delResource(Resource resource);
	
	int batchDelResource(String batchDelId);
	
	Resource getResourceById(String id);
	
	int getCount(Map<String,Object> map);
	
	List<Resource> getResourcePage(Map<String,Object> map);
	
	List<Resource> getResources(Map<String,Object> map);
	
	List<Resource> getResourceMenuByUserId(Map<String,Object> map);
}
