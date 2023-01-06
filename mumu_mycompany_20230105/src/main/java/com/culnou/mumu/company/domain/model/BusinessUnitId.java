package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class BusinessUnitId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String businessUnitId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected BusinessUnitId() {}
	
	public BusinessUnitId(String businessUnitId) {
		this.setBusinessUnitId(businessUnitId);
	}
	
	protected void setBusinessUnitId(String businessUnitId) {
		//セッターで一貫性制約を保持する。
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		if(businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessUnitId.");
		}

		this.businessUnitId = businessUnitId;
	}
	public String businessUnitId() {
		return this.businessUnitId;
	}
	@Override
	public BusinessUnitId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new BusinessUnitId(this.businessUnitId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			BusinessUnitId businessUnitId = (BusinessUnitId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(businessUnitId.businessUnitId().equals(this.businessUnitId())){
				equality = true;
			}
		}
		return equality;
	}
}
