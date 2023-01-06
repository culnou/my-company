package com.culnou.mumu.company.domain.model;

import com.culnou.mumu.company.infrastructure.persistence.ProductTaskEntity;


public abstract class AbstractProductTaskRepository implements ProductTaskRepository {
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public ProductTask convertFromEntity(ProductTaskEntity entity) {
		ProductTaskId productTaskId = entity.getProductTaskId();
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId of ProductTaskEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of ProductTaskEntity may not be set to null.");
		}
		ProductTypeId productTypeId = entity.getProductTypeId();
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId of ProductTaskEntity may not be set to null.");
		}
		String productTypeName = entity.getProductTypeName();
		if(productTypeName == null) {
			throw new IllegalArgumentException("The productTypeName of ProductTaskEntity may not be set to null.");
		}
		
		String productTaskName = entity.getProductTaskName();
		if(productTaskName == null) {
			throw new IllegalArgumentException("The productTaskName of ProductTaskEntity may not be set to null.");
		}
		return new ProductTask(productTaskId, companyId, productTypeId, productTypeName, productTaskName);
	}


}
