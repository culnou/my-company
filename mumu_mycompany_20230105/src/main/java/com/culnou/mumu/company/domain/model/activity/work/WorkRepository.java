package com.culnou.mumu.company.domain.model.activity.work;

import java.util.List;

import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;

public interface WorkRepository {
	
	
	public void save(Work workf) throws Exception;
	
	public Work findWorkOfId(WorkId workId) throws Exception;
	
	public List<Work> findWorksOfWorkflow(WorkflowId workflowId, WorkType workType) throws Exception;

}
