package com.culnou.mumu.compnay.controller;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.culnou.mumu.company.domain.model.data.type.DataAccessType;

import lombok.Data;
@Data
public class AssociatedDataTypeDto {
	private String dataTypeId;
	private String dataTypeName;
	@Enumerated(EnumType.STRING)
	private DataAccessType dataAccessType;

}
