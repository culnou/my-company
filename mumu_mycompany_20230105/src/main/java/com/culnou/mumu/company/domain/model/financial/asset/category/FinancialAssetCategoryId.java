package com.culnou.mumu.company.domain.model.financial.asset.category;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class FinancialAssetCategoryId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String financialAssetCategoryId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected FinancialAssetCategoryId() {}
	
	public FinancialAssetCategoryId(String financialAssetCategoryId) {
		this.setFinancialAssetCategoryId(financialAssetCategoryId);
	}
	//ドメイン以外から設定できないようにする。
	protected void setFinancialAssetCategoryId(String financialAssetCategoryId) {
		//セッターで一貫性制約を保持する。
		if(financialAssetCategoryId == null) {
			throw new IllegalArgumentException("The financialAssetCategoryId may not be set to null.");
		}
		if(financialAssetCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a financialAssetCategoryId.");
		}

		this.financialAssetCategoryId = financialAssetCategoryId;
	}
	
	@Override
	public FinancialAssetCategoryId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new FinancialAssetCategoryId(this.getFinancialAssetCategoryId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			FinancialAssetCategoryId financialAssetCategoryId = (FinancialAssetCategoryId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(financialAssetCategoryId.getFinancialAssetCategoryId().equals(this.getFinancialAssetCategoryId())){
				equality = true;
			}
		}
		return equality;
	}


}
