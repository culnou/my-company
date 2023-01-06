package com.culnou.mumu.company.domain.model.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;




@Service
@Transactional
public class ProductServiceRegistry {
	
	@Qualifier("productServiceJpaRepository")
	@Autowired
	private ProductServiceRepository repository;
	
	
	public ProductServiceId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProductServiceId(str);
	}
	
	public void createProductService(ProductService productService) throws Exception{
		//エンティティ整合性が保持できているかチェックする
		//Java Validationを使う
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<ProductService>> violations = validator.validate(productService);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		productService.setProductSerivceState(ProductServiceState.Registered);
		repository.save(productService);
	}
	
	public List<ProductService> findProductServicesOfDepartment(String departmentId) throws Exception{
		return repository.findProductServicesOfDepartment(departmentId);
	}
	
	public List<ProductService> findProductServicesOfProduct(String productId) throws Exception{
		return repository.findProductServicesOfProduct(productId);
	}

}
