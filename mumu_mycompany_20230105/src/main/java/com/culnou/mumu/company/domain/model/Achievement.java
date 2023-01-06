package com.culnou.mumu.company.domain.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;

@Data
@Embeddable
public class Achievement {
	//@Embeddedの入れ子は可能。
	//https://stackoverflow.com/questions/15168339/using-embedded-in-embeddable-class
	@Embedded
	private Goal goal;
	private Date actualDate;
	private String actualValue;
	private Date createdAchievementAt;
	private Date updatedAchievementAt;
	

}
