package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface CustomerTypeRepository {
	//識別子オブジェクトはリポジトリが生成する。
	public CustomerTypeId nextIdentity() throws Exception;
	
	//コマンド。
	public void save(CustomerType customerType) throws Exception;
	
	public void remove(CustomerType customerType) throws Exception;
	
	//クエリ
	public CustomerType customerTypeOfId(CustomerTypeId customerTypeId) throws Exception;
	
	public List<CustomerType> customerTypesOfCompany(CompanyId companyId) throws Exception;
	
	public List<CustomerType> customerTypesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception;

}
