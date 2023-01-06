package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.infrastructure.persistence.BusinessProcessJpaRepository;
import com.culnou.mumu.compnay.controller.TaskDto;

@Service
@Transactional
public class BusinessProcessService {
	
	@Autowired
	private BusinessProcessJpaRepository repository;
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	//ビジネスプロセスにタスクを割り当てる。
	public void assignTaskToBusinessProcess(TaskDto task, BusinessProcessId businessProcessId) throws Exception{
		//AssociatedTask taskに変換
		AssociatedTask aTask = new AssociatedTask();
		aTask.setTaskOrder(task.getTaskOrder());
		aTask.setTaskId(task.getTaskId());
		aTask.setTaskName(task.getTaskName());
		aTask.setJobId(task.getJobId());		
		aTask.setJobName(task.getJobName());
		BusinessProcess businessProcess = repository.businessProcessOfId(businessProcessId);
		List<AssociatedTask> tasks = businessProcess.getAssociatedTasks();
		boolean check = true;
		for(AssociatedTask tsk : tasks) {
			if((tsk.getTaskId().equals(task.getTaskId())) && (tsk.getTaskOrder() == task.getTaskOrder())) {
				check = false;
				break;
			}
		}
		if(check) {
			businessProcess.getAssociatedTasks().add(aTask);
		}
		
		repository.save(businessProcess);
	}
	
	public void replaceTaskOrder(TaskDto source, TaskDto target, BusinessProcessId businessProcessId) throws Exception{
		
		BusinessProcess businessProcess = repository.businessProcessOfId(businessProcessId);
		List<AssociatedTask> tasks = businessProcess.getAssociatedTasks();
		for(AssociatedTask task : tasks) {
			if((source.getTaskId().equals(task.getTaskId())) && (source.getTaskOrder() == task.getTaskOrder())) {
				task.setTaskOrder(target.getTaskOrder());
			}
		}
		repository.save(businessProcess);
		
	}
	
	
		
	//ビジネスプロセスからタスクを解放する。
	public void releaseTaskFromBusinessProcess(TaskDto task, BusinessProcessId businessProcessId) throws Exception{
		//AssociatedTask taskに変換
		AssociatedTask aTask = new AssociatedTask();
		aTask.setTaskOrder(task.getTaskOrder());
		aTask.setTaskId(task.getTaskId());
		aTask.setTaskName(task.getTaskName());
		aTask.setJobId(task.getJobId());		
		aTask.setJobName(task.getJobName());
		BusinessProcess businessProcess = repository.businessProcessOfId(businessProcessId);
		businessProcess.getAssociatedTasks().remove(aTask);
		repository.save(businessProcess);
	}
	
	public boolean isUsed(BusinessProcessId businessProcessId) throws Exception{
		boolean result = false;
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfBusinessProcess(businessProcessId);
		if(actionPlans.size() > 0) {
			result = true;
		}
		return result;
	}
	
	public boolean indicatorIsUsed(BusinessProcessId businessProcessId, Indicator indicator) throws Exception{
		boolean result = false;
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfBusinessProcess(businessProcessId);
		for(ActionPlan actionPlan : actionPlans) {
			for(Goal goal : actionPlan.getGoals()) {
				if(goal.getIndicator().equals(indicator)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	
		

}
