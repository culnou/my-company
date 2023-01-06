package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.DepartmentType;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;

import lombok.Data;


@Data
public class DepartmentDto {
	
	//必須
	private String departmentId;
	private String companyId;
	private DepartmentType departmentType;
	private String jobId;
	private String jobName;
	private String departmentName;
	//任意
	private String businessUnitId;
	private String businessUnitName;
	private String departmentDescription;
	private String url;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private List<AssociatedApplicationCategory> associatedApplicationCategories = new ArrayList<>();
	private List<AssociatedPartnerCategory> associatedPartnerCategories = new ArrayList<>();
	private Date startDate;
	private Date endDate;
	private BusinessState businessState;

}
