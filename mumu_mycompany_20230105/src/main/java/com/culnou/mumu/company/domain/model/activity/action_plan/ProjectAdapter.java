package com.culnou.mumu.company.domain.model.activity.action_plan;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.project.Project;
import com.culnou.mumu.company.domain.model.project.ProjectId;
import com.culnou.mumu.company.domain.model.project.ProjectRepository;

@Service
@Transactional
public class ProjectAdapter {
	
	@Qualifier("projectJpaRepository")
	@Autowired
	private ProjectRepository projectRepository;
	
	Project findProjectOfId(ProjectId projectId) throws Exception{
		return projectRepository.findProjectOfId(projectId);
		
	}
	
	

}
