package com.culnou.mumu.company.infrastructure.persistence.pdoduct.work;

import org.springframework.data.jpa.repository.JpaRepository;

import com.culnou.mumu.company.domain.model.product.work.ProductWork;
import com.culnou.mumu.company.domain.model.product.work.ProductWorkId;



public interface ProductWorkJpaDataRepository extends JpaRepository<ProductWork, ProductWorkId> {
	
	public ProductWork findByProductWorkId(ProductWorkId productWorkId);

}
