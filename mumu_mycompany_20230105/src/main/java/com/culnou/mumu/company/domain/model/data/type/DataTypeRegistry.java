package com.culnou.mumu.company.domain.model.data.type;

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




@Service
@Transactional
public class DataTypeRegistry {
	
	@Qualifier("dataTypeJpaRepository")
	@Autowired
	private DataTypeRepository repository;
	
	@Autowired
	private RemovableDataTypeChecker checker;
	
	public DataTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new DataTypeId(str);
	}
	
	protected void save(DataType dataType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<DataType>> violations = validator.validate(dataType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(dataType);
	}
	
	protected void remove(DataType dataType) throws Exception{
		if(dataType == null) {
			throw new IllegalArgumentException("The_dataType_may_not_be_set_to_null");
		}
		//ビジネスロジック
		//データタイプが削除できるかドメインサービスを使って検証する。
		if(!checker.removable(dataType)) {
			throw new IllegalArgumentException("The_dataType_can_not_remove");
		}
		repository.remove(dataType);
	}
	
	protected DataType findDataTypeOfId(String dataTypeId) throws Exception{
		return repository.findDataTypeOfId(new DataTypeId(dataTypeId));
	}
	
	protected List<DataType> findDataTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findDataTypesOfBusinessDomain(businessDomainId);
	}
	
	protected List<DataType> findDataTypesOfBusinessDomainAndDataClass(String businessDomainId, DataClass dataClass) throws Exception{
		return repository.findDataTypesOfBusinessDomainAndDataClass(businessDomainId, dataClass);
	}
	
	protected List<DataType> findDataTypesOfCompany(String companyId) throws Exception{
		return repository.findDataTypesOfCompany(companyId);
	}

	

}
