package com.culnou.mumu.company.domain.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductTask {
	
	private ProductTaskId productTaskId;
	private CompanyId companyId;
	private ProductTypeId productTypeId;
	private String productTypeName;
	private String productTaskName;
	//任意
	@Setter
	private String productTaskDescription;
	@Setter
	private Url url;
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	protected ProductTask() {}
	
	protected ProductTask(ProductTaskId productTaskId, CompanyId companyId, ProductTypeId productTypeId, String productTypeName, String productTaskName) {
		this.setProductTaskId(productTaskId);
		this.setCompanyId(companyId);
		this.setProductTypeId(productTypeId);
		this.setProductTypeName(productTypeName);
		this.setProductTaskName(productTaskName);
	}
	protected void setProductTaskId(ProductTaskId productTaskId) {
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId may not be set to null.");
		}
		this.productTaskId = productTaskId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setProductTypeId(ProductTypeId productTypeId) {
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		this.productTypeId =productTypeId;
	}
	
	public void setProductTypeName(String productTypeName) {
		if(productTypeName == null) {
			throw new IllegalArgumentException("The productTypeName may not be set to null.");
		}
		if(productTypeName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeName.");
		}
		this.productTypeName = productTypeName;
	}
	
	public void setProductTaskName(String productTaskName) {
		if(productTaskName == null) {
			throw new IllegalArgumentException("The productTaskName may not be set to null.");
		}
		if(productTaskName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTaskName.");
		}
		this.productTaskName = productTaskName;
	}
	
	

}
