package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.DomainName;

public interface CompanyJpaDataRepository extends JpaRepository<CompanyEntity, CompanyId> {
	CompanyEntity findByCompanyId(CompanyId companyId);
	@Query(value = "select company from CompanyEntity company where company.domainName = :DomainName")
	public List<CompanyEntity> findCompaniesOfDomainName(@Param("DomainName") DomainName domainName);
	
	@Query(value = "select company from CompanyEntity company where company.companyName = :CompanyName")
	public List<CompanyEntity> findCompaniesOfCompanyName(@Param("CompanyName") String companyName);

}
