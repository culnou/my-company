package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;

@Data
public class ApplicationFunctionDto {
	
	private String applicationFunctionId;
	private String companyId;
	private String applicationCategoryId;
	private String applicationCategoryName;
	private String applicationTaskId;
	private String applicationTaskName;
	private String applicationFunctionName;
	private String applicationFunctionDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
