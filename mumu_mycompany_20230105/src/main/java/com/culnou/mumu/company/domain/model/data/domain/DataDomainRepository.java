package com.culnou.mumu.company.domain.model.data.domain;

import java.util.List;



public interface DataDomainRepository {
	
    public DataDomain findDataDomainOfId(DataDomainId dataDomainId) throws Exception;
	
    public List<DataDomain> findDataDomainsOfCompany(String companyId) throws Exception;
	
	public void save(DataDomain dataDomain) throws Exception;
	
	public void remove(DataDomain dataDomain) throws Exception;

}
