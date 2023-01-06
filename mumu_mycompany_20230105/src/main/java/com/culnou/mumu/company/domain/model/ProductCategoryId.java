package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class ProductCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String productCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	public ProductCategoryId() {}
	
	public ProductCategoryId(String productCategoryId) {
		this.setProductCategoryId(productCategoryId);
	}
	
	protected void setProductCategoryId(String productCategoryId) {
		//セッターで一貫性制約を保持する。
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		if(productCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productCategoryId.");
		}
		this.productCategoryId = productCategoryId;
	}
	public String productCategoryId() {
		return this.productCategoryId;
	}
	@Override
	public ProductCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductCategoryId(this.productCategoryId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductCategoryId productCategoryId = (ProductCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productCategoryId.productCategoryId().equals(this.productCategoryId())){
				equality = true;
			}
		}
		return equality;
	}
	

}
