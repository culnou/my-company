package com.culnou.mumu.company.domain.model.activity.workflow;

import java.util.List;

import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.project.ProjectId;


public interface WorkflowRepository {
	
    public void save(Workflow workflow) throws Exception;
    
    public void remove(Workflow workflow) throws Exception;
	
	public Workflow findWorkflowOfId(WorkflowId workflowId) throws Exception;
	
	public List<Workflow> findWorkflowsOfActionPlan(ActionPlanId actionPlanId) throws Exception;
	
	public List<Workflow> findWorkflowsOfProject(ProjectId projectId) throws Exception;
	
	public List<Workflow> findWorkflowsOfProgram(ProgramId programId) throws Exception;

}
