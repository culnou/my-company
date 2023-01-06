package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.financial.asset.type.AssociatedFinancialAssetType;

import lombok.Data;
@Data
public class FinancialAssetCategoryDto {
	
	private String financialAssetCategoryId;
	private String financialAssetCategoryName;
	private String financialAssetCategoryDescription;
	private String companyId;
	private String businessUnitId;
	private String financialAssetTypeId;
	private String url;
	private BusinessState businessState;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private List<AssociatedFinancialAssetType> associatedFinancialAssetTypes = new ArrayList<>();

}
