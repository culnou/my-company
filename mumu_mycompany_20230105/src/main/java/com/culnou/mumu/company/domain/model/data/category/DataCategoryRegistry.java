package com.culnou.mumu.company.domain.model.data.category;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.type.DataClass;


@Service
@Transactional
public class DataCategoryRegistry {
	@Qualifier("dataCategoryJpaRepository")
	@Autowired
	private DataCategoryRepository repository;
	
	public DataCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new DataCategoryId(str);
	}
	
	public void save(DataCategory dataCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<DataCategory>> violations = validator.validate(dataCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(dataCategory);
	}
	
	public void remove(DataCategory dataCategory) throws Exception{
		repository.remove(dataCategory);
	}
	
	public DataCategory findDataCategoryOfId(String dataCategoryId) throws Exception{
		return repository.dataCategoryOfId(new DataCategoryId(dataCategoryId));
	}
	
	public List<DataCategory> findDataCategoriesOfDataType(String dataTypeId) throws Exception{
		return repository.dataCategoriesOfDataType(dataTypeId);
	}
	
	public List<DataCategory> findDataCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.dataCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<DataCategory> findDataCategoriesOfBusinessUnitAndDataClass(String businessUnitId, DataClass dataClass) throws Exception{
		return repository.dataCategoriesOfBusinessUnitAndDataClass(new BusinessUnitId(businessUnitId), dataClass);
	}
	
	public List<DataCategory> findDataCategoriesOfCompany(String companyId) throws Exception{
		return repository.dataCategoriesOfCompany(new CompanyId(companyId));
	}
	
	


}
