package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;

import com.culnou.mumu.company.domain.model.application.type.ApplicationClass;

import lombok.Data;

@Data
public class ApplicationTypeDto {
	private String applicationTypeId;
	private String companyId;
	private String applicationTypeName;
	private ApplicationClass applicationClass;
	private String applicationTypeDescription;
	private String businessDomainId;
	private String businessDomainName;
	private String jobId;
	private String jobName;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();
	private Date createdAt;
	private Date updatedAt;

}
