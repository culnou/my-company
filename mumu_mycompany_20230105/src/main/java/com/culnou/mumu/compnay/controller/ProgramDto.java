package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.AssociatedCustomerCategory;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.activity.action_plan.AssociatedActionPlan;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.category.AssociatedDataCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.AssociatedFinancialAssetCategory;
import com.culnou.mumu.company.domain.model.member.category.AssociatedMemberCategory;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.place.category.AssociatedPlaceCategory;

import lombok.Data;
@Data
public class ProgramDto {
	
	private String programId;
	private String companyId;
	private String businessUnitId;
	private String programName;
	private String programDescription;
	private String hypothesis;
	private String result;
	private String vision;
	private String slogan;
	private String url;
	private Date startDate;
	private Date endDate;
	private BusinessState businessState;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private List<AssociatedCustomerCategory> associatedCustomerCategories = new ArrayList<>();
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	private List<AssociatedMemberCategory> associatedMemberCategories = new ArrayList<>();
	private List<AssociatedPartnerCategory> associatedPartnerCategories = new ArrayList<>();
	private List<AssociatedPlaceCategory> associatedPlaceCategories = new ArrayList<>();
	private List<AssociatedActionPlan> associatedActionPlans = new ArrayList<>();
	private List<AssociatedFinancialAssetCategory> associatedFinancialAssetCategories = new ArrayList<>();
	private List<AssociatedDataCategory> associatedDataCategories = new ArrayList<>();
	private List<AssociatedApplicationCategory> associatedApplicationCategories = new ArrayList<>();

}
