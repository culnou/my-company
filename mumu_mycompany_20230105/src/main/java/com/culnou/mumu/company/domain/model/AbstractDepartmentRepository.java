package com.culnou.mumu.company.domain.model;

import com.culnou.mumu.company.infrastructure.persistence.DepartmentEntity;


public abstract class AbstractDepartmentRepository implements DepartmentRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public Department convertFromEntity(DepartmentEntity entity) {
		DepartmentId departmentId = entity.getDepartmentId();
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId of DepartmentEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of DepartmentEntity may not be set to null.");
		}
		DepartmentType departmentType = entity.getDepartmentType();
		if(departmentType == null) {
			throw new IllegalArgumentException("The departmentType of DepartmentEntity may not be set to null.");
		}
		JobId jobId = entity.getJobId();
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId of DepartmentEntity may not be set to null.");
		}
		String jobName = entity.getJobName();
		if(jobName == null) {
			throw new IllegalArgumentException("The jobName of DepartmentEntity may not be set to null.");
		}
		String departmentName = entity.getDepartmentName();
		if(departmentName == null) {
			throw new IllegalArgumentException("The departmentName of DepartmentEntity may not be set to null.");
		}
		return new Department(departmentId, companyId, departmentType, jobId, jobName, departmentName);
	}

}
