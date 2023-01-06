package com.culnou.mumu.company.domain.model.member;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.project.Project;
import com.culnou.mumu.company.domain.model.project.ProjectService;

@Service
@Transactional
public class MemberProjectAdapter {
	
	@Autowired
	private ProjectService projectService;
	
	/*
	 * プロジェクトが所属する事業単位を探す
	 */
	BusinessUnitId findBusinessUnitOfProject(String projectId) throws Exception{
		Project project = projectService.findProjectOfId(projectId);
		if(project == null) {
			throw new Exception("The_project_may_not_exist");
		}
		return project.getBusinessUnitId();
	}
	
	Project findProjectOfId(String projectId) throws Exception{
		Project project = projectService.findProjectOfId(projectId);
		if(project == null) {
			throw new Exception("The_project_may_not_exist");
		}
		return project;
	}

}
