package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.ProductFunctionId;
import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductCategoryId;


public interface ProductFunctionJpaDataRepository extends JpaRepository<ProductFunctionEntity, ProductFunctionId> {
	public ProductFunctionEntity findByProductFunctionId(ProductFunctionId productFunctionId);
	@Query(value = "select productFunction from ProductFunctionEntity productFunction where productFunction.companyId = :CompanyId")
	public List<ProductFunctionEntity> findProductFunctionsOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select productFunction from ProductFunctionEntity productFunction where productFunction.productCategoryId = :ProductCategoryId")
	public List<ProductFunctionEntity> findProductFunctionsOfProductCategory(@Param("ProductCategoryId") ProductCategoryId productCategoryId);
	@Query(value = "select productFunction from ProductFunctionEntity productFunction where productFunction.productTaskId = :ProductTaskId")
	public List<ProductFunctionEntity> findProductFunctionsOfProductTask(@Param("ProductTaskId") ProductTaskId productTaskId);

}
