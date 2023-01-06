package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedAction {
	private int actionOrder;
	private String actionId;
	private String actionName;
	private String departmentId;
	private String departmentName;

}
