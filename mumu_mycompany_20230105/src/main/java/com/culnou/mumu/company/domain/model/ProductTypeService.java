package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductTypeService {
	
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Qualifier("productTaskJpaRepository")
	@Autowired
	private ProductTaskRepository productTaskRepository;
	@Qualifier("productTypeJpaRepository")
	@Autowired
	private ProductTypeRepository productTypeRepository;
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	
	public boolean isUsed(ProductTypeId productTypeId) throws Exception{
		boolean result = false;
		//製品カテゴリが使用している。
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfProductType(productTypeId);
		if(productCategories.size() > 0) {
			result = true;
		}
		
		//製品タスクが使用している。
		List<ProductTask> productTasks = productTaskRepository.productTasksOfProductType(productTypeId);
		if(productTasks.size() > 0) {
			result = true;
		}
		/*
		//製品KPIが使用している。
		ProductType productType = productTypeRepository.productTypeOfId(productTypeId);
		if(productType.getIndicators().size() > 0) {
			result = true;
		}
		*/
		//事業ドメインが使用している。
		/*
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfProductType(productTypeId);
		if(businessDomains.size() > 0) {
			result = true;
		}
		*/
		
		
		return result;
	}

}
