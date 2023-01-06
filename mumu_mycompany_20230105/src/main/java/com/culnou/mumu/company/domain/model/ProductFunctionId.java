package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import lombok.Getter;

public class ProductFunctionId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String productFunctionId;
	
	protected ProductFunctionId() {}
	
	public ProductFunctionId(String productFunctionId) {
		this.setProductFunctionId(productFunctionId);
	}
	protected void setProductFunctionId(String productFunctionId){
		//セッターで一貫性制約を保持する。
		if(productFunctionId == null) {
			throw new IllegalArgumentException("The productFunctionId may not be set to null.");
		}
		if(productFunctionId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productFunctionId.");
		}

		this.productFunctionId = productFunctionId;
	}
	
	@Override
	public ProductFunctionId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductFunctionId(this.productFunctionId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductFunctionId productFunctionId = (ProductFunctionId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productFunctionId.getProductFunctionId().equals(this.getProductFunctionId())){
				equality = true;
			}
		}
		return equality;
	}

}
