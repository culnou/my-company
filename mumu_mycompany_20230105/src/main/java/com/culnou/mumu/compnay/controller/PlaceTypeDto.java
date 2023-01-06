package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.place.type.PlaceClass;

import lombok.Data;

@Data
public class PlaceTypeDto {
	
	private String placeTypeId;
	private String companyId;
	private String placeTypeName;
	private PlaceClass placeClass;
	private String placeTypeDescription;
	private String businessDomainId;
	private String businessDomainName;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();
	private String dataTypeId;
	private Date createdAt;
	private Date updatedAt;

}
