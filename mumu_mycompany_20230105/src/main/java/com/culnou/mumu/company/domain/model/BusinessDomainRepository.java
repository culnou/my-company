package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface BusinessDomainRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public BusinessDomainId nextIdentity() throws Exception;
	
	//コマンド。
	public void save(BusinessDomain businessDomain) throws Exception;
	
	public void remove(BusinessDomain businessDomain) throws Exception;
	
	//クエリ
	public BusinessDomain businessDomainOfId(BusinessDomainId businessDomainId) throws Exception;
	
	public List<BusinessDomain> businessDomainOfCompany(CompanyId companyId) throws Exception;
	
	public List<BusinessDomain> businessDomainsOfCustomerType(CustomerTypeId customerTypeId) throws Exception;
	
	public List<BusinessDomain> businessDomainsOfProductType(ProductTypeId productTypeId) throws Exception;
	
	public List<BusinessDomain> findEnterprises(boolean enterprise, CompanyId companyId) throws Exception;
	
	
}
