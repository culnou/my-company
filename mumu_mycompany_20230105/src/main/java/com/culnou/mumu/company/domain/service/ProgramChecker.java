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
import com.culnou.mumu.company.domain.model.program.Program;
import com.culnou.mumu.company.domain.model.program.ProgramService;

import com.culnou.mumu.compnay.controller.WorkflowDto;

@Service
@Transactional
public class ProgramChecker {
	
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private MemberRegistry memberRegistry;
	@Autowired
	private ProgramService service;
	
	
	public String avarable(String programId) throws Exception{
		String message = "OK";
		//ワークフローに割当てられている場合削除できない。
		List<WorkflowDto> workflows = workflowService.findWorkflowOfProgram(programId);
		if(workflows.size() > 0) {
			message = "already_assigned_to_workflow";
		}
		//メンバーが割当てられている場合削除できない。
		List<Member> members = memberRegistry.findMembersOfProgram(programId);
		if(members.size() > 0) {
			message = "already_assigned_to_member";
		}
		Program program = service.findProgramOfId(programId);
		List<Goal> goals = program.getGoals();
		if(goals.size() > 0) {
			message = "already_assigned_to_goal";
		}
		List<Achievement> achievements = program.getAchievements();
		if(achievements.size() > 0) {
			message = "already_assigned_to_achievement";
		}
		return message;
		
	}

}
