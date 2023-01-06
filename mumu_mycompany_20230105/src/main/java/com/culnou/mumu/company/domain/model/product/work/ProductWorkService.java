package com.culnou.mumu.company.domain.model.product.work;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.common.ProductServiceAdapter;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductWorkDto;


@Service
@Transactional
public class ProductWorkService {
	
	@Autowired
	private ProductWorkRegistry registry;
	@Autowired
	private MarketProductWorkAdapter marketAdapter;
	@Autowired
	private ProductServiceAdapter productServiceAdapter;
	
	public MessageDto createProductWork(ProductWorkDto dto) {
		MessageDto message = new MessageDto();
		try {
			ProductWork work = new ProductWork();
			work.setProductWorkId(registry.nextIdentity());
			work.setCompanyId(dto.getCompanyId());
			work.setProductId(dto.getProductId());
			work.setProductServiceId(productServiceAdapter.findProductsOfProduct(dto.getProductId()).get(0).getProductServiceId().getProductServiceId());
			work.setWorkId(dto.getWorkId());
			work.setWorkName(dto.getWorkName());
			work.setWorkDescription(dto.getWorkDescription());
			work.setUrl(dto.getUrl());
			work.setExpendedTime(dto.getExpendedTime());
			//市場の製品ワークを作成する
			dto.setProductWorkId(work.getProductWorkId().getProductWorkId());
			dto.setProductServiceId(work.getProductServiceId());
			message = marketAdapter.createProductWork(dto);
			if(message.getResult().equals("OK")) {
				registry.createProductWork(work);
			}
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		
		return message;
	}

}
