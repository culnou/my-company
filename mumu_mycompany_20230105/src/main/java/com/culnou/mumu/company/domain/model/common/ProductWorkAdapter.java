package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.product.work.ProductWorkService;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductWorkDto;

@Service
@Transactional
public class ProductWorkAdapter {
	
	@Autowired
	private ProductWorkService service;
	
	public MessageDto createProductWork(ProductWorkDto dto) {
		return this.service.createProductWork(dto);
	}

}
