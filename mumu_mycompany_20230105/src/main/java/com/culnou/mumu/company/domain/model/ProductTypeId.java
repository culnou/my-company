package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class ProductTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6314393371049212380L;
	
	String productTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ProductTypeId() {}
	
	public ProductTypeId(String productTypeId) {
		this.setProductTypeId(productTypeId);
	}
	
	protected void setProductTypeId(String productTypeId) {
		//セッターで一貫性制約を保持する。
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		if(productTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}

		this.productTypeId = productTypeId;
	}
	public String productTypeId() {
		return this.productTypeId;
	}
	@Override
	public ProductTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductTypeId(this.productTypeId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductTypeId productTypeId = (ProductTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productTypeId.productTypeId().equals(this.productTypeId())){
				equality = true;
			}
		}
		return equality;
	}
}
