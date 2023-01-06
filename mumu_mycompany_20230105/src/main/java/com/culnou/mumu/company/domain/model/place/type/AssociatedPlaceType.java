package com.culnou.mumu.company.domain.model.place.type;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedPlaceType {
	
	private String placeTypeId;
	private String placeTypeName;

}
