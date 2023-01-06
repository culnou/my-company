package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductTaskService {

	@Qualifier("productFunctionJpaRepository")
	@Autowired
	private ProductFunctionRepository productFunctionRepository;
	
	public boolean isUsed(ProductTaskId productTaskId) throws Exception{
		boolean result = false;
		//製品機能に使用されているか。
		List<ProductFunction> functions = productFunctionRepository.productFunctionsOfProductTask(productTaskId);
		if(functions.size() > 0) {
			result = true;
		}
		
		return result;
	}
}
