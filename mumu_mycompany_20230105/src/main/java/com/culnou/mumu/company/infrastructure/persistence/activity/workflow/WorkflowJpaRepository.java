package com.culnou.mumu.company.infrastructure.persistence.activity.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.activity.workflow.Workflow;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowRepository;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.project.ProjectId;

@Service("workflowJpaRepository")
@Transactional
public class WorkflowJpaRepository implements WorkflowRepository{
	
	@Autowired
	private WorkflowJpaDataRepository repository;

	@Override
	public void save(Workflow workflow) throws Exception {
		// TODO Auto-generated method stub
		repository.save(workflow);
		
	}

	@Override
	public Workflow findWorkflowOfId(WorkflowId workflowId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByWorkflowId(workflowId);
	}

	@Override
	public List<Workflow> findWorkflowsOfActionPlan(ActionPlanId actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findWorkflowsOfActionPlan(actionPlanId);
	}

	@Override
	public void remove(Workflow workflow) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(workflow);
		
	}

	@Override
	public List<Workflow> findWorkflowsOfProject(ProjectId projectId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findWorkflowsOfProject(projectId);
	}

	@Override
	public List<Workflow> findWorkflowsOfProgram(ProgramId programId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findWorkflowsOfProgram(programId);
	}

}
