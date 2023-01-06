package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.ProductTypeId;


public interface BusinessDomainJpaDataRepository extends JpaRepository<BusinessDomainEntity, BusinessDomainId> {
	public BusinessDomainEntity findByBusinessDomainId(BusinessDomainId businessDomainId);
	
	@Query(value = "select businessDomain from BusinessDomainEntity businessDomain where businessDomain.companyId = :CompanyId")
	public List<BusinessDomainEntity> findBusinessDomainsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select businessDomain from BusinessDomainEntity businessDomain where businessDomain.customerTypeId = :CustomerTypeId")
	public List<BusinessDomainEntity> findBusinessDomainsOfCustomerType(@Param("CustomerTypeId") CustomerTypeId customerTypeId);
	
	@Query(value = "select businessDomain from BusinessDomainEntity businessDomain where businessDomain.productTypeId = :ProductTypeId")
	public List<BusinessDomainEntity> findBusinessDomainsOfProductType(@Param("ProductTypeId") ProductTypeId productTypeId);

	@Query(value = "select businessDomain from BusinessDomainEntity businessDomain where businessDomain.enterprise = :Enterprise and businessDomain.companyId = :CompanyId")
	public List<BusinessDomainEntity> findEnterprises(@Param("Enterprise") boolean enterprise, @Param("CompanyId") CompanyId companyId);
}
