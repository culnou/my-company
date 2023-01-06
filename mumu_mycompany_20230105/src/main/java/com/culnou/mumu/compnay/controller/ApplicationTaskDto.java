package com.culnou.mumu.compnay.controller;

import java.util.Date;


import lombok.Data;

@Data
public class ApplicationTaskDto {
	
	private String applicationTaskId;
	private String companyId;
	private String applicationTypeId;
	private String applicationTypeName;
	private String applicationTaskName;
	private String applicationTaskDescription;
	private String taskId;
	private String taskName;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
