package com.culnou.mumu.company.domain.model.data.domain;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class DataDomainId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String dataDomainId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DataDomainId() {}
	
	public DataDomainId(String dataDomainId) {
		this.setDataDomainId(dataDomainId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setDataDomainId(String dataDomainId) {
		//セッターで一貫性制約を保持する。
		if(dataDomainId == null) {
			throw new IllegalArgumentException("The dataDomainId may not be set to null.");
		}
		if(dataDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a dataDomainId.");
		}

		this.dataDomainId = dataDomainId;
	}
	
	@Override
	public DataDomainId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DataDomainId(this.getDataDomainId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DataDomainId dataDomainId = (DataDomainId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(dataDomainId.getDataDomainId().equals(this.getDataDomainId())){
				equality = true;
			}
		}
		return equality;
	}


}
