package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BusinessProcess {
	
	//必須
	private BusinessProcessId businessProcessId;
	private CompanyId companyId;
	private BusinessProcessType businessProcessType;
	private String businessProcessName;
	//任意
	@Setter
	private BusinessDomainId businessDomainId;
	@Setter
	private String businessDomainName;
	@Setter
	private List<AssociatedTask> associatedTasks = new ArrayList<>();
	@Setter
	private String businessProcessDescription;
	@Setter
	private Url url;
	@Setter
	private BusinessProcessClass businessProcessClass;
	@Getter
	@Setter
	private List<Indicator> indicators = new ArrayList<>();
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	protected void setBusinessProcessId(BusinessProcessId businessProcessId) {
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		this.businessProcessId = businessProcessId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setBusinessProcessType(BusinessProcessType businessProcessType) {
		if(businessProcessType == null) {
			throw new IllegalArgumentException("The businessProcessType may not be set to null.");
		}
		this.businessProcessType = businessProcessType;
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
	
	//タスクの順番でソートする。
	public void sortAssociatedTasks(){
		Collections.sort(this.associatedTasks, new BusinessProcessTaskComparator());
	}
	
	protected BusinessProcess(BusinessProcessId businessProcessId, CompanyId companyId, BusinessProcessType businessProcessType, String businessProcessName) {
		this.setBusinessProcessId(businessProcessId);
		this.setCompanyId(companyId);
		this.setBusinessProcessType(businessProcessType);
		this.setBusinessProcessName(businessProcessName);
	}
    public ActionPlan defineActionPlan(ActionPlanId actionPlanId, ActionPlanType actionPlanType, String actionPlanName) {
    	if(actionPlanId == null) {
			throw new IllegalArgumentException("The actionPlanId may not be set to null.");
		}
    	if(actionPlanName == null) {
			throw new IllegalArgumentException("The actionPlanName may not be set to null.");
		}
    	return new ActionPlan(actionPlanId, this.companyId, this.businessProcessId, this.businessProcessName, actionPlanType, actionPlanName);
    }
    
    
}
