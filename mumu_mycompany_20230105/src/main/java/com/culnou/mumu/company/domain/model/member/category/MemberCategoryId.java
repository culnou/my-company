package com.culnou.mumu.company.domain.model.member.category;

import java.io.Serializable;

import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;

import lombok.Getter;

@Getter
public class MemberCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String memberCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected MemberCategoryId() {}
	
	public MemberCategoryId(String memberCategoryId) {
		this.setMemberCategoryId(memberCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setMemberCategoryId(String memberCategoryId) {
		//セッターで一貫性制約を保持する。
		if(memberCategoryId == null) {
			throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
		}
		if(memberCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_memberCategoryId");
		}

		this.memberCategoryId = memberCategoryId;
	}
	
	@Override
	public MemberCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new MemberCategoryId(this.getMemberCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			MemberCategoryId memberCategoryId = (MemberCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(memberCategoryId.getMemberCategoryId().equals(this.getMemberCategoryId())){
				equality = true;
			}
		}
		return equality;
	}



}
