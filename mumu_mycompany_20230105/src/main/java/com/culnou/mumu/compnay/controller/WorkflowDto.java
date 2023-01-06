package com.culnou.mumu.compnay.controller;

import java.util.Date;

import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;

@Data
public class WorkflowDto {
	
	private String workflowId;
	private String companyId;
	private String actionPlanId;
	private String projectId;
	private String programId;
	private String workflowName;
	private String workflowDescription;
	private String hypothesis;
	private String result;
	private Date startDate;
	private Date endDate;
	private BusinessState businessState;
	private Date createdAt;
	private Date updatedAt;
	String url;

}
