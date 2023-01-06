package com.culnou.mumu.company.infrastructure.persistence;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractCustomerTypeRepository;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerType;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
@Service("customerTypeJpaRepository")
@Transactional
public class CustomerTypeJpaRepository extends AbstractCustomerTypeRepository {
	
	@Autowired
	private CustomerTypeJpaDataRepository repository;
	
	
	private CustomerType convertCustomerTypeEntityToCustomerType(CustomerTypeEntity entity) {
		CustomerType type = convertFromEntity(entity);
		if(entity.getValues() != null) {
			type.setValues(entity.getValues());
		}
		if(entity.getCause() != null) {
			type.setCause(entity.getCause());
		}
		if(entity.getIssue() != null) {
			type.setIssue(entity.getIssue());
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
		if(entity.getAttributes() != null) {
			type.setAttributes(entity.getAttributes());
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
	
	private CustomerTypeEntity convertCustomerTypeToCustomerTypeEntity(CustomerType type) {
		CustomerTypeEntity entity = new CustomerTypeEntity();
		entity.setCompanyId(type.companyId());
		entity.setCustomerTypeId(type.customerTypeId());
		if(type.customerTypeName() != null) {
			entity.setCustomerTypeName(type.customerTypeName());
		}
		if(type.getPersonality() != null) {
			entity.setPersonality(type.getPersonality());
		}
		if(type.values() != null) {
			entity.setValues(type.values());
		}
		if(type.getCause() != null) {
			entity.setCause(type.getCause());
		}
		if(type.getIssue() != null) {
			entity.setIssue(type.getIssue());
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
		if(type.getAttributes() != null) {
			entity.setAttributes(type.getAttributes());
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
	
	private List<CustomerType> convertCustomerTypeEntitiesToCustomerTypes(List<CustomerTypeEntity> entities){
		List<CustomerType> types = new ArrayList<>();
		for(CustomerTypeEntity entity : entities) {
			types.add(this.convertCustomerTypeEntityToCustomerType(entity));
		}
		return types;
	}
	
	@Override
	public CustomerTypeId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new CustomerTypeId(str);
	}
	

	@Override
	public void save(CustomerType customerType) throws Exception {
		// TODO Auto-generated method stub
		if(customerType == null) {
			throw new IllegalArgumentException("The customerType may not be set to null.");
		}
		repository.save(this.convertCustomerTypeToCustomerTypeEntity(customerType));

	}

	@Override
	public void remove(CustomerType customerType) throws Exception {
		// TODO Auto-generated method stub
		if(customerType == null) {
			throw new IllegalArgumentException("The customerType may not be set to null.");
		}
		repository.delete(this.convertCustomerTypeToCustomerTypeEntity(customerType));

	}

	@Override
	public CustomerType customerTypeOfId(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		CustomerTypeEntity entity = repository.findByCustomerTypeId(customerTypeId);
		//エンティティ単体をFindするメソッドでは、エンティティのNullチェックを行うようにします。これをしないと、エンティティからPOJOに変換する処理でNullポインターエラーが発生します。
		if(entity == null) {
			throw new Exception("The CustomerTypeEntity may not be set to null.");
		}
		return this.convertCustomerTypeEntityToCustomerType(entity);
	}

	@Override
	public List<CustomerType> customerTypesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<CustomerTypeEntity> entities = repository.findCustomerTypesOfCompany(companyId);
		return this.convertCustomerTypeEntitiesToCustomerTypes(entities);

	}
	
	public List<CustomerType> customerTypesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		List<CustomerTypeEntity> entities = repository.findCustomerTypesOfBusinessDomain(businessDomainId);
		return this.convertCustomerTypeEntitiesToCustomerTypes(entities);
	}

}
