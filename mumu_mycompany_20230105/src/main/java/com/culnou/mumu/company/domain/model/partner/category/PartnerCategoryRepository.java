package com.culnou.mumu.company.domain.model.partner.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


public interface PartnerCategoryRepository {
	
	//コマンド
    public void save(PartnerCategory partnerCategory) throws Exception;
	
	public void remove(PartnerCategory partnerCategory) throws Exception;
	
	//クエリ
	public PartnerCategory partnerCategoryOfId(PartnerCategoryId partnerCategoryId) throws Exception;
	
	public List<PartnerCategory> partnerCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<PartnerCategory> partnerCategoriesOfPartnerType(String partnerTypeId) throws Exception;
	
	public List<PartnerCategory> partnerCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
