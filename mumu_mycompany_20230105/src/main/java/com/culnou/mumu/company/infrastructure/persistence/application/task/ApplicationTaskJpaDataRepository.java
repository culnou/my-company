package com.culnou.mumu.company.infrastructure.persistence.application.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTask;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;


public interface ApplicationTaskJpaDataRepository extends JpaRepository<ApplicationTask, ApplicationTaskId> {
	
    public ApplicationTask findByapplicationTaskId(ApplicationTaskId applicationTaskId);
	
	@Query(value = "select applicationTask from ApplicationTask applicationTask where applicationTask.companyId = :CompanyId")
	public List<ApplicationTask> findApplicationTasksOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select applicationTask from ApplicationTask applicationTask where applicationTask.applicationTypeId = :ApplicationTypeId")
	public List<ApplicationTask> findApplicationTasksOfApplicationType(@Param("ApplicationTypeId") ApplicationTypeId applicationTypeId);
	
	@Query(value = "select applicationTask from ApplicationTask applicationTask where applicationTask.taskId = :TaskId")
	public List<ApplicationTask> findApplicationTasksOfTask(@Param("TaskId") TaskId taskId);


}
