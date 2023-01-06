package com.culnou.mumu.company.domain.model.knowledge.awareness;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class AwarenessId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String awarenessId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected AwarenessId() {}
	
	public AwarenessId(String awarenessId) {
		this.setAwarenessId(awarenessId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setAwarenessId(String awarenessId) {
		//セッターで一貫性制約を保持する。
		if(awarenessId == null) {
			throw new IllegalArgumentException("The_awarenessId_may_not_be_set_to_null");
		}
		if(awarenessId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_awarenessId");
		}

		this.awarenessId = awarenessId;
	}
	
	
	
	@Override
	public AwarenessId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new AwarenessId(this.getAwarenessId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			AwarenessId awarenessId = (AwarenessId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(awarenessId.getAwarenessId().equals(this.getAwarenessId())){
				equality = true;
			}
		}
		return equality;
	}

}
