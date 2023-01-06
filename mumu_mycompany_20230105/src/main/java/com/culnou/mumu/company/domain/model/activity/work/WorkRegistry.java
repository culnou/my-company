package com.culnou.mumu.company.domain.model.activity.work;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;



@Service
@Transactional
public class WorkRegistry {
	
	
	@Qualifier("workJpaRepository")
	@Autowired
	private WorkRepository repository;
	
	
	protected WorkId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new WorkId(str);
	}
	
	protected void save(Work work) throws Exception{
		repository.save(work);
	}
	
	protected Work findWorkOfId(String workId) throws Exception{
		return repository.findWorkOfId(new WorkId(workId));
	}
	
	protected List<Work> findWorksOfWorkflow(WorkflowId workflowId, WorkType workType) throws Exception{
		return repository.findWorksOfWorkflow(workflowId, workType);
	}

}
