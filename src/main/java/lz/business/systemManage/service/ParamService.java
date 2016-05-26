package lz.business.systemManage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import lz.model.SystemParam;

public interface ParamService {

	
	
	int insertParam(SystemParam systemParam);
	
	int updateParam(SystemParam systemParam);
	
	SystemParam getParamById(String id);
	
	List<SystemParam> getParamByParamKey(String paramKey);
	
	int delSystemParam(String id);
	
	int batchDelSystemParam(String batchDelId);
	
	int getParamCountByParamKey(String paramKey);
	
	PageInfo<SystemParam> getParamByPage(Map<String,Object> map);
	
}
