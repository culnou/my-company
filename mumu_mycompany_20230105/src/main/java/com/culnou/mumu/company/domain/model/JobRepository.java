package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface JobRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public JobId nextIdentity() throws Exception;
	
	//コマンド
    public void save(Job job) throws Exception;
	
	public void remove(Job job) throws Exception;
	
	//クエリ
	public Job jobOfId(JobId jobId) throws Exception;
	
	public List<Job> jobsOfCompany(CompanyId companyId) throws Exception;
	
	public List<Job> jobsOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception;
	
	public List<Job> jobsOfJobType(JobType jobType) throws Exception;

}
