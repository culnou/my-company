package com.culnou.mumu.company.domain.model.application.type;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedApplicationType {
	
	private String applicationTypeId;
	private String applicationTypeName;

}
