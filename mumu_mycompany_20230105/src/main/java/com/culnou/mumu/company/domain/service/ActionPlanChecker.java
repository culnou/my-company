package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.ActionPlanRepository;

import com.culnou.mumu.company.domain.model.AssociatedAction;
import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.compnay.controller.ProgramDto;
import com.culnou.mumu.compnay.controller.ProjectDto;
@Service
@Transactional
public class ActionPlanChecker {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProgramService programService;
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	public String avarable(String actionPlanId) throws Exception{
		String message = "OK";
		//プロジェクトやプログラムに割当てられている場合削除できない。
		List<ProjectDto> projects = projectService.findProjectsOfActionPlan(actionPlanId);
		if(projects.size() > 0) {
			message = "already_assigned_to_projects";
		}
		List<ProgramDto> programs = programService.findProgramsOfActionPlan(actionPlanId);
		if(programs.size() > 0) {
			message = "already_assigned_to_programs";
		}
		ActionPlan actionPlan = actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlanId));
		List<AssociatedAction> actions = actionPlan.getAssociatedActions();
		if(actions.size() > 0) {
			message = "already_assigned_to_action";
		}
		return message;
	}

}
