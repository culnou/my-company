package com.culnou.mumu.compnay.controller;

import java.util.Date;



import lombok.Data;

@Data
public class ItDto {
	
	private String itId;
	private String companyId;
	private String itName;
	private String itDescription;
	private String businessUnitId;
	private String itCategoryId;
	private String itCategoryName;
	private String placeId;
	private String url;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;

}
