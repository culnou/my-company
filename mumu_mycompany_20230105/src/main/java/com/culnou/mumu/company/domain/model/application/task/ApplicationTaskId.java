package com.culnou.mumu.company.domain.model.application.task;

import java.io.Serializable;


import lombok.Getter;

@Getter
public class ApplicationTaskId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String applicationTaskId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ApplicationTaskId() {}
	
	public ApplicationTaskId(String applicationTaskId) {
		this.setApplicationTaskId(applicationTaskId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setApplicationTaskId(String applicationTaskId) {
		//セッターで一貫性制約を保持する。
		if(applicationTaskId == null) {
			throw new IllegalArgumentException("The applicationTaskId may not be set to null.");
		}
		if(applicationTaskId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a applicationTaskId.");
		}

		this.applicationTaskId = applicationTaskId;
	}
	
	@Override
	public ApplicationTaskId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ApplicationTaskId(this.getApplicationTaskId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ApplicationTaskId applicationTaskId = (ApplicationTaskId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(applicationTaskId.getApplicationTaskId().equals(this.getApplicationTaskId())){
				equality = true;
			}
		}
		return equality;
	}



}
