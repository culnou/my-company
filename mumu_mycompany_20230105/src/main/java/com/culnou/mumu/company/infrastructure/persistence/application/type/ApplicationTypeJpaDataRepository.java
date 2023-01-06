package com.culnou.mumu.company.infrastructure.persistence.application.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationType;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;


public interface ApplicationTypeJpaDataRepository extends JpaRepository<ApplicationType, ApplicationTypeId> {
	
	public ApplicationType findByapplicationTypeId(ApplicationTypeId applicationTypeId);
	
	@Query(value = "select applicationType from ApplicationType applicationType where applicationType.companyId = :CompanyId")
	public List<ApplicationType> findApplicationsTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select applicationType from ApplicationType applicationType where applicationType.businessDomainId = :BusinessDomainId")
	public List<ApplicationType> findApplicationTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	@Query(value = "select applicationType from ApplicationType applicationType where applicationType.jobId = :JobId")
	public List<ApplicationType> findApplicationTypesOfJob(@Param("JobId") JobId jobId);


}
