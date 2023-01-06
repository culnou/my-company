package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractBusinessDomainRepository;
import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.ProductTypeId;
@Service("businessDomainJpaRepository")
@Transactional
public class BusinessDomainJpaRepository extends AbstractBusinessDomainRepository {

	@Autowired
	private BusinessDomainJpaDataRepository repository;
	
	@Override
	public BusinessDomainId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new BusinessDomainId(str);
	}
	
	public List<BusinessDomain> findEnterprises(boolean enterprise, CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return this.convertBusinessDomainEntitiesToBusinessDomains(repository.findEnterprises(enterprise, companyId));
	}

	@Override
	public void save(BusinessDomain businessDomain) throws Exception {
		// TODO Auto-generated method stub
		if(businessDomain == null) {
			throw new IllegalArgumentException("The businessDomain may not be set to null.");
		}
		repository.save(this.convertBusinessDomainToBusinessDomainEntity(businessDomain));
	}
	
	@Override
	public List<BusinessDomain> businessDomainOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<BusinessDomainEntity> entities = repository.findBusinessDomainsOfCompany(companyId);
		return this.convertBusinessDomainEntitiesToBusinessDomains(entities);
	}
	
	@Override
	public List<BusinessDomain> businessDomainsOfCustomerType(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		List<BusinessDomainEntity> entities = repository.findBusinessDomainsOfCustomerType(customerTypeId);
		return this.convertBusinessDomainEntitiesToBusinessDomains(entities);
	}

	@Override
	public List<BusinessDomain> businessDomainsOfProductType(ProductTypeId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		List<BusinessDomainEntity> entities = repository.findBusinessDomainsOfProductType(productTypeId);
		return this.convertBusinessDomainEntitiesToBusinessDomains(entities);
	}
	@Override
	public BusinessDomain businessDomainOfId(BusinessDomainId businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		BusinessDomainEntity entity = repository.findByBusinessDomainId(businessDomainId);
		//エンティティ単体をFindするメソッドでは、エンティティのNullチェックを行うようにします。これをしないと、エンティティからPOJOに変換する処理でNullポインターエラーが発生します。
		if(entity == null) {
			throw new Exception("The BusinessDomainEntity may not be set to null.");
		}
		return this.convertBusinessDomainEntityToBusinessDomain(entity);
	}
	
	@Override
	public void remove(BusinessDomain businessDomain) throws Exception {
		// TODO Auto-generated method stub
		if(businessDomain == null) {
			throw new IllegalArgumentException("The businessDomain may not be set to null.");
		}
		repository.delete(this.convertBusinessDomainToBusinessDomainEntity(businessDomain));
	}
	
	
	private List<BusinessDomain> convertBusinessDomainEntitiesToBusinessDomains(List<BusinessDomainEntity> entities){
		List<BusinessDomain> businessDomains = new ArrayList<>();
		for(BusinessDomainEntity entity : entities) {
			businessDomains.add(this.convertBusinessDomainEntityToBusinessDomain(entity));
		}
		return businessDomains;
	}
	
	
	private BusinessDomain convertBusinessDomainEntityToBusinessDomain(BusinessDomainEntity entity) {
	    BusinessDomain businessDomain = this.convertFromEntity(entity);
	    if(entity.getBusinessDomainName() != null) {
	    	businessDomain.setBusinessDomainName(entity.getBusinessDomainName());
	    }
	    if(entity.getIndustryGroup() != null) {
	    	businessDomain.setIndustryGroup(entity.getIndustryGroup());
	    }
	    if(entity.getIndustry() != null) {
	    	businessDomain.setIndustry(entity.getIndustry());
	    }
	    if(entity.getIndustrySubGroup() != null) {
	    	businessDomain.setIndustrySubGroup(entity.getIndustrySubGroup());
	    }
	    if(entity.getPurpose() != null) {
	    	businessDomain.setPurpose(entity.getPurpose());
		}
	    businessDomain.setEnterprise(entity.isEnterprise());
	    if(entity.getBusinessModel() != null) {
	    	businessDomain.setBusinessModel(entity.getBusinessModel());
		}
	    if(entity.getCustomerType() != null) {
	    	businessDomain.setCustomerType(entity.getCustomerType());
		}
	    if(entity.getProductType() != null) {
	    	businessDomain.setProductType(entity.getProductType());
		}
	    if(entity.getUrl() != null) {
	    	businessDomain.setUrl(entity.getUrl());
		}
	    if(entity.getCustomerTypeId() != null) {
	    	businessDomain.setCustomerTypeId(entity.getCustomerTypeId());
	    }
	    if(entity.getCustomerTypeName() != null) {
	    	businessDomain.setCustomerTypeName(entity.getCustomerTypeName());
	    }
	    if(entity.getProductTypeId() != null) {
	    	businessDomain.setProductTypeId(entity.getProductTypeId());
	    }
	    if(entity.getProductTypeName() != null) {
	    	businessDomain.setProductTypeName(entity.getProductTypeName());
	    }
	    if(entity.getIndicators() != null) {
	    	businessDomain.setIndicators(entity.getIndicators());
	    }
	    if(entity.getAssociatedProductTypes() != null) {
	    	businessDomain.setAssociatedProductTypes(entity.getAssociatedProductTypes());
	    }
	    if(entity.getAssociatedCustomerTypes() != null) {
	    	businessDomain.setAssociatedCustomerTypes(entity.getAssociatedCustomerTypes());
	    }
	    if(entity.getAssociatedUrls() != null) {
	    	businessDomain.setAssociatedUrls(entity.getAssociatedUrls());
	    }
	    if(entity.getCreatedAt() != null) {
	    	businessDomain.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	businessDomain.setUpdatedAt(entity.getUpdatedAt());
	    }
	    
	    return businessDomain;
	}
	
	private BusinessDomainEntity convertBusinessDomainToBusinessDomainEntity(BusinessDomain businessDomain){
		BusinessDomainEntity entity = new BusinessDomainEntity();
		entity.setBusinessDomainId(businessDomain.businessDomainId());
		entity.setCompanyId(businessDomain.companyId());
		if(businessDomain.businessDomainName() != null) {
			entity.setBusinessDomainName(businessDomain.businessDomainName());
		}
		if(businessDomain.industryGroup() != null) {
			entity.setIndustryGroup(businessDomain.industryGroup());
		}
		if(businessDomain.industry() != null) {
			entity.setIndustry(businessDomain.industry());
		}
		if(businessDomain.industrySubGroup() != null) {
			entity.setIndustrySubGroup(businessDomain.industrySubGroup());
		}
		if(businessDomain.purpose() != null) {
			entity.setPurpose(businessDomain.purpose());
		}
		entity.setEnterprise(businessDomain.isEnterprise());
		if(businessDomain.url() != null) {
			entity.setUrl(businessDomain.url());
		}
		if(businessDomain.getBusinessModel() != null) {
			entity.setBusinessModel(businessDomain.getBusinessModel());
		}
		if(businessDomain.getCustomerType() != null) {
			entity.setCustomerType(businessDomain.getCustomerType());
		}
		if(businessDomain.getProductType() != null) {
			entity.setProductType(businessDomain.getProductType());
		}
		if(businessDomain.customerTypeId() != null) {
			entity.setCustomerTypeId(businessDomain.customerTypeId());
		}
		if(businessDomain.getAssociatedProductTypes() != null) {
			entity.setAssociatedProductTypes(businessDomain.getAssociatedProductTypes());
		}
		if(businessDomain.getAssociatedCustomerTypes() != null) {
			entity.setAssociatedCustomerTypes(businessDomain.getAssociatedCustomerTypes());
		}
		if(businessDomain.getAssociatedUrls() != null) {
			entity.setAssociatedUrls(businessDomain.getAssociatedUrls());
		}
		if(businessDomain.customerTypeName() != null) {
			entity.setCustomerTypeName(businessDomain.customerTypeName());
		}
		if(businessDomain.productTypeId() != null) {
			entity.setProductTypeId(businessDomain.productTypeId());
		}
		if(businessDomain.productTypeName() != null) {
			entity.setProductTypeName(businessDomain.productTypeName());
		}
		if(businessDomain.getIndicators() != null) {
			entity.setIndicators(businessDomain.getIndicators());
		}
		if(businessDomain.getCreatedAt() != null) {
			entity.setCreatedAt(businessDomain.getCreatedAt());
		}
		if(businessDomain.getUpdatedAt() != null) {
			entity.setUpdatedAt(businessDomain.getUpdatedAt());
		}
		return entity;
	}
	
	

}
