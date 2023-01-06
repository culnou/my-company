package com.culnou.mumu.company.domain.model.it.type;

import java.util.List;



public interface ItTypeRepository {
	
    public ItType findItTypeOfId(ItTypeId itTypeId) throws Exception;
	
    public List<ItType> findItTypesOfCompany(String companyId) throws Exception;
	
	public List<ItType> findItTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public void save(ItType itType) throws Exception;
	
	public void remove(ItType itType) throws Exception;

}
