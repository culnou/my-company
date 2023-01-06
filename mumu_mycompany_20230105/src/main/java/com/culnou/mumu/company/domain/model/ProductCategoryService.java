package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductCategoryService {
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	
	public boolean isUsed(ProductCategoryId productCategoryId) throws Exception{
		boolean result = false;
		/*
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfProductCategory(productCategoryId.productCategoryId);
		if(businessUnits.size() > 0) {
			result = true;
	     }
	     */
		
		return result;
	}
	
	public boolean goalIsUsed(ProductCategoryId productCategoryId, Indicator indicator) throws Exception{
		boolean result = false;
		ProductCategory productCategory = productCategoryRepository.productCategoryOfId(productCategoryId);
		List<Achievement> achievements = productCategory.getAchievements();
		for(Achievement achievement : achievements) {
			if(achievement.getGoal().getIndicator().equals(indicator)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	//製品機能のロジック
	
	

}
