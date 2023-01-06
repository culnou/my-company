package com.culnou.mumu.company.domain.model.financial.asset.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


public interface FinancialAssetCategoryRepository {
	
	//コマンド
    public void save(FinancialAssetCategory financialAssetCategory) throws Exception;
	
	public void remove(FinancialAssetCategory financialAssetCategory) throws Exception;
	
	//クエリ
	public FinancialAssetCategory financialAssetCategoryOfId(FinancialAssetCategoryId financialAssetCategoryId) throws Exception;
	
	public List<FinancialAssetCategory> financialAssetCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<FinancialAssetCategory> financialAssetCategoriesOfFinancialAssetType(String financialAssetTypeId) throws Exception;
	
	public List<FinancialAssetCategory> financialAssetCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
