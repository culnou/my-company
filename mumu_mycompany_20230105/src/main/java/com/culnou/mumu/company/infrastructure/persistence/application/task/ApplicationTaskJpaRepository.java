package com.culnou.mumu.company.infrastructure.persistence.application.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTask;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskRepository;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;

@Service("applicationTaskJpaRepository")
@Transactional
public class ApplicationTaskJpaRepository implements ApplicationTaskRepository {
	
	@Autowired
	private ApplicationTaskJpaDataRepository repository;

	@Override
	public ApplicationTask findApplicationTaskOfId(ApplicationTaskId applicationTaskId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByapplicationTaskId(applicationTaskId);
	}

	@Override
	public List<ApplicationTask> findApplicationTasksOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationTasksOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<ApplicationTask> findApplicationTasksOfApplicationType(String applicationTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationTasksOfApplicationType(new ApplicationTypeId(applicationTypeId));
	}

	@Override
	public void save(ApplicationTask applicationTask) throws Exception {
		// TODO Auto-generated method stub
		repository.save(applicationTask);

	}

	@Override
	public void remove(ApplicationTask applicationTask) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(applicationTask);

	}

	@Override
	public List<ApplicationTask> findApplicationTasksOfTask(TaskId taskId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationTasksOfTask(taskId);
	}

}
