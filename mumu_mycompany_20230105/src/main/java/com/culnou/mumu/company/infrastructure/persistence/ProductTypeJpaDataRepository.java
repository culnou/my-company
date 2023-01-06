package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.ProductTypeId;

public interface ProductTypeJpaDataRepository extends JpaRepository<ProductTypeEntity, ProductTypeId> {
	public ProductTypeEntity findByProductTypeId(ProductTypeId productTypeId);
	@Query(value = "select productType from ProductTypeEntity productType where productType.companyId = :CompanyId")
	public List<ProductTypeEntity> findProductTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	//製品タイプで使用されている場合、顧客タイプを編集、削除できないようにするために必要。
	@Query(value = "select productType from ProductTypeEntity productType where productType.customerTypeId = :CustomerTypeId")
	public List<ProductTypeEntity> findProductTypesOfCustomerType(@Param("CustomerTypeId") CustomerTypeId customerTypeId);
	@Query(value = "select productType from ProductTypeEntity productType where productType.businessDomainId = :BusinessDomainId")
	public List<ProductTypeEntity> findProductTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	



}
