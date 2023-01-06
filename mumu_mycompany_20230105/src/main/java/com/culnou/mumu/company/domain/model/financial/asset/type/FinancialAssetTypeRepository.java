package com.culnou.mumu.company.domain.model.financial.asset.type;

import java.util.List;



public interface FinancialAssetTypeRepository {
	
    public FinancialAssetType findFinancialAssetTypeOfId(FinancialAssetTypeId financialAssetTypeId) throws Exception;
	
    public List<FinancialAssetType> findFinancialAssetTypesOfCompany(String companyId) throws Exception;
	
    
	public List<FinancialAssetType> findFinancialAssetTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public void save(FinancialAssetType financialAssetType) throws Exception;
	
	public void remove(FinancialAssetType financialAssetType) throws Exception;

}
