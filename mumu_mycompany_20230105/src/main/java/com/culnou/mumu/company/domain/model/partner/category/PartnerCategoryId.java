package com.culnou.mumu.company.domain.model.partner.category;

import java.io.Serializable;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;

import lombok.Getter;

@Getter
public class PartnerCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String partnerCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected PartnerCategoryId() {}
	
	public PartnerCategoryId(String partnerCategoryId) {
		this.setPartnerCategoryId(partnerCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setPartnerCategoryId(String partnerCategoryId) {
		//セッターで一貫性制約を保持する。
		if(partnerCategoryId == null) {
			throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
		}
		if(partnerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_partnerCategoryId");
		}

		this.partnerCategoryId = partnerCategoryId;
	}
	
	@Override
	public PartnerCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PartnerCategoryId(this.getPartnerCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PartnerCategoryId partnerCategoryId = (PartnerCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(partnerCategoryId.getPartnerCategoryId().equals(this.getPartnerCategoryId())){
				equality = true;
			}
		}
		return equality;
	}



}
