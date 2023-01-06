package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;

public interface CustomerTypeJpaDataRepository extends JpaRepository<CustomerTypeEntity, CustomerTypeId> {
	public CustomerTypeEntity findByCustomerTypeId(CustomerTypeId customerTypeId);
	@Query(value = "select customerType from CustomerTypeEntity customerType where customerType.companyId = :CompanyId")
	public List<CustomerTypeEntity> findCustomerTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select customerType from CustomerTypeEntity customerType where customerType.businessDomainId = :BusinessDomainId")
	public List<CustomerTypeEntity> findCustomerTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
}
