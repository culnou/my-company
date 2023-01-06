package com.culnou.mumu.compnay.controller;

import lombok.Data;

@Data
public class ActionDto {
	
	//必須
	private String actionId;
	private String companyId;
	private String taskId;
	private String taskName;
	private String departmentId;
	private String departmentName;
	private String actionName;
	
	//任意
	private String actionDescription;
	private String url;
	private String applicationUrl;
	private String personUrl;
	private String applicationProductId;
	private String personProductId;
	private String applicationCategoryId;
	private String partnerCategoryId;


}
