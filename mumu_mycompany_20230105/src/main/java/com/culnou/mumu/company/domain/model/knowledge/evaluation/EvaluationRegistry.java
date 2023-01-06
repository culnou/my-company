package com.culnou.mumu.company.domain.model.knowledge.evaluation;

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
public class EvaluationRegistry {
	
	@Qualifier("evaluationJpaRepository")
	@Autowired
	private EvaluationRepository repository;
	
	protected EvaluationId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new EvaluationId(str);
	}
	
	protected void save(Evaluation evaluation) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Evaluation>> violations = validator.validate(evaluation);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Errorになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(evaluation);
	}
	
	protected void remove(Evaluation evaluation) throws Exception{
		repository.removeEvaluation(evaluation);
	}
	
	protected Evaluation findEvaluationOfId(String evaluationId) throws Exception{
		return repository.findEvaluationOfId(new EvaluationId(evaluationId));
	}
	
	protected List<Evaluation> findEvaluationsOfTarget(String targetId) throws Exception{
		return repository.findEvaluationsOfTarget(targetId);
	}

	


}
