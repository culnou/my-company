package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;

import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductTypeId;

public interface ProductCategoryJpaDataRepository extends JpaRepository<ProductCategoryEntity, ProductCategoryId> {
	public ProductCategoryEntity findByProductCategoryId(ProductCategoryId productCategoryId);
	@Query(value = "select productCategory from ProductCategoryEntity productCategory where productCategory.companyId = :CompanyId")
	public List<ProductCategoryEntity> findProductCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select productCategory from ProductCategoryEntity productCategory where productCategory.productTypeId = :ProductTypeId")
	public List<ProductCategoryEntity> findProductCategoriesOfProductType(@Param("ProductTypeId") ProductTypeId productTypeId);
	@Query(value = "select productCategory from ProductCategoryEntity productCategory where productCategory.customerCategoryId = :CustomerCategoryId")
	public List<ProductCategoryEntity> findProductCategoriesOfCustomerCategory(@Param("CustomerCategoryId") CustomerCategoryId customerCategoryId);
	@Query(value = "select productCategory from ProductCategoryEntity productCategory where productCategory.businessUnitId = :BusinessUnitId")
	public List<ProductCategoryEntity> findProductCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	@Query(value = "select productCategory from ProductCategoryEntity productCategory where productCategory.businessUnitId = :BusinessUnitId and productCategory.productClass = :ProductClass")
	public List<ProductCategoryEntity> findProductCategoriesOfProductClass(@Param("BusinessUnitId") BusinessUnitId businessUnitId, @Param("ProductClass") ProductClass productClass);
	
	

}
