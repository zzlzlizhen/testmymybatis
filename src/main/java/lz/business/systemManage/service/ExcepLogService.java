/**
 * 
 */
package lz.business.systemManage.service;

import java.util.Map;

import lz.model.ExcepLog;
import lz.model.OperLog;

import com.github.pagehelper.PageInfo;

/**
 * @author lizhen_pc
 *123
 */
public interface ExcepLogService {

	int insertExcepLog(ExcepLog excepLog);
	
	int batchDelExcepLog(String batchDelId);
	
	ExcepLog getExcepLogById(String id);
	
	PageInfo<ExcepLog> getExcepLogByPage(Map<String,Object> map);
}
