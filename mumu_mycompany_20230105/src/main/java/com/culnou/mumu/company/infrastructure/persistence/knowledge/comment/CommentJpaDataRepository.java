package com.culnou.mumu.company.infrastructure.persistence.knowledge.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.culnou.mumu.company.domain.model.knowledge.comment.Comment;
import com.culnou.mumu.company.domain.model.knowledge.comment.CommentId;


public interface CommentJpaDataRepository extends JpaRepository<Comment, CommentId> {
	
	public Comment findByCommentId(CommentId commentId);
	
	@Query(value = "select comment from Comment comment where comment.targetId = :TargetId")
	public List<Comment> findCommentsOfTarget(@Param("TargetId") String targetId);

}
