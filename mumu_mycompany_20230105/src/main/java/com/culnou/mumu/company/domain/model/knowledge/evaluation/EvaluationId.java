package com.culnou.mumu.company.domain.model.knowledge.evaluation;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class EvaluationId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String evaluationId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected EvaluationId() {}
	
	public EvaluationId(String evaluationId) {
		this.setEvaluationId(evaluationId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setEvaluationId(String evaluationId) {
		//セッターで一貫性制約を保持する。
		if(evaluationId == null) {
			throw new IllegalArgumentException("The_evaluationId_may_not_be_set_to_null");
		}
		if(evaluationId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_evaluationId");
		}

		this.evaluationId = evaluationId;
	}
	
	
	
	@Override
	public EvaluationId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new EvaluationId(this.getEvaluationId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			EvaluationId evaluationId = (EvaluationId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(evaluationId.getEvaluationId().equals(this.getEvaluationId())){
				equality = true;
			}
		}
		return equality;
	}

}
