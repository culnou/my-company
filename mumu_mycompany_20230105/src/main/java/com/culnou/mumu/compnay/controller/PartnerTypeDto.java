package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;


import lombok.Data;
@Data
public class PartnerTypeDto {
	
	private String companyId;
	private String businessDomainId;
	private String partnerTypeId;
	private String partnerTypeName;
	private String partnerTypeDescription;
	private List<Indicator> indicators = new ArrayList<>();
	private String dataTypeId;
	private String url;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;

}
