package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.ActionPlanType;
import com.culnou.mumu.company.domain.model.AssociatedAction;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;


@Data
public class ActionPlanDto {
	
	//必須
	private String actionPlanId;
	private String companyId;
	private String businessProcessId;
	private String businessProcessName;
	private ActionPlanType actionPlanType;
	private String actionPlanName;
	//任意
	private String businessUnitId;
	private String businessUnitName;
	private String hypothesis;
	private String result;
	private String customerCategoryId;
	private String customerCategoryName;
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	private List<AssociatedAction> associatedActions = new ArrayList<>();
	private String actionPlanDescription;
	private String projectId;
	private String url;
	private Date startDate;
	private Date endDate;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private BusinessState businessState;

}
