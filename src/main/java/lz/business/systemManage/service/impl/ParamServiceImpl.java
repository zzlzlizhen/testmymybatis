package lz.business.systemManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lz.business.systemManage.service.ParamService;
import lz.dao.SystemParamMapper;
import lz.model.SystemParam;
import lz.model.SystemParamExample;
import lz.utils.IdGenerateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("paramService")
public class ParamServiceImpl implements ParamService {

	@Autowired
	private SystemParamMapper systemParamMapper;
	
	@Override
	public List<SystemParam> getParamByPage(Map<String, Object> map) {
		List<SystemParam> list = null;
		try {
			list = systemParamMapper.selectParamByPage(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertParam(SystemParam systemParam) {
		systemParam.setId(IdGenerateUtils.getId());
		return systemParamMapper.insertSelective(systemParam);
	}
	
	@Override
	public int updateParam(SystemParam systemParam) {
		return systemParamMapper.updateByPrimaryKeySelective(systemParam);
	}
	
	@Override
	public int getParamCount(Map<String, Object> map) {
		/*SystemParamExample example = new SystemParamExample();
		if(map.get("searchId")!=null){
			String searchId = (String)map.get("searchId");
			example.createCriteria().andParamKeyLike(searchId).andParam
		}*/
		int count = systemParamMapper.getCountByPage(map);
		return count;
	}

	@Override
	public int getParamCountByParamKey(String paramKey) {
		SystemParamExample example = new SystemParamExample();
		example.createCriteria().andParamKeyEqualTo(paramKey);
		int count = systemParamMapper.countByExample(example);
		return count;
	}

	@Override
	public SystemParam getParamById(String id) {
		SystemParam systemParem = systemParamMapper.selectByPrimaryKey(id);
		return systemParem;
	}

	@Override
	public List<SystemParam> getParamByParamKey(String paramKey) {
		SystemParamExample example = new SystemParamExample();
		example.createCriteria().andParamKeyEqualTo(paramKey);
		List<SystemParam> systemParams = systemParamMapper.selectByExample(example);
		return systemParams;
	}

	@Override
	public int delSystemParam(String id) {
		return systemParamMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int batchDelSystemParam(String batchDelId) {
		List<String> batchDelIds = new ArrayList<String>();
		for(String id:batchDelId.split(",")){
			batchDelIds.add(id);
		}
		return systemParamMapper.batchDelSystemParam(batchDelIds);
	}

	
}
