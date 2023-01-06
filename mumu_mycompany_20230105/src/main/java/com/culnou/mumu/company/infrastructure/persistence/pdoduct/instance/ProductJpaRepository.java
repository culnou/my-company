package com.culnou.mumu.company.infrastructure.persistence.pdoduct.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.product.instance.Product;
import com.culnou.mumu.company.domain.model.product.instance.ProductId;
import com.culnou.mumu.company.domain.model.product.instance.ProductRepository;

@Service("productJpaRepository")
@Transactional
public class ProductJpaRepository implements ProductRepository {
	
	@Autowired
	private ProductJpaDataRepository repository;

	@Override
	public void save(Product product) throws Exception {
		// TODO Auto-generated method stub
		repository.save(product);
		
	}

	@Override
	public Product findByProductId(ProductId productId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByProductId(productId);
	}

	@Override
	public void remove(Product product) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(product);
		
	}

	@Override
	public List<Product> findProductsOfDepartment(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProductsOfDepartment(departmentId);
	}

	@Override
	public List<Product> findProductsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProductsOfProductCategory(productCategoryId);
	}

}
