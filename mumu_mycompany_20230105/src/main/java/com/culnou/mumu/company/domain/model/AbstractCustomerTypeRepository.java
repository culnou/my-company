package com.culnou.mumu.company.domain.model;

import java.util.List;


import com.culnou.mumu.company.infrastructure.persistence.CustomerTypeEntity;

public class AbstractCustomerTypeRepository implements CustomerTypeRepository {
	
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public CustomerType convertFromEntity(CustomerTypeEntity entity) {
		return new CustomerType(entity.getCompanyId(), entity.getCustomerTypeId(), entity.getCustomerTypeName(), entity.getPersonality());
	}

	@Override
	public CustomerTypeId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CustomerType customerType) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(CustomerType customerType) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerType customerTypeOfId(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerType> customerTypesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CustomerType> customerTypesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		return null;
	}

}
