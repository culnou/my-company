package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
@Getter
public class Job {
	//必須
	private JobId jobId;
	private CompanyId companyId;
	private JobType jobType;
	private String jobName;
	private List<Role> roles = new ArrayList<>();
	//任意
	@Setter
	private BusinessDomainId businessDomainId;
	@Setter
	private String businessDomainName;
	@Setter
	private String jobDescription;
	@Setter
	private Url url;
	@Getter
	@Setter
	private List<Indicator> indicators = new ArrayList<>();
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	//必須項目
    //クラスの不変条件
	protected void setJobId(JobId jobId) {
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		this.jobId = jobId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setJobType(JobType jobType) {
		if(jobType == null) {
			throw new IllegalArgumentException("The jobType may not be set to null.");
		}
		this.jobType = jobType;
	}
	
	public void setJobName(String jobName) {
		if(jobName == null) {
			throw new IllegalArgumentException("The jobName may not be set to null.");
		}
		if(jobName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a jobName.");
		}
		this.jobName = jobName;
	}
	
	public void setRoles(List<Role> roles) {
		if(roles == null) {
			throw new IllegalArgumentException("The roles may not be set to null.");
		}
		if(!(roles.size() > 0)) {
			throw new IllegalArgumentException("The role may not be set.");
		}
		this.roles = roles;
	}
	//コンストラクタ
	//クラスの不変条件として、自己カプセル化によって初期状態として必須項目には値が入っていることを保証する。
	protected Job(JobId jobId, CompanyId companyId, JobType jobType, String jobName, List<Role> roles) {
		this.setJobId(jobId);
		this.setCompanyId(companyId);
		this.setJobType(jobType);
		this.setJobName(jobName);
		this.setRoles(roles);
	}
	
	
	public Department definDepartment(DepartmentId departmentId, DepartmentType departmentType, String departmentName) {
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId may not be set to null.");
		}
		if(departmentName == null) {
			throw new IllegalArgumentException("The departmentName may not be set to null.");
		}
		if(departmentType == null) {
			throw new IllegalArgumentException("The departmentType may not be set to null.");
		}
		return new Department(departmentId, this.companyId, departmentType, this.jobId, this.jobName, departmentName);
	}
	
    public Task defineTask(TaskId taskId, Function function, String taskName) {
    	if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
    	if(function == null) {
			throw new IllegalArgumentException("The function may not be set to null.");
		}
    	if(taskName == null) {
			throw new IllegalArgumentException("The taskName may not be set to null.");
		}
    	return new Task(taskId, this.companyId, this.jobId, this.jobName, function, taskName);
    }
}
