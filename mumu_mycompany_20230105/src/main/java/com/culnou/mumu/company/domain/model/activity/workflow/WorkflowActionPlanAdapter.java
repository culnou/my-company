package com.culnou.mumu.company.domain.model.activity.workflow;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.ActionPlanRepository;



@Service
@Transactional
public class WorkflowActionPlanAdapter {
	
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	protected ActionPlan findActionPlanOfId(String actionPlanId) throws Exception{
		return actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlanId));
	}

}
