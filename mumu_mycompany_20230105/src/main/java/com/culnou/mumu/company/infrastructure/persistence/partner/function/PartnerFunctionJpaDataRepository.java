package com.culnou.mumu.company.infrastructure.persistence.partner.function;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunction;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionId;

public interface PartnerFunctionJpaDataRepository extends JpaRepository<PartnerFunction, PartnerFunctionId> {
	
	public PartnerFunction findByPartnerFunctionId(PartnerFunctionId partnerFunctionId);
	@Query(value = "select partnerFunction from PartnerFunction partnerFunction where partnerFunction.companyId = :CompanyId")
	public List<PartnerFunction> findPartnerFunctionsOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select partnerFunction from PartnerFunction partnerFunction where partnerFunction.partnerCategoryId = :PartnerCategoryId")
	public List<PartnerFunction> findPartnerFunctionsOfPartnerCategory(@Param("PartnerCategoryId") PartnerCategoryId partnerCategoryId);
	@Query(value = "select partnerFunction from PartnerFunction partnerFunction where partnerFunction.taskId = :TaskId")
	public List<PartnerFunction> findPartnerFunctionsOfTask(@Param("TaskId") TaskId taskId);


}
