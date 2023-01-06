package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedCustomerCategory {
	
	private String customerCategoryId;
	private String customerCategoryName;

}
