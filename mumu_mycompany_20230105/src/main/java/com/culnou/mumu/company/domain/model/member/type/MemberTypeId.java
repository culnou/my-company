package com.culnou.mumu.company.domain.model.member.type;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class MemberTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected MemberTypeId() {}
	
	public MemberTypeId(String memberTypeId) {
		this.setMemberTypeId(memberTypeId);
	}
	//セッターをprivateにしてコンストラクタでIDを設定することで後でIDを変更されないので不変性が保証される。
	private void setMemberTypeId(String memberTypeId) {
		//セッターで一貫性制約を保持する。
		if(memberTypeId == null) {
			throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
		}
		if(memberTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_memberTypeId");
		}

		this.memberTypeId = memberTypeId;
	}
	
	@Override
	public MemberTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new MemberTypeId(this.getMemberTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			MemberTypeId memberTypeId = (MemberTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(memberTypeId.getMemberTypeId().equals(this.getMemberTypeId())){
				equality = true;
			}
		}
		return equality;
	}


}
