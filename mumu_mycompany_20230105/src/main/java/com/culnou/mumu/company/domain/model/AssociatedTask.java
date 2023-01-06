package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedTask {
	
	private int taskOrder;
	private String taskId;
	private String taskName;
	private String jobId;
	private String jobName;

}
