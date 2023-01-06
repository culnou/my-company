package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class CustomerTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String customerTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected CustomerTypeId() {}
	
	public CustomerTypeId(String customerTypeId) {
		this.setCustomerTypeId(customerTypeId);
	}
	
	protected void setCustomerTypeId(String customerTypeId) {
		//セッターで一貫性制約を保持する。
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}

		this.customerTypeId = customerTypeId;
	}
	public String customerTypeId() {
		return this.customerTypeId;
	}
	@Override
	public CustomerTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new CustomerTypeId(this.customerTypeId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			CustomerTypeId customerTypeId = (CustomerTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(customerTypeId.customerTypeId().equals(this.customerTypeId())){
				equality = true;
			}
		}
		return equality;
	}
}
