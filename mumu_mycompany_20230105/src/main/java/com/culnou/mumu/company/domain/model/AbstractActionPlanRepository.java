package com.culnou.mumu.company.domain.model;


import com.culnou.mumu.company.infrastructure.persistence.ActionPlanEntity;

public abstract class AbstractActionPlanRepository implements ActionPlanRepository {
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public ActionPlan convertFromEntity(ActionPlanEntity entity) {
		ActionPlanId actionPlanId = entity.getActionPlanId();
		if(actionPlanId == null) {
			throw new IllegalArgumentException("The ActionPlanId of ActionPlanEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of ActionPlanEntity may not be set to null.");
		}
		BusinessProcessId businessProcessId = entity.getBusinessProcessId();
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId of ActionPlanEntity may not be set to null.");
		}
		String businessProcessName = entity.getBusinessProcessName();
		if(businessProcessName == null) {
			throw new IllegalArgumentException("The businessProcessName of ActionPlanEntity may not be set to null.");
		}
		ActionPlanType actionPlanType = entity.getActionPlanType();
		if(actionPlanType == null) {
			throw new IllegalArgumentException("The actionPlanType of ActionPlanEntity may not be set to null.");
		}
		String actionPlanName = entity.getActionPlanName();
		if(actionPlanName == null) {
			throw new IllegalArgumentException("The actionPlanName of ActionPlanEntity may not be set to null.");
		}
		return new ActionPlan(actionPlanId, companyId, businessProcessId, businessProcessName, actionPlanType, actionPlanName);
	}
}
