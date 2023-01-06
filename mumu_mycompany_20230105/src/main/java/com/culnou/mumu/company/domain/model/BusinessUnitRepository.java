package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface BusinessUnitRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public BusinessUnitId nextIdentity() throws Exception;
	
	//コマンド。
	public void save(BusinessUnit businessUnit) throws Exception;
	
	public void remove(BusinessUnit businessUnit) throws Exception;
	
	//クエリ
	public BusinessUnit businessUnitOfId(BusinessUnitId businessUnitId) throws Exception;
	
	public List<BusinessUnit> businessUnitsOfCompany(CompanyId companyId) throws Exception;
	
	public List<BusinessUnit> businessUnitsOfDomain(BusinessDomainId businessDomainId) throws Exception;
	
	public List<BusinessUnit> businessUnitsOfCustomerCategory(CustomerCategoryId customerCategoryId) throws Exception;
	
	public List<BusinessUnit> businessUnitsOfProductCategory(String productCategoryId) throws Exception;


}
