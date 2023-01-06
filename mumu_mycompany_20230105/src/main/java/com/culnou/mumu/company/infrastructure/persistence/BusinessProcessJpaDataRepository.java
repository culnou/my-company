package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessProcessId;

import com.culnou.mumu.company.domain.model.CompanyId;

public interface BusinessProcessJpaDataRepository extends JpaRepository<BusinessProcessEntity, BusinessProcessId> {
	
    public BusinessProcessEntity findByBusinessProcessId(BusinessProcessId businessProcessId);
	
	@Query(value = "select businessProcess from BusinessProcessEntity businessProcess where businessProcess.companyId = :CompanyId")
	public List<BusinessProcessEntity> findBusinessProcessesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select businessProcess from BusinessProcessEntity businessProcess where businessProcess.businessDomainId = :BusinessDomainId")
	public List<BusinessProcessEntity> findBusinessProcessesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	
	@Query(value = "select businessProcess from BusinessProcessEntity businessProcess join businessProcess.associatedTasks task where task.taskId = :TaskId")
	public List<BusinessProcessEntity> findBusinessProcessesOfTask(@Param("TaskId") String taskId);

}
