package com.culnou.mumu.company.domain.model.place.category;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedPlaceCategory {
	
	private String placeCategoryId;
	private String placeCategoryName;

}
