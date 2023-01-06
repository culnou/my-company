package com.culnou.mumu.company.domain.model.it.type;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedItType {
	
	private String itTypeId;
	private String itTypeName;

}
