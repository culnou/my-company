package com.culnou.mumu.compnay.controller;


import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.JobType;
import com.culnou.mumu.company.domain.model.Role;


import lombok.Data;

@Data
public class JobDto {
	
	private String jobId;
	private String companyId;
	private JobType jobType;
	private String jobName;
	private List<Role> roles;
	private String businessDomainId;
	private String businessDomainName;
	private String jobDescription;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();

}
