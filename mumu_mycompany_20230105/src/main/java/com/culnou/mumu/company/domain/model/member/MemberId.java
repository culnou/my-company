package com.culnou.mumu.company.domain.model.member;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class MemberId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected MemberId() {}
	
	public MemberId(String memberId) {
		this.setMemberId(memberId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setMemberId(String memberId) {
		//セッターで一貫性制約を保持する。
		if(memberId == null) {
			throw new IllegalArgumentException("The memberId may not be set to null.");
		}
		if(memberId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a memberId.");
		}

		this.memberId = memberId;
	}
	
	@Override
	public MemberId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new MemberId(this.getMemberId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			MemberId memberId = (MemberId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(memberId.getMemberId().equals(this.getMemberId())){
				equality = true;
			}
		}
		return equality;
	}

}
