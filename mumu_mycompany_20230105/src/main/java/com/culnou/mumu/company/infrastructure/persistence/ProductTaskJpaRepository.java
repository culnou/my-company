package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractProductTaskRepository;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.ProductTask;
import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTypeId;

@Service("productTaskJpaRepository")
@Transactional
public class ProductTaskJpaRepository extends AbstractProductTaskRepository {

	@Autowired
	private ProductTaskJpaDataRepository repository;
	
	/*
	 * 変換処理
	 */
	private ProductTask convertEntityToPojo(ProductTaskEntity entity) {
		ProductTask pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getProductTaskDescription() != null) {
			pojo.setProductTaskDescription(entity.getProductTaskDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getCreatedAt() != null) {
			pojo.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	pojo.setUpdatedAt(entity.getUpdatedAt());
	    }
		return pojo;
	}
	
	private ProductTaskEntity convertPojoToEntity(ProductTask pojo) {
		ProductTaskEntity entity = new ProductTaskEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setProductTaskId(pojo.getProductTaskId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setProductTypeId(pojo.getProductTypeId());
		entity.setProductTypeName(pojo.getProductTypeName());
		entity.setProductTaskName(pojo.getProductTaskName());
		//任意項目
		if(pojo.getProductTaskDescription() != null) {
			entity.setProductTaskDescription(pojo.getProductTaskDescription());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getCreatedAt() != null) {
			entity.setCreatedAt(pojo.getCreatedAt());
		}
		if(pojo.getUpdatedAt() != null) {
			entity.setUpdatedAt(pojo.getUpdatedAt());
		}
		return entity;
	}
	
	private List<ProductTask> convertEntitiesToPojos(List<ProductTaskEntity> entities){
		List<ProductTask> pojos = new ArrayList<>();
		for(ProductTaskEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}

	@Override
	public ProductTaskId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProductTaskId(str);
	}

	@Override
	public void save(ProductTask productTask) throws Exception {
		// TODO Auto-generated method stub
		if(productTask == null) {
			throw new IllegalArgumentException("The productTask may not be set to null.");
		}
		repository.save(this.convertPojoToEntity(productTask));


	}

	@Override
	public void remove(ProductTask productTask) throws Exception {
		// TODO Auto-generated method stub
		if(productTask == null) {
			throw new IllegalArgumentException("The productTask may not be set to null.");
		}
		repository.delete(this.convertPojoToEntity(productTask));

	}

	@Override
	public ProductTask productTaskOfId(ProductTaskId productTaskId) throws Exception {
		// TODO Auto-generated method stub
		if(productTaskId == null) {
			throw new IllegalArgumentException("The productTaskId may not be set to null.");
		}
		//集約のライフサイクルはリポジトリで保証するため検索結果の検証を行う。
		ProductTaskEntity entity = repository.findByProductTaskId(productTaskId);
		if(entity == null) {
			throw new IllegalArgumentException("The ProductTaskEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<ProductTask> productTasksOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ProductTaskEntity> entities = repository.findProductTasksOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<ProductTask> productTasksOfProductType(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		List<ProductTaskEntity> entities = repository.findProductTasksOfProductType(productTypeId);
		return this.convertEntitiesToPojos(entities);
	}

}
