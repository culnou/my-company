package com.culnou.mumu.company.infrastructure.persistence.data.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.domain.DataDomain;
import com.culnou.mumu.company.domain.model.data.domain.DataDomainId;


public interface DataDomainJpaDataRepository extends JpaRepository<DataDomain, DataDomainId> {
	
    public DataDomain findBydataDomainId(DataDomainId dataDomainId);
	
	@Query(value = "select dataDomain from DataDomain dataDomain where dataDomain.companyId = :CompanyId")
	public List<DataDomain> findDatasDomainsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	

}
