package com.culnou.mumu.company.domain.model.partner.type;

import java.util.List;



public interface PartnerTypeRepository {
	
    public PartnerType findPartnerTypeOfId(PartnerTypeId partnerTypeId) throws Exception;
	
    public List<PartnerType> findPartnerTypesOfCompany(String companyId) throws Exception;
	
	public List<PartnerType> findPartnerTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	
	
	public void save(PartnerType partnerType) throws Exception;
	
	public void remove(PartnerType partnerType) throws Exception;

}
