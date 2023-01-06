package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractProductCategoryRepository;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.ProductCategory;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductTypeId;
@Service("productCategoryJpaRepository")
@Transactional
public class ProductCategoryJpaRepository extends AbstractProductCategoryRepository {
	
	@Autowired
	private ProductCategoryJpaDataRepository repository;
	
	private ProductCategory convertProductCategoryEntityToProductCategory(ProductCategoryEntity entity) {
		ProductCategory category = this.convertFromEntity(entity);
		if(entity.getProductCategoryName() != null) {
			category.setProductCategoryName(entity.getProductCategoryName());
		}
		if(entity.getProductCategoryDescription() != null) {
			category.setProductCategoryDescription(entity.getProductCategoryDescription());
		}
		if(entity.getProductCategoryPosition() != null) {
			category.setProductCategoryPosition(entity.getProductCategoryPosition());
		}
		if(entity.getProductTypeName() != null) {
			category.setProductTypeName(entity.getProductTypeName());
		}
		if(entity.getCustomerCategoryId() != null) {
			category.setCustomerCategoryId(entity.getCustomerCategoryId());
		}
		if(entity.getCustomerCategoryName() != null) {
			category.setCustomerCategoryName(entity.getCustomerCategoryName());
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
	    if(entity.getProductClass() != null) {
	    	category.setProductClass(entity.getProductClass());
	    }
	    if(entity.getRoles() != null) {
	    	category.setRoles(entity.getRoles());
	    }
	    if(entity.getGoodses() != null) {
	    	category.setGoodses(entity.getGoodses());
	    }
	    if(entity.getServices() != null) {
	    	category.setServices(entity.getServices());
	    }
	    if(entity.getPersonality() != null) {
	    	category.setPersonality(entity.getPersonality());
	    }
	    if(entity.getServiceType() != null) {
	    	category.setServiceType(entity.getServiceType());
	    }
	    if(entity.getService() != null) {
	    	category.setService(entity.getService());
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
	    if(entity.getCustomerJourney() != null) {
	    	category.setCustomerJourney(entity.getCustomerJourney());
		}
	    if(entity.getServiceScenario() != null) {
	    	category.setServiceScenario(entity.getServiceScenario());
		}
	    if(entity.getDomainModel() != null) {
	    	category.setDomainModel(entity.getDomainModel());
		}
	    if(entity.getDemo() != null) {
	    	category.setDemo(entity.getDemo());
		}
	    if(entity.getBusinessUnitId() != null) {
	    	category.setBusinessUnitId(entity.getBusinessUnitId());
		}
	   
		return category;
	}

	private ProductCategoryEntity convertProductCategoryToProductCategoryEntity(ProductCategory category) {
		ProductCategoryEntity entity = new ProductCategoryEntity();
		entity.setCompanyId(category.companyId());
		entity.setProductTypeId(category.productTypeId());
		entity.setProductCategoryId(category.productCategoryId());
		if(category.productCategoryName() != null) {
			entity.setProductCategoryName(category.productCategoryName());
		}
		if(category.productCategoryDescription() != null) {
			entity.setProductCategoryDescription(category.productCategoryDescription());
		}
		if(category.productCategoryPosition() != null) {
			entity.setProductCategoryPosition(category.productCategoryPosition());
		}
		if(category.productTypeName() != null) {
			entity.setProductTypeName(category.productTypeName());
		}
		if(category.customerCategoryId() != null) {
			entity.setCustomerCategoryId(category.customerCategoryId());
		}
		if(category.customerCategoryName() != null) {
			entity.setCustomerCategoryName(category.customerCategoryName());
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
		
		if(category.productClass() != null) {
			entity.setProductClass(category.productClass());
		}
		
		if(category.roles() != null) {
			entity.setRoles(category.roles());
		}
		if(category.goodses() != null) {
			entity.setGoodses(category.goodses());
		}
		if(category.services() != null) {
			entity.setServices(category.services());
		}
		if(category.personality() != null) {
			entity.setPersonality(category.personality());
		}
		if(category.serviceType() != null) {
			entity.setServiceType(category.serviceType());
		}
		if(category.service() != null) {
			entity.setService(category.service());
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
		if(category.getCustomerJourney() != null) {
			entity.setCustomerJourney(category.getCustomerJourney());
		}
		if(category.getServiceScenario() != null) {
			entity.setServiceScenario(category.getServiceScenario());
		}
		if(category.getDomainModel() != null) {
			entity.setDomainModel(category.getDomainModel());
		}
		if(category.getDemo() != null) {
			entity.setDemo(category.getDemo());
		}
		if(category.getBusinessUnitId() != null) {
			entity.setBusinessUnitId(category.getBusinessUnitId());
		}
        
		return entity;
	}
	
	private List<ProductCategory> convertProductCategoryEntitiesToProductCategories(List<ProductCategoryEntity> entities){
		List<ProductCategory> cats = new ArrayList<>();
		for(ProductCategoryEntity entity : entities) {
			cats.add(this.convertProductCategoryEntityToProductCategory(entity));
		}
		return cats;
	}

	@Override
	public ProductCategoryId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ProductCategoryId(str);
	}

	@Override
	public void save(ProductCategory productCategory) throws Exception {
		// TODO Auto-generated method stub
		if(productCategory == null) {
			throw new IllegalArgumentException("The productCategory may not be set to null.");
		}
		repository.save(this.convertProductCategoryToProductCategoryEntity(productCategory));


	}

	@Override
	public void remove(ProductCategory productCategory) throws Exception {
		// TODO Auto-generated method stub
		if(productCategory == null) {
			throw new IllegalArgumentException("The productCategory may not be set to null.");
		}
		repository.delete(this.convertProductCategoryToProductCategoryEntity(productCategory));


	}

	@Override
	public ProductCategory productCategoryOfId(ProductCategoryId productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		ProductCategoryEntity entity = repository.findByProductCategoryId(productCategoryId);
		//エンティティ単体をFindするメソッドでは、エンティティのNullチェックを行うようにします。これをしないと、エンティティからPOJOに変換する処理でNullポインターエラーが発生します。
		if(entity == null) {
			throw new Exception("The ProductCategoryEntity may not be set to null.");
		}
		return this.convertProductCategoryEntityToProductCategory(entity);

	}

	@Override
	public List<ProductCategory> productCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ProductCategoryEntity> entities = repository.findProductCategoriesOfCompany(companyId);
		return this.convertProductCategoryEntitiesToProductCategories(entities);

	}
	
	public List<ProductCategory> productCategoriesOfProductClass(BusinessUnitId businessUnitId, ProductClass productClass) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		if(productClass == null) {
			throw new IllegalArgumentException("The_productClass_may_not_be_set_to_null");
		}
		List<ProductCategoryEntity> entities = repository.findProductCategoriesOfProductClass(businessUnitId, productClass);
		return this.convertProductCategoryEntitiesToProductCategories(entities);

	}
	
	
	
	public List<ProductCategory> productCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		List<ProductCategoryEntity> entities = repository.findProductCategoriesOfBusinessUnit(businessUnitId);
		return this.convertProductCategoryEntitiesToProductCategories(entities);
	}

	@Override
	public List<ProductCategory> productCategoriesOfProductType(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		List<ProductCategoryEntity> entities = repository.findProductCategoriesOfProductType(productTypeId);
		return this.convertProductCategoryEntitiesToProductCategories(entities);
	}

	@Override
	public List<ProductCategory> productCategoriesOfCustomerCategory(CustomerCategoryId customerCategoryId)
			throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		List<ProductCategoryEntity> entities = repository.findProductCategoriesOfCustomerCategory(customerCategoryId);
		return this.convertProductCategoryEntitiesToProductCategories(entities);

	}

}
