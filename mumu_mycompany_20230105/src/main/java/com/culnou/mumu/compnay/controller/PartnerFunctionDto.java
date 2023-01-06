package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;

@Data
public class PartnerFunctionDto {
	private String partnerFunctionId;
	private String companyId;
	private String partnerCategoryId;
	private String partnerCategoryName;
	private String taskId;
	private String taskName;
	private String partnerFunctionName;
	private String partnerFunctionDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
