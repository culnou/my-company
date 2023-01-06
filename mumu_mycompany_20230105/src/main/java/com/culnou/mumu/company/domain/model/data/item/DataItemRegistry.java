package com.culnou.mumu.company.domain.model.data.item;

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
public class DataItemRegistry {
	
	@Qualifier("dataItemJpaRepository")
	@Autowired
	private DataItemRepository repository;
	
	public DataItemId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new DataItemId(str);
	}
	
	protected void save(DataItem dataItem) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<DataItem>> violations = validator.validate(dataItem);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(dataItem);
	}
	
	protected void remove(DataItem dataItem) throws Exception{
		repository.remove(dataItem);
	}
	
	protected DataItem findDataItemOfId(String dataItemId) throws Exception{
		return repository.findDataItemOfId(new DataItemId(dataItemId));
	}
	
	protected List<DataItem> findDataItemsOfDataType(String dataTypeId) throws Exception{
		return repository.findDataItemsOfDataType(dataTypeId);
	}
	
	protected List<DataItem> findDataItemsOfCompany(String companyId) throws Exception{
		return repository.findDataItemsOfCompany(companyId);
	}



}
