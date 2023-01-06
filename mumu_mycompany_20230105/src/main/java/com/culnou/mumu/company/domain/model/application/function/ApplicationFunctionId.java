package com.culnou.mumu.company.domain.model.application.function;

import java.io.Serializable;


import lombok.Getter;

@Getter
public class ApplicationFunctionId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String applicationFunctionId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ApplicationFunctionId() {}
	
	public ApplicationFunctionId(String applicationFunctionId) {
		this.setApplicationFunctionId(applicationFunctionId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setApplicationFunctionId(String applicationFunctionId) {
		//セッターで一貫性制約を保持する。
		if(applicationFunctionId == null) {
			throw new IllegalArgumentException("The applicationFunctionId may not be set to null.");
		}
		if(applicationFunctionId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a applicationFunctionId.");
		}

		this.applicationFunctionId = applicationFunctionId;
	}
	
	@Override
	public ApplicationFunctionId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ApplicationFunctionId(this.getApplicationFunctionId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ApplicationFunctionId applicationFunctionId = (ApplicationFunctionId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(applicationFunctionId.getApplicationFunctionId().equals(this.getApplicationFunctionId())){
				equality = true;
			}
		}
		return equality;
	}




}
