package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class ActionId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String actionId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ActionId() {}
	
	public ActionId(String actionId) {
		this.setActionId(actionId);
	}
	
	protected void setActionId(String actionId){
		//セッターで一貫性制約を保持する。
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		if(actionId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a actionId.");
		}

		this.actionId = actionId;
	}
	
	public String actionId() {
		return this.actionId;
	}
	
	@Override
	public ActionId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ActionId(this.actionId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ActionId actionId = (ActionId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(actionId.actionId().equals(this.actionId())){
				equality = true;
			}
		}
		return equality;
	}

}
