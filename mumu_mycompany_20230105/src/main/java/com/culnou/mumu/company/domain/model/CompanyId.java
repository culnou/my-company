package com.culnou.mumu.company.domain.model;

import java.io.Serializable;



public class CompanyId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected CompanyId() {}
	
	public CompanyId(String id) {
		this.setId(id);
	}
	
	protected void setId(String id) {
		
		//セッターで一貫性制約を保持する。
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		if(id.isEmpty()) {
			throw new IllegalArgumentException("Must provide a id.");
		}
		this.id= id;
	}
	
	public String id() {
		return this.id;
	}
	@Override
	public CompanyId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new CompanyId(this.id);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			CompanyId companyId = (CompanyId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(companyId.id().equals(this.id())){
				equality = true;
			}
		}
		return equality;
	}

}
