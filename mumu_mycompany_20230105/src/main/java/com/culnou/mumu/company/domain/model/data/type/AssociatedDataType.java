package com.culnou.mumu.company.domain.model.data.type;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
@Embeddable
public class AssociatedDataType {
	
	private String dataTypeId;
	private String dataTypeName;
	@Enumerated(EnumType.STRING)
	private DataAccessType dataAccessType;

}
