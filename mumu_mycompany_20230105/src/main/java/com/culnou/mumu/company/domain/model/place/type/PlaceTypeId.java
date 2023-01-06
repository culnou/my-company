package com.culnou.mumu.company.domain.model.place.type;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class PlaceTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String placeTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected PlaceTypeId() {}
	
	public PlaceTypeId(String placeTypeId) {
		this.setPlaceTypeId(placeTypeId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setPlaceTypeId(String placeTypeId) {
		//セッターで一貫性制約を保持する。
		if(placeTypeId == null) {
			throw new IllegalArgumentException("The placeTypeId may not be set to null.");
		}
		if(placeTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a placeTypeId.");
		}

		this.placeTypeId = placeTypeId;
	}
	
	@Override
	public PlaceTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PlaceTypeId(this.getPlaceTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PlaceTypeId placeTypeId = (PlaceTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(placeTypeId.getPlaceTypeId().equals(this.getPlaceTypeId())){
				equality = true;
			}
		}
		return equality;
	}


}
