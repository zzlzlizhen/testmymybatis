package lz.business.systemManage.service;

import java.util.List;
import java.util.Map;

import lz.model.SystemParam;

public interface ParamService {

	List<SystemParam> getParamByPage(Map<String,Object> map);
	
	int insertParam(SystemParam systemParam);
	
	int updateParam(SystemParam systemParam);
	
	int getParamCount(Map<String,Object> map);
	
	int getParamCountByParamKey(String paramKey);
	
	SystemParam getParamById(String id);
	
	List<SystemParam> getParamByParamKey(String paramKey);
	
	int delSystemParam(String id);
	
	int batchDelSystemParam(String batchDelId);
	
}
