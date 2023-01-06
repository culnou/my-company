package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobType;

public interface JobJpaDataRepository extends JpaRepository<JobEntity, JobId> {
	
	public JobEntity findByJobId(JobId jobId);
	@Query(value = "select job from JobEntity job where job.companyId = :CompanyId")
	public List<JobEntity> findJobsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	//特定の事業ドメインに対応するジョブ
	@Query(value = "select job from JobEntity job where job.businessDomainId = :BusinessDomainId")
	public List<JobEntity> findJobsOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	//特定のジョブタイプに対応するジョブ
	@Query(value = "select job from JobEntity job where job.jobType = :JobType")
	public List<JobEntity> findJobsOfJobType(@Param("JobType") JobType jobType);
	
}
