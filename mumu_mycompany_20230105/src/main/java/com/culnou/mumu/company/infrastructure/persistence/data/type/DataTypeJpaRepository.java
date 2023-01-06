package com.culnou.mumu.company.infrastructure.persistence.data.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.model.data.type.DataType;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;
import com.culnou.mumu.company.domain.model.data.type.DataTypeRepository;

@Service("dataTypeJpaRepository")
@Transactional
public class DataTypeJpaRepository implements DataTypeRepository{
	
	@Autowired
	private DataTypeJpaDataRepository repository;

	@Override
	public DataType findDataTypeOfId(DataTypeId dataTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findBydataTypeId(dataTypeId);
	}

	@Override
	public List<DataType> findDataTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDatasTypesOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<DataType> findDataTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}
	
	public List<DataType> findDataTypesOfBusinessDomainAndDataClass(String businessDomainId, DataClass dataClass) throws Exception{
		return repository.findDataTypesOfBusinessDomainAndDataClass(new BusinessDomainId(businessDomainId), dataClass);
	}

	@Override
	public void save(DataType dataType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(dataType);

	}

	@Override
	public void remove(DataType dataType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(dataType);

	}

	@Override
	public List<DataType> findChildrenOfParent(String parentId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findChildrenOfParent(parentId);
	}


}
