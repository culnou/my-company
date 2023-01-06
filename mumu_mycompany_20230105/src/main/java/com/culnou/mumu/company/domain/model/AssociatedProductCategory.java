package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedProductCategory {
	
	private String productCategoryId;
	private String productCategoryName;

}
