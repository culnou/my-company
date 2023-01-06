package com.culnou.mumu.company.domain.model.application.type;

import java.util.List;



public interface ApplicationTypeRepository {
	
    public ApplicationType findApplicationTypeOfId(ApplicationTypeId applicationTypeId) throws Exception;
	
    public List<ApplicationType> findApplicationTypesOfCompany(String companyId) throws Exception;
	
	public List<ApplicationType> findApplicationTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public List<ApplicationType> findApplicationTypesOfJob(String jobId) throws Exception;
	
	public void save(ApplicationType applicationType) throws Exception;
	
	public void remove(ApplicationType applicationType) throws Exception;

}
