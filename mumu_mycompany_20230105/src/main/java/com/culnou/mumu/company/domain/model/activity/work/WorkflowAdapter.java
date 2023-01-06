package com.culnou.mumu.company.domain.model.activity.work;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.activity.workflow.Workflow;

import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowService;

@Service
@Transactional
public class WorkflowAdapter {
	
	@Autowired
	private WorkflowService workflowService;
	
	protected Workflow findWorkflowOfId(String workflowId) throws Exception{
		return workflowService.findWorkflowOfId(workflowId);
	}

}
