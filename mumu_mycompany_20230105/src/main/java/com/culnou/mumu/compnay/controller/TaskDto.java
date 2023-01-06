package com.culnou.mumu.compnay.controller;


import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;

import lombok.Data;

@Data
public class TaskDto {
	
	//必須
	private int taskOrder;
	private String taskId;
	private String companyId;
	private String jobId;
	private String jobName;
	private String functionId;
	private String functionName;
	private String taskName;
	//任意
	private String taskDescription;
	private String url;
	private List<AssociatedDataType> associatedDataTypes = new ArrayList<>();

}
