package com.culnou.mumu.company.infrastructure.persistence.activity.workflow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.ActionPlanId;

import com.culnou.mumu.company.domain.model.activity.workflow.Workflow;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.project.ProjectId;



public interface WorkflowJpaDataRepository extends JpaRepository<Workflow, WorkflowId> {
	
    public Workflow findByWorkflowId(WorkflowId workflowId);
	
	@Query(value = "select workflow from Workflow workflow where workflow.actionPlanId = :ActionPlanId")
	public List<Workflow> findWorkflowsOfActionPlan(@Param("ActionPlanId") ActionPlanId actionPlanId);
	
	@Query(value = "select workflow from Workflow workflow where workflow.projectId = :ProjectId")
	public List<Workflow> findWorkflowsOfProject(@Param("ProjectId") ProjectId projectId);
	
	@Query(value = "select workflow from Workflow workflow where workflow.programId = :ProgramId")
	public List<Workflow> findWorkflowsOfProgram(@Param("ProgramId") ProgramId programId);

}
