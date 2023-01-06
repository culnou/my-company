package com.culnou.mumu.company.infrastructure.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.service.ProductService;
import com.culnou.mumu.company.domain.model.service.ProductServiceId;
import com.culnou.mumu.company.domain.model.service.ProductServiceRepository;

@Service("productServiceJpaRepository")
@Transactional
public class ProductServiceJpaRepository implements ProductServiceRepository {
	
	
	@Autowired
	private ProductServiceJpaDataRepository repository;

	@Override
	public void save(ProductService productService) throws Exception {
		// TODO Auto-generated method stub
		repository.save(productService);
		

	}

	@Override
	public ProductService findByProductServiceId(ProductServiceId productServiceId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByProductServiceId(productServiceId);
	}

	@Override
	public void remove(ProductService productService) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(productService);

	}

	@Override
	public List<ProductService> findProductServicesOfDepartment(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProductServicesOfDepartment(departmentId);
	}

	@Override
	public List<ProductService> findProductServicesOfProduct(String productId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProductServicesOfProduct(productId);
	}

}
