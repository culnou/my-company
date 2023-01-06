package com.culnou.mumu.company.domain.model;

import java.util.List;




public interface ActionRepository {
	//識別子オブジェクトはリポジトリが生成する。
	public ActionId nextIdentity() throws Exception;
	
	//コマンド
    public void save(Action action) throws Exception;
	
	public void remove(Action action) throws Exception;
	
	//クエリ
	public Action actionOfId(ActionId actionId) throws Exception;
	
	public List<Action> actionsOfCompany(CompanyId companyId) throws Exception;
	
	public List<Action> actionsOfDepartment(DepartmentId departmentId) throws Exception;
	
	public List<Action> findActionsOfTask(TaskId taskId);
	
	public List<Action> findActionsOfApplicationFunction(String applicationFunctionId) throws Exception;
	
	public List<Action> findActionsOfPartnerFunction(String partnerFunctionId) throws Exception;



}
