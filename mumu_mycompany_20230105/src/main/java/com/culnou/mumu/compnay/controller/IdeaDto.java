package com.culnou.mumu.compnay.controller;

import java.util.Date;

import com.culnou.mumu.company.domain.model.knowledge.idea.IdeaTarget;

import lombok.Data;

@Data
public class IdeaDto {
	private String ideaId;
	private String companyId;
	private String memberId;
	private String firstName;
	private String lastName;
	private String targetId;
	private IdeaTarget ideaTarget;
	private String ideaName;
	private String ideaDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;
	private String averageScore;

}
