package com.culnou.mumu.company.infrastructure.persistence.financial.asset.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetType;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetTypeId;




public interface FinancialAssetTypeJpaDataRepository extends JpaRepository<FinancialAssetType, FinancialAssetTypeId> {

	public FinancialAssetType findByFinancialAssetTypeId(FinancialAssetTypeId financialAssetTypeId);
	
	@Query(value = "select financialAssetType from FinancialAssetType financialAssetType where financialAssetType.companyId = :CompanyId")
	public List<FinancialAssetType> findFinanicialAssetTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select financialAssetType from FinancialAssetType financialAssetType where financialAssetType.businessDomainId = :BusinessDomainId")
	public List<FinancialAssetType> findFinanicialAssetTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
}
