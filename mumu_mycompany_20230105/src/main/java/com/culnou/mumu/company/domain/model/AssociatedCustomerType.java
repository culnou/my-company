package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedCustomerType {
	
	private String customerTypeId;
	private String customerTypeName;

}
