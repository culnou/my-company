package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.compnay.controller.ProgramDto;
import com.culnou.mumu.compnay.controller.ProjectDto;
@Service
@Transactional
public class FinancialAssetCategoryChecker {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProgramService programService;
	
	public String avarable(String financialAssetCategoryId) throws Exception{
		String message = "OK";
		//プロジェクトやプログラムに割当てられている場合削除できない。
		List<ProjectDto> projects = projectService.findProjectsOfFinancialAssetCategory(financialAssetCategoryId);
		if(projects.size() > 0) {
			message = "already_assigned_to_projects";
		}
		List<ProgramDto> programs = programService.findProgramsOfFinancialAssetCategory(financialAssetCategoryId);
		if(programs.size() > 0) {
			message = "already_assigned_to_programs";
		}
		return message;
	}

}
