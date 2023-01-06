package com.culnou.mumu.company.domain.model.data.category;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedDataCategory {
	
	private String dataCategoryId;
	private String dataCategoryName;

}
