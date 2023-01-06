package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class ActionPlanId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String actionPlanId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ActionPlanId() {}
	
	public ActionPlanId(String actionPlanId) {
		this.setActionPlanId(actionPlanId);
	}
	
	protected void setActionPlanId(String actionPlanId){
		//セッターで一貫性制約を保持する。
		if(actionPlanId == null) {
			throw new IllegalArgumentException("The actionPlanId may not be set to null.");
		}
		if(actionPlanId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a actionPlanId.");
		}

		this.actionPlanId = actionPlanId;
	}
	
	public String actionPlanId() {
		return this.actionPlanId;
	}
	
	@Override
	public ActionPlanId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ActionPlanId(this.actionPlanId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ActionPlanId actionPlanId = (ActionPlanId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(actionPlanId.actionPlanId().equals(this.actionPlanId())){
				equality = true;
			}
		}
		return equality;
	}

}
