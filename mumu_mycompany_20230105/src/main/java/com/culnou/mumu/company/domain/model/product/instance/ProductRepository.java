package com.culnou.mumu.company.domain.model.product.instance;

import java.util.List;



public interface ProductRepository {
	
	public void save(Product product) throws Exception;
	
	
	public Product findByProductId(ProductId productId) throws Exception;
	
	public void remove(Product product) throws Exception;
	
	public List<Product> findProductsOfDepartment(String departmentId) throws Exception;
	
	public List<Product> findProductsOfProductCategory(String productCategoryId) throws Exception;

}
