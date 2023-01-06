package com.culnou.mumu.company.domain.model.product.instance;

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
public class ProductRegistry {
	//レジストリ（記録後）でリポジトリの実装方法の違い（JPAとMongoなど）をラップする。
	@Qualifier("productJpaRepository")
	@Autowired
	private ProductRepository repository;
	
	
	
	//レジストリ（記録簿）が識別子を生成する。
	public ProductId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return new ProductId(str);
	}
	
	protected Product findProductOfId(ProductId productId) throws Exception{
		return repository.findByProductId(productId);
	}
	
	protected List<Product> findProductsOfProductCategory(String productCategoryId) throws Exception{
		return repository.findProductsOfProductCategory(productCategoryId);
	}
	protected void removeProduct(Product product) throws Exception{
		repository.remove(product);
	}
	protected void createProduct(Product product) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Product>> violations = validator.validate(product);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		
		
		repository.save(product);
	}
	
	protected void registerProduct(Product product) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Product>> violations = validator.validate(product);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		product.setMarket(true);
		product.setProductState(ProductState.Registered);
		repository.save(product);
	}
	
	protected void assignProduct(Product product) throws Exception{
		//事前条件
		//エンティティ整合性を保証する（JavaのValidationを活用する）
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Product>> violations = validator.validate(product);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		if(!product.getProductState().equals(ProductState.Registered)) {
			throw new Exception("The_product_is_not_registered");
		}
		if(!product.isMarket()) {
			throw new Exception("The_product_is_not_registered_to_market");
		}
		product.setProductState(ProductState.Assigned);
		repository.save(product);
	}
	
	public void releaseProduct(ProductId productId) throws Exception{
		if(productId == null) {
			throw new Exception("The_productId_may_not_be_set_to_null");
		}
		Product product = repository.findByProductId(productId);
		if(product == null) {
			throw new Exception("The_product_may_not_be_set_to_null");
		}
		if(product.getProductState().equals(ProductState.Released)) {
			throw new Exception("The_product_is_already_released");
		}
		product.setProductState(ProductState.Released);
		repository.save(product);
	}
	
	
	

}
