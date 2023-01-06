package com.culnou.mumu.company.domain.model.partner.type;

import java.io.Serializable;

import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeId;

import lombok.Getter;
@Getter
public class PartnerTypeId  implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String partnerTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected PartnerTypeId() {}
	
	public PartnerTypeId(String partnerTypeId) {
		this.setPartnerTypeId(partnerTypeId);
	}
	//セッターをprivateにしてコンストラクタでIDを設定することで後でIDを変更されないので不変性が保証される。
	private void setPartnerTypeId(String partnerTypeId) {
		//セッターで一貫性制約を保持する。
		if(partnerTypeId == null) {
			throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
		}
		if(partnerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_partnerTypeId");
		}

		this.partnerTypeId = partnerTypeId;
	}
	
	@Override
	public PartnerTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PartnerTypeId(this.getPartnerTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PartnerTypeId partnerTypeId = (PartnerTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(partnerTypeId.getPartnerTypeId().equals(this.getPartnerTypeId())){
				equality = true;
			}
		}
		return equality;
	}

}
