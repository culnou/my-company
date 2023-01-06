package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowService;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberRegistry;
import com.culnou.mumu.company.domain.model.project.Project;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.compnay.controller.WorkflowDto;

@Service
@Transactional
public class ProjectChecker {
	
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private MemberRegistry memberRegistry;
	@Autowired
	private ProjectService service;
	
	
	public String avarable(String projectId) throws Exception{
		String message = "OK";
		//ワークフローに割当てられている場合削除できない。
		List<WorkflowDto> workflows = workflowService.findWorkflowOfProject(projectId);
		if(workflows.size() > 0) {
			message = "already_assigned_to_workflow";
		}
		//メンバーが割当てられている場合削除できない。
		List<Member> members = memberRegistry.findMembersOfProject(projectId);
		if(members.size() > 0) {
			message = "already_assigned_to_member";
		}
		Project project = service.findProjectOfId(projectId);
		List<Goal> goals = project.getGoals();
		if(goals.size() > 0) {
			message = "already_assigned_to_goal";
		}
		List<Achievement> achievements = project.getAchievements();
		if(achievements.size() > 0) {
			message = "already_assigned_to_achievement";
		}
		return message;
	}
	

}
