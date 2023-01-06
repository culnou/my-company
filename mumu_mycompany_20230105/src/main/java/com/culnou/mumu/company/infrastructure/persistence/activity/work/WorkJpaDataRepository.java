package com.culnou.mumu.company.infrastructure.persistence.activity.work;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.culnou.mumu.company.domain.model.activity.work.Work;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.activity.work.WorkType;

import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;


public interface WorkJpaDataRepository extends JpaRepository<Work, WorkId> {
	
	public Work findByWorkId(WorkId workId);
	
	@Query(value = "select work from Work work where work.workflowId = :WorkflowId and work.workType = :WorkType")
	public List<Work> findWorksOfWorkflow(@Param("WorkflowId") WorkflowId workflowId, @Param("WorkType") WorkType workType);

}
