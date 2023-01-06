package com.culnou.mumu.company.infrastructure.persistence.pdoduct.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.product.work.ProductWork;
import com.culnou.mumu.company.domain.model.product.work.ProductWorkRepository;



@Service("productWorkJpaRepository")
@Transactional
public class ProductWorkJpaRepository implements ProductWorkRepository {
	
	@Autowired
	private ProductWorkJpaDataRepository repository;

	@Override
	public void save(ProductWork productWork) throws Exception {
		// TODO Auto-generated method stub
		repository.save(productWork);

	}

}
