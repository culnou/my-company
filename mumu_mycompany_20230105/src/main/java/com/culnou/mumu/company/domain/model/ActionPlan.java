package com.culnou.mumu.company.domain.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.project.ProjectId;

import lombok.Getter;
import lombok.Setter;
@Getter
public class ActionPlan {
	
	//必須
	private ActionPlanId actionPlanId;
	private CompanyId companyId;
	private BusinessProcessId businessProcessId;
	private String businessProcessName;
	private ActionPlanType actionPlanType;
	private String actionPlanName;
	@Setter
	private ProjectId projectId;
	//任意
	@Setter
	private BusinessUnitId businessUnitId;
	@Setter
	private String businessUnitName;
	@Setter
	private CustomerCategoryId customerCategoryId;
	@Setter
	private String customerCategoryName;
	@Setter
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	@Setter
	private List<AssociatedAction> associatedActions = new ArrayList<>();
	@Setter
	private String actionPlanDescription;
	@Setter
	private Url url;
	@Setter
	private Date startDate;
	@Setter
	private Date endDate;
	@Setter
	private String hypothesis;
	@Setter
	private String result;
	@Getter
	@Setter
	private List<Goal> goals = new ArrayList<>();
	@Getter
	@Setter
	private List<Achievement> achievements = new ArrayList<>();
	@Setter
	private Date createdAt;
	@Setter
	private Date updatedAt;
	@Setter
	private BusinessState businessState;
	
	protected void setActionPlanId(ActionPlanId actionPlanId) {
		if(actionPlanId == null) {
			throw new IllegalArgumentException("The actionPlanId may not be set to null.");
		}
		this.actionPlanId = actionPlanId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	public void setBusinessProcessId(BusinessProcessId businessProcessId) {
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		this.businessProcessId = businessProcessId;
	}
	
	public void setBusinessProcessName(String businessProcessName) {
		if(businessProcessName == null) {
			throw new IllegalArgumentException("The businessProcessName may not be set to null.");
		}
		if(businessProcessName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessProcessName.");
		}
		this.businessProcessName = businessProcessName;
	}
	public void setActionPlanType(ActionPlanType actionPlanType) {
		if(actionPlanType == null) {
			throw new IllegalArgumentException("The actionPlanType may not be set to null.");
		}
		this.actionPlanType = actionPlanType;
	}
	public void setActionPlanName(String actionPlanName) {
		if(actionPlanName == null) {
			throw new IllegalArgumentException("The actionPlanName may not be set to null.");
		}
		if(actionPlanName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a actionPlanName.");
		}
		this.actionPlanName = actionPlanName;
	}
	
	protected ActionPlan(ActionPlanId actionPlanId, CompanyId companyId, BusinessProcessId businessProcessId, String businessProcessName, ActionPlanType actionPlanType, String actionPlanName) {
		this.setActionPlanId(actionPlanId);
		this.setCompanyId(companyId);
		this.setBusinessProcessId(businessProcessId);
		this.setBusinessProcessName(businessProcessName);
		this.setActionPlanType(actionPlanType);
		this.setActionPlanName(actionPlanName);
	}

}
