package com.culnou.mumu.company.domain.model.financial.asset.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryRegistry;

/*
 * メンバータイプが削除できるかチェックするドメインサービス
 */
@Service
@Transactional
public class DeleteFinancialAssetTypeChecker {
	@Autowired
	private FinancialAssetCategoryRegistry registry;
	
	public boolean removable(String financialAssetTypeId) throws Exception{
		boolean check = true;
		if(financialAssetTypeId == null || financialAssetTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_financialAssetTypeId_may_not_be_set_to_null");
		}
		//対応するカテゴリがあるか検証する。
		List<FinancialAssetCategory> categories = registry.findFinancialAssetCategoriesOfFinancialAssetType(financialAssetTypeId);
		
		if(categories.size() > 0) {
			check = false;
		}
		return check;
	}

}
