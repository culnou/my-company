package com.culnou.mumu.company.domain.model.data.instance;

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

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;


@Service
@Transactional
public class DataRegistory {
	@Qualifier("dataJpaRepository")
	@Autowired
	private DataRepository repository;
	
	public DataId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new DataId(str);
	}
	
	public void save(Data data) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Data>> violations = validator.validate(data);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(data);
	}
	
	public void remove(Data data) throws Exception{
		repository.remove(data);
	}
	
	public Data findDataOfId(String dataId) throws Exception{
		return repository.dataOfId(new DataId(dataId));
	}
	
	public List<Data> findDataOfDataCategory(String dataCategoryId) throws Exception{
		return repository.dataOfDataCategory(new DataCategoryId(dataCategoryId));
	}
	
	public List<Data> findDataOfWork(String workId) throws Exception{
		return repository.dataOfWork(new WorkId(workId));
	}
	
	public List<Data> findDataOfCompany(String companyId) throws Exception{
		return repository.dataOfCompany(new CompanyId(companyId));
	}


}
