/**
 * 
 */
package lz.business.systemManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lz.business.systemManage.service.OperLogService;
import lz.dao.OperLogMapper;
import lz.model.OperLog;
import lz.model.SystemParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author lizhen_pc
 *123
 */
@Transactional
@Service("operLogService")
public class OperLogServiceImpl implements OperLogService {

	@Autowired
	private OperLogMapper operLogMapper;
	
	@Override
	public int insertOperLog(OperLog operLog) {
		return operLogMapper.insert(operLog);
	}

	@Override
	public int batchDelOperLog(String batchDelId) {
		List<String> batchDelIds = new ArrayList<String>();
		for(String id:batchDelId.split(",")){
			batchDelIds.add(id);
		}
		return operLogMapper.batchDelOperLog(batchDelIds);
	}

	@Override
	public PageInfo<OperLog> getOperLogByPage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		List<OperLog> list = operLogMapper.selectOperLogByPage(map);
		PageInfo<OperLog> page = new PageInfo<OperLog>(list);
		return page;
	}

}
