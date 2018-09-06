package lz.business.clothesManager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lz.business.clothesManager.service.ClothesPropertyService;
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("clothesPropertyService")
public class ClothesPropertyServiceImpl implements ClothesPropertyService {

}
