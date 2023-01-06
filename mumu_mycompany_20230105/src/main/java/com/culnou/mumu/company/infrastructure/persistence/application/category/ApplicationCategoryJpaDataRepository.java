package com.culnou.mumu.company.infrastructure.persistence.application.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;


public interface ApplicationCategoryJpaDataRepository extends JpaRepository<ApplicationCategory, ApplicationCategoryId> {
	public ApplicationCategory findByApplicationCategoryId(ApplicationCategoryId applicationCategoryId);
	@Query(value = "select applicationCategory from ApplicationCategory applicationCategory where applicationCategory.companyId = :CompanyId")
	public List<ApplicationCategory> findApplicationCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	//@ElementCollectionのJoin
	@Query(value = "select applicationCategory from ApplicationCategory applicationCategory join applicationCategory.associatedApplicationTypes applicationTypes where applicationTypes.applicationTypeId = :ApplicationTypeId")
	public List<ApplicationCategory> findApplicationCategoriesOfApplicationType(@Param("ApplicationTypeId") String applicationTypeId);
	@Query(value = "select applicationCategory from ApplicationCategory applicationCategory where applicationCategory.businessUnitId = :BusinessUnitId")
	public List<ApplicationCategory> findApplicationCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);

}
