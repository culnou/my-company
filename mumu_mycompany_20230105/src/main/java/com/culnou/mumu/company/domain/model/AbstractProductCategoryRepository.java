package com.culnou.mumu.company.domain.model;

import java.util.List;


import com.culnou.mumu.company.infrastructure.persistence.ProductCategoryEntity;

public class AbstractProductCategoryRepository implements ProductCategoryRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public ProductCategory convertFromEntity(ProductCategoryEntity entity) {
		return new ProductCategory(entity.getCompanyId(), entity.getProductTypeId(), entity.getProductCategoryId());
	}

	@Override
	public ProductCategoryId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ProductCategory> productCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		return null;
	}

	@Override
	public void save(ProductCategory productCategory) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(ProductCategory productCategory) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductCategory productCategoryOfId(ProductCategoryId productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public List<ProductCategory> productCategoriesOfProductClass(BusinessUnitId businessUnitId, ProductClass productClass) throws Exception{
		return null;
	}

	@Override
	public List<ProductCategory> productCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategory> productCategoriesOfProductType(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategory> productCategoriesOfCustomerCategory(CustomerCategoryId customerCategoryId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
