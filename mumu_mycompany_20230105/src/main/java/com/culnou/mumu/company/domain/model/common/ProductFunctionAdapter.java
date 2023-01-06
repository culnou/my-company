package com.culnou.mumu.company.domain.model.common;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductFunction;
import com.culnou.mumu.company.domain.model.ProductFunctionRepository;

@Service
@Transactional
public class ProductFunctionAdapter {
	
	@Qualifier("productFunctionJpaRepository")
	@Autowired
	private ProductFunctionRepository repository;
	
	public List<ProductFunction> productFunctionsOfProductCategory(ProductCategoryId productCategoryId) throws Exception{
		return repository.productFunctionsOfProductCategory(productCategoryId);
	}

}
