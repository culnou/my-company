package com.culnou.mumu.company.domain.model;

import java.util.List;


import com.culnou.mumu.company.infrastructure.persistence.ProductTypeEntity;

public class AbstractProductTypeRepository implements ProductTypeRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public ProductType convertFromEntity(ProductTypeEntity entity) {
		return new ProductType(entity.getCompanyId(), entity.getProductTypeId());
	}

	@Override
	public ProductTypeId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ProductType productType) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(ProductType productType) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductType productTypeOfId(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductType> productTypesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ProductType> productTypesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		return null;
	}

	@Override
	public List<ProductType> productTypesOfCustomerType(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
