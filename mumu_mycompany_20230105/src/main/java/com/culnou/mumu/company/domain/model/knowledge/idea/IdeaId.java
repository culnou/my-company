package com.culnou.mumu.company.domain.model.knowledge.idea;

import java.io.Serializable;

import com.culnou.mumu.company.domain.model.knowledge.idea.IdeaId;

import lombok.Getter;

@Getter
public class IdeaId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String ideaId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected IdeaId() {}
	
	public IdeaId(String ideaId) {
		this.setIdeaId(ideaId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setIdeaId(String ideaId) {
		//セッターで一貫性制約を保持する。
		if(ideaId == null) {
			throw new IllegalArgumentException("The_ideaId_may_not_be_set_to_null");
		}
		if(ideaId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_ideaId");
		}

		this.ideaId = ideaId;
	}
	
	
	
	@Override
	public IdeaId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new IdeaId(this.getIdeaId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			IdeaId ideaId = (IdeaId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(ideaId.getIdeaId().equals(this.getIdeaId())){
				equality = true;
			}
		}
		return equality;
	}

}
