package com.culnou.mumu.company.domain.service.business.process;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanRepository;
import com.culnou.mumu.company.domain.model.BusinessProcessId;



@Service
@Transactional
public class CheckBusinessProcessUsed {
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	public boolean check(BusinessProcessId businessProcessId) throws Exception{
		boolean result = false;
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfBusinessProcess(businessProcessId);
		if(actionPlans.size() > 0) {
			result = true;
		}
		return result;
	}

}
