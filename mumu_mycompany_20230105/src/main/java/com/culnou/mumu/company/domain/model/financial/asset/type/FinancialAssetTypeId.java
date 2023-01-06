package com.culnou.mumu.company.domain.model.financial.asset.type;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class FinancialAssetTypeId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String financialAssetTypeId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected FinancialAssetTypeId() {}
	
	public FinancialAssetTypeId(String financialAssetTypeId) {
		this.setFinancialAssetTypeId(financialAssetTypeId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setFinancialAssetTypeId(String financialAssetTypeId) {
		//セッターで一貫性制約を保持する。
		if(financialAssetTypeId == null) {
			throw new IllegalArgumentException("The financialAssetTypeId may not be set to null.");
		}
		if(financialAssetTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a financialAssetTypeId.");
		}

		this.financialAssetTypeId = financialAssetTypeId;
	}
	
	@Override
	public FinancialAssetTypeId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new FinancialAssetTypeId(this.getFinancialAssetTypeId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			FinancialAssetTypeId financialAssetTypeId = (FinancialAssetTypeId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(financialAssetTypeId.getFinancialAssetTypeId().equals(this.getFinancialAssetTypeId())){
				equality = true;
			}
		}
		return equality;
	}


}
