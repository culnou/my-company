package com.culnou.mumu.company.domain.model.knowledge.result;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class ResultId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String resultId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ResultId() {}
	
	public ResultId(String resultId) {
		this.setResultId(resultId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setResultId(String resultId) {
		//セッターで一貫性制約を保持する。
		if(resultId == null) {
			throw new IllegalArgumentException("The_resultId_may_not_be_set_to_null");
		}
		if(resultId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_resultId");
		}

		this.resultId = resultId;
	}
	
	
	
	@Override
	public ResultId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ResultId(this.getResultId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ResultId resultId = (ResultId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(resultId.getResultId().equals(this.getResultId())){
				equality = true;
			}
		}
		return equality;
	}

}
