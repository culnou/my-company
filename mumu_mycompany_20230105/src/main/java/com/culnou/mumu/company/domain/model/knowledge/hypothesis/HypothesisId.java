package com.culnou.mumu.company.domain.model.knowledge.hypothesis;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class HypothesisId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String hypothesisId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected HypothesisId() {}
	
	public HypothesisId(String hypothesisId) {
		this.setHypothesisId(hypothesisId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setHypothesisId(String hypothesisId) {
		//セッターで一貫性制約を保持する。
		if(hypothesisId == null) {
			throw new IllegalArgumentException("The_hypothesisId_may_not_be_set_to_null");
		}
		if(hypothesisId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_hypothesisId");
		}

		this.hypothesisId = hypothesisId;
	}
	
	
	
	@Override
	public HypothesisId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new HypothesisId(this.getHypothesisId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			HypothesisId hypothesisId = (HypothesisId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(hypothesisId.getHypothesisId().equals(this.getHypothesisId())){
				equality = true;
			}
		}
		return equality;
	}

}
