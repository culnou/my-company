package com.culnou.mumu.company.infrastructure.persistence.application.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.instance.Application;
import com.culnou.mumu.company.domain.model.application.instance.ApplicationId;
import com.culnou.mumu.company.domain.model.it.instance.ItId;


public interface ApplicationJpaDataRepository extends JpaRepository<Application, ApplicationId> {
	public Application findByApplicationId(ApplicationId applicationId);
	@Query(value = "select application from Application application where application.companyId = :CompanyId")
	public List<Application> findApplicationsOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select application from Application application where application.applicationCategoryId = :ApplicationCategoryId")
	public List<Application> findApplicationsOfApplicationCategory(@Param("ApplicationCategoryId") ApplicationCategoryId applicationCategoryId);
	@Query(value = "select application from Application application where application.businessUnitId = :BusinessUnitId")
	public List<Application> findApplicationsOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	@Query(value = "select application from Application application where application.itId = :ItId")
	public List<Application> findApplicationsOfIt(@Param("ItId") ItId itId);
	
}



