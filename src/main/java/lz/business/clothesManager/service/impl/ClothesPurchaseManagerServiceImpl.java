package lz.business.clothesManager.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lz.business.clothesManager.service.ClothesPurchaseManagerService;
import lz.dao.YznzColthesMapper;
import lz.model.Resource;
import lz.model.SystemParam;
import lz.model.YznzColthes;
import lz.utils.IdGenerateUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("clothesPurchaseManagerService")
public class ClothesPurchaseManagerServiceImpl implements ClothesPurchaseManagerService {

	@Autowired
	private YznzColthesMapper ycMapper;
	
	@Override
	public PageInfo<YznzColthes> getPurchaseByPage(Map<String, Object> map) {
		PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"));
		List<YznzColthes> list = ycMapper.selectPurchaseByPage(map);
		PageInfo<YznzColthes> page = new PageInfo<YznzColthes>(list);
		return page;
	}

	@Override
	public int insertYznzClother(YznzColthes yznzClothes) {
		yznzClothes.setId(IdGenerateUtils.getId());
		yznzClothes.setAddTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return ycMapper.insertSelective(yznzClothes);
	}

	@Override
	public YznzColthes getParamById(String id) {
		YznzColthes yznzColthe = ycMapper.selectByPrimaryKey(id);
		return yznzColthe;
	}

	@Override
	public int getParamCountByParamKey(String paramKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateYznzClother(YznzColthes yznzColthes) {
		yznzColthes.setUpdateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return ycMapper.updateByPrimaryKeySelective(yznzColthes);
		
	}
	
}
