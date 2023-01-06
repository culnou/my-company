package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import lombok.Getter;

public class ProductTaskId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String productTaskId;
	
	protected ProductTaskId() {}

	public ProductTaskId(String productTaskId) {
		this.setProductTaskId(productTaskId);
	}
	
	protected void setProductTaskId(String productTaskId){
		//セッターで一貫性制約を保持する。
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId may not be set to null.");
		}
		if(productTaskId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTaskId.");
		}

		this.productTaskId = productTaskId;
	}
	
	@Override
	public ProductTaskId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductTaskId(this.productTaskId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductTaskId productTaskId = (ProductTaskId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productTaskId.getProductTaskId().equals(this.getProductTaskId())){
				equality = true;
			}
		}
		return equality;
	}

}
