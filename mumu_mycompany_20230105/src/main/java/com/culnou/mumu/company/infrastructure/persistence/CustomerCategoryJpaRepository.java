package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractCustomerCategoryRepository;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategory;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
@Service("customerCategoryJpaRepository")
@Transactional
public class CustomerCategoryJpaRepository extends AbstractCustomerCategoryRepository {
	
	@Autowired
	private CustomerCategoryJpaDataRepository repository;
	
	private CustomerCategory convertCustomerCategoryEntityToCustomerCategory(CustomerCategoryEntity entity) {
		CustomerCategory category = this.convertFromEntity(entity);
		if(entity.getCustomerCategoryName() != null) {
			category.setCustomerCategoryName(entity.getCustomerCategoryName());
		}
		if(entity.getCustomerTypeName() != null) {
			category.setCustomerTypeName(entity.getCustomerTypeName());
		}
		if(entity.getCustomerCategoryDescription() != null) {
			category.setCustomerCategoryDescription(entity.getCustomerCategoryDescription());
		}
	    if(entity.getIndustryGroup() != null) {
	    	category.setIndustryGroup(entity.getIndustryGroup());
	    }
	    if(entity.getIndustry() != null) {
	    	category.setIndustry(entity.getIndustry());
	    }
	    if(entity.getIndustrySubGroup() != null) {
	    	category.setIndustrySubGroup(entity.getIndustrySubGroup());
	    }
	    if(entity.getGender() != null) {
	    	category.setGender(entity.getGender());
	    }
	    if(entity.getPersonality() != null) {
	    	category.setPersonality(entity.getPersonality());
	    }
	    if(entity.getSize() != null) {
	    	category.setSize(entity.getSize());
	    }
	    if(entity.getCountries() != null) {
	    	category.setCoutries(entity.getCountries());
	    }
	    if(entity.getAges() != null) {
	    	category.setAges(entity.getAges());
	    }
	    if(entity.getGoals() != null) {
	    	category.setGoals(entity.getGoals());
	    }
	    if(entity.getAchievements() != null) {
	    	category.setAchievements(entity.getAchievements());
	    }
	    if(entity.getUrl() != null) {
	    	category.setUrl(entity.getUrl());
		}
	    if(entity.getBusinessUnitId() != null) {
	    	category.setBusinessUnitId(entity.getBusinessUnitId());
		}
		return category;
	}
	
	private CustomerCategoryEntity convertCustomerCategoryToCustomerCategoryEntity(CustomerCategory category) {
		CustomerCategoryEntity entity = new CustomerCategoryEntity();
		entity.setCompanyId(category.companyId());
		entity.setCustomerTypeId(category.customerTypeId());
		entity.setCustomerCategoryId(category.customerCategoryId());
		if(category.customerCategoryName() != null) {
			entity.setCustomerCategoryName(category.customerCategoryName());
		}
		if(category.getCustomerCategoryDescription() != null) {
			entity.setCustomerCategoryDescription(category.getCustomerCategoryDescription());
		}
		if(category.customerTypeName() != null) {
			entity.setCustomerTypeName(category.customerTypeName());
		}
		if(category.industryGroup() != null) {
			entity.setIndustryGroup(category.industryGroup());
		}
		if(category.industry() != null) {
			entity.setIndustry(category.industry());
		}
		if(category.industrySubGroup() != null) {
			entity.setIndustrySubGroup(category.industrySubGroup());
		}
		if(category.gender() != null) {
			entity.setGender(category.gender());
		}
		if(category.personality() != null) {
			entity.setPersonality(category.personality());
		}
		if(category.size() != null) {
			entity.setSize(category.size());
		}
		if(category.countries() != null) {
			entity.setCountries(category.countries());
		}
		if(category.ages() != null) {
			entity.setAges(category.ages());
		}
		if(category.getGoals() != null) {
			entity.setGoals(category.getGoals());
		}
		if(category.getAchievements() != null) {
			entity.setAchievements(category.getAchievements());
		}
		if(category.getUrl() != null) {
			entity.setUrl(category.getUrl());
		}
		if(category.getBusinessUnitId() != null) {
			entity.setBusinessUnitId(category.getBusinessUnitId());
		}

		return entity;
	}
	
	private List<CustomerCategory> convertCustomerCategoryEntitiesToCustomerCategories(List<CustomerCategoryEntity> entities){
		List<CustomerCategory> cats = new ArrayList<>();
		for(CustomerCategoryEntity entity : entities) {
			cats.add(this.convertCustomerCategoryEntityToCustomerCategory(entity));
		}
		return cats;
	}
	@Override
	public CustomerCategoryId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new CustomerCategoryId(str);
	}

	@Override
	public void save(CustomerCategory customerCategory) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategory == null) {
			throw new IllegalArgumentException("The customerCategory may not be set to null.");
		}
		repository.save(this.convertCustomerCategoryToCustomerCategoryEntity(customerCategory));

	}

	@Override
	public void remove(CustomerCategory customerCategory) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategory == null) {
			throw new IllegalArgumentException("The customerCategory may not be set to null.");
		}
		repository.delete(this.convertCustomerCategoryToCustomerCategoryEntity(customerCategory));

	}

	@Override
	public CustomerCategory customerCategoryOfId(CustomerCategoryId customerCategoryId) throws Exception {
		//検索条件チェック
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		CustomerCategoryEntity entity = repository.findByCustomerCategoryId(customerCategoryId);
		//エンティティ単体をFindするメソッドでは、エンティティのNullチェックを行うようにします。これをしないと、エンティティからPOJOに変換する処理でNullポインターエラーが発生します。
		if(entity == null) {
			throw new Exception("The CustomerCategoryEntity may not be set to null.");
		}
		return this.convertCustomerCategoryEntityToCustomerCategory(entity);
	}

	@Override
	public List<CustomerCategory> customerCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<CustomerCategoryEntity> entities = repository.findCustomerCategoriesOfCompany(companyId);
		return this.convertCustomerCategoryEntitiesToCustomerCategories(entities);

	}
	
	public List<CustomerCategory> customerCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		List<CustomerCategoryEntity> entities = repository.findCustomerCategoriesOfBusinessUnit(businessUnitId);
		return this.convertCustomerCategoryEntitiesToCustomerCategories(entities);
	}

	@Override
	public List<CustomerCategory> customerCategoriesOfCustomerType(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		List<CustomerCategoryEntity> entities = repository.findCustomerCategoriesOfCustomerType(customerTypeId);
		return this.convertCustomerCategoryEntitiesToCustomerCategories(entities);
	}
}
