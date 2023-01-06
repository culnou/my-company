package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeService;
import com.culnou.mumu.compnay.controller.ApplicationTypeDto;

@Service
@Transactional
public class JobService {
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	@Qualifier("jobJpaRepository")
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private ApplicationTypeService applicationTypeService;
	
	public boolean isUsedExceptApp(JobId jobId) throws Exception{
		boolean result = false;
		//ジョブが関連する部門の調査
		List<Department> departments = departmentRepository.departmentsOfJob(jobId);
		if(departments.size() > 0) {
			result = true;
		}
		//ジョブが関連するアプリケーションタイプの調査
		/*
		List<ApplicationTypeDto> applicationTypes = applicationTypeService.findApplicationTypesOfJob(jobId.jobId());
		if(applicationTypes.size() > 0) {
			
			result = true;
		}
		*/
		
		
		return result;
	}
	
	public boolean isUsed(JobId jobId) throws Exception{
		boolean result = false;
		//ジョブが関連する部門の調査
		List<Department> departments = departmentRepository.departmentsOfJob(jobId);
		if(departments.size() > 0) {
			result = true;
		}
		//ジョブが関連するアプリケーションタイプの調査
		List<ApplicationTypeDto> applicationTypes = applicationTypeService.findApplicationTypesOfJob(jobId.jobId());
		if(applicationTypes.size() > 0) {
			
			result = true;
		}
		
		
		return result;
	}
	
	public boolean indicatorIsUsed(JobId jobId, Indicator indicator) throws Exception{
		boolean result = false;
		List<Department> departments = departmentRepository.departmentsOfJob(jobId);
		for(Department department : departments) {
			for(Goal goal : department.getGoals()) {
				if(goal.getIndicator().equals(indicator)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

}
