package com.culnou.mumu.company.domain.model.application.type;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ApplicationTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String applicationTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ApplicationTypeId() {}
	
	public ApplicationTypeId(String applicationTypeId) {
		this.setApplicationTypeId(applicationTypeId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setApplicationTypeId(String applicationTypeId) {
		//セッターで一貫性制約を保持する。
		if(applicationTypeId == null) {
			throw new IllegalArgumentException("The applicationTypeId may not be set to null.");
		}
		if(applicationTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a applicationTypeId.");
		}

		this.applicationTypeId = applicationTypeId;
	}
	
	@Override
	public ApplicationTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ApplicationTypeId(this.getApplicationTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ApplicationTypeId applicationTypeId = (ApplicationTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(applicationTypeId.getApplicationTypeId().equals(this.getApplicationTypeId())){
				equality = true;
			}
		}
		return equality;
	}


}
