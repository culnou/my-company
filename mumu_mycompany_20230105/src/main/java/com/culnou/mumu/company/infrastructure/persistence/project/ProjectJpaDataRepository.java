package com.culnou.mumu.company.infrastructure.persistence.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.project.Project;
import com.culnou.mumu.company.domain.model.project.ProjectId;

public interface ProjectJpaDataRepository extends JpaRepository<Project, ProjectId> {
	
	public Project findByProjectId(ProjectId projectId);
	
	@Query(value = "select project from Project project where project.businessUnitId = :BusinessUnitId")
	public List<Project> findProjectsOfBusinessUnsit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	
	@Query(value = "select project from Project project where project.companyId = :CompanyId")
	public List<Project> findProjectsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select project from Project project join project.associatedCustomerCategories customerCategories where customerCategories.customerCategoryId = :CustomerCategoryId")
	public List<Project> findProjectsOfCustomerCategory(@Param("CustomerCategoryId") String customerCategoryId);
	
	@Query(value = "select project from Project project join project.associatedProductCategories productCategories where productCategories.productCategoryId = :ProductCategoryId")
	public List<Project> findProjectsOfProductCategory(@Param("ProductCategoryId") String productCategoryId);

	@Query(value = "select project from Project project join project.associatedMemberCategories memberCategores where memberCategores.memberCategoryId = :MemberCategoryId")
	public List<Project> findProjectsOfMemberCategory(@Param("MemberCategoryId") String memberCategoryId);

	@Query(value = "select project from Project project join project.associatedPartnerCategories partnerCategories where partnerCategories.partnerCategoryId = :PartnerCategoryId")
	public List<Project> findProjectsOfPartnerCategory(@Param("PartnerCategoryId") String partnerCategoryId);

	@Query(value = "select project from Project project join project.associatedDataCategories dataCategories where dataCategories.dataCategoryId = :DataCategoryId")
	public List<Project> findProjectsOfDataCategory(@Param("DataCategoryId") String dataCategoryId);

	@Query(value = "select project from Project project join project.associatedApplicationCategories applicationCategories where applicationCategories.applicationCategoryId = :ApplicationCategoryId")
	public List<Project> findProjectsOfApplicationCategory(@Param("ApplicationCategoryId") String applicationCategoryId);

	@Query(value = "select project from Project project join project.associatedPlaceCategories placeCategories where placeCategories.placeCategoryId = :PlaceCategoryId")
	public List<Project> findProjectsOfPlaceCategory(@Param("PlaceCategoryId") String placeCategoryId);

	@Query(value = "select project from Project project join project.associatedActionPlans actionPlans where actionPlans.actionPlanId = :ActionPlanId")
	public List<Project> findProjectsOfActionPlan(@Param("ActionPlanId") String actionPlanId);

	@Query(value = "select project from Project project join project.associatedFinancialAssetCategories financialAssetCategories where financialAssetCategories.financialAssetCategoryId = :FinancialAssetCategoryId")
	public List<Project> findProjectsOfFinancialAssetCategory(@Param("FinancialAssetCategoryId") String financialAssetCategoryId);

}
