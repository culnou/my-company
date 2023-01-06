package com.culnou.mumu.company.domain.model.knowledge.comment;

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
public class CommentRegistry {
	
	@Qualifier("commentJpaRepository")
	@Autowired
	private CommentRepository repository;
	
	protected CommentId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new CommentId(str);
	}
	
	protected void save(Comment comment) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(comment);
	}
	
	protected void remove(Comment comment) throws Exception{
		repository.removeComment(comment);
	}
	
	protected Comment findCommentOfId(String commentId) throws Exception{
		return repository.findCommentOfId(new CommentId(commentId));
	}
	
	protected List<Comment> findCommentsOfTarget(String targetId) throws Exception{
		return repository.findCommentsOfTarget(targetId);
	}

	
	
	

}
