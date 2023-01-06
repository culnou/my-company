package com.culnou.mumu.company.domain.model;

import java.util.List;

import com.culnou.mumu.company.infrastructure.persistence.CustomerCategoryEntity;


public class AbstractCustomerCategoryRepository implements CustomerCategoryRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public CustomerCategory convertFromEntity(CustomerCategoryEntity entity) {
		return new CustomerCategory(entity.getCompanyId(), entity.getCustomerTypeId(), entity.getCustomerCategoryId());
	}

	@Override
	public CustomerCategoryId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CustomerCategory customerCategory) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(CustomerCategory customerCategory) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerCategory customerCategoryOfId(CustomerCategoryId customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerCategory> customerCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerCategory> customerCategoriesOfCustomerType(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CustomerCategory> customerCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		return null;
	}

}
