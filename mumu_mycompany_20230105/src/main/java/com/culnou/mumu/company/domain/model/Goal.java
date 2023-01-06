package com.culnou.mumu.company.domain.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;

@Data
@Embeddable
public class Goal {
	//@Embeddedの入れ子は可能。
	//https://stackoverflow.com/questions/15168339/using-embedded-in-embeddable-class
	@Embedded
	private Indicator indicator;
	private Date plannedDate;
	private String plannedValue;
	private Date deadline;
	private String goalOwnerId;
	private String goalOwnerName;
	private String goalOwnerType;
	private Date createdGoalAt;
	private Date updatedGoalAt;
	

}
