package com.culnou.mumu.company.domain.model;

import com.culnou.mumu.company.infrastructure.persistence.ProductFunctionEntity;


public abstract class AbstractProductFunctionRepository implements ProductFunctionRepository {
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public ProductFunction convertFromEntity(ProductFunctionEntity entity) {
		ProductFunctionId productFunctionId = entity.getProductFunctionId();
		if(productFunctionId == null) {
			throw new IllegalArgumentException("The productFunctionId of ProductFunctionEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of ProductFunctionEntity may not be set to null.");
		}
		ProductCategoryId productCategoryId = entity.getProductCategoryId();
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId of ProductFunctionEntity may not be set to null.");
		}
		String productCategoryName = entity.getProductCategoryName();
		if(productCategoryName == null) {
			throw new IllegalArgumentException("The productCategoryName of ProductFunctionEntity may not be set to null.");
		}
		ProductTaskId productTaskId = entity.getProductTaskId();
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId of ProductFunctionEntity may not be set to null.");
		}
		String productTaskName = entity.getProductTaskName();
		if(productTaskName == null) {
			throw new IllegalArgumentException("The productTaskName of ProductFunctionEntity may not be set to null.");
		}
		String productFunctionName = entity.getProductFunctionName();
		if(productFunctionName == null) {
			throw new IllegalArgumentException("The productFunctionName of ProductFunctionEntity may not be set to null.");
		}
		return new ProductFunction(productFunctionId, companyId, productCategoryId, productCategoryName, productTaskId, productTaskName, productFunctionName);
	}

}
