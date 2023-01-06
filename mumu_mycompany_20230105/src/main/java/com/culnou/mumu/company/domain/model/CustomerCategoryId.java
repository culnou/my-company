package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class CustomerCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String customerCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	public CustomerCategoryId() {}
	
	public CustomerCategoryId(String customerCategoryId) {
		this.setCustomerCategoryId(customerCategoryId);
	}
	
	protected void setCustomerCategoryId(String customerCategoryId) {
		//セッターで一貫性制約を保持する。
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		if(customerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerCategoryId.");
		}
		this.customerCategoryId = customerCategoryId;
	}
	public String customerCategoryId() {
		return this.customerCategoryId;
	}
	@Override
	public CustomerCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new CustomerCategoryId(this.customerCategoryId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			CustomerCategoryId customerCategoryId = (CustomerCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(customerCategoryId.customerCategoryId().equals(this.customerCategoryId())){
				equality = true;
			}
		}
		return equality;
	}
	

}
