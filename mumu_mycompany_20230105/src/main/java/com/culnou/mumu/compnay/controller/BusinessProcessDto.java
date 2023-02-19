package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;

import java.util.List;

import com.culnou.mumu.company.domain.model.AssociatedBusinessProcess;
import com.culnou.mumu.company.domain.model.AssociatedTask;
import com.culnou.mumu.company.domain.model.BusinessProcessClass;

import com.culnou.mumu.company.domain.model.BusinessProcessType;
import com.culnou.mumu.company.domain.model.Indicator;

import lombok.Data;


@Data
public class BusinessProcessDto {
	//必須
	private String businessProcessId;
	private String companyId;
	private BusinessProcessType businessProcessType;
	private String businessProcessName;
	//任意
	private String businessDomainId;
	private String businessDomainName;
	private List<AssociatedTask> associatedTasks = new ArrayList<>();
	private List<AssociatedBusinessProcess> associatedBusinessProcesses = new ArrayList<>();
	private String businessProcessDescription;
	private BusinessProcessClass businessProcessClass;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();
	private boolean used;
	private String parent;
}
