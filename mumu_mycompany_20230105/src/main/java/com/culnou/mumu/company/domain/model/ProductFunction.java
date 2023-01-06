package com.culnou.mumu.company.domain.model;

import lombok.Getter;
import lombok.Setter;
@Getter
public class ProductFunction {
	
	private ProductFunctionId productFunctionId;
	private CompanyId companyId;
	private ProductCategoryId productCategoryId;
	private ProductTaskId productTaskId;
	private String productTaskName;
	private String productCategoryName;
	private String productFunctionName;
	//任意
	@Setter
	private String productFunctionDescription;
	@Setter
	private Url url;
	@Setter
	private Function function;
	
	protected ProductFunction() {};
	
	protected ProductFunction(ProductFunctionId productFunctionId, CompanyId companyId, ProductCategoryId productCategoryId, String ProductCategoryName, ProductTaskId productTaskId, String productTaskName, String ProductFunctionName) {
		this.setProductFunctionId(productFunctionId);
		this.setCompanyId(companyId);
		this.setProductCategoryId(productCategoryId);
		this.setProductCategoryName(ProductCategoryName);
		this.setProductTaskId(productTaskId);
		this.setProductTaskName(productTaskName);
		this.setProductFunctionName(ProductFunctionName);
	}
	
	protected void setProductFunctionId(ProductFunctionId productFunctionId) {
		if(productFunctionId == null) {
			throw new IllegalArgumentException("The productFunctionId may not be set to null.");
		}
		this.productFunctionId = productFunctionId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId = companyId;
	}
	
	public void setProductCategoryId(ProductCategoryId productCategoryId) {
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		this.productCategoryId =productCategoryId;
	}
	
	public void setProductTaskId(ProductTaskId productTaskId) {
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId may not be set to null.");
		}
		this.productTaskId =productTaskId;
	}
	
	public void setProductTaskName(String productTaskName) {
		if(productTaskName == null) {
			throw new IllegalArgumentException("The productTaskName may not be set to null.");
		}
		this.productTaskName =productTaskName;
	}
	
	public void setProductCategoryName(String productCategoryName) {
		if(productCategoryName == null) {
			throw new IllegalArgumentException("The productCategoryName may not be set to null.");
		}
		if(productCategoryName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productCategoryName.");
		}
		this.productCategoryName = productCategoryName;
	}
	
	public void setProductFunctionName(String productFunctionName) {
		if(productFunctionName == null) {
			throw new IllegalArgumentException("The productFunctionName may not be set to null.");
		}
		if(productFunctionName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productFunctionName.");
		}
		this.productFunctionName = productFunctionName;
	}
	
	

}
