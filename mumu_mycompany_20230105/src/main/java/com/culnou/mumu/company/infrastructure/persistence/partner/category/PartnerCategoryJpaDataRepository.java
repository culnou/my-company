package com.culnou.mumu.company.infrastructure.persistence.partner.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;



public interface PartnerCategoryJpaDataRepository extends JpaRepository<PartnerCategory, PartnerCategoryId>{
	public PartnerCategory findByPartnerCategoryId(PartnerCategoryId partnerCategoryId);
	@Query(value = "select partnerCategory from PartnerCategory partnerCategory where partnerCategory.companyId = :CompanyId")
	public List<PartnerCategory> findPartnerCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select partnerCategory from PartnerCategory partnerCategory join partnerCategory.associatedPartnerTypes partnerTypes where partnerTypes.partnerTypeId = :PartnerTypeId")
	public List<PartnerCategory> findPartnerCategoriesOfPartnerType(@Param("PartnerTypeId") String partnerTypeId);
	@Query(value = "select partnerCategory from PartnerCategory partnerCategory where partnerCategory.businessUnitId = :BusinessUnitId")
	public List<PartnerCategory> findPartnerCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);


}
