package com.culnou.mumu.company.infrastructure.persistence.data.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.data.category.DataCategory;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryRepository;
import com.culnou.mumu.company.domain.model.data.type.DataClass;



@Service("dataCategoryJpaRepository")
@Transactional
public class DataCategoryJpaRepository implements DataCategoryRepository{
	@Autowired
	private DataCategoryJpaDataRepository repository;

	@Override
	public void save(DataCategory dataCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(dataCategory);

	}

	@Override
	public void remove(DataCategory dataCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(dataCategory);

	}

	@Override
	public DataCategory dataCategoryOfId(DataCategoryId dataCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByDataCategoryId(dataCategoryId);
	}

	@Override
	public List<DataCategory> dataCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataCategoriesOfCompany(companyId);
	}

	@Override
	public List<DataCategory> dataCategoriesOfDataType(String dataTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataCategoriesOfDataType(dataTypeId);
	}

	@Override
	public List<DataCategory> dataCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataCategoriesOfBusinessUnit(businessUnitId);
	}

	@Override
	public List<DataCategory> dataCategoriesOfBusinessUnitAndDataClass(BusinessUnitId businessUnitId, DataClass dataClass)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataCategoriesOfBusinessUnitAndDataClass(businessUnitId, dataClass);
	}


}
