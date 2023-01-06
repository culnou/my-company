package com.culnou.mumu.company.domain.model.partner.category;

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
public class PartnerCategoryRegistry {
	@Qualifier("partnerCategoryJpaRepository")
	@Autowired
	private PartnerCategoryRepository repository;
	
	public PartnerCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new PartnerCategoryId(str);
	}
	
	public void save(PartnerCategory partnerCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<PartnerCategory>> violations = validator.validate(partnerCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(partnerCategory);
	}
	
	public void remove(PartnerCategory partnerCategory) throws Exception{
		repository.remove(partnerCategory);
	}
	
	public PartnerCategory findPartnerCategoryOfId(String partnerCategoryId) throws Exception{
		return repository.partnerCategoryOfId(new PartnerCategoryId(partnerCategoryId));
	}
	
	public List<PartnerCategory> findPartnerCategoriesOfPartnerType(String partnerTypeId) throws Exception{
		return repository.partnerCategoriesOfPartnerType(partnerTypeId);
	}
	
	public List<PartnerCategory> findPartnerCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.partnerCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<PartnerCategory> findPartnerCategoriesOfCompany(String companyId) throws Exception{
		return repository.partnerCategoriesOfCompany(new CompanyId(companyId));
	}

}
