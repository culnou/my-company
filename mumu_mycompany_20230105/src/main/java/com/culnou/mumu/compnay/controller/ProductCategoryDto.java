package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Goods;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.Role;
import com.culnou.mumu.company.domain.model.Service;
import com.culnou.mumu.company.domain.model.ServiceType;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Data;


@Data
public class ProductCategoryDto {
	
	private String productCategoryId;
	private String productCategoryName;
	private String productCategoryDescription;
	private String productCategoryPosition;
	private String companyId;
	private String businessUnitId;
	private String productTypeId;
	private String productTypeName;
	private String customerCategoryId;
	private String customerCategoryName;
	private String industryGroupId;
	private String industryGroupName;
	private String industryId;
	private String industryName;
	private String industrySubGroupId;
	private String industrySubGroupName;
	private List<Role> roles;
	private List<Goods> goodses;
	private List<Service> services;
	private ProductClass productClass;
	private ServiceType serviceType;
	private Service service;
	private Personality personality;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private String url;
	private String customerJourney;
	private String serviceScenario;
	private String domainModel;
	private String demo;
	private Date startDate;
	private Date endDate;
	private BusinessState businessState;

}
