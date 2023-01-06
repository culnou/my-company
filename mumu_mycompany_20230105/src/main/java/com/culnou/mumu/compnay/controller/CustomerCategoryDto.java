package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Age;
import com.culnou.mumu.company.domain.model.Country;
import com.culnou.mumu.company.domain.model.Gender;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Size;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Data;
@Data
public class CustomerCategoryDto {
	
	private String customerCategoryId;
	private String customerCategoryName;
	private String customerCategoryDescription;
	private String companyId;
	private String businessUnitId;
	private String customerTypeId;
	private String customerTypeName;
	private String industryGroupId;
	private String industryGroupName;
	private String industryId;
	private String industryName;
	private String industrySubGroupId;
	private String industrySubGroupName;
	private String url;
	private Gender gender;
	private Personality personality;
	private Size size;
	private List<Country> countries;
	private List<Age> ages;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private Date startDate;
	private Date endDate;
	private BusinessState businessState;

}
