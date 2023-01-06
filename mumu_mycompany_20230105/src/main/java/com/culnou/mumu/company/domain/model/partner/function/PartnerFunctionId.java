package com.culnou.mumu.company.domain.model.partner.function;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class PartnerFunctionId  implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String partnerFunctionId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected PartnerFunctionId() {}
	
	public PartnerFunctionId(String partnerFunctionId) {
		this.setPartnerFunctionId(partnerFunctionId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setPartnerFunctionId(String partnerFunctionId) {
		//セッターで一貫性制約を保持する。
		if(partnerFunctionId == null) {
			throw new IllegalArgumentException("The partnerFunctionId may not be set to null.");
		}
		if(partnerFunctionId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a partnerFunctionId.");
		}

		this.partnerFunctionId = partnerFunctionId;
	}
	
	@Override
	public PartnerFunctionId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PartnerFunctionId(this.getPartnerFunctionId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PartnerFunctionId partnerFunctionId = (PartnerFunctionId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(partnerFunctionId.getPartnerFunctionId().equals(this.getPartnerFunctionId())){
				equality = true;
			}
		}
		return equality;
	}



}
