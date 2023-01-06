package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractBusinessUnitRepository;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;


@Service("businessUnitJpaRepository")
@Transactional
public class BusinessUnitJpaRepository extends AbstractBusinessUnitRepository {
	
	@Autowired
	private BusinessUnitJpaDataRepository repository;
	
	@Override
	public BusinessUnitId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new BusinessUnitId(str);
	}
	
	@Override
	public void save(BusinessUnit businessUnit) throws Exception {
		// TODO Auto-generated method stub
		if(businessUnit == null) {
			throw new IllegalArgumentException("The businessUnit may not be set to null.");
		}
		repository.save(this.convertBusinessUnitToBusinessUnitEntity(businessUnit));
	}
	
	@Override
	public List<BusinessUnit> businessUnitsOfDomain(BusinessDomainId businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		List<BusinessUnitEntity> entities = repository.findBusinessUnitsOfDomain(businessDomainId);
		return this.convertBusinessUnitEntitiesToBusinessUnits(entities);
	}
	
	@Override
	public List<BusinessUnit> businessUnitsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<BusinessUnitEntity> entities = repository.findBusinessUnitsOfCompany(companyId);
		return this.convertBusinessUnitEntitiesToBusinessUnits(entities);
	}
	
	@Override
	public List<BusinessUnit> businessUnitsOfCustomerCategory(CustomerCategoryId customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		List<BusinessUnitEntity> entities = repository.findBusinessUnitsOfCustomerCategory(customerCategoryId);
		return this.convertBusinessUnitEntitiesToBusinessUnits(entities);
	}

	@Override
	public List<BusinessUnit> businessUnitsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		List<BusinessUnitEntity> entities = repository.findBusinessUnitsOfProductCategory(productCategoryId);
		return this.convertBusinessUnitEntitiesToBusinessUnits(entities);
	}
	
	@Override
	public BusinessUnit businessUnitOfId(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		BusinessUnitEntity entity = repository.findByBusinessUnitId(businessUnitId);
		//エンティティ単体をFindするメソッドでは、エンティティのNullチェックを行うようにします。これをしないと、エンティティからPOJOに変換する処理でNullポインターエラーが発生します。
		if(entity == null) {
			throw new Exception("The BusinessUnitEntity may not be set to null.");
		}
		return this.convertBusinessUnitEntityToBusinessUnit(entity);
	}
	
	@Override
	public void remove(BusinessUnit businessUnit) throws Exception {
		// TODO Auto-generated method stub
		if(businessUnit == null) {
			throw new IllegalArgumentException("The businessUnit may not be set to null.");
		}
		repository.delete(this.convertBusinessUnitToBusinessUnitEntity(businessUnit));
	}

	
	
	
	private List<BusinessUnit> convertBusinessUnitEntitiesToBusinessUnits(List<BusinessUnitEntity> entities){
		List<BusinessUnit> businessUnits = new ArrayList<>();
		for(BusinessUnitEntity entity : entities) {
			businessUnits.add(this.convertBusinessUnitEntityToBusinessUnit(entity));
		}
		return businessUnits;
	}
	
	
	private BusinessUnit convertBusinessUnitEntityToBusinessUnit(BusinessUnitEntity entity) {
	    BusinessUnit businessUnit = this.convertFromEntity(entity);
	    if(entity.getBusinessDomainName() != null) {
	    	businessUnit.setBusinessDomainName(entity.getBusinessDomainName());
	    }
	    if(entity.getBusinessUnitName() != null) {
	    	businessUnit.setBusinessUnitName(entity.getBusinessUnitName());
	    }
	    if(entity.getVision() != null) {
	    	businessUnit.setVision(entity.getVision());
		}
	    if(entity.getBusinessState() != null) {
	    	businessUnit.setBusinessState(entity.getBusinessState());
		}
	    if(entity.getHypothesis() != null) {
	    	businessUnit.setHypothesis(entity.getHypothesis());
		}
	    if(entity.getResult() != null) {
	    	businessUnit.setResult(entity.getResult());
		}
	    if(entity.getBusinessStrategy() != null) {
	    	businessUnit.setBusinessStrategy(entity.getBusinessStrategy());
		}
	    if(entity.getCustomerCategory() != null) {
	    	businessUnit.setCustomerCategory(entity.getCustomerCategory());
		}
	    if(entity.getProductCategory() != null) {
	    	businessUnit.setProductCategory(entity.getProductCategory());
		}
	    if(entity.getUrl() != null) {
	    	businessUnit.setUrl(entity.getUrl());
		}
	    if(entity.getStartDate() != null) {
	    	businessUnit.setStartDate(entity.getStartDate());
		}
	    if(entity.getEndDate() != null) {
	    	businessUnit.setEndDate(entity.getEndDate());
		}
	    if(entity.getIndustry() != null) {
	    	businessUnit.setIndustry(entity.getIndustry());
	    }
	    
	    if(entity.getCustomerCategoryId() != null) {
	    	businessUnit.setCustomerCategoryId(entity.getCustomerCategoryId());
	    }
	    if(entity.getCustomerCategoryName() != null) {
	    	businessUnit.setCustomerCategoryName(entity.getCustomerCategoryName());
	    }
	    if(entity.getProductCategoryId() != null) {
	    	businessUnit.setProductCategoryId(entity.getProductCategoryId());
	    }
	    if(entity.getProductCategoryName() != null) {
	    	businessUnit.setProductCategoryName(entity.getProductCategoryName());
	    }
	    if(entity.getAssociatedProductCategories() != null) {
	    	businessUnit.setAssociatedProductCategories(entity.getAssociatedProductCategories());
	    }
	    if(entity.getAssociatedCustomerCategories() != null) {
	    	businessUnit.setAssociatedCustomerCategories(entity.getAssociatedCustomerCategories());
	    }
	    if(entity.getGoals() != null) {
	    	businessUnit.setGoals(entity.getGoals());
	    }
	    if(entity.getAchievements() != null) {
	    	businessUnit.setAchievements(entity.getAchievements());
	    }
	    if(entity.getCreatedAt() != null) {
	    	businessUnit.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	businessUnit.setUpdatedAt(entity.getUpdatedAt());
	    }
	    
	    return businessUnit;
	}
	
	private BusinessUnitEntity convertBusinessUnitToBusinessUnitEntity(BusinessUnit businessUnit){
		BusinessUnitEntity entity = new BusinessUnitEntity();
		entity.setBusinessUnitId(businessUnit.businessUnitId());
		entity.setBusinessDomainId(businessUnit.businessDomainId());
		entity.setCompanyId(businessUnit.companyId());
		if(businessUnit.businessDomainName() != null) {
			entity.setBusinessDomainName(businessUnit.businessDomainName());
		}
		if(businessUnit.businessUnitName() != null) {
			entity.setBusinessUnitName(businessUnit.businessUnitName());
		}
		if(businessUnit.vision() != null) {
			entity.setVision(businessUnit.vision());
		}
		if(businessUnit.getHypothesis() != null) {
			entity.setHypothesis(businessUnit.getHypothesis());
		}
		if(businessUnit.getResult() != null) {
			entity.setResult(businessUnit.getResult());
		}
		if(businessUnit.getBusinessStrategy() != null) {
			entity.setBusinessStrategy(businessUnit.getBusinessStrategy());
		}
		if(businessUnit.getCustomerCategory() != null) {
			entity.setCustomerCategory(businessUnit.getCustomerCategory());
		}
		if(businessUnit.getProductCategory() != null) {
			entity.setProductCategory(businessUnit.getProductCategory());
		}
		if(businessUnit.getBusinessState() != null) {
			entity.setBusinessState(businessUnit.getBusinessState());
		}
		if(businessUnit.getUrl() != null) {
			
			entity.setUrl(businessUnit.getUrl());
		}
        if(businessUnit.getStartDate() != null) {
			
			entity.setStartDate(businessUnit.getStartDate());
		}
        if(businessUnit.getEndDate() != null) {
			
			entity.setEndDate(businessUnit.getEndDate());
		}
		if(businessUnit.getIndustry() != null) {
			entity.setIndustry(businessUnit.getIndustry());
		}
		
		if(businessUnit.customerCategoryId() != null) {
			entity.setCustomerCategoryId(businessUnit.customerCategoryId());
		}
		if(businessUnit.customerCategoryName() != null) {
			entity.setCustomerCategoryName(businessUnit.customerCategoryName());
		}
		if(businessUnit.productCategoryId() != null) {
			entity.setProductCategoryId(businessUnit.productCategoryId());
		}
		if(businessUnit.productCategoryName() != null) {
			entity.setProductCategoryName(businessUnit.productCategoryName());
		}
		if(businessUnit.getAssociatedProductCategories() != null) {
			entity.setAssociatedProductCategories(businessUnit.getAssociatedProductCategories());
		}
		if(businessUnit.getAssociatedCustomerCategories() != null) {
			entity.setAssociatedCustomerCategories(businessUnit.getAssociatedCustomerCategories());
		}
		if(businessUnit.getGoals() != null) {
			entity.setGoals(businessUnit.getGoals());
		}
		if(businessUnit.getAchievements() != null) {
			entity.setAchievements(businessUnit.getAchievements());
		}
		if(businessUnit.getCreatedAt() != null) {
			entity.setCreatedAt(businessUnit.getCreatedAt());
		}
		if(businessUnit.getUpdatedAt() != null) {
			entity.setUpdatedAt(businessUnit.getUpdatedAt());
		}
		return entity;
	}

}
