package com.culnou.mumu.company.domain.model.organization.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentRepository;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeService;
import com.culnou.mumu.company.domain.model.member.type.MemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeRepository;
import com.culnou.mumu.compnay.controller.ApplicationTypeDto;

/*
 * ジョブが編集できるかチェックするドメインサービス
 */
@Service
@Transactional
public class DeleteJobChecker {
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ApplicationTypeService applicationTypeService;
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	@Qualifier("memberTypeJpaRepository")
	@Autowired
	private MemberTypeRepository memberTypeRepository;
	
	public boolean removable(String jobId) throws Exception{
		boolean check = true;
		if(jobId== null || jobId.isEmpty()) {
			throw new IllegalArgumentException("The_jobId_may_not_be_set_to_null");
		}
		//対応する部門があるか検証
		List<Department> departments = departmentRepository.departmentsOfJob(new JobId(jobId));
		if(departments.size() > 0) {
			check = false;
		}
		//ジョブが関連するアプリケーションタイプの調査
		List<ApplicationTypeDto> applicationTypes = applicationTypeService.findApplicationTypesOfJob(jobId);
		if(applicationTypes.size() > 0) {
			check = false;
		}
		//ジョブが関連するタスクの調査
		List<Task> tasks = taskRepository.tasksOfJob(new JobId(jobId));
		if(tasks.size() > 0) {
			check = false;
		}
		//ジョブが関連するメンバータイプの調査
		List<MemberType> memberTypes = memberTypeRepository.findMemberTypesOfJob(jobId);
		if(memberTypes.size() > 0) {
			check = false;
		}
		
		
		
		return check;
	}

}
