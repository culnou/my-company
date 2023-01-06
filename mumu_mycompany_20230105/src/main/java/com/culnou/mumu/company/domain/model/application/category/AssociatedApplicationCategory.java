package com.culnou.mumu.company.domain.model.application.category;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedApplicationCategory {
	
	private String applicationCategoryId;
	private String applicationCategoryName;

}
