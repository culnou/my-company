package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractProductFunctionRepository;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.ProductFunction;
import com.culnou.mumu.company.domain.model.ProductFunctionId;
import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductCategoryId;

@Service("productFunctionJpaRepository")
@Transactional
public class ProductFunctionJpaRepository extends AbstractProductFunctionRepository {
	
	
	@Autowired
	private ProductFunctionJpaDataRepository repository;

	/*
	 * 変換処理
	 */
	private ProductFunction convertEntityToPojo(ProductFunctionEntity entity) {
		ProductFunction pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getProductFunctionDescription() != null) {
			pojo.setProductFunctionDescription(entity.getProductFunctionDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getFunction() != null) {
			pojo.setFunction(entity.getFunction());
		}
		
		return pojo;
	}
	
	private ProductFunctionEntity convertPojoToEntity(ProductFunction pojo) {
		ProductFunctionEntity entity = new ProductFunctionEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setProductFunctionId(pojo.getProductFunctionId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setProductCategoryId(pojo.getProductCategoryId());
		entity.setProductCategoryName(pojo.getProductCategoryName());
		entity.setProductTaskId(pojo.getProductTaskId());
		entity.setProductTaskName(pojo.getProductTaskName());
		entity.setProductFunctionName(pojo.getProductFunctionName());
		//任意項目
		if(pojo.getProductFunctionDescription() != null) {
			entity.setProductFunctionDescription(pojo.getProductFunctionDescription());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getFunction() != null) {
			entity.setFunction(pojo.getFunction());
		}
		return entity;
	}
	
	private List<ProductFunction> convertEntitiesToPojos(List<ProductFunctionEntity> entities){
		List<ProductFunction> pojos = new ArrayList<>();
		for(ProductFunctionEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}

	@Override
	public ProductFunctionId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProductFunctionId(str);
	}

	@Override
	public void save(ProductFunction productFunction) throws Exception {
		// TODO Auto-generated method stub
		if(productFunction == null) {
			throw new IllegalArgumentException("The productFunction may not be set to null.");
		}
		repository.save(this.convertPojoToEntity(productFunction));


	}

	@Override
	public void remove(ProductFunction productFunction) throws Exception {
		// TODO Auto-generated method stub
		if(productFunction == null) {
			throw new IllegalArgumentException("The productFunction may not be set to null.");
		}
		repository.delete(this.convertPojoToEntity(productFunction));

	}

	@Override
	public ProductFunction productFunctionOfId(ProductFunctionId productFunctionId) throws Exception {
		// TODO Auto-generated method stub
		if(productFunctionId == null) {
			throw new IllegalArgumentException("The productFunctionId may not be set to null.");
		}
		//集約のライフサイクルはリポジトリで保証するため検索結果の検証を行う。
		ProductFunctionEntity entity = repository.findByProductFunctionId(productFunctionId);
		if(entity == null) {
			throw new IllegalArgumentException("The ProductFunctionEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<ProductFunction> productFunctionsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ProductFunctionEntity> entities = repository.findProductFunctionsOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<ProductFunction> productFunctionsOfProductCategory(ProductCategoryId productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		List<ProductFunctionEntity> entities = repository.findProductFunctionsOfProductCategory(productCategoryId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<ProductFunction> productFunctionsOfProductTask(ProductTaskId productTaskId) throws Exception {
		// TODO Auto-generated method stub
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId may not be set to null.");
		}
		List<ProductFunctionEntity> entities = repository.findProductFunctionsOfProductTask(productTaskId);
		return this.convertEntitiesToPojos(entities);
	}

}
