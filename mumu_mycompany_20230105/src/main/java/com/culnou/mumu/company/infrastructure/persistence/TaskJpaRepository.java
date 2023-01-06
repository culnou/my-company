package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractTaskRepository;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.organization.job.task.AddTaskChecker;

@Service("taskJpaRepository")
@Transactional
public class TaskJpaRepository extends AbstractTaskRepository {
	
	@Autowired
	private TaskJpaDataRepository repository;
	
	@Autowired
	AddTaskChecker checker;
	
	/*
	 * 変換処理
	 */
	private Task convertEntityToPojo(TaskEntity entity) {
		Task pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getTaskDescription() != null) {
			pojo.setTaskDescription(entity.getTaskDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getCreatedAt() != null) {
			pojo.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	pojo.setUpdatedAt(entity.getUpdatedAt());
	    }
	    
	    pojo.setAssociatedDataTypes(entity.getAssociatedDataTypes());
		
		return pojo;
	}
	
	private TaskEntity convertPojoToEntity(Task pojo) {
		
		TaskEntity entity = new TaskEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setTaskId(pojo.getTaskId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setJobId(pojo.getJobId());
		entity.setJobName(pojo.getJobName());
		entity.setFunction(pojo.getFunction());
		entity.setTaskName(pojo.getTaskName());
		
		entity.setAssociatedDataTypes(pojo.getAssociatedDataTypes());
		//任意項目
		if(pojo.getTaskDescription() != null) {
			entity.setTaskDescription(pojo.getTaskDescription());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		
		if(pojo.getCreatedAt() != null) {
			entity.setCreatedAt(pojo.getCreatedAt());
		}
		if(pojo.getUpdatedAt() != null) {
			entity.setUpdatedAt(pojo.getUpdatedAt());
		}
		return entity;
	}
	
	private List<Task> convertEntitiesToPojos(List<TaskEntity> entities){
		List<Task> pojos = new ArrayList<>();
		for(TaskEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}

	@Override
	public TaskId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new TaskId(str);
	}

	@Override
	public void save(Task task) throws Exception {
		// TODO Auto-generated method stub
		if(task == null) {
			throw new IllegalArgumentException("The_task_may_not_be_set_to_null");
		}
		
		repository.save(this.convertPojoToEntity(task));

	}
	
	

	@Override
	public void remove(Task task) throws Exception {
		// TODO Auto-generated method stub
		if(task == null) {
			throw new IllegalArgumentException("The_task_may_not_be_set_to_null");
		}
		repository.delete(this.convertPojoToEntity(task));

	}

	@Override
	public Task TaskOfId(TaskId taskId) throws Exception {
		// TODO Auto-generated method stub
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		//集約のライフサイクルはリポジトリで保証するため検索結果の検証を行う。
		TaskEntity entity = repository.findByTaskId(taskId);
		if(entity == null) {
			throw new IllegalArgumentException("The taskEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<Task> tasksOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<TaskEntity> entities = repository.findTasksOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Task> tasksOfJob(JobId jobId) throws Exception {
		// TODO Auto-generated method stub
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		List<TaskEntity> entities = repository.findTasksOfJob(jobId);
		return this.convertEntitiesToPojos(entities);
	}

}
