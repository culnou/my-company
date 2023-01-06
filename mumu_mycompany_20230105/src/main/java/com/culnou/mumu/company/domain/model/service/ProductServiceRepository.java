package com.culnou.mumu.company.domain.model.service;

import java.util.List;



public interface ProductServiceRepository {
	
    public void save(ProductService productService) throws Exception;
	
	public ProductService findByProductServiceId(ProductServiceId productServiceId) throws Exception;
	
	public void remove(ProductService productService) throws Exception;
	
	public List<ProductService> findProductServicesOfDepartment(String departmentId) throws Exception;
	
	public List<ProductService> findProductServicesOfProduct(String productId) throws Exception;

}
