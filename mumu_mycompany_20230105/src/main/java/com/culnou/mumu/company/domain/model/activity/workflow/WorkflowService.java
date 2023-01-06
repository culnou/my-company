package com.culnou.mumu.company.domain.model.activity.workflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkService;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.project.ProjectId;

import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.WorkDto;
import com.culnou.mumu.compnay.controller.WorkflowDto;



@Service
@Transactional
public class WorkflowService {
	
	@Autowired
	private WorkflowRegistry workflowRegistry;
	@Autowired
	private WorkflowActionPlanAdapter actionPlanAdapter;
	@Autowired
	private WorkService workService;
	
	
	
	public Workflow findWorkflowOfId(String workflowId) throws Exception{
		return workflowRegistry.findWorkflowOfId(workflowId);
	}
	
	public WorkflowDto findWorkflowDtoOfId(String workflowId) throws Exception{
		return this.convertWorkflow(workflowRegistry.findWorkflowOfId(workflowId));
	}
	
	public List<WorkflowDto> findWorkflowOfProject(String projectId) throws Exception{
		return this.convertWorkflows(workflowRegistry.findWorkflowsOfProject(new ProjectId(projectId)));
	}
	
	public List<WorkflowDto> findWorkflowOfProgram(String programId) throws Exception{
		return this.convertWorkflows(workflowRegistry.findWorkflowsOfProgram(new ProgramId(programId)));
	}
	
	/*
	 * アクションプランのワークフローを探す
	 */
	public List<WorkflowDto> findWorkflowsOfActionPlan(String actionPlanId) throws Exception{
		List<WorkflowDto> workflowDtos = new ArrayList<>();
		try {
			//事前条件の検証
			ActionPlan actionPlan = null;
			actionPlan = actionPlanAdapter.findActionPlanOfId(actionPlanId);
			if(actionPlan == null) {
				throw new Exception("The_action_plan_may_not_exist");
			}else {
				List<Workflow> wofkflows = workflowRegistry.findWorkflowsOfActionPlan(new ActionPlanId(actionPlanId));
				workflowDtos.addAll(this.convertWorkflows(wofkflows));
			}
		}catch(Exception ex) {
			throw ex;
		}
		return workflowDtos;
	}
	
