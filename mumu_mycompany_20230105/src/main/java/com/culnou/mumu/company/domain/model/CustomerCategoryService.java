package com.culnou.mumu.company.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerCategoryService {
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	
	
	
	public boolean isUsed(CustomerCategoryId customerCategoryId) throws Exception{
		boolean result = false;
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfCustomerCategory(customerCategoryId);
		if(businessUnits.size() > 0) {
			result = true;
		}
		
		return result;
	}
	
	public boolean goalIsUsed(CustomerCategoryId customerCategoryId, Indicator indicator) throws Exception{
		boolean result = false;
		CustomerCategory customerCategory = customerCategoryRepository.customerCategoryOfId(customerCategoryId);
		List<Achievement> achievements = customerCategory.getAchievements();
		for(Achievement achievement : achievements) {
			if(achievement.getGoal().getIndicator().equals(indicator)){
				result = true;
				break;
			}
		}
		return result;
	}

}
