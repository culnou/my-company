package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.infrastructure.persistence.ActionPlanJpaRepository;
import com.culnou.mumu.compnay.controller.ActionDto;

@Service
@Transactional
public class ActionPlanService {
	
	@Autowired
	private ActionPlanJpaRepository repository;
	
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	//アクションをアクションプランに割り当てる。
	public void assignActionToActionPlan(ActionDto action, ActionPlanId actionPlanId) throws Exception{
		//AssociatedAction actionに変換
		AssociatedAction anAction = new AssociatedAction();
		anAction.setActionId(action.getActionId());
		anAction.setActionName(action.getActionName());
		anAction.setDepartmentId(action.getDepartmentId());
		anAction.setDepartmentName(action.getDepartmentName());
		ActionPlan actionPlan = repository.actionPlanOfId(actionPlanId);
		actionPlan.getAssociatedActions().add(anAction);
		repository.save(actionPlan);
	}
	
	//アクションをアクションプランから解放する。
	public void releaseActionFromActionPlan(ActionDto action, ActionPlanId actionPlanId) throws Exception{
		//AssociatedAction actionに変換
		AssociatedAction anAction = new AssociatedAction();
		anAction.setActionId(action.getActionId());
		anAction.setActionName(action.getActionName());
		anAction.setDepartmentId(action.getDepartmentId());
		anAction.setDepartmentName(action.getDepartmentName());
		ActionPlan actionPlan = repository.actionPlanOfId(actionPlanId);
		actionPlan.getAssociatedActions().remove(anAction);
		repository.save(actionPlan);
	}
	
	//アクションプランに必要なアクションを取得する。
	public List<Action> actionsOfActionPlan(ActionPlanId actionPlanId) throws Exception{
		//アクションプランの取得
		ActionPlan actionPlan = repository.actionPlanOfId(actionPlanId);
		//アクションプランに対応するビジネスプロセスの取得
		BusinessProcessId businessProcessId = actionPlan.getBusinessProcessId();
		BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(businessProcessId);
		//ビジネスプロセスに対応するアクションを取得
		List<AssociatedTask> associatedTasks = businessProcess.getAssociatedTasks();
		List<Action> actions = new ArrayList<>();
		for(AssociatedTask task : associatedTasks) {
			TaskId taskId = new TaskId(task.getTaskId());
			List<Action> acts = actionRepository.findActionsOfTask(taskId);
			actions.addAll(acts);
		}
		return actions;
	}


}
