package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.ActionPlanId;

import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.project.ProjectId;

public interface ActionPlanJpaDataRepository extends JpaRepository<ActionPlanEntity, ActionPlanId> {
	
    public ActionPlanEntity findByActionPlanId(ActionPlanId actionPlanId);
	
	@Query(value = "select actionPlan from ActionPlanEntity actionPlan where actionPlan.companyId = :CompanyId")
	public List<ActionPlanEntity> findActionPlansOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select actionPlan from ActionPlanEntity actionPlan where actionPlan.businessProcessId = :BusinessProcessId")
	public List<ActionPlanEntity> findActionPlansOfBusinessProcess(@Param("BusinessProcessId") BusinessProcessId businessProcessId);
	
	@Query(value = "select actionPlan from ActionPlanEntity actionPlan join actionPlan.associatedActions action where action.actionId = :ActionId")
	public List<ActionPlanEntity> findActionPlansOfAction(@Param("ActionId") String actionId);
	
	@Query(value = "select actionPlan from ActionPlanEntity actionPlan join actionPlan.associatedProductCategories productCategory where productCategory.productCategoryId = :ProductCategoryId")
	public List<ActionPlanEntity> findActionPlansOfProductCategory(@Param("ProductCategoryId") String productCategoryId);

	@Query(value = "select actionPlan from ActionPlanEntity actionPlan where actionPlan.customerCategoryId = :CustomerCategoryId")
	public List<ActionPlanEntity> findActionPlansOfCustomerCategory(@Param("CustomerCategoryId") CustomerCategoryId customerCategoryId);
	
	@Query(value = "select actionPlan from ActionPlanEntity actionPlan where actionPlan.businessUnitId = :BusinessUnitId")
	public List<ActionPlanEntity> findActionPlansOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	
	@Query(value = "select actionPlan from ActionPlanEntity actionPlan where actionPlan.projectId = :ProjectId")
	public List<ActionPlanEntity> findActionPlansOfProject(@Param("ProjectId") ProjectId projectId);
	


}
