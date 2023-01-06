package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface ProductCategoryRepository {
	//識別子オブジェクトはリポジトリが生成する。
	public ProductCategoryId nextIdentity() throws Exception;
	
	//コマンド
    public void save(ProductCategory productCategory) throws Exception;
	
	public void remove(ProductCategory productCategory) throws Exception;
	
	//クエリ
	public ProductCategory productCategoryOfId(ProductCategoryId productCategoryId) throws Exception;
	
	public List<ProductCategory> productCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<ProductCategory> productCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;
	
	public List<ProductCategory> productCategoriesOfProductType(ProductTypeId productTypeId) throws Exception;
	
	public List<ProductCategory> productCategoriesOfCustomerCategory(CustomerCategoryId customerCategoryId) throws Exception;
	
	public List<ProductCategory> productCategoriesOfProductClass(BusinessUnitId businessUnitId, ProductClass productClass) throws Exception;

}
