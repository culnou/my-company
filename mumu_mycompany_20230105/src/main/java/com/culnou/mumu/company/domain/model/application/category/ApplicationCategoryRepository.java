package com.culnou.mumu.company.domain.model.application.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;



public interface ApplicationCategoryRepository {
	
	//コマンド
    public void save(ApplicationCategory applicationCategory) throws Exception;
	
	public void remove(ApplicationCategory applicationCategory) throws Exception;
	
	//クエリ
	public ApplicationCategory applicationCategoryOfId(ApplicationCategoryId applicationCategoryId) throws Exception;
	
	public List<ApplicationCategory> applicationCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<ApplicationCategory> applicationCategoriesOfApplicationType(String applicationTypeId) throws Exception;
	
	public List<ApplicationCategory> applicationCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
