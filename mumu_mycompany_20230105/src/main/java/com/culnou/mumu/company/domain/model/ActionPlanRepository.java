package com.culnou.mumu.company.domain.model;

import java.util.List;

import com.culnou.mumu.company.domain.model.project.ProjectId;

public interface ActionPlanRepository {
	//識別子オブジェクトはリポジトリが生成する。
	public ActionPlanId nextIdentity() throws Exception;
	
	//コマンド
    public void save(ActionPlan actionPlan) throws Exception;
	
	public void remove(ActionPlan actionPlan) throws Exception;
	
	//クエリ
	public ActionPlan actionPlanOfId(ActionPlanId actionPlanId) throws Exception;
	
	public List<ActionPlan> actionPlansOfCompany(CompanyId companyId) throws Exception;
	
	public List<ActionPlan> actionPlansOfBusinessProcess(BusinessProcessId businessProcessId) throws Exception;
	
	//アクションがアクションプランに使用されているかどうか調べるときに必要。
	public List<ActionPlan> actionPlansOfAction(String actionId) throws Exception;
	
	public List<ActionPlan> actionPlansOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;
	
	public List<ActionPlan> actionPlansOfProject(ProjectId projectId) throws Exception;

}
