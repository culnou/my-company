package com.culnou.mumu.company.domain.model.organization.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentRepository;
import com.culnou.mumu.company.domain.model.JobId;


/*
 * ジョブが編集できるかチェックするドメインサービス
 */
@Service
@Transactional
public class EditJobChecker {
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public boolean editable(String jobId) throws Exception{
		boolean check = true;
		if(jobId== null || jobId.isEmpty()) {
			throw new IllegalArgumentException("The_jobId_may_not_be_set_to_null");
		}
		//対応する部門があるか検証
		List<Department> departments = departmentRepository.departmentsOfJob(new JobId(jobId));
		if(departments.size() > 0) {
			check = false;
		}
		return check;
	}

}
