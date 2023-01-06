package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;


public interface BusinessUnitJpaDataRepository extends JpaRepository<BusinessUnitEntity, BusinessUnitId> {
	public BusinessUnitEntity findByBusinessUnitId(BusinessUnitId businessUnitId);
	
	@Query(value = "select businessUnit from BusinessUnitEntity businessUnit where businessUnit.companyId = :CompanyId")
	public List<BusinessUnitEntity> findBusinessUnitsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select businessUnit from BusinessUnitEntity businessUnit where businessUnit.businessDomainId = :BusinessDomainId")
	public List<BusinessUnitEntity> findBusinessUnitsOfDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	@Query(value = "select businessUnit from BusinessUnitEntity businessUnit where businessUnit.customerCategoryId = :CustomerCategoryId")
	public List<BusinessUnitEntity> findBusinessUnitsOfCustomerCategory(@Param("CustomerCategoryId") CustomerCategoryId customerCategoryId);
	
	@Query(value = "select businessUnit from BusinessUnitEntity businessUnit join businessUnit.associatedProductCategories productCategory where productCategory.productCategoryId = :ProductCategoryId")
	public List<BusinessUnitEntity> findBusinessUnitsOfProductCategory(@Param("ProductCategoryId") String productCategoryId);

}
