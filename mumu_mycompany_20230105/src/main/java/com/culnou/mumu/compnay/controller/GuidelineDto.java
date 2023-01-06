package com.culnou.mumu.compnay.controller;

import java.util.Date;


import com.culnou.mumu.company.domain.model.knowledge.guideline.GuidelineTarget;

import lombok.Data;

@Data
public class GuidelineDto {
	
	private String guidelineId;
	private String companyId;
	private String targetId;
	private GuidelineTarget guidelineTarget;
	private String guidelineName;
	private String guidelineDescription;
	private String version;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
