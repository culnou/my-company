package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractJobRepository;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.Job;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobType;
@Service("jobJpaRepository")
@Transactional
public class JobJpaRepository extends AbstractJobRepository {
	
	@Autowired
	private JobJpaDataRepository repository;
	
	/*
	 * 変換処理
	 */
	private Job convertEntityToPojo(JobEntity entity) {
		Job pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getBusinessDomainId() != null) {
			pojo.setBusinessDomainId(entity.getBusinessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			pojo.setBusinessDomainName(entity.getBusinessDomainName());
		}
		if(entity.getJobDescription() != null) {
			pojo.setJobDescription(entity.getJobDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getIndicators() != null) {
			pojo.setIndicators(entity.getIndicators());
		}
		if(entity.getCreatedAt() != null) {
			pojo.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	pojo.setUpdatedAt(entity.getUpdatedAt());
	    }
		
		return pojo;
	}
	
	private JobEntity convertPojoToEntity(Job pojo) {
		JobEntity entity = new JobEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setJobId(pojo.getJobId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setJobType(pojo.getJobType());
		entity.setJobName(pojo.getJobName());
		entity.setRoles(pojo.getRoles());
		//任意項目
		if(pojo.getBusinessDomainId() != null) {
			entity.setBusinessDomainId(pojo.getBusinessDomainId());
		}
		if(pojo.getBusinessDomainName() != null) {
			entity.setBusinessDomainName(pojo.getBusinessDomainName());
		}
		if(pojo.getJobDescription() != null) {
			entity.setJobDescription(pojo.getJobDescription());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getIndicators() != null) {
			entity.setIndicators(pojo.getIndicators());
		}
		if(pojo.getCreatedAt() != null) {
			entity.setCreatedAt(pojo.getCreatedAt());
		}
		if(pojo.getUpdatedAt() != null) {
			entity.setUpdatedAt(pojo.getUpdatedAt());
		}
		return entity;
	}
	
	private List<Job> convertEntitiesToPojos(List<JobEntity> entities){
		List<Job> pojos = new ArrayList<>();
		for(JobEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}

	@Override
	public JobId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new JobId(str);
	}

	@Override
	public void save(Job job) throws Exception {
		// TODO Auto-generated method stub
		if(job == null) {
			throw new IllegalArgumentException("The job may not be set to null.");
		}
		repository.save(convertPojoToEntity(job));

	}

	@Override
	public void remove(Job job) throws Exception {
		// TODO Auto-generated method stub
		if(job == null) {
			throw new IllegalArgumentException("The job may not be set to null.");
		}
		repository.delete(convertPojoToEntity(job));
	}

	@Override
	public Job jobOfId(JobId jobId) throws Exception {
		// TODO Auto-generated method stub
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		//集約のライフサイクルはリポジトリで保証するため検索結果の検証を行う。
		JobEntity entity = repository.findByJobId(jobId);
		if(entity == null) {
			throw new IllegalArgumentException("The jobEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<Job> jobsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<JobEntity> entities = repository.findJobsOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Job> jobsOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		List<JobEntity> entities = repository.findJobsOfBusinessDomain(businessDomainId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Job> jobsOfJobType(JobType jobType) throws Exception {
		// TODO Auto-generated method stub
		if(jobType == null) {
			throw new IllegalArgumentException("The jobType may not be set to null.");
		}
		List<JobEntity> entities = repository.findJobsOfJobType(jobType);
		return this.convertEntitiesToPojos(entities);
	}

}
