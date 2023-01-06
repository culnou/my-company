package com.culnou.mumu.company.infrastructure.persistence.knowledge.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.knowledge.comment.Comment;
import com.culnou.mumu.company.domain.model.knowledge.comment.CommentId;
import com.culnou.mumu.company.domain.model.knowledge.comment.CommentRepository;

@Service("commentJpaRepository")
@Transactional
public class CommentJpaRepository implements CommentRepository {
	
	@Autowired
	private CommentJpaDataRepository repository;

	@Override
	public Comment findCommentOfId(CommentId commentId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByCommentId(commentId);
	}

	@Override
	public List<Comment> findCommentsOfTarget(String targetId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findCommentsOfTarget(targetId);
	}

	@Override
	public void save(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		repository.save(comment);

	}

	@Override
	public void removeComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(comment);

	}

}
