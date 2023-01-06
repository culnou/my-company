package com.culnou.mumu.company.infrastructure.persistence.financial.asset.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryId;



public interface FinancialAssetCategoryJpaDataRepository extends JpaRepository<FinancialAssetCategory, FinancialAssetCategoryId> {
	public FinancialAssetCategory findByFinancialAssetCategoryId(FinancialAssetCategoryId financialAssetCategoryId);
	@Query(value = "select financialAssetCategory from FinancialAssetCategory financialAssetCategory where financialAssetCategory.companyId = :CompanyId")
	public List<FinancialAssetCategory> findFinancialAssetCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select financialAssetCategory from FinancialAssetCategory financialAssetCategory join financialAssetCategory.associatedFinancialAssetTypes financialAssetTypes where financialAssetTypes.financialAssetTypeId = :FinancialAssetTypeId")
	public List<FinancialAssetCategory> findFinancialAssetCategoriesOfFinancialAssetType(@Param("FinancialAssetTypeId") String financialAssetTypeId);
	@Query(value = "select financialAssetCategory from FinancialAssetCategory financialAssetCategory where financialAssetCategory.businessUnitId = :BusinessUnitId")
	public List<FinancialAssetCategory> findFinancialAssetCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);


}
