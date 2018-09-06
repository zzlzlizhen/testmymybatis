package lz.business.clothesManager.service;

import java.util.Map;

import lz.model.SystemParam;
import lz.model.YznzColthes;

import com.github.pagehelper.PageInfo;


public interface ClothesPurchaseManagerService {
	PageInfo<YznzColthes> getPurchaseByPage(Map<String,Object> map);
	int insertYznzClother(YznzColthes yznzColthes);
	YznzColthes getParamById(String id);
	int getParamCountByParamKey(String paramKey);
}