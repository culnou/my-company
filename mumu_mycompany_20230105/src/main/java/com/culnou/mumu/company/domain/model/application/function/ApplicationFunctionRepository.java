package com.culnou.mumu.company.domain.model.application.function;

import java.util.List;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;


public interface ApplicationFunctionRepository {
	
	//コマンド
    public void save(ApplicationFunction applicationFunction) throws Exception;
	
	public void remove(ApplicationFunction applicationFunction) throws Exception;
	
	//クエリ
	public ApplicationFunction applicationFunctionOfId(ApplicationFunctionId applicationFunctionId) throws Exception;
	
	public List<ApplicationFunction> applicationFunctionsOfCompany(CompanyId companyId) throws Exception;
	
	public List<ApplicationFunction> applicationFunctionsOfApplicationCategory(ApplicationCategoryId applicationCategoryId) throws Exception;

	public List<ApplicationFunction> applicationFunctionsOfApplicationTask(ApplicationTaskId applicationTaskId) throws Exception;


}
