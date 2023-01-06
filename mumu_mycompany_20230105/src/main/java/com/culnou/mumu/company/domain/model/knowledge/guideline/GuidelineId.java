package com.culnou.mumu.company.domain.model.knowledge.guideline;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class GuidelineId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String guidelineId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected GuidelineId() {}
	
	public GuidelineId(String guidelineId) {
		this.setGuidelineId(guidelineId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setGuidelineId(String guidelineId) {
		//セッターで一貫性制約を保持する。
		if(guidelineId == null) {
			throw new IllegalArgumentException("The_guidelineId_may_not_be_set_to_null");
		}
		if(guidelineId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_guidelineId");
		}

		this.guidelineId = guidelineId;
	}
	
	
	
	@Override
	public GuidelineId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new GuidelineId(this.getGuidelineId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			GuidelineId guidelineId = (GuidelineId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(guidelineId.getGuidelineId().equals(this.getGuidelineId())){
				equality = true;
			}
		}
		return equality;
	}

}
