package com.culnou.mumu.company.domain.model.place.instance;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class PlaceId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String placeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected PlaceId() {}
	
	public PlaceId(String placeId) {
		this.setPlaceId(placeId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setPlaceId(String placeId) {
		//セッターで一貫性制約を保持する。
		if(placeId == null) {
			throw new IllegalArgumentException("The placeId may not be set to null.");
		}
		if(placeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a placeId.");
		}

		this.placeId = placeId;
	}
	
	@Override
	public PlaceId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PlaceId(this.getPlaceId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PlaceId placeId = (PlaceId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(placeId.getPlaceId().equals(this.getPlaceId())){
				equality = true;
			}
		}
		return equality;
	}


}
