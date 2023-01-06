package com.culnou.mumu.compnay.controller;



import java.util.Date;

import lombok.Data;

@Data
public class ProductTaskDto {
	private String productTaskId;
	private String companyId;
	private String productTypeId;
	private String productTypeName;
	private String productTaskName;
	//任意
	private String productTaskDescription;
	private String url;
	private Date createdAt;
	private Date updatedAt;

}
