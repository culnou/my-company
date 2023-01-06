package com.culnou.mumu.company.domain.model;

import java.util.List;

import com.culnou.mumu.company.infrastructure.persistence.BusinessDomainEntity;

public class AbstractBusinessDomainRepository implements BusinessDomainRepository {

	@Override
	public BusinessDomainId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BusinessDomain businessDomain) throws Exception {
		// TODO Auto-generated method stub

	}
	//BusinessDomainのコンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public BusinessDomain convertFromEntity(BusinessDomainEntity entity) {
		return new BusinessDomain(entity.getCompanyId(), entity.getBusinessDomainId());
	}

	@Override
	public List<BusinessDomain> businessDomainOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessDomain businessDomainOfId(BusinessDomainId businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(BusinessDomain businessDomain) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BusinessDomain> businessDomainsOfCustomerType(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessDomain> businessDomainsOfProductType(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessDomain> findEnterprises(boolean enterprise, CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	

}
