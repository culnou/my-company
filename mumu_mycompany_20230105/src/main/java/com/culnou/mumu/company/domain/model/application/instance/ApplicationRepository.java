package com.culnou.mumu.company.domain.model.application.instance;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


public interface ApplicationRepository {
	//コマンド
    public void save(Application application) throws Exception;
	
	public void remove(Application application) throws Exception;
	
	//クエリ
	public Application applicationOfId(ApplicationId applicationId) throws Exception;
	
	public List<Application> applicationsOfCompany(CompanyId companyId) throws Exception;
	
	public List<Application> applicationsOfApplicationCategory(String applicationCategoryId) throws Exception;
	
	public List<Application> applicationsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;
	
	public List<Application> applicationsOfIt(String ItId) throws Exception;

	

}
