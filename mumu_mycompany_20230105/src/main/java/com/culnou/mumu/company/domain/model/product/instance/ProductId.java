package com.culnou.mumu.company.domain.model.product.instance;

import java.io.Serializable;


import lombok.Getter;

@Getter
public class ProductId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ProductId() {}
	
	public ProductId(String productId) {
		this.setProductId(productId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setProductId(String productId) {
		//セッターで一貫性制約を保持する。
		if(productId == null) {
			throw new IllegalArgumentException("The_id_may_not_be_set_to_null");
		}
		if(productId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_id");
		}

		this.productId = productId;
	}
	
	@Override
	public ProductId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductId(this.getProductId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductId productId = (ProductId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productId.getProductId().equals(this.getProductId())){
				equality = true;
			}
		}
		return equality;
	}

}
