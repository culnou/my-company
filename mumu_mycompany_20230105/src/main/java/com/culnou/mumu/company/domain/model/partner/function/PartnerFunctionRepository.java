package com.culnou.mumu.company.domain.model.partner.function;

import java.util.List;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;

public interface PartnerFunctionRepository {
	
	//コマンド
    public void save(PartnerFunction partnerFunction) throws Exception;
	
	public void remove(PartnerFunction partnerFunction) throws Exception;
	
	//クエリ
	public PartnerFunction partnerFunctionOfId(PartnerFunctionId partnerFunctionId) throws Exception;
	
	public List<PartnerFunction> partnerFunctionsOfCompany(CompanyId companyId) throws Exception;
	
	public List<PartnerFunction> partnerFunctionsOfPartnerCategory(PartnerCategoryId partnerCategoryId) throws Exception;

	public List<PartnerFunction> partnerFunctionsOfTask(TaskId taskId) throws Exception;


}
