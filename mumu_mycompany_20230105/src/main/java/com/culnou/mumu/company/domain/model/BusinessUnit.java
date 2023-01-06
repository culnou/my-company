package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;

import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.category.DataCategory;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryId;

import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;

import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;

import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;


import lombok.Getter;
import lombok.Setter;

public class BusinessUnit {
	
	private BusinessUnitId businessUnitId;
	private BusinessDomainId businessDomainId;
	private CompanyId companyId;
	private String businessUnitName;
	private String businessDomainName;
	private String vision;
	@Getter
	@Setter
	private String slogan;
	private CustomerCategoryId customerCategoryId;
	private String customerCategoryName;
	private ProductCategoryId productCategoryId;
	private String productCategoryName;
	@Setter
	@Getter
	private Industry industry;
	@Setter
	@Getter
	private Url url;
	@Setter
	@Getter
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	@Setter
	@Getter
	private List<AssociatedCustomerCategory> associatedCustomerCategories = new ArrayList<>();
	@Getter
	@Setter
	private List<Goal> goals = new ArrayList<>();
	@Getter
	@Setter
	private List<Achievement> achievements = new ArrayList<>();
	@Getter
	@Setter
	private String hypothesis;
	@Getter
	@Setter
	private String result;
	@Getter
	@Setter
	private String businessStrategy;
	@Getter
	@Setter
	private String customerCategory;
	@Getter
	@Setter
	private String productCategory;
	@Getter
	@Setter
	private Date startDate;
	@Getter
	@Setter
	private Date endDate;
	@Getter
	@Setter
	private BusinessState businessState;
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	
	
	public BusinessUnit(CompanyId companyId, BusinessDomainId businessDmainId, BusinessUnitId businessUnitId) {
		this.setCompanyId(companyId);
		this.setBusinessDomainId(businessDmainId);
		this.setBusinessUnitId(businessUnitId);
	}
	
	
	protected void setBusinessUnitId(BusinessUnitId businessUnitId) {
		//不変性の保持。
		if(this.businessUnitId != null) {
			throw new IllegalArgumentException("The_businessUnitId_id_is_already_exist");
		}
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	public BusinessUnitId businessUnitId() {
		return this.businessUnitId;
	}
	
	
	public void setBusinessDomainId(BusinessDomainId businessDomainId) {
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
		}
		this.businessDomainId = businessDomainId;
	}
	
	public BusinessDomainId businessDomainId() {
		return this.businessDomainId;
	}
	
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId =companyId;
	}
	
	public CompanyId companyId() {
		return this.companyId;
	}
	
	public void setBusinessUnitName(String businessUnitName) {
		if(businessUnitName == null) {
			throw new IllegalArgumentException("The_businessUnitName_may_not_be_set_to_null");
		}
		if(businessUnitName.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_business_Unit_names");
		}
		
		this.businessUnitName = businessUnitName;
	}
	
	public String businessUnitName() {
		return this.businessUnitName;
	}
	
	public void setVision(String vision) {
		/*
		if(vision == null) {
			throw new IllegalArgumentException("The vision may not be set to null.");
		}
		*/
		this.vision = vision;
	}
	
	public String vision() {
		return this.vision;
	}
	
	public void setBusinessDomainName(String businessDomainName) {
		/*
		if(businessDomainName == null) {
			throw new IllegalArgumentException("The businessDomainName may not be set to null.");
		}
		*/
		this.businessDomainName = businessDomainName;
	}
	
	public String businessDomainName() {
		return this.businessDomainName;
	}
	
	public void setCustomerCategoryId(CustomerCategoryId customerCategoryId) {
		/*
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		*/
		this.customerCategoryId = customerCategoryId;
	}
	
	public CustomerCategoryId customerCategoryId() {
		return this.customerCategoryId;
	}
	
	public void setCustomerCategoryName(String customerCategoryName) {
		/*
		if(customerCategoryName == null) {
			throw new IllegalArgumentException("The customerCategoryName may not be set to null.");
		}
		*/
		this.customerCategoryName = customerCategoryName;
	}
	
	public String customerCategoryName() {
		return this.customerCategoryName;
	}
	
	public void setProductCategoryId(ProductCategoryId productCategoryId) {
		/*
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		*/
		this.productCategoryId = productCategoryId;
	}
	
	public ProductCategoryId productCategoryId() {
		return this.productCategoryId;
	}
	
	public void setProductCategoryName(String productCategoryName) {
		/*
		if(productCategoryName == null) {
			throw new IllegalArgumentException("The productCategoryName may not be set to null.");
		}
		*/
		this.productCategoryName = productCategoryName;
	}
	
	public String productCategoryName() {
		return this.productCategoryName;
	}
	
	public ApplicationCategory defineApplicationCategory(ApplicationCategoryId id, String name) {
		//事前条件の検証
		if(id == null) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_applicationCategoryName_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new ApplicationCategory(id, name, companyId, businessUnitId);
	}
	
	public DataCategory defineDataCategory(DataCategoryId id, String name, DataClass dataClass) {
		//事前条件の検証
		if(id == null) {
			throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_dataCategoryName_may_not_be_set_to_null");
		}
		if(dataClass == null) {
			throw new IllegalArgumentException("The_dataClass_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new DataCategory(id, name, companyId, businessUnitId, dataClass);
	}
	
	public MemberCategory defineMemberCategory(MemberCategoryId id, String name) {
		//事前条件の検証
		
		if(id == null) {
			throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_memberCategoryName_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new MemberCategory(id, name, companyId, businessUnitId);
	}
	
	public PartnerCategory definePartnerCategory(PartnerCategoryId id, String name) {
		//事前条件の検証
		
		if(id == null) {
			throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_partnerCategoryName_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new PartnerCategory(id, name, companyId, businessUnitId);
	}
	
	public FinancialAssetCategory defineFinancialAssetCategory(FinancialAssetCategoryId id, String name) {
		//事前条件の検証
		
		if(id == null) {
			throw new IllegalArgumentException("The_financialAssetCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_financialAssetCategoryName_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new FinancialAssetCategory(id, name, companyId, businessUnitId);
	}
	
	public ItCategory defineItCategory(ItCategoryId id, String name) {
		//事前条件の検証
		
		if(id == null) {
			throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_itCategoryName_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new ItCategory(id, name, companyId, businessUnitId);
	}
	
	public PlaceCategory definePlaceCategory(PlaceCategoryId id, String name) {
		//事前条件の検証
		
		if(id == null) {
			throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
		}
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("The_placeCategoryName_may_not_be_set_to_null");
		}
		//ビジネスロジック
		return new PlaceCategory(id, name, companyId, businessUnitId);
	}
	
	
	
	

}
