package com.culnou.mumu.compnay.controller;

import java.util.Date;


import com.culnou.mumu.company.domain.model.knowledge.hypothesis.HypothesisTarget;

import lombok.Data;

@Data
public class HypothesisDto {
	
	private String hypothesisId;
	private String companyId;
	private String targetId;
	private HypothesisTarget hypothesisTarget;
	private String hypothesisName;
	private String hypothesisDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
