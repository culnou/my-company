package com.culnou.mumu.company.infrastructure.persistence.pdoduct.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.culnou.mumu.company.domain.model.product.instance.Product;
import com.culnou.mumu.company.domain.model.product.instance.ProductId;

public interface ProductJpaDataRepository extends JpaRepository<Product, ProductId> {
	public Product findByProductId(ProductId productId);
	
	@Query(value = "select product from Product product where product.productCategoryId = :ProductCategoryId")
	public List<Product> findProductsOfProductCategory(@Param("ProductCategoryId") String productCategoryId);
	
	@Query(value = "select product from Product product where product.departmentId = :DepartmentId")
	public List<Product> findProductsOfDepartment(@Param("DepartmentId") String departmentId);

}
