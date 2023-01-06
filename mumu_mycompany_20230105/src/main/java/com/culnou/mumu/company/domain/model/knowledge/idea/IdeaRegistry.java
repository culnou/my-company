package com.culnou.mumu.company.domain.model.knowledge.idea;

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
public class IdeaRegistry {
	@Qualifier("ideaJpaRepository")
	@Autowired
	private IdeaRepository repository;
	
	protected IdeaId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new IdeaId(str);
	}
	
	protected void save(Idea idea) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Idea>> violations = validator.validate(idea);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Errorになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(idea);
	}
	
	protected void remove(Idea idea) throws Exception{
		repository.removeIdea(idea);
	}
	
	protected Idea findIdeaOfId(String ideaId) throws Exception{
		return repository.finIdeaOfId(new IdeaId(ideaId));
	}
	
	protected List<Idea> findIdeasOfTarget(String targetId) throws Exception{
		return repository.findIdeasOfTarget(targetId);
	}


}
