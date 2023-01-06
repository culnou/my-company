package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Task {
	
	//必須
	private TaskId taskId;
	private CompanyId companyId;
	private JobId jobId;
	private String jobName;
	private Function function;
	private String taskName;
	//任意
	@Setter
	private String taskDescription;
	@Setter
	private Url url;
	@Setter
	@Getter
	private List<AssociatedDataType> associatedDataTypes = new ArrayList<>();
	@Setter
	private Date createdAt;
	@Setter
	private Date updatedAt;
	
	protected void setTaskId(TaskId taskId) {
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		this.taskId = taskId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setJobId(JobId jobId) {
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		this.jobId =jobId;
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
	
	public void setFunction(Function function) {
		if(function == null) {
			throw new IllegalArgumentException("The function may not be set to null.");
		}
		this.function = function;
	}
	
	public void setTaskName(String taskName) {
		if(taskName == null) {
			throw new IllegalArgumentException("The taskName may not be set to null.");
		}
		if(taskName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a taskName.");
		}
		this.taskName = taskName;
	}
	
	protected Task(TaskId taskId, CompanyId companyId, JobId jobId, String jobName, Function fucntion, String taskName) {
		this.setTaskId(taskId);
		this.setCompanyId(companyId);
		this.setJobId(jobId);
		this.setJobName(jobName);
		this.setFunction(fucntion);
		this.setTaskName(taskName);
	}
	
	//ビジネスロジック
	//データタイプの割当。
	public void assignDataType(AssociatedDataType dataType) {
		for(AssociatedDataType data : this.associatedDataTypes) {
			if(data.getDataTypeId().equals(dataType.getDataTypeId()) && data.getDataAccessType().equals(dataType.getDataAccessType())) {
				throw new IllegalArgumentException("The_dataType_accessMethod_already_exist");
			}
		}
		this.associatedDataTypes.add(dataType);
	}
	
	//データタイプの解除
	public void releaseDataType(AssociatedDataType dataType) {
		this.associatedDataTypes.remove(this.associatedDataTypes.indexOf(dataType));
	}

}
