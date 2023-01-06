package com.culnou.mumu.company.domain.model.product.instance;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedProductFunction {
	
	private String productFunctionId;
	private String productFunctionName;
	private String productFunctionUrl;
	private String parentProductId;
	private String functionId;

}
