package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Getter;
import lombok.Setter;

public class CustomerCategory {
	
	private CustomerCategoryId customerCategoryId;
	private String customerCategoryName;
	private CompanyId companyId;
	@Getter
	@Setter
	private BusinessUnitId businessUnitId;
	private CustomerTypeId customerTypeId;
	private String customerTypeName;
	private List<Country> countries = new ArrayList<>();
	private List<Age> ages = new ArrayList<>();
	private Gender gender;
	private Personality personality;
	private IndustryGroup industryGroup;
	private Industry industry;
	private IndustrySubGroup industrySubGroup;
	private Size size;
	@Getter
	@Setter
	private Url url;
	@Getter
	@Setter
	private String customerCategoryDescription;
	@Getter
	@Setter
	private List<Goal> goals = new ArrayList<>();
	@Getter
	@Setter
	private List<Achievement> achievements = new ArrayList<>();
	@Getter
	@Setter
	private Date startDate;
	@Getter
	@Setter
	private Date endDate;
	@Getter
	@Setter
	private BusinessState businessState;
	
	protected CustomerCategory(CompanyId companyId, CustomerTypeId customerTypeId, CustomerCategoryId customerCategoryId) {
		this.setCompanyId(companyId);
		this.setCustomerTypeId(customerTypeId);
		this.setCustomerCategoryId(customerCategoryId);
	}
	
	protected void setCustomerCategoryId(CustomerCategoryId customerCategoryId) {
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		this.customerCategoryId = customerCategoryId;
	}
	public CustomerCategoryId customerCategoryId() {
		return this.customerCategoryId;
	}
	

	public void setCustomerCategoryName(String customerCategoryName) {
		if(customerCategoryName == null) {
			throw new IllegalArgumentException("The customerCategoryName may not be set to null.");
		}
		this.customerCategoryName = customerCategoryName;
	}
	public String customerCategoryName() {
		return this.customerCategoryName;
	}
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId =companyId;
	}
	public CompanyId companyId() {
		return this.companyId;
	}
	public void setCustomerTypeId(CustomerTypeId customerTypeId) {
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		this.customerTypeId = customerTypeId;
	}
	
	public CustomerTypeId customerTypeId() {
		return this.customerTypeId;
	}
	public void setIndustryGroup(IndustryGroup industryGroup) {
		if(industryGroup == null) {
			throw new IllegalArgumentException("The industryGroup may not be set to null.");
		}
		this.industryGroup = industryGroup;
	}
	public void setCustomerTypeName(String customerTypeName) {
		if(customerTypeName == null) {
			throw new IllegalArgumentException("The customerTypeName may not be set to null.");
		}
		this.customerTypeName = customerTypeName;
	}
	public String customerTypeName() {
		return this.customerTypeName;
	}
	public IndustryGroup industryGroup() {
		return this.industryGroup;
	}
	
	public void setIndustry(Industry industry) {
		if(industry == null) {
			throw new IllegalArgumentException("The industry may not be set to null.");
		}
		this.industry = industry;
	}
	
	public Industry industry() {
		return this.industry;
	}
	
	public void setIndustrySubGroup(IndustrySubGroup industrySubGroup) {
		if(industrySubGroup == null) {
			throw new IllegalArgumentException("The industrySubGroup may not be set to null.");
		}
		this.industrySubGroup = industrySubGroup;
	}
	
	public IndustrySubGroup industrySubGroup() {
		return this.industrySubGroup;
	}
	public void setCoutries(List<Country> countries) {
		if(countries == null) {
			throw new IllegalArgumentException("The countries may not be set to null.");
		}
		this.countries = countries;
	}
	public List<Country> countries(){
		return this.countries;
	}
	
	public void setAges(List<Age> ages) {
		if(ages == null) {
			throw new IllegalArgumentException("The ages may not be set to null.");
		}
		this.ages = ages;
	}
	public List<Age> ages(){
		return this.ages;
	}
	public void setGender(Gender gender) {
		/*
		if(gender == null) {
			throw new IllegalArgumentException("The gender may not be set to null.");
		}
		*/
		this.gender = gender;
	}
	public Gender gender() {
		return this.gender;
	}
	public void setSize(Size size) {
		if(size == null) {
			throw new IllegalArgumentException("The size may not be set to null.");
		}
		this.size = size;
	}
	public Size size() {
		return this.size;
	}
	public void setPersonality(Personality personality) {
		if(personality == null) {
			throw new IllegalArgumentException("The personality may not be set to null.");
		}
		this.personality = personality;
	}
	public Personality personality() {
		return this.personality;
	}

}
