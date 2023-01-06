package com.culnou.mumu.company.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedProductType {
	
	private String productTypeId;
	private String productTypeName;

}
