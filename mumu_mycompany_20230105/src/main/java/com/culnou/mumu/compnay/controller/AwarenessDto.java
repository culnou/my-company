package com.culnou.mumu.compnay.controller;

import java.util.Date;

import lombok.Data;

@Data
public class AwarenessDto {
	
	private String awarenessId;
	private String companyId;
	private String memberId;
	private String awarenessName;
	private String awarenessDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
