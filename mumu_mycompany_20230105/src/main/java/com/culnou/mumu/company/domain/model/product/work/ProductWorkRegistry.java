package com.culnou.mumu.company.domain.model.product.work;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;




@Service
@Transactional
public class ProductWorkRegistry {
	
	@Qualifier("productWorkJpaRepository")
	@Autowired
	private ProductWorkRepository repository;
	
	public ProductWorkId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return new ProductWorkId(str);
	}
	
	protected void createProductWork(ProductWork productWork) throws Exception{
		repository.save(productWork);
	}

}
