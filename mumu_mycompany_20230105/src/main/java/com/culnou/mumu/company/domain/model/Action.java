package com.culnou.mumu.company.domain.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Action {
	
	//必須
	private ActionId actionId;
	private CompanyId companyId;
	private TaskId taskId;
	private String taskName;
	private DepartmentId departmentId;
	private String departmentName;
	private String actionName;
	
	
	//任意
	@Setter
	private String actionDescription;
	@Setter
	private Url url;
	//アプリケーションサービスのURL
	@Setter
	private String applicationUrl;
	@Setter
	private String applicationProductId;
	//パーソナルサービスのURL
	@Setter
	private String personUrl;
	@Setter
	private String personProductId;
	//アプリケーション機能のIDを設定する。
	@Setter
	private String applicationCategoryId;
	//パートナー機能のIDを設定する。
	@Setter
	private String partnerCategoryId;
	@Setter
	private Date createdAt;
	@Setter
	private Date updatedAt;
	
	protected void setActionId(ActionId actionId) {
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		this.actionId = actionId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setTaskId(TaskId taskId) {
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		this.taskId = taskId;
	}
	
	public void setTaskName(String taskName) {
		if(taskName == null) {
			throw new IllegalArgumentException("The taskName may not be set to null.");
		}
		if(taskName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a taskName.");
		}
		this.taskName = taskName;
	}
	
	public void setDepartmentId(DepartmentId departmentId) {
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId may not be set to null.");
		}
		this.departmentId = departmentId;
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
	public void setActionName(String actionName) {
		if(actionName == null) {
			throw new IllegalArgumentException("The actionName may not be set to null.");
		}
		if(actionName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a actionName.");
		}
		this.actionName = actionName;
	}
	
	protected Action(ActionId actionId, CompanyId companyId, TaskId taskId, String taskName, DepartmentId departmentId, String departmentName, String actionName) {
		this.setActionId(actionId);
		this.setCompanyId(companyId);
		this.setTaskId(taskId);
		this.setTaskName(taskName);
		this.setDepartmentId(departmentId);
		this.setDepartmentName(departmentName);
		this.setActionName(actionName);
	}

}
