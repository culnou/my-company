package com.culnou.mumu.company.domain.model;

import java.util.List;

import com.culnou.mumu.company.infrastructure.persistence.BusinessUnitEntity;

public class AbstractBusinessUnitRepository implements BusinessUnitRepository {

	@Override
	public BusinessUnitId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BusinessUnit businessUnit) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(BusinessUnit businessUnit) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public BusinessUnit businessUnitOfId(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessUnit> businessUnitsOfDomain(BusinessDomainId businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	//BusinessUnitのコンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public BusinessUnit convertFromEntity(BusinessUnitEntity entity) {
		return new BusinessUnit(entity.getCompanyId(), entity.getBusinessDomainId(), entity.getBusinessUnitId());
	}

	@Override
	public List<BusinessUnit> businessUnitsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessUnit> businessUnitsOfCustomerCategory(CustomerCategoryId customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessUnit> businessUnitsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