	/*
	 * ワークフローを作成する
	 */
	public MessageDto createWorkflow(WorkflowDto workflowDto){	
		MessageDto message = new MessageDto();
		try {
			ActionPlan actionPlan = null;
			actionPlan = actionPlanAdapter.findActionPlanOfId(workflowDto.getActionPlanId());
			if(actionPlan == null) {
				message.setErrorMessage("The_action_plan_may_not_exist");
			}
			Workflow workflow = this.convertWorkflowDto(workflowDto);
			workflow.setWorkflowId(workflowRegistry.nextIdentity());
			workflowRegistry.registerWorkflow(workflow);
			message.setReturnValue(workflow.getWorkflowId().getWorkflowId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
		
	}
	
	public MessageDto addWorkflowToProject(WorkflowDto workflowDto){	
		MessageDto message = new MessageDto();
		try {
			ActionPlan actionPlan = null;
			actionPlan = actionPlanAdapter.findActionPlanOfId(workflowDto.getActionPlanId());
			if(actionPlan == null) {
				message.setErrorMessage("The_action_plan_may_not_exist");
			}
			if(workflowDto.getProjectId() == null || workflowDto.getProjectId().isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			Workflow workflow = this.convertWorkflowDto(workflowDto);
			workflow.setWorkflowId(workflowRegistry.nextIdentity());
			workflow.setCreatedAt(new Date());
			workflowRegistry.registerWorkflow(workflow);
			message.setReturnValue(workflow.getWorkflowId().getWorkflowId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
		
	}
	
	public MessageDto updateWorkflow(WorkflowDto workflowDto){
		MessageDto message = new MessageDto();
		try {
			if(workflowDto.getWorkflowId() == null || workflowDto.getWorkflowId().isEmpty()) {
				throw new IllegalArgumentException("The_workflowId_may_not_be_set_to_null");
			}
			Workflow foundWorkflow = workflowRegistry.findWorkflowOfId(workflowDto.getWorkflowId());
			if(foundWorkflow == null) {
				message.setErrorMessage("The_workflow_is_not_exist");
			}
			//ToDoやDoingのワークがある場合は完了できない。
			if(workflowDto.getBusinessState().equals(BusinessState.Completed)) {
				List<WorkDto> doingWorks = workService.findDoingWorks(workflowDto.getWorkflowId());
				if(doingWorks.size() > 0) {
					throw new IllegalArgumentException("There_are_doing_works");
				}
				List<WorkDto> todoWorks = workService.findTodoWorks(workflowDto.getWorkflowId());
				if(todoWorks.size() > 0) {
					throw new IllegalArgumentException("There_are_todo_works");
				}
			}
			
			foundWorkflow.setWorkflowName(workflowDto.getWorkflowName());
			foundWorkflow.setWorkflowDescription(workflowDto.getWorkflowDescription());
			foundWorkflow.setHypothesis(workflowDto.getHypothesis());
			foundWorkflow.setResult(workflowDto.getResult());
			foundWorkflow.setStartDate(workflowDto.getStartDate());
			foundWorkflow.setEndDate(workflowDto.getEndDate());
			foundWorkflow.setBusinessState(workflowDto.getBusinessState());
			if(workflowDto.getActionPlanId() != null && !workflowDto.getActionPlanId().isEmpty()) {
				foundWorkflow.setActionPlanId(new ActionPlanId(workflowDto.getActionPlanId()));
			}
			if(workflowDto.getProjectId() != null && !workflowDto.getProjectId().isEmpty()) {
				foundWorkflow.setProjectId(new ProjectId(workflowDto.getProjectId()));
			}
			if(workflowDto.getProgramId() != null && !workflowDto.getProgramId().isEmpty()) {
				foundWorkflow.setProgramId(new ProgramId(workflowDto.getProgramId()));
			}
			foundWorkflow.setUpdatedAt(new Date());
			workflowRegistry.registerWorkflow(foundWorkflow);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}		
		return message;	
	}
	
	public MessageDto removeWorkflow(String workflowId){	
		MessageDto message = new MessageDto();
		try {
			if(workflowId == null || workflowId.isEmpty()) {
				throw new IllegalArgumentException("The_workflowId_may_not_be_set_to_null");
			}
			Workflow foundWorkflow = workflowRegistry.findWorkflowOfId(workflowId);
			if(foundWorkflow == null) {
				throw new IllegalArgumentException("The_workflow_is_not_exist");
			}
			if(!foundWorkflow.getBusinessState().equals(BusinessState.Planned)) {
				throw new IllegalArgumentException("The_status_is_started");
			}
			
			workflowRegistry.remove(foundWorkflow);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}		
		return message;	
	}
	
	public MessageDto addWorkflowToProgram(WorkflowDto workflowDto){	
		MessageDto message = new MessageDto();
		try {
			ActionPlan actionPlan = null;
			actionPlan = actionPlanAdapter.findActionPlanOfId(workflowDto.getActionPlanId());
			if(actionPlan == null) {
				message.setErrorMessage("The_action_plan_may_not_exist");
			}
			if(workflowDto.getProgramId() == null || workflowDto.getProgramId().isEmpty()) {
				throw new IllegalArgumentException("The_programd_may_not_be_set_to_null");
			}
			Workflow workflow = this.convertWorkflowDto(workflowDto);
			workflow.setWorkflowId(workflowRegistry.nextIdentity());
			workflow.setCreatedAt(new Date());
			workflowRegistry.registerWorkflow(workflow);
			message.setReturnValue(workflow.getWorkflowId().getWorkflowId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
		
	}
	
	
	
	/*
	 * ワークフローのワークを確認する
	 */
	
	private Workflow convertWorkflowDto(WorkflowDto dto) throws Exception{
		Workflow entity = new Workflow();
		if(dto.getCompanyId() == null) {
			throw new Exception("The_companyId_may_not_be_set_to_null");
		}
		if(dto.getActionPlanId() == null) {
			throw new Exception("The_actionPlanId_may_not_be_set_to_null");
		}
		if(dto.getWorkflowId() != null) {
			entity.setWorkflowId(new WorkflowId(dto.getWorkflowId()));
		}
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		entity.setActionPlanId(new ActionPlanId(dto.getActionPlanId()));
		entity.setWorkflowName(dto.getWorkflowName());
		entity.setWorkflowDescription(dto.getWorkflowDescription());
		entity.setHypothesis(dto.getHypothesis());
		entity.setResult(dto.getResult());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setBusinessState(dto.getBusinessState());
		//entity.setUrl(dto.getUrl());
		if(dto.getProjectId() != null && !dto.getProjectId().isEmpty()) {
			entity.setProjectId(new ProjectId(dto.getProjectId()));
		}
		if(dto.getProgramId() != null && !dto.getProgramId().isEmpty()) {
			entity.setProgramId(new ProgramId(dto.getProgramId()));
		}
		
		return entity;
	}
	private WorkflowDto convertWorkflow(Workflow workflow) {
		WorkflowDto workflowDto = new WorkflowDto();
		workflowDto.setWorkflowId(workflow.getWorkflowId().getWorkflowId());
		workflowDto.setCompanyId(workflow.getCompanyId().id());
		workflowDto.setActionPlanId(workflow.getActionPlanId().actionPlanId());
		workflowDto.setWorkflowName(workflow.getWorkflowName());
		workflowDto.setWorkflowDescription(workflow.getWorkflowDescription());
		workflowDto.setHypothesis(workflow.getHypothesis());
		workflowDto.setResult(workflow.getResult());
		workflowDto.setStartDate(workflow.getStartDate());
		workflowDto.setEndDate(workflow.getEndDate());
		workflowDto.setBusinessState(workflow.getBusinessState());		
		//workflowDto.setUrl(workflow.getUrl());
		if(workflow.getProjectId() != null) {
			workflowDto.setProjectId(workflow.getProjectId().getProjectId());
		}
		if(workflow.getProgramId() != null) {
			workflowDto.setProgramId(workflow.getProgramId().getProgramId());
		}
		return workflowDto;
	}
	
	private List<WorkflowDto> convertWorkflows(List<Workflow> workflows){
		List<WorkflowDto> workflowDtos = new ArrayList<>();
		for(Workflow workflow : workflows) {
			workflowDtos.add(this.convertWorkflow(workflow));
		}
		return workflowDtos;
	}

}
