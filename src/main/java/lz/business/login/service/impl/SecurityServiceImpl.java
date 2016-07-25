/**
 * 
 */
package lz.business.login.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lz.business.login.service.SecurityService;
import lz.dao.SecurityMapper;
import lz.model.Security;
import lz.model.SecurityExample;
import lz.utils.IdGenerateUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lizhen_pc
 *123
 */
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
	@Autowired
	private SecurityMapper securityMapper;
	@Override
	public Security getSecurity(Map<String, Object> map) {
		SecurityExample se = new SecurityExample();
		List<Security> list = null;
		if(map.get("phone")!=null&&map.get("securityType")!=null){
			se.createCriteria().andPhoneEqualTo((String)map.get("phone")).andTypeEqualTo((String)map.get("securityType"));
			list = securityMapper.selectByExample(se);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}else if(map.get("email")!=null&&map.get("securityType")!=null){
			se.createCriteria().andEmailEqualTo((String)map.get("email")).andTypeEqualTo((String)map.get("securityType"));
			list = securityMapper.selectByExample(se);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * 验证当前时间和上次发送验证码时间间隔是否大于一个时间值
	 */
	@Override
	public int getSecurityIsEffective(Map<String, Object> map) {
		SecurityExample se = new SecurityExample();
		se.createCriteria().andPhoneEqualTo((String)map.get("phone")).andTypeEqualTo((String)map.get("securityType")).andCreateTimeGreaterThanOrEqualTo((String)map.get("createTime"));
		return securityMapper.countByExample(se);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int insertSecurity(Security security) {
		security.setId(IdGenerateUtils.getId());
		security.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return securityMapper.insertSelective(security);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int updateSecurity(Security security) {
		security.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return securityMapper.updateByPrimaryKeySelective(security);
	}


}
