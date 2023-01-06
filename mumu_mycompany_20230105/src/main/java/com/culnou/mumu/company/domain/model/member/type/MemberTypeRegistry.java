package com.culnou.mumu.company.domain.model.member.type;

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
public class MemberTypeRegistry {
	
	@Qualifier("memberTypeJpaRepository")
	@Autowired
	private MemberTypeRepository repository;
	
	public MemberTypeId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new MemberTypeId(str);
	}
	
	public void save(MemberType memberType) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<MemberType>> violations = validator.validate(memberType);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		
		repository.save(memberType);
	}
	
	public void remove(MemberType memberType) throws Exception{
		repository.remove(memberType);
	}
	
	public MemberType findMemberTypeOfId(String memberTypeId) throws Exception{
		return repository.findMemberTypeOfId(new MemberTypeId(memberTypeId));
	}
	
	public List<MemberType> findMemberTypesOfBusinessDomain(String businessDomainId) throws Exception{
		return repository.findMemberTypesOfBusinessDomain(businessDomainId);
	}
	
	public List<MemberType> findMemberTypesOfCompany(String companyId) throws Exception{
		return repository.findMemberTypesOfCompany(companyId);
	}
	
	protected List<MemberType> findMemberTypesOfJob(String jobId) throws Exception{
		return repository.findMemberTypesOfJob(jobId);
	}

}
