package com.culnou.mumu.company.domain.model.service;

import java.io.Serializable;


import lombok.Getter;
@Getter
public class ProductServiceId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productServiceId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ProductServiceId() {}
	
	public ProductServiceId(String productServiceId) {
		this.setProductServiceId(productServiceId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setProductServiceId(String productServiceId) {
		//セッターで一貫性制約を保持する。
		if(productServiceId == null) {
			throw new IllegalArgumentException("The productServiceId may not be set to null.");
		}
		if(productServiceId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productServiceId.");
		}

		this.productServiceId = productServiceId;
	}
	
	@Override
	public ProductServiceId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductServiceId(this.getProductServiceId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductServiceId productServiceId = (ProductServiceId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productServiceId.getProductServiceId().equals(this.getProductServiceId())){
				equality = true;
			}
		}
		return equality;
	}

}
