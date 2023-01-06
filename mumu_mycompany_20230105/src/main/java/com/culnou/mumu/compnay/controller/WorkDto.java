package com.culnou.mumu.compnay.controller;

import java.util.Date;

import com.culnou.mumu.company.domain.model.activity.work.WorkType;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;

@Data
public class WorkDto {
	
	private String workId;
	private String companyId;
	private String memberId;
	private String memberName;
	private String workflowId;
	private String actionId;
	private WorkType workType;
	private String workName;
	private String workDescription;
	private Date startDate;
	private Date endDate;
	private String url;
	private BusinessState businessState;
	private long expendedTime;
	private String applicationUrl;
	private String personUrl;
	private String averageScore;
	
	

}
