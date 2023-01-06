package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.activity.work.WorkService;
import com.culnou.mumu.compnay.controller.WorkDto;


@Service
@Transactional
public class WorkflowChecker {
	
	@Autowired
	private WorkService workService;
	
	public String avarable(String workflowId) throws Exception{
		String message = "OK";
		//ワークフローに割当てられている場合削除できない。
		List<WorkDto> todoWorks = workService.findTodoWorks(workflowId);
		if(todoWorks.size() > 0) {
			message = "already_assigned_to_work";
		}
		List<WorkDto> doingWorks = workService.findDoingWorks(workflowId);
		if(doingWorks.size() > 0) {
			message = "already_assigned_to_work";
		}
		List<WorkDto> doneWorks = workService.findDoneWorks(workflowId);
		if(doneWorks.size() > 0) {
			message = "already_assigned_to_work";
		}
		return message;
	}

}
