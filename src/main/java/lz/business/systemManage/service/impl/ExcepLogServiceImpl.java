/**
 * 
 */
package lz.business.systemManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lz.business.systemManage.service.ExcepLogService;
import lz.business.systemManage.service.OperLogService;
import lz.dao.ExcepLogMapper;
import lz.dao.OperLogMapper;
import lz.model.ExcepLog;
import lz.model.ExcepLogExample;
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
@Service("excepLogService")
public class ExcepLogServiceImpl implements ExcepLogService {

	@Autowired
	private ExcepLogMapper excepLogMapper;
	
	@Override
	public int insertExcepLog(ExcepLog excepLog) {
		return excepLogMapper.insert(excepLog);
	}

	@Override
	public int batchDelExcepLog(String batchDelId) {
		List<String> batchDelIds = new ArrayList<String>();
		for(String id:batchDelId.split(",")){
			batchDelIds.add(id);
		}
		return excepLogMapper.batchDelExcepLog(batchDelIds);
	}

	@Override
	public PageInfo<ExcepLog> getExcepLogByPage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		PageInfo<ExcepLog> page = null;
		try {
			List<ExcepLog> list = excepLogMapper.selectExcepLogByPage(map);
			page = new PageInfo<ExcepLog>(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public ExcepLog getExcepLogById(String id) {
		return excepLogMapper.selectByPrimaryKey(id);
	}
}
