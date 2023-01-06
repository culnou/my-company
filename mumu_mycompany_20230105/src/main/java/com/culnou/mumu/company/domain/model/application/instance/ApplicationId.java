package com.culnou.mumu.company.domain.model.application.instance;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ApplicationId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String applicationId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ApplicationId() {}
	
	public ApplicationId(String applicationId) {
		this.setApplicationId(applicationId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setApplicationId(String applicationId) {
		//セッターで一貫性制約を保持する。
		if(applicationId == null) {
			throw new IllegalArgumentException("The applicationId may not be set to null.");
		}
		if(applicationId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a applicationId.");
		}

		this.applicationId = applicationId;
	}
	
	@Override
	public ApplicationId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ApplicationId(this.getApplicationId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ApplicationId applicationId = (ApplicationId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(applicationId.getApplicationId().equals(this.getApplicationId())){
				equality = true;
			}
		}
		return equality;
	}


}
