package com.culnou.mumu.company.domain.model.it.category;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ItCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String itCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ItCategoryId() {}
	
	public ItCategoryId(String itCategoryId) {
		this.setItCategoryId(itCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setItCategoryId(String itCategoryId) {
		//セッターで一貫性制約を保持する。
		if(itCategoryId == null) {
			throw new IllegalArgumentException("The itCategoryId may not be set to null.");
		}
		if(itCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a itCategoryId.");
		}

		this.itCategoryId = itCategoryId;
	}
	
	@Override
	public ItCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ItCategoryId(this.getItCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ItCategoryId itCategoryId = (ItCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(itCategoryId.getItCategoryId().equals(this.getItCategoryId())){
				equality = true;
			}
		}
		return equality;
	}


}
