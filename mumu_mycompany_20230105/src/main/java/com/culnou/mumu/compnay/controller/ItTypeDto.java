package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.it.type.ItClass;

import lombok.Data;

@Data
public class ItTypeDto {
	private String itTypeId;
	private String companyId;
	private String itTypeName;
	private ItClass itClass;
	private String itTypeDescription;
	private String businessDomainId;
	private String businessDomainName;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();
	private Date createdAt;
	private Date updatedAt;

}
