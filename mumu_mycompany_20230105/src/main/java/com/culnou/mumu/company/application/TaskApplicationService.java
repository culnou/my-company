package com.culnou.mumu.company.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.company.domain.model.organization.job.task.AddTaskChecker;
import com.culnou.mumu.company.domain.model.organization.job.task.DeleteTaskChecker;
import com.culnou.mumu.company.domain.model.organization.job.task.EditTaskChecker;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.TaskDto;

@Service
@Transactional
public class TaskApplicationService {
	
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	@Qualifier("taskJpaRepository")
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	AddTaskChecker addTaskChecker;
	
	@Autowired
	EditTaskChecker editTaskChecker;
	
	@Autowired
	DeleteTaskChecker deleteTaskChecker;
	
	public MessageDto defineTask(TaskDto taskDto) throws Exception{
		MessageDto message = new MessageDto();
		try {
			//同じ名前のタスクがないか検証する。
			boolean check = addTaskChecker.addable(taskDto.getJobId(),taskDto.getTaskName());
			if(!check) {
				//同じ名前のタスクがある場合登録できない。
				throw new Exception("The_task_can_not_save_by_same_name");
			}
			companyService.addTask(taskDto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateTask(TaskDto taskDto) throws Exception{
		MessageDto message = new MessageDto();
		try {
			//編集できない場合、機能の変更はできない。
			if(!editTaskChecker.editable(taskDto.getTaskId())) {
				Task task = taskRepository.TaskOfId(new TaskId(taskDto.getTaskId()));
				if(task == null) {
					throw new Exception("The_task_not_found");
				}
				if(task.getFunction() == null) {
					throw new Exception("The_function_not_found");
				}
				if(task.getFunction().getFunctionId() == null || task.getFunction().getFunctionId().isEmpty()) {
					throw new Exception("The_function_not_found");
				}
				if(taskDto.getFunctionId() == null || taskDto.getFunctionId().isEmpty()) {
					throw new Exception("The_function_not_found");
				}
				if(!task.getFunction().getFunctionId().equals(taskDto.getFunctionId())) {
					throw new Exception("The_task_has_already_used_at_edit");
				}
			}//if
			
			companyService.updateTask(taskDto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	
	}
	
	public MessageDto removeTask(String task) throws Exception{
		MessageDto message = new MessageDto();
		try {
			//タスクが削除可能か検証する。
			boolean check = deleteTaskChecker.removable(task);
			if(!check) {
				throw new Exception("The_task_has_already_used_at_delete");
			}
			companyService.deleteTask(task);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto assignDataType(String taskId, AssociatedDataType dataType) throws Exception{
		MessageDto message = new MessageDto();
		try {
			Task task = taskRepository.TaskOfId(new TaskId(taskId));
			task.assignDataType(dataType);
			taskRepository.save(task);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto releaseDataType(String taskId, AssociatedDataType dataType) throws Exception{
		MessageDto message = new MessageDto();
		try {
			Task task = taskRepository.TaskOfId(new TaskId(taskId));
			task.releaseDataType(dataType);
			taskRepository.save(task);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

}
