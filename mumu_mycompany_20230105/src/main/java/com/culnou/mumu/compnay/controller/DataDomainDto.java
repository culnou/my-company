package com.culnou.mumu.compnay.controller;

import java.util.Date;


import lombok.Data;

@Data
public class DataDomainDto {
	
	private String dataDomainId;
	private String companyId;
	private String dataDomainName;
	private String dataDomainDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
