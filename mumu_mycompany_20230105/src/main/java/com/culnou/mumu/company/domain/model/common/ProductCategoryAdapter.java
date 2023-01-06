package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.CompanyService;
import com.culnou.mumu.compnay.controller.ProductCategoryDto;

@Service
@Transactional
public class ProductCategoryAdapter {
	
	@Autowired
	private CompanyService companyService;
	
	public ProductCategoryDto findProductCategoryOfId(String productCategoryId) throws Exception{
		return companyService.findProductCategoryById(productCategoryId);
	}

}
