package com.culnou.mumu.company.domain.model.organization.job.task;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionRepository;

import com.culnou.mumu.company.domain.model.TaskId;

/*
 * タスクが編集できるかチェックするドメインサービス
 */
@Service
@Transactional
public class EditTaskChecker {
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	public boolean editable(String taskId) throws Exception{
		boolean check = true;
		if(taskId== null || taskId.isEmpty()) {
			throw new IllegalArgumentException("The_taskId_may_not_be_set_to_null");
		}
		//対応するアクションがあるか検証
		List<Action> actions = actionRepository.findActionsOfTask(new TaskId(taskId));
		if(actions.size() > 0) {
			check = false;
		}
		return check;
	}

}
