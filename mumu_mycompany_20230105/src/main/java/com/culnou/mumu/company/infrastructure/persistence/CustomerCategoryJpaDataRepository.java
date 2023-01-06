package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;


public interface CustomerCategoryJpaDataRepository extends JpaRepository<CustomerCategoryEntity, CustomerCategoryId> {
	public CustomerCategoryEntity findByCustomerCategoryId(CustomerCategoryId customerCategoryId);
	@Query(value = "select customerCategory from CustomerCategoryEntity customerCategory where customerCategory.companyId = :CompanyId")
	public List<CustomerCategoryEntity> findCustomerCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select customerCategory from CustomerCategoryEntity customerCategory where customerCategory.customerTypeId = :CustomerTypeId")
	public List<CustomerCategoryEntity> findCustomerCategoriesOfCustomerType(@Param("CustomerTypeId") CustomerTypeId customerTypeId);
	@Query(value = "select customerCategory from CustomerCategoryEntity customerCategory where customerCategory.businessUnitId = :BusinessUnitId")
	public List<CustomerCategoryEntity> findCustomerCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
}
