package com.culnou.mumu.compnay.controller;



import lombok.Data;

@Data
public class ProductWorkDto {
	
	private String productWorkId;
	private String companyId;
	private String productId;
	private String productServiceId;
	private String workId;
	private String workName;
	private String workDescription;
	private String url;
	private long expendedTime;

}
