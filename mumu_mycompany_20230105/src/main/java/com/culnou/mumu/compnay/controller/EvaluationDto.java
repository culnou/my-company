package com.culnou.mumu.compnay.controller;

import java.util.Date;


import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationTarget;

import lombok.Data;


@Data
public class EvaluationDto {
	
	private String evaluationId;
	private String companyId;
	private String targetId;
	private EvaluationTarget evaluationTarget;
	private String evaluationName;
	private int evaluationNumber;
	private String evaluationDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
