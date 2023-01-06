package com.culnou.mumu.company.infrastructure.persistence.it.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.it.type.ItType;
import com.culnou.mumu.company.domain.model.it.type.ItTypeId;



public interface ItTypeJpaDataRepository extends JpaRepository<ItType, ItTypeId> {
	
	public ItType findByitTypeId(ItTypeId itTypeId);
	
	@Query(value = "select itType from ItType itType where itType.companyId = :CompanyId")
	public List<ItType> findItsTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select itType from ItType itType where itType.businessDomainId = :BusinessDomainId")
	public List<ItType> findItTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	

}
