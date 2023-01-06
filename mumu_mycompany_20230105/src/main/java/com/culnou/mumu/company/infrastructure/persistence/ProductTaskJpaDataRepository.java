package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTypeId;


public interface ProductTaskJpaDataRepository extends JpaRepository<ProductTaskEntity, ProductTaskId> {
	public ProductTaskEntity findByProductTaskId(ProductTaskId productTaskId);
	@Query(value = "select productTask from ProductTaskEntity productTask where productTask.companyId = :CompanyId")
	public List<ProductTaskEntity> findProductTasksOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select productTask from ProductTaskEntity productTask where productTask.productTypeId = :ProductTypeId")
	public List<ProductTaskEntity> findProductTasksOfProductType(@Param("ProductTypeId") ProductTypeId productTypeId);

}
