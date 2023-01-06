package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.JobId;

import com.culnou.mumu.company.domain.model.TaskId;

public interface TaskJpaDataRepository extends JpaRepository<TaskEntity, TaskId> {
	
	public TaskEntity findByTaskId(TaskId taskId);
	@Query(value = "select task from TaskEntity task where task.companyId = :CompanyId")
	public List<TaskEntity> findTasksOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select task from TaskEntity task where task.jobId = :JobId")
	public List<TaskEntity> findTasksOfJob(@Param("JobId") JobId jobId);
	

}
