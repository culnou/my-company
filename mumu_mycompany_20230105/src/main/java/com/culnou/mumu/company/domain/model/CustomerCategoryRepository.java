package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface CustomerCategoryRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public CustomerCategoryId nextIdentity() throws Exception;
	
	//コマンド
    public void save(CustomerCategory customerCategory) throws Exception;
	
	public void remove(CustomerCategory customerCategory) throws Exception;
	
	//クエリ
	public CustomerCategory customerCategoryOfId(CustomerCategoryId customerCategoryId) throws Exception;
	
	public List<CustomerCategory> customerCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<CustomerCategory> customerCategoriesOfCustomerType(CustomerTypeId customerTypeId) throws Exception;
	
	public List<CustomerCategory> customerCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;

}
