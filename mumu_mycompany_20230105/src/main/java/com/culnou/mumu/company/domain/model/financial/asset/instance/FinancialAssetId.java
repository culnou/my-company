package com.culnou.mumu.company.domain.model.financial.asset.instance;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class FinancialAssetId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String financialAssetId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected FinancialAssetId() {}
	
	public FinancialAssetId(String financialAssetId) {
		this.setFinancialAssetId(financialAssetId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setFinancialAssetId(String financialAssetId) {
		//セッターで一貫性制約を保持する。
		if(financialAssetId == null) {
			throw new IllegalArgumentException("The financialAssetId may not be set to null.");
		}
		if(financialAssetId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a financialAssetId.");
		}

		this.financialAssetId = financialAssetId;
	}
	
	@Override
	public FinancialAssetId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new FinancialAssetId(this.getFinancialAssetId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			FinancialAssetId financialAssetId = (FinancialAssetId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(financialAssetId.getFinancialAssetId().equals(this.getFinancialAssetId())){
				equality = true;
			}
		}
		return equality;
	}

	

}
