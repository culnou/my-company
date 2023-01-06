package com.culnou.mumu.company.domain.model.activity.domain;

import java.io.Serializable;


import lombok.Getter;

@Getter
public class ActivityDomainId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String activityDomainId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ActivityDomainId() {}
	
	public ActivityDomainId(String activityDomainId) {
		this.setActivityDomainId(activityDomainId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setActivityDomainId(String activityDomainId) {
		//セッターで一貫性制約を保持する。
		if(activityDomainId == null) {
			throw new IllegalArgumentException("The activityDomainId may not be set to null.");
		}
		if(activityDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a activityDomainId.");
		}

		this.activityDomainId = activityDomainId;
	}
	
	@Override
	public ActivityDomainId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ActivityDomainId(this.getActivityDomainId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ActivityDomainId activityDomainId = (ActivityDomainId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(activityDomainId.getActivityDomainId().equals(this.getActivityDomainId())){
				equality = true;
			}
		}
		return equality;
	}


}
