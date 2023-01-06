package com.culnou.mumu.company.domain.model.application.category;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ApplicationCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String applicationCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ApplicationCategoryId() {}
	
	public ApplicationCategoryId(String applicationCategoryId) {
		this.setApplicationCategoryId(applicationCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setApplicationCategoryId(String applicationCategoryId) {
		//セッターで一貫性制約を保持する。
		if(applicationCategoryId == null) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		if(applicationCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_applicationCategoryId");
		}

		this.applicationCategoryId = applicationCategoryId;
	}
	
	@Override
	public ApplicationCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ApplicationCategoryId(this.getApplicationCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ApplicationCategoryId applicationCategoryId = (ApplicationCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(applicationCategoryId.getApplicationCategoryId().equals(this.getApplicationCategoryId())){
				equality = true;
			}
		}
		return equality;
	}



}
