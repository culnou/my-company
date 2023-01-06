package com.culnou.mumu.company.domain.model.member.category;

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
public class MemberCategoryRegistry {
	@Qualifier("memberCategoryJpaRepository")
	@Autowired
	private MemberCategoryRepository repository;
	
	public MemberCategoryId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new MemberCategoryId(str);
	}
	
	public void save(MemberCategory memberCategory) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<MemberCategory>> violations = validator.validate(memberCategory);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(memberCategory);
	}
	
	public void remove(MemberCategory memberCategory) throws Exception{
		repository.remove(memberCategory);
	}
	
	public MemberCategory findMemberCategoryOfId(String memberCategoryId) throws Exception{
		return repository.memberCategoryOfId(new MemberCategoryId(memberCategoryId));
	}
	
	public List<MemberCategory> findMemberCategoriesOfMemberType(String memberTypeId) throws Exception{
		return repository.memberCategoriesOfMemberType(memberTypeId);
	}
	
	public List<MemberCategory> findMemberCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		return repository.memberCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
	}
	
	public List<MemberCategory> findMemberCategoriesOfCompany(String companyId) throws Exception{
		return repository.memberCategoriesOfCompany(new CompanyId(companyId));
	}
	

}
