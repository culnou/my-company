package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.TaskRepository;

@Service
@Transactional
public class TaskAdapter {
	
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository repository;
	
	public Task findTaskOfId(TaskId taskId) throws Exception{
		return repository.TaskOfId(taskId);
	}

}
