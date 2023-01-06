package com.culnou.mumu.company.infrastructure.persistence.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.service.ProductService;
import com.culnou.mumu.company.domain.model.service.ProductServiceId;

public interface ProductServiceJpaDataRepository extends JpaRepository<ProductService, ProductServiceId> {

	public ProductService findByProductServiceId(ProductServiceId productServiceId);
	
	@Query(value = "select productService from ProductService productService where productService.departmentId = :DepartmentId")
	public List<ProductService> findProductServicesOfDepartment(@Param("DepartmentId") String departmentId);
	
	@Query(value = "select productService from ProductService productService where productService.productId = :ProductId")
	public List<ProductService> findProductServicesOfProduct(@Param("ProductId") String productId);
}
