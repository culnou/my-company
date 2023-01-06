package com.culnou.mumu.compnay.controller;

import java.util.Date;

import com.culnou.mumu.company.domain.model.knowledge.comment.CommentTarget;

import lombok.Data;

@Data
public class CommentDto {
	
	private String commentId;
	private String companyId;
	private String targetId;
	private CommentTarget commentTarget;
	private String commentName;
	private String commentDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
