package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface ProductTypeRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public ProductTypeId nextIdentity() throws Exception;
	
	//コマンド。
	public void save(ProductType productType) throws Exception;
	
	public void remove(ProductType productType) throws Exception;
	
	//クエリ
	public ProductType productTypeOfId(ProductTypeId productTypeId) throws Exception;
	
	public List<ProductType> productTypesOfCompany(CompanyId companyId) throws Exception;
	
	public List<ProductType> productTypesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception;
	//製品タイプで使用されている場合、顧客タイプを編集、削除できないようにするために必要。
	public List<ProductType> productTypesOfCustomerType(CustomerTypeId customerTypeId) throws Exception;
	
	
}
