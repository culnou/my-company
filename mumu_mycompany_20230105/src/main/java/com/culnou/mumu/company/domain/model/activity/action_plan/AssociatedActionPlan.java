package com.culnou.mumu.company.domain.model.activity.action_plan;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedActionPlan {
	
	private String actionPlanId;
	private String actionPlanName;

}
