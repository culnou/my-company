package com.culnou.mumu.company.domain.model.data.item;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class DataItemId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String dataItemId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DataItemId() {}
	
	public DataItemId(String dataItemId) {
		this.setDataItemId(dataItemId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setDataItemId(String dataItemId) {
		//セッターで一貫性制約を保持する。
		if(dataItemId == null) {
			throw new IllegalArgumentException("The dataItemId may not be set to null.");
		}
		if(dataItemId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a dataItemId.");
		}

		this.dataItemId = dataItemId;
	}
	
	@Override
	public DataItemId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DataItemId(this.getDataItemId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DataItemId dataItemId = (DataItemId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(dataItemId.getDataItemId().equals(this.getDataItemId())){
				equality = true;
			}
		}
		return equality;
	}


}
