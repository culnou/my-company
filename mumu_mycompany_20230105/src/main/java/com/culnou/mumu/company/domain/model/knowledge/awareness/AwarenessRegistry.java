package com.culnou.mumu.company.domain.model.knowledge.awareness;

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

import com.culnou.mumu.company.domain.model.member.MemberId;




@Service
@Transactional
public class AwarenessRegistry {
	
	@Qualifier("awarenessJpaRepository")
	@Autowired
	private AwarenessRepository repository;
	
	protected AwarenessId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new AwarenessId(str);
	}
	
	protected void save(Awareness awareness) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Awareness>> violations = validator.validate(awareness);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(awareness);
	}
	
	protected void remove(Awareness awareness) throws Exception{
		repository.removeAwareness(awareness);
	}
	
	protected Awareness findAwarenessOfId(String awarenessId) throws Exception{
		return repository.findAwarenessOfId(new AwarenessId(awarenessId));
	}
	
	protected List<Awareness> findAwarenessesOfMember(MemberId memberId) throws Exception{
		return repository.findAwarenessesOfMember(memberId);
	}

}
