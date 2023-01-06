package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface BusinessProcessRepository {
	//識別子オブジェクトはリポジトリが生成する。
	public BusinessProcessId nextIdentity() throws Exception;
	
	//コマンド
    public void save(BusinessProcess businessProcess) throws Exception;
	
	public void remove(BusinessProcess businessProcess) throws Exception;
	
	
	//クエリ
	public BusinessProcess businessProcessOfId(BusinessProcessId businessProcessId) throws Exception;
	
	public List<BusinessProcess> businessProcessesOfCompany(CompanyId companyId) throws Exception;
	
	public List<BusinessProcess> businessProcessesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception;
	
	//タスクがビジネスプロセスに使用されているかどうか調べるときに必要。
	public List<BusinessProcess> businessProcessesOfTask(String taskId) throws Exception;


}
