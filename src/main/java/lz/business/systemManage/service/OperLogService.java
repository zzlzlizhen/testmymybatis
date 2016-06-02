/**
 * 
 */
package lz.business.systemManage.service;

import java.util.Map;

import lz.model.OperLog;

import com.github.pagehelper.PageInfo;

/**
 * @author lizhen_pc
 *123
 */
public interface OperLogService {

	int insertOperLog(OperLog operLog);
	
	int batchDelOperLog(String batchDelId);
	
	PageInfo<OperLog> getOperLogByPage(Map<String,Object> map);
}
