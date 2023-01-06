package com.culnou.mumu.company.infrastructure.persistence.it.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;



public interface ItCategoryJpaDataRepository extends JpaRepository<ItCategory, ItCategoryId> {
	public ItCategory findByItCategoryId(ItCategoryId itCategoryId);
	@Query(value = "select itCategory from ItCategory itCategory where itCategory.companyId = :CompanyId")
	public List<ItCategory> findItCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select itCategory from ItCategory itCategory join itCategory.associatedItTypes itTypes where itTypes.itTypeId = :ItTypeId")
	public List<ItCategory> findItCategoriesOfItType(@Param("ItTypeId") String itTypeId);
	@Query(value = "select itCategory from ItCategory itCategory where itCategory.businessUnitId = :BusinessUnitId")
	public List<ItCategory> findItCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);


}
