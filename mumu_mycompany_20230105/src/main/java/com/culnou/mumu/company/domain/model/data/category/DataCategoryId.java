package com.culnou.mumu.company.domain.model.data.category;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class DataCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String dataCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DataCategoryId() {}
	
	public DataCategoryId(String dataCategoryId) {
		this.setDataCategoryId(dataCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setDataCategoryId(String dataCategoryId) {
		//セッターで一貫性制約を保持する。
		if(dataCategoryId == null) {
			throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
		}
		if(dataCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_dataCategoryId");
		}

		this.dataCategoryId = dataCategoryId;
	}
	
	@Override
	public DataCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DataCategoryId(this.getDataCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DataCategoryId dataCategoryId = (DataCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(dataCategoryId.getDataCategoryId().equals(this.getDataCategoryId())){
				equality = true;
			}
		}
		return equality;
	}


}
