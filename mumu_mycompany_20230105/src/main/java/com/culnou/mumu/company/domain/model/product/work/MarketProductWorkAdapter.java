package com.culnou.mumu.company.domain.model.product.work;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.culnou.mumu.company.domain.model.common.Utility;
import com.culnou.mumu.compnay.controller.MessageDto;

import com.culnou.mumu.compnay.controller.ProductWorkDto;

@Service
@Transactional
public class MarketProductWorkAdapter {
	
	//市場の製品ワークを作成する
	public MessageDto createProductWork(ProductWorkDto productWorkDto) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/product_works";
		HttpEntity<ProductWorkDto> entity = new HttpEntity<>(productWorkDto);
		ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, MessageDto.class);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}

}
