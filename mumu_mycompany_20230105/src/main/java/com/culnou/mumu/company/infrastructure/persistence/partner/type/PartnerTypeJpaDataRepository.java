package com.culnou.mumu.company.infrastructure.persistence.partner.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.partner.type.PartnerType;
import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeId;


public interface PartnerTypeJpaDataRepository extends JpaRepository<PartnerType, PartnerTypeId> {
	
	public PartnerType findByPartnerTypeId(PartnerTypeId partnerTypeId);
	
	@Query(value = "select partnerType from PartnerType partnerType where partnerType.companyId = :CompanyId")
	public List<PartnerType> findPartnerTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select partnerType from PartnerType partnerType where partnerType.businessDomainId = :BusinessDomainId")
	public List<PartnerType> findPartnerTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	

}
