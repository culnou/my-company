package com.culnou.mumu.compnay.controller;


import com.culnou.mumu.company.domain.model.common.Attribute;

import lombok.Data;
@Data
public class AttributesDto {
	
	private Attribute previousAttribute = null;
	private Attribute postAttribute = null;

}
