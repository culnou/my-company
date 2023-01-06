package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class BusinessDomainId implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String businessDomainId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected BusinessDomainId() {}
	
	public BusinessDomainId(String businessDomainId) {
		this.setBusinessDomainId(businessDomainId);
	}
	
	protected void setBusinessDomainId(String businessDomainId){
		//セッターで一貫性制約を保持する。
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}

		this.businessDomainId = businessDomainId;
	}
	
	public String businessDomainId() {
		return this.businessDomainId;
	}
	
	@Override
	public BusinessDomainId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new BusinessDomainId(this.businessDomainId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			BusinessDomainId businessDomainId = (BusinessDomainId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(businessDomainId.businessDomainId().equals(this.businessDomainId())){
				equality = true;
			}
		}
		return equality;
	}

}
