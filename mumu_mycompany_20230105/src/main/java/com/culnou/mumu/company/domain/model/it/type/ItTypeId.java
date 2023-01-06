package com.culnou.mumu.company.domain.model.it.type;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ItTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String itTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ItTypeId() {}
	
	public ItTypeId(String itTypeId) {
		this.setItTypeId(itTypeId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setItTypeId(String itTypeId) {
		//セッターで一貫性制約を保持する。
		if(itTypeId == null) {
			throw new IllegalArgumentException("The itTypeId may not be set to null.");
		}
		if(itTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a itTypeId.");
		}

		this.itTypeId = itTypeId;
	}
	
	@Override
	public ItTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ItTypeId(this.getItTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ItTypeId itTypeId = (ItTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(itTypeId.getItTypeId().equals(this.getItTypeId())){
				equality = true;
			}
		}
		return equality;
	}


}
