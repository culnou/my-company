package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.product.instance.ProductService;
import com.culnou.mumu.compnay.controller.ProductDto;

@Service
@Transactional
public class ProductAdapter {
	
	@Autowired
	private ProductService productService;
	
	public ProductDto findProductOfId(String productId) throws Exception{
		return productService.findProductOfId(productId);
	}

}
