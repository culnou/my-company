package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.member.type.AssociatedMemberType;

import lombok.Data;
@Data
public class MemberCategoryDto {
	
	private String memberCategoryId;
	private String memberCategoryName;
	private String memberCategoryDescription;
	private String companyId;
	private String businessUnitId;
	private String memberTypeId;
	private String url;
	private BusinessState businessState;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private List<AssociatedMemberType> associatedMemberTypes = new ArrayList<>();

}
