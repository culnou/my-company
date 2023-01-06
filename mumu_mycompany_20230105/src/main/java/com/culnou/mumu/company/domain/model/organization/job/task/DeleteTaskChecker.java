package com.culnou.mumu.company.domain.model.organization.job.task;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionRepository;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunction;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionRegistry;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTask;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskRepository;

/*
 * タスクが削除できるかチェックするドメインサービス
 */
@Service
@Transactional
public class DeleteTaskChecker {
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	@Qualifier("applicationTaskJpaRepository")
	@Autowired
	private ApplicationTaskRepository applicationTaskRepository;
	
	@Autowired
	private ApplicationFunctionRegistry appFuncRegistry;
	
	public boolean removable(String taskId) throws Exception{
		
		boolean check = true;
		//対応するアクションがあるか検証
		List<Action> actions = actionRepository.findActionsOfTask(new TaskId(taskId));
		if(actions.size() > 0) {
			check = false;
		}
		//対応するアプリケーションタスクがあるか検証
		List<ApplicationTask> applicationTasks = applicationTaskRepository.findApplicationTasksOfTask(new TaskId(taskId));
		if(applicationTasks.size() > 0) {
			//タスクを定義すると、自動的にアプリケーションタスクが生成されるので、一度、タスクを生成すると削除できなくなる。
			//なので、アプリケーションタスクに対するアプリケーション機能をないことをさらに確認する。2022/12/22
			for(ApplicationTask task : applicationTasks) {
				List<ApplicationFunction> functions = appFuncRegistry.applicationFunctionsOfApplicationTask(task.getApplicationTaskId());
			    if(functions.size() > 0) {
			    	check = false;
			    }
			}
			
		}
		return check;
	}

}
