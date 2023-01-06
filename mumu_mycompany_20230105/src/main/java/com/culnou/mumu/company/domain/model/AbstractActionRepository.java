package com.culnou.mumu.company.domain.model;

import com.culnou.mumu.company.infrastructure.persistence.ActionEntity;


public abstract class AbstractActionRepository implements ActionRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public Action convertFromEntity(ActionEntity entity) {
		ActionId actionId = entity.getActionId();
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId of ActionEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of ActionEntity may not be set to null.");
		}
		TaskId taskId = entity.getTaskId();
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId of ActionEntity may not be set to null.");
		}
		String taskName = entity.getTaskName();
		if(taskName == null) {
			throw new IllegalArgumentException("The taskName of ActionEntity may not be set to null.");
		}
		DepartmentId departmentId = entity.getDepartmentId();
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId of ActionEntity may not be set to null.");
		}
		String departmentName = entity.getDepartmentName();
		if(departmentName == null) {
			throw new IllegalArgumentException("The departmentName of ActionEntity may not be set to null.");
		}
		String actionName = entity.getActionName();
		if(actionName == null) {
			throw new IllegalArgumentException("The actionName of ActionEntity may not be set to null.");
		}
		return new Action(actionId, companyId, taskId, taskName, departmentId, departmentName, actionName);
	}

}
