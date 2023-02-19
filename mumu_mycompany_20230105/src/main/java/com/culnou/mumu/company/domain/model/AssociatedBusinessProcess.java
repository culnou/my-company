package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedBusinessProcess {
	
	private int businessProcessOrder;
	private String businessProcessId;
	private String businessProcessName;
	

}
