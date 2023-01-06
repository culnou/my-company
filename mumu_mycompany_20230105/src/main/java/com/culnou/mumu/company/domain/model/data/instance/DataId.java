package com.culnou.mumu.company.domain.model.data.instance;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class DataId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String dataId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DataId() {}
	
	public DataId(String dataId) {
		this.setDataId(dataId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setDataId(String dataId) {
		//セッターで一貫性制約を保持する。
		if(dataId == null) {
			throw new IllegalArgumentException("The dataId may not be set to null.");
		}
		if(dataId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a dataId.");
		}

		this.dataId = dataId;
	}
	
	@Override
	public DataId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DataId(this.getDataId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DataId dataId = (DataId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(dataId.getDataId().equals(this.getDataId())){
				equality = true;
			}
		}
		return equality;
	}



}
