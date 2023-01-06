package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerTypeService {
	
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	@Qualifier("customerTypeJpaRepository")
	@Autowired
	private CustomerTypeRepository customerTypeRepository;
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	@Qualifier("productTypeJpaRepository")
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	public boolean isUsed(CustomerTypeId customerTypeId) throws Exception{
		boolean result = false;
		//顧客カテゴリが使用している。
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfCustomerType(customerTypeId);
		if(customerCategories.size() > 0) {
			result = true;
		}
		List<ProductType> productTypes = productTypeRepository.productTypesOfCustomerType(customerTypeId);
		if(productTypes.size() > 0) {
			result = true;
		}
		/*
		//顧客KPIが使用している。
		CustomerType customerType = customerTypeRepository.customerTypeOfId(customerTypeId);
		if(customerType.getIndicators().size() > 0) {
			result = true;
		}
		*/
		//事業ドメインが使用している。
		/*
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfCustomerType(customerTypeId);
		if(businessDomains.size() > 0) {
			result = true;
		}
		*/
		
		return result;
	}
	
	public boolean indicatorIsUsed(CustomerTypeId customerTypeId, Indicator indicator) throws Exception{
		boolean result = false;
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfCustomerType(customerTypeId);
		for(CustomerCategory customerCategory : customerCategories) {
			for(Goal goal : customerCategory.getGoals()) {
				if(goal.getIndicator().equals(indicator)) {
					result = true;
					break;
				}
			}
		}
		return result;
		
	}

}
