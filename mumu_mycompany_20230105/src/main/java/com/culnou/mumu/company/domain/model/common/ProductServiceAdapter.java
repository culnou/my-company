package com.culnou.mumu.company.domain.model.common;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.service.ProductServiceService;
import com.culnou.mumu.compnay.controller.ProductServiceDto;

@Service
@Transactional
public class ProductServiceAdapter {
	
	@Autowired
	private ProductServiceService service;
	
	public List<ProductServiceDto> findProductsOfProduct(String productId) throws Exception{
		return service.findProductsOfProduct(productId);
	}

}
