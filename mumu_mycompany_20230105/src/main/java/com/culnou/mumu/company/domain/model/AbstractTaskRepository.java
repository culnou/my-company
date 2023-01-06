package com.culnou.mumu.company.domain.model;


import com.culnou.mumu.company.infrastructure.persistence.TaskEntity;

public abstract class AbstractTaskRepository implements TaskRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public Task convertFromEntity(TaskEntity entity) {
		TaskId taskId = entity.getTaskId();
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId of TaskEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of TaskEntity may not be set to null.");
		}
		JobId jobId = entity.getJobId();
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId of TaskEntity may not be set to null.");
		}
		String jobName = entity.getJobName();
		if(jobName == null) {
			throw new IllegalArgumentException("The jobName of TaskEntity may not be set to null.");
		}
		Function function = entity.getFunction();
		if(function == null) {
			throw new IllegalArgumentException("The function of TaskEntity may not be set to null.");
		}
		String taskName = entity.getTaskName();
		if(taskName == null) {
			throw new IllegalArgumentException("The taskName of TaskEntity may not be set to null.");
		}
		return new Task(taskId, companyId, jobId, jobName, function, taskName);
	}

}
