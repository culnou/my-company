package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.AssociatedCustomerCategory;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;


@Data
public class BusinessUnitDto {
	private String businessUnitId;
	private String businessDomainId;
	private String companyId;
	private String businessDomainName;
	private String businessUnitName;
	private String vision;
	private String url;
	private String customerCategoryId;
	private String customerCategoryName;
	private String productCategoryId;
	private String productCategoryName;
	private String industryId;
	private String industryName;
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	private List<AssociatedCustomerCategory> associatedCustomerCategories = new ArrayList<>();
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private String hypothesis;
	private String result;
	private String businessStrategy;
	private String customerCategory;
	private String productCategory;
	private Date startDate;
	private Date endDate;
	private BusinessState businessState;
	
	
}
