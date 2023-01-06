package com.culnou.mumu.compnay.controller;

import java.util.Date;


import com.culnou.mumu.company.domain.model.knowledge.result.ResultTarget;

import lombok.Data;

@Data
public class ResultEntityDto {
	
	private String resultId;
	private String companyId;
	private String targetId;
	private ResultTarget resultTarget;
	private String resultName;
	private String resultDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;
	

}
