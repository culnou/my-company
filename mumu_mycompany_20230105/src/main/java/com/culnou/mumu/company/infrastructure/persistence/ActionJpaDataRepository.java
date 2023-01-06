package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.ActionId;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.TaskId;

interface ActionJpaDataRepository extends JpaRepository<ActionEntity, ActionId> {
	
	public ActionEntity findByActionId(ActionId actionId);
	
	@Query(value = "select action from ActionEntity action where action.companyId = :CompanyId")
	public List<ActionEntity> findActionsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select action from ActionEntity action where action.departmentId = :DepartmentId")
	public List<ActionEntity> findActionsOfDepartment(@Param("DepartmentId") DepartmentId departmentId);

	@Query(value = "select action from ActionEntity action where action.taskId = :TaskId")
	public List<ActionEntity> findActionsOfTask(@Param("TaskId") TaskId taskId);
	
	@Query(value = "select action from ActionEntity action where action.applicationCategoryId = :ApplicationFunctionId")
	public List<ActionEntity> findActionsOfApplicationFunction(@Param("ApplicationFunctionId") String applicationFunctionId);
	
	@Query(value = "select action from ActionEntity action where action.partnerCategoryId = :PartnerFunctionId")
	public List<ActionEntity> findActionsOfPartnerFunction(@Param("PartnerFunctionId") String partnerFunctionId);


}
