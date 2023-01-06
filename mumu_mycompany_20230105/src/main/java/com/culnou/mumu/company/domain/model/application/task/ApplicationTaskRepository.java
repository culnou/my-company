package com.culnou.mumu.company.domain.model.application.task;

import java.util.List;

import com.culnou.mumu.company.domain.model.TaskId;



public interface ApplicationTaskRepository {
	
    public ApplicationTask findApplicationTaskOfId(ApplicationTaskId applicationTaskId) throws Exception;
	
    public List<ApplicationTask> findApplicationTasksOfCompany(String companyId) throws Exception;
	
	public List<ApplicationTask> findApplicationTasksOfApplicationType(String applicationTypeId) throws Exception;
	
	public List<ApplicationTask> findApplicationTasksOfTask(TaskId taskId) throws Exception;
	
	public void save(ApplicationTask applicationTask) throws Exception;
	
	public void remove(ApplicationTask applicationTask) throws Exception;

}
