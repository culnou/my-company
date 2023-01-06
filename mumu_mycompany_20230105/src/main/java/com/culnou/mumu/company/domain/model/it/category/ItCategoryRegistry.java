package com.culnou.mumu.company.domain.model.it.category;

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



@Service
@Transactional
public class ItCategoryRegistry {
	@Qualifier("itCategoryJpaRepository")
	@Autowired
	private ItCategoryRepository repository;
	
	public ItCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ItCategoryId(str);
	}
	
	public void save(ItCategory itCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ItCategory>> violations = validator.validate(itCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(itCategory);
	}
	
	public void remove(ItCategory itCategory) throws Exception{
		repository.remove(itCategory);
	}
	
	public ItCategory findItCategoryOfId(String itCategoryId) throws Exception{
		return repository.itCategoryOfId(new ItCategoryId(itCategoryId));
	}
	
	public List<ItCategory> findItCategoriesOfItType(String itTypeId) throws Exception{
		return repository.itCategoriesOfItType(itTypeId);
	}
	
	public List<ItCategory> findItCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.itCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<ItCategory> findItCategoriesOfCompany(String companyId) throws Exception{
		return repository.itCategoriesOfCompany(new CompanyId(companyId));
	}

}
