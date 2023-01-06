package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;
@Data
public class PlaceDto {
	
	private String placeId;
	private String placeName;
	private String placeDescription;
	private String companyId;
	private String businessUnitId;
	private String placeCategoryId;
	private String placeCategoryName;
	private String url;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;

}
