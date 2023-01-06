package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractProductTypeRepository;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.ProductType;
import com.culnou.mumu.company.domain.model.ProductTypeId;
@Service("productTypeJpaRepository")
@Transactional
public class ProductTypeJpaRepository extends AbstractProductTypeRepository {
	
	@Autowired
	private ProductTypeJpaDataRepository repository;
	
	private ProductType convertProductTypeEntityToProductType(ProductTypeEntity entity) {
		ProductType type = convertFromEntity(entity);
		if(entity.getCustomerTypeId() != null) {
			type.setCustomerTypeId(entity.getCustomerTypeId());
		}
		if(entity.getCustomerTypeName() != null) {
			type.setCustomerTypeName(entity.getCustomerTypeName());
		}
		if(entity.getProductTypeName() != null) {
			type.setProductTypeName(entity.getProductTypeName());
		}
		if(entity.getProductClass() != null) {
			type.setProductClass(entity.getProductClass());
	    }
		if(entity.getValueProposition() != null) {
			type.setValueProposition(entity.getValueProposition());
		}
		if(entity.getSolution() != null) {
			type.setSolution(entity.getSolution());
		}
		if(entity.getProblem() != null) {
			type.setProblem(entity.getProblem());
		}
		if(entity.getUrl() != null) {
			type.setUrl(entity.getUrl());
		}
		if(entity.getIndicators() != null) {
			type.setIndicators(entity.getIndicators());
		}
		if(entity.getBusinessDomainId() != null) {
			type.setBusinessDomainId(entity.getBusinessDomainId());
		}
		if(entity.getCreatedAt() != null) {
			type.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	type.setUpdatedAt(entity.getUpdatedAt());
	    }
	    if(entity.getAttributes() != null) {
			type.setAttributes(entity.getAttributes());
		}
	    if(entity.getEntityName() != null) {
	    	type.setEntityName(entity.getEntityName());
	    }
	    if(entity.getEntityEnglishName() != null) {
	    	type.setEntityEnglishName(entity.getEntityEnglishName());
	    }
	    if(entity.getDataOwner() != null) {
	    	type.setDataOwner(entity.getDataOwner());
	    }
	    if(entity.getAddress() != null) {
	    	type.setAddress(entity.getAddress());
	    }
	    if(entity.getAssociatedConstraint() != null) {
	    	type.setAssociatedConstraint(entity.getAssociatedConstraint());
	    }
	    if(entity.getExistenceConstraint() != null) {
	    	type.setExistenceConstraint(entity.getExistenceConstraint());
	    }
	    if(entity.getDataAmount() != null) {
	    	type.setDataAmount(entity.getDataAmount());
	    }
	    if(entity.getDataTypeId() != null) {
	    	type.setDataTypeId(entity.getDataTypeId());
	    }

		
		return type;
	}
	
	private ProductTypeEntity convertProductTypeToProductTypeEntity(ProductType type) {
		ProductTypeEntity entity = new ProductTypeEntity();
		entity.setCompanyId(type.companyId());
		entity.setProductTypeId(type.productTypeId());
		if(type.customerTypeId() != null) {
			entity.setCustomerTypeId(type.customerTypeId());
		}
		if(type.customerTypeName() != null) {
			entity.setCustomerTypeName(type.customerTypeName());
		}
		if(type.productTypeName() != null) {
			entity.setProductTypeName(type.productTypeName());
		}
		if(type.getProductClass() != null) {
			entity.setProductClass(type.getProductClass());
		}
		if(type.getValueProposition() != null) {
			entity.setValueProposition(type.getValueProposition());
		}
		if(type.solution() != null) {
			entity.setSolution(type.solution());
		}
		if(type.problem() != null) {
			entity.setProblem(type.problem());
		}
		if(type.getUrl() != null) {
			entity.setUrl(type.getUrl());
		}
		if(type.getIndicators() != null) {
			entity.setIndicators(type.getIndicators());
		}
		if(type.getBusinessDomainId() != null) {
			entity.setBusinessDomainId(type.getBusinessDomainId());
		}
		if(type.getCreatedAt() != null) {
			entity.setCreatedAt(type.getCreatedAt());
		}
		if(type.getUpdatedAt() != null) {
			entity.setUpdatedAt(type.getUpdatedAt());
		}
		if(type.getAttributes() != null) {
			entity.setAttributes(type.getAttributes());
		}
		if(type.getEntityName() != null) {
			entity.setEntityName(type.getEntityName());
		}
		if(type.getEntityEnglishName() != null) {
			entity.setEntityEnglishName(type.getEntityEnglishName());
		}
		if(type.getEntityDescription() != null) {
			entity.setEntityDescription(type.getEntityDescription());
		}
		if(type.getDataOwner() != null) {
			entity.setDataOwner(type.getDataOwner());
		}
		if(type.getAddress() != null) {
			entity.setAddress(type.getAddress());
		}
		if(type.getAssociatedConstraint() != null) {
			entity.setAssociatedConstraint(type.getAssociatedConstraint());
		}
		if(type.getExistenceConstraint() != null) {
			entity.setExistenceConstraint(type.getExistenceConstraint());
		}
		if(type.getDataAmount() != null) {
			entity.setDataAmount(type.getDataAmount());
		}
		if(type.getDataTypeId() != null) {
			entity.setDataTypeId(type.getDataTypeId());
		}

		
		return entity;
	}
	
	private List<ProductType> convertProductTypeEntitiesToProductTypes(List<ProductTypeEntity> entities){
		List<ProductType> types = new ArrayList<>();
		for(ProductTypeEntity entity : entities) {
			types.add(this.convertProductTypeEntityToProductType(entity));
		}
		return types;
	}
	
	@Override
	public ProductTypeId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProductTypeId(str);
	}

	@Override
	public void save(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		if(productType == null) {
			throw new IllegalArgumentException("The productType may not be set to null.");
		}
		repository.save(this.convertProductTypeToProductTypeEntity(productType));


	}

	@Override
	public void remove(ProductType productType) throws Exception {
		// TODO Auto-generated method stub
		if(productType == null) {
			throw new IllegalArgumentException("The customerType may not be set to null.");
		}
		repository.delete(this.convertProductTypeToProductTypeEntity(productType));


	}

	@Override
	public ProductType productTypeOfId(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		ProductTypeEntity entity = repository.findByProductTypeId(productTypeId);
		//エンティティ単体をFindするメソッドでは、エンティティのNullチェックを行うようにします。これをしないと、エンティティからPOJOに変換する処理でNullポインターエラーが発生します。
		if(entity == null) {
			throw new Exception("The ProductTypeEntity may not be set to null.");
		}
		return this.convertProductTypeEntityToProductType(entity);
	}

	@Override
	public List<ProductType> productTypesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ProductTypeEntity> entities = repository.findProductTypesOfCompany(companyId);
		return this.convertProductTypeEntitiesToProductTypes(entities);
	}
	
	public List<ProductType> productTypesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		List<ProductTypeEntity> entities = repository.findProductTypesOfBusinessDomain(businessDomainId);
		return this.convertProductTypeEntitiesToProductTypes(entities);
	}
	
	//製品タイプで使用されている場合、顧客タイプを編集、削除できないようにするために必要。
	@Override
	public List<ProductType> productTypesOfCustomerType(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		List<ProductTypeEntity> entities = repository.findProductTypesOfCustomerType(customerTypeId);
		return this.convertProductTypeEntitiesToProductTypes(entities);
	}


}
