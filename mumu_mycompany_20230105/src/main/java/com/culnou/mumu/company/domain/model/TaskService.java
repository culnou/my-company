package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	public boolean isUsed(TaskId taskId) throws Exception{
		boolean result = false;
		List<Action> actions = actionRepository.findActionsOfTask(taskId);
		if(actions.size() > 0) {
			result =true;
		}
		List<BusinessProcess> businessProcesses = businessProcessRepository.businessProcessesOfTask(taskId.taskId());
		if(businessProcesses.size() > 0) {
			result =true;
		}
		return result;
	}

}
