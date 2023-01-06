package com.culnou.mumu.company.infrastructure.persistence.application.function;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunction;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;


public interface ApplicationFunctionJpaDataRepository extends JpaRepository<ApplicationFunction, ApplicationFunctionId> {
	public ApplicationFunction findByApplicationFunctionId(ApplicationFunctionId applicationFunctionId);
	@Query(value = "select applicationFunction from ApplicationFunction applicationFunction where applicationFunction.companyId = :CompanyId")
	public List<ApplicationFunction> findApplicationFunctionsOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select applicationFunction from ApplicationFunction applicationFunction where applicationFunction.applicationCategoryId = :ApplicationCategoryId")
	public List<ApplicationFunction> findApplicationFunctionsOfApplicationCategory(@Param("ApplicationCategoryId") ApplicationCategoryId applicationCategoryId);
	@Query(value = "select applicationFunction from ApplicationFunction applicationFunction where applicationFunction.applicationTaskId = :ApplicationTaskId")
	public List<ApplicationFunction> findApplicationFunctionsOfApplicationTask(@Param("ApplicationTaskId") ApplicationTaskId applicationTaskId);


}
