package lz.business.systemManage.service;

import java.util.Map;

import lz.model.SystemParam;

import com.github.pagehelper.PageInfo;

public interface ParamService {

	
	
	int insertParam(SystemParam systemParam);
	
	int updateParam(SystemParam systemParam);
	
	SystemParam getParamById(String id);
	
	SystemParam getParamByParamKey(String paramKey);
	
	int delSystemParam(String id);
	
	int batchDelSystemParam(String batchDelId);
	
	int getParamCountByParamKey(String paramKey);
	
	PageInfo<SystemParam> getParamByPage(Map<String,Object> map);
	
}
