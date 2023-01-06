package com.culnou.mumu.company.domain.model.it.instance;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ItId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String itId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ItId() {}
	
	public ItId(String itId) {
		this.setItId(itId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setItId(String itId) {
		//セッターで一貫性制約を保持する。
		if(itId == null) {
			throw new IllegalArgumentException("The itId may not be set to null.");
		}
		if(itId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a itId.");
		}

		this.itId = itId;
	}
	
	@Override
	public ItId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ItId(this.getItId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ItId itId = (ItId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(itId.getItId().equals(this.getItId())){
				equality = true;
			}
		}
		return equality;
	}


}
