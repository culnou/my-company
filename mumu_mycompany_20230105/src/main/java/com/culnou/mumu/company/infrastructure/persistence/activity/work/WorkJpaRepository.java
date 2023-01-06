package com.culnou.mumu.company.infrastructure.persistence.activity.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.activity.work.Work;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.activity.work.WorkRepository;
import com.culnou.mumu.company.domain.model.activity.work.WorkType;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;
@Service("workJpaRepository")
@Transactional
public class WorkJpaRepository implements WorkRepository{
	
	@Autowired
	private WorkJpaDataRepository repository;

	@Override
	public List<Work> findWorksOfWorkflow(WorkflowId workflowId, WorkType workType) throws Exception {
		// TODO Auto-generated method stub
		return repository.findWorksOfWorkflow(workflowId, workType);
	}

	@Override
	public Work findWorkOfId(WorkId workId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByWorkId(workId);
	}

	@Override
	public void save(Work work) throws Exception {
		// TODO Auto-generated method stub
		repository.save(work);
		
	}

}
