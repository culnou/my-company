package com.culnou.mumu.company.domain.model.knowledge.comment;

import java.util.List;



public interface CommentRepository {
	
    public Comment findCommentOfId(CommentId commentId) throws Exception;
	
	public List<Comment> findCommentsOfTarget(String targetId) throws Exception;
	
	public void save(Comment comment) throws Exception;
	
	public void removeComment(Comment comment) throws Exception;

}
