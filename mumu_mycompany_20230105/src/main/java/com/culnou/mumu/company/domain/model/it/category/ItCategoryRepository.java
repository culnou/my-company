package com.culnou.mumu.company.domain.model.it.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


public interface ItCategoryRepository {
	
	//コマンド
    public void save(ItCategory itCategory) throws Exception;
	
	public void remove(ItCategory itCategory) throws Exception;
	
	//クエリ
	public ItCategory itCategoryOfId(ItCategoryId itCategoryId) throws Exception;
	
	public List<ItCategory> itCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<ItCategory> itCategoriesOfItType(String itTypeId) throws Exception;
	
	public List<ItCategory> itCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
