package com.culnou.mumu.company.domain.model.knowledge.guideline;

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
public class GuidelineRegistry {
	
	@Qualifier("guidelineJpaRepository")
	@Autowired
	private GuidelineRepository repository;
	
	protected GuidelineId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new GuidelineId(str);
	}
	
	protected void save(Guideline guideline) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Guideline>> violations = validator.validate(guideline);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Errorになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(guideline);
	}
	
	protected void remove(Guideline guideline) throws Exception{
		repository.removeGuideline(guideline);
	}
	
	protected Guideline findGuidelineOfId(String guidelineId) throws Exception{
		return repository.finGuidelineOfId(new GuidelineId(guidelineId));
	}
	
	protected List<Guideline> findGuidelinesOfTarget(String targetId) throws Exception{
		return repository.findGuidelinesOfTarget(targetId);
	}


}
