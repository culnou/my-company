package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.ApplicationApplicationService;
import com.culnou.mumu.company.application.ApplicationFunctionApplicationService;

import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.compnay.controller.ApplicationDto;
import com.culnou.mumu.compnay.controller.ApplicationFunctionDto;
import com.culnou.mumu.compnay.controller.ProgramDto;
import com.culnou.mumu.compnay.controller.ProjectDto;

@Service
@Transactional
public class ApplicationCategoryChecker {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProgramService programService;
	@Autowired
	private ApplicationFunctionApplicationService applicationFunctionService;
	@Autowired
	private ApplicationApplicationService applicationService;
	
	public String avarable(String applicationCategoryId) throws Exception{
		String message = "OK";
		//プロジェクトやプログラムに割当てられている場合削除できない。
		List<ProjectDto> projects = projectService.findProjectsOfApplicationCategory(applicationCategoryId);
		if(projects.size() > 0) {
			message = "already_assigned_to_projects";
		}
		List<ProgramDto> programs = programService.findProgramsOfApplicationCategory(applicationCategoryId);
		if(programs.size() > 0) {
			message = "already_assigned_to_programs";
		}
		List<ApplicationFunctionDto> applicationFunctions = applicationFunctionService.findApplicationFunctionsOfApplicationCategory(applicationCategoryId);
		if(applicationFunctions.size() > 0) {
			message = "already_assigned_to_applicationFunction";
		}
		List<ApplicationDto> applications = applicationService.findApplicationsOfApplicationCategory(applicationCategoryId);
		if(applications.size() > 0) {
			message = "already_assigned_to_application";
		}
		return message;
	}

}
