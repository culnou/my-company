package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;

@Data
public class ApplicationDto {
	
	private String applicationId;
	private String applicationName;
	private String applicationDescription;
	private String companyId;
	private String businessUnitId;
	private String itId;
	private String applicationCategoryId;
	private String applicationCategoryName;
	private String url;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;

}
