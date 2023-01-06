package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Department {
	
	//必須
	private DepartmentId departmentId;
	private CompanyId companyId;
	private DepartmentType departmentType;
	private JobId jobId;
	private String jobName;
	private String departmentName;
	//任意
	@Setter
	private BusinessUnitId businessUnitId;
	@Setter
	private String businessUnitName;
	@Setter
	private String departmentDescription;
	@Setter
	private Url url;
	@Getter
	@Setter
	private List<Goal> goals = new ArrayList<>();
	@Getter
	@Setter
	private List<Achievement> achievements = new ArrayList<>();
	@Getter
	@Setter
	private Date startDate;
	@Getter
	@Setter
	private Date endDate;
	@Getter
	@Setter
	private BusinessState businessState;
	@Getter
	@Setter
	private List<AssociatedApplicationCategory> associatedApplicationCategories = new ArrayList<>();
	@Getter
	@Setter
	private List<AssociatedPartnerCategory> associatedPartnerCategories = new ArrayList<>();
	
	@Getter
	@Setter
	private Date createdAt;
	@Getter
	@Setter
	private Date updatedAt;
	
	protected void setDepartmentId(DepartmentId departmentId) {
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId may not be set to null.");
		}
		this.departmentId = departmentId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setDepartmentType(DepartmentType departmentType) {
		if(departmentType == null) {
			throw new IllegalArgumentException("The departmentType may not be set to null.");
		}
		this.departmentType = departmentType;
	}
	
	
	public void setJobId(JobId jobId) {
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		this.jobId =jobId;
	}
	
	public void setJobName(String jobName) {
		if(jobName == null) {
			throw new IllegalArgumentException("The jobName may not be set to null.");
		}
		if(jobName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a jobName.");
		}
		this.jobName = jobName;
	}
	
	public void setDepartmentName(String departmentName) {
		if(departmentName == null) {
			throw new IllegalArgumentException("The departmentName may not be set to null.");
		}
		if(departmentName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a departmentName.");
		}
		this.departmentName = departmentName;
	}
	
	protected Department(DepartmentId departmentId, CompanyId companyId, DepartmentType departmentType, JobId jobId, String jobName, String departmentName) {
		this.setDepartmentId(departmentId);
		this.setCompanyId(companyId);
		this.setDepartmentType(departmentType);
		this.setJobId(jobId);
		this.setJobName(jobName);
		this.setDepartmentName(departmentName);
	}
	
	public Action defineAction(ActionId actionId, TaskId taskId, String taskName, String actionName) {
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		if(taskName == null) {
			throw new IllegalArgumentException("The taskName may not be set to null.");
		}
		if(actionName == null) {
			throw new IllegalArgumentException("The actionName may not be set to null.");
		}
		return new Action(actionId, this.companyId, taskId, taskName, this.departmentId, this.departmentName, actionName);
		
	}
	
	//ビジネスロジック
	//アプリケーションを部門に割当てる。
	public void assignApplication(AssociatedApplicationCategory application) throws Exception{
		if(application == null) {
			throw new IllegalArgumentException("The_application_may_not_be_set_to_null");
		}
		if(this.associatedApplicationCategories.contains(application)) {
			throw new IllegalArgumentException("The_application_is_already_exist");
		}
		this.getAssociatedApplicationCategories().add(application);
	}
	//アプリケーションを部門から解除する。
	public void releaseApplication(AssociatedApplicationCategory application) throws Exception{
		if(application == null) {
			throw new IllegalArgumentException("The_application_may_not_be_set_to_null");
		}
		if(!this.associatedApplicationCategories.contains(application)) {
			throw new IllegalArgumentException("The_application_dose_not_exist");
		}
		this.getAssociatedApplicationCategories().remove(application);
	}
	
	//パートナーを部門に割当てる。
	public void assignPartner(AssociatedPartnerCategory partner) throws Exception{
		if(partner == null) {
			throw new IllegalArgumentException("The_partner_may_not_be_set_to_null");
		}
		if(this.associatedPartnerCategories.contains(partner)) {
			throw new IllegalArgumentException("The_partner_is_already_exist");
		}
		this.getAssociatedPartnerCategories().add(partner);
	}
	//パートナーを部門から解除する。
	public void releasePartner(AssociatedPartnerCategory partner) throws Exception{
		if(partner == null) {
			throw new IllegalArgumentException("The_partner_may_not_be_set_to_null");
		}
		if(!this.associatedPartnerCategories.contains(partner)) {
			throw new IllegalArgumentException("The_partner_dose_not_exist");
		}
		this.getAssociatedPartnerCategories().remove(partner);
	}
	

}
