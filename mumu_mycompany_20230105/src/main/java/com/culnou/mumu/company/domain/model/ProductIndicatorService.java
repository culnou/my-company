package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductIndicatorService {
	
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	public boolean isUsed(ProductTypeId productTypeId, Indicator indicator) throws Exception{
		boolean result = false;
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfProductType(productTypeId);
		for(ProductCategory productCategory : productCategories) {
			for(Goal goal : productCategory.getGoals()) {
				if(goal.getIndicator().equals(indicator)) {
					result = true;
					break;
				}
			}
		}
		return result;
		
	}

}
