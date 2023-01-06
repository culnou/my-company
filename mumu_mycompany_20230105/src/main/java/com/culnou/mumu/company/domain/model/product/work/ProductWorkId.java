package com.culnou.mumu.company.domain.model.product.work;

import java.io.Serializable;


import lombok.Getter;
@Getter
public class ProductWorkId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String productWorkId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ProductWorkId() {}
	
	public ProductWorkId(String productWorkId) {
		this.setProductWorkId(productWorkId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setProductWorkId(String productWorkId) {
		//セッターで一貫性制約を保持する。
		if(productWorkId == null) {
			throw new IllegalArgumentException("The productWorkId may not be set to null.");
		}
		if(productWorkId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productWorkId.");
		}

		this.productWorkId = productWorkId;
	}
	
	@Override
	public ProductWorkId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProductWorkId(this.getProductWorkId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProductWorkId productWorkId = (ProductWorkId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(productWorkId.getProductWorkId().equals(this.getProductWorkId())){
				equality = true;
			}
		}
		return equality;
	}

}
