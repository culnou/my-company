package com.culnou.mumu.company.application;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentRepository;
import com.culnou.mumu.company.domain.model.Job;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobRepository;

import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.member.type.AssociatedJob;
import com.culnou.mumu.compnay.controller.DepartmentDto;
import com.culnou.mumu.compnay.controller.MessageDto;

import com.culnou.mumu.compnay.controller.TaskDto;

@Service
@Transactional
public class DepartmentApplicationService {
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Qualifier("jobJpaRepository")
	@Autowired
	private JobRepository jobRepository;
	
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	
	public MessageDto addDepartment(DepartmentDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.addDepartment(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateDepartment(DepartmentDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.updateDepartment(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeDepartment(String departmentId) {
		MessageDto message = new MessageDto();
		try {
			companyService.deleteDepartment(departmentId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public List<TaskDto> tasksOfDepartment(String departmentId) throws Exception{
		List<TaskDto> tasks = new ArrayList<>();
		Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
		if(department == null) {
			throw new Exception("The_department_may_not_exist");
		}
		List<AssociatedJob> associatedJobs = department.getAssociatedJobs();
		for(AssociatedJob associatedJob : associatedJobs) {
			Job job = jobRepository.jobOfId(new JobId(associatedJob.getJobId()));
			List<Task> foundTasks = taskRepository.tasksOfJob(job.getJobId());
			tasks.addAll(this.convertTasksToTaskDtos(foundTasks));
		}
		
		return tasks;
	}
	
	//Taskの変換
	TaskDto convertTaskToTaskDto(Task task) {
		TaskDto dto = new TaskDto();
		dto.setTaskId(task.getTaskId().taskId());
		dto.setCompanyId(task.getCompanyId().id());
		dto.setJobId(task.getJobId().jobId());
		dto.setJobName(task.getJobName());
		dto.setFunctionId(task.getFunction().getFunctionId());
		dto.setFunctionName(task.getFunction().getFunctionName());
		dto.setTaskName(task.getTaskName());
		if(task.getTaskDescription() != null) {
			dto.setTaskDescription(task.getTaskDescription());
		}
		if(task.getUrl() != null) {
			dto.setUrl(task.getUrl().getUrl());
		}
		dto.setAssociatedDataTypes(task.getAssociatedDataTypes());
		
		return dto;
	}
	private List<TaskDto> convertTasksToTaskDtos(List<Task> tasks){
		List<TaskDto> dtos = new ArrayList<>();
		for(Task task : tasks) {
			dtos.add(this.convertTaskToTaskDto(task));
		}
		return dtos;
	}
	
	
	
	
	
}
