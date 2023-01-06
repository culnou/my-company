package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;

@Data
public class CompleteWorkDto {
	
	private String workId;
	private Date endDate;
	private String companyId;
	private String productId;

}
