package com.culnou.mumu.company.domain.model.data.type;

import java.io.Serializable;




import lombok.Getter;

@Getter
public class DataTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String dataTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DataTypeId() {}
	
	public DataTypeId(String dataTypeId) {
		this.setDataTypeId(dataTypeId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setDataTypeId(String dataTypeId) {
		//セッターで一貫性制約を保持する。
		if(dataTypeId == null) {
			throw new IllegalArgumentException("The dataTypeId may not be set to null.");
		}
		if(dataTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a dataTypeId.");
		}

		this.dataTypeId = dataTypeId;
	}
	
	@Override
	public DataTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DataTypeId(this.getDataTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DataTypeId dataTypeId = (DataTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(dataTypeId.getDataTypeId().equals(this.getDataTypeId())){
				equality = true;
			}
		}
		return equality;
	}

	

}
