package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;

@Data
public class DataDto {
	private String dataId;
	private String dataName;
	private String dataDescription;
	private String companyId;
	private String dataCategoryId;
	private String dataCategoryName;
	private String workId;
	private String url;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;
}
