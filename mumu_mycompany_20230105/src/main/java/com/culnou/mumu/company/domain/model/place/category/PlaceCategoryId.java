package com.culnou.mumu.company.domain.model.place.category;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class PlaceCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String placeCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected PlaceCategoryId() {}
	
	public PlaceCategoryId(String placeCategoryId) {
		this.setPlaceCategoryId(placeCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setPlaceCategoryId(String placeCategoryId) {
		//セッターで一貫性制約を保持する。
		if(placeCategoryId == null) {
			throw new IllegalArgumentException("The placeCategoryId may not be set to null.");
		}
		if(placeCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a placeCategoryId.");
		}

		this.placeCategoryId = placeCategoryId;
	}
	
	@Override
	public PlaceCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PlaceCategoryId(this.getPlaceCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PlaceCategoryId placeCategoryId = (PlaceCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(placeCategoryId.getPlaceCategoryId().equals(this.getPlaceCategoryId())){
				equality = true;
			}
		}
		return equality;
	}


}
