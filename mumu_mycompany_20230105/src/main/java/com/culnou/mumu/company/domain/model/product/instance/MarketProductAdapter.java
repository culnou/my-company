package com.culnou.mumu.company.domain.model.product.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.culnou.mumu.company.domain.model.common.Utility;

import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductDto;
import com.culnou.mumu.compnay.controller.RoleDto;


@Service
@Transactional
public class MarketProductAdapter {
	
	//市場の製品を作成する
	public MessageDto createProduct(ProductDto productDto) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/products";
		HttpEntity<ProductDto> entity = new HttpEntity<>(productDto);
		ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, MessageDto.class);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}
	
	
	protected List<ProductDto> findAssignableProducts(List<RoleDto> roles, String industryId) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/products/roles/industry/{industryId}";
		HttpEntity<List<RoleDto>> entity = new HttpEntity<>(roles);
		Map<String, String> params = new HashMap<>();
	    params.put("industryId", industryId);
	    ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<ProductDto>>() {}, params);
		List<ProductDto> products = responseEntity.getBody();
		return products;
	}
	
	public MessageDto assignProductToDepartment(String productId, String departmentId, String companyId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/assign/products/{productId}/department/{departmentId}/company/{companyId}";
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	    params.put("productId", productId);
	    params.put("departmentId", departmentId);
	    params.put("companyId", companyId);
	    ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, null, MessageDto.class, params);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}
	
	public MessageDto releaseProduct(String productId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/release/products/{productId}";
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	    params.put("productId", productId);
	    ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, null, MessageDto.class, params);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}

}
