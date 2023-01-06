package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class BusinessProcessId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String businessProcessId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected BusinessProcessId() {}
	
	public BusinessProcessId(String businessProcessId) {
		this.setBusinessProcessId(businessProcessId);
	}
	
	protected void setBusinessProcessId(String businessProcessId){
		//セッターで一貫性制約を保持する。
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		if(businessProcessId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessProcessId.");
		}

		this.businessProcessId = businessProcessId;
	}
	
	public String businessProcessId() {
		return this.businessProcessId;
	}
	
	@Override
	public BusinessProcessId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new BusinessProcessId(this.businessProcessId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			BusinessProcessId businessProcessId = (BusinessProcessId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(businessProcessId.businessProcessId().equals(this.businessProcessId())){
				equality = true;
			}
		}
		return equality;
	}

}
