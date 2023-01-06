package com.culnou.mumu.company.domain.model;

import java.util.List;


import com.culnou.mumu.company.infrastructure.persistence.JobEntity;


public abstract class AbstractJobRepository implements JobRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public Job convertFromEntity(JobEntity entity) {
		//Entityの必須項目検証はPojoへの変換時に行う。
		JobId jobId = entity.getJobId();
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId of JobEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of JobEntity may not be set to null.");
		}
		JobType jobType = entity.getJobType();
		if(jobType == null) {
			throw new IllegalArgumentException("The jobType of JobEntity may not be set to null.");
		}
		String jobName = entity.getJobName();
		if(jobName == null) {
			throw new IllegalArgumentException("The jobName of JobEntity may not be set to null.");
		}
		List<Role> roles = entity.getRoles();
		if(roles == null) {
			throw new IllegalArgumentException("The roles of JobEntity may not be set to null.");
		}
		return new Job(jobId, companyId, jobType, jobName, roles);
	}

}
