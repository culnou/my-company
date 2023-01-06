package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.common.AssociatedUrl;
import com.culnou.mumu.company.domain.model.member.type.MemberClass;
import com.culnou.mumu.company.domain.model.member.type.MemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeId;
import com.culnou.mumu.company.domain.model.partner.type.PartnerType;
import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeId;

import lombok.Getter;
import lombok.Setter;

public class BusinessDomain {
	//必須
	private BusinessDomainId businessDomainId;
	//必須
	private CompanyId companyId;
	private IndustryGroup industryGroup;
	private Industry industry;
	private IndustrySubGroup industrySubGroup;
	//必須
	private String businessDomainName;
	@Getter
	@Setter
	private boolean enterprise;
	private String purpose;
	private Url url;
	private CustomerTypeId customerTypeId;
	private String customerTypeName;
	//未使用
	private ProductTypeId productTypeId;
	//未使用
	private String productTypeName;
	@Getter
	@Setter
	private List<Indicator> indicators = new ArrayList<>();
	@Getter
	@Setter
	private List<AssociatedUrl> associatedUrls = new ArrayList<>();
	@Setter
	@Getter
	private List<AssociatedProductType> associatedProductTypes = new ArrayList<>();
	@Setter
	@Getter
	private List<AssociatedCustomerType> associatedCustomerTypes = new ArrayList<>();
	@Setter
	@Getter
	private String businessModel;
	@Setter
	@Getter
	private String customerType;
	@Setter
	@Getter
	private String productType;
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	public BusinessDomain(CompanyId companyId, BusinessDomainId businessDmainId) {
		this.setCompanyId(companyId);
		this.setBusinessDomainId(businessDmainId);
	}
	
	protected void setBusinessDomainId(BusinessDomainId businessDomainId) {
		//不変性の保持。
		if(this.businessDomainId != null) {
				throw new IllegalArgumentException("The_businessDomainId_id_is_already_exist");
		}
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
	
	public void setIndustryGroup(IndustryGroup industryGroup) {
		/*
		if(industryGroup == null) {
			throw new IllegalArgumentException("The industryGroup may not be set to null.");
		}
		*/
		this.industryGroup = industryGroup;
	}
	
	public IndustryGroup industryGroup() {
		return this.industryGroup;
	}
	
	public void setIndustry(Industry industry) {
		/*
		if(industry == null) {
			throw new IllegalArgumentException("The industry may not be set to null.");
		}
		*/
		this.industry = industry;
	}
	
	public Industry industry() {
		return this.industry;
	}
	
	public void setIndustrySubGroup(IndustrySubGroup industrySubGroup) {
		/*
		if(industrySubGroup == null) {
			throw new IllegalArgumentException("The industrySubGroup may not be set to null.");
		}
		*/
		this.industrySubGroup = industrySubGroup;
	}
	
	public IndustrySubGroup industrySubGroup() {
		return this.industrySubGroup;
	}
	
	public void setBusinessDomainName(String businessDomainName) {
		if(businessDomainName == null) {
			throw new IllegalArgumentException("The_businessDomainName_may_not_be_set_to_null");
		}
		if(businessDomainName.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_business_domain_names");
		}
		this.businessDomainName = businessDomainName;
	}
	
	public String businessDomainName() {
		return this.businessDomainName;
	}
	
	public void setPurpose(String purpose) {
		/*
		if(purpose == null) {
			throw new IllegalArgumentException("The purpose may not be set to null.");
		}
		*/
		this.purpose = purpose;
	}
	
	public String purpose() {
		return this.purpose;
	}
	
	public void setUrl(Url url) {
		/*
		if(url == null) {
			throw new IllegalArgumentException("The refer may not be set to null.");
		}
		*/
		this.url = url;
	}
	
	public Url url() {
		return this.url;
	}
	
	public void setCustomerTypeId(CustomerTypeId customerTypeId) {
		/*
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		*/
		this.customerTypeId = customerTypeId;
	}
	
	public CustomerTypeId customerTypeId() {
		return this.customerTypeId;
	}
	
	public void setCustomerTypeName(String customerTypeName) {
		/*
		if(customerTypeName == null) {
			throw new IllegalArgumentException("The customerTypeName may not be set to null.");
		}
		*/
		this.customerTypeName = customerTypeName;
	}
	
	public String customerTypeName() {
		return this.customerTypeName;
	}
	
	public void setProductTypeId(ProductTypeId productTypeId) {
		/*
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		*/
		this.productTypeId = productTypeId;
	}
	
	public ProductTypeId productTypeId() {
		return this.productTypeId;
	}
	
	public void setProductTypeName(String productTypeName) {
		/*
		if(productTypeName == null) {
			throw new IllegalArgumentException("The productTypeName may not be set to null.");
		}
		*/
		this.productTypeName = productTypeName;
	}
	
	public String productTypeName() {
		return this.productTypeName;
	}
	
	//ビジネスメソッド（コマンド）
	public void defineIndicator(Indicator indicator){
		
		if(indicator == null) {
			throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
		}
		//指標はユニークであるようにする。
		int index = this.indicators.indexOf(indicator);
		if(index != -1) {
			throw new IllegalArgumentException("The_indicator_already_exist");
		}
		this.indicators.add(indicator);
	}
	
	public void replaceIndicator(Indicator previousIndicator, Indicator postIndicator) {
		int index = this.indicators.indexOf(previousIndicator);
		if(index == -1) {
			throw new IllegalArgumentException("The_indicator_dose_not_exist");
		}
		this.indicators.set(index, postIndicator);	
	}
	
	public void removeIndicator(Indicator indicator) {
		int index = this.indicators.indexOf(indicator);
		if(index == -1) {
			throw new IllegalArgumentException("The_indicator_dose_not_exist");
		}
		this.indicators.remove(index);	
	}
	
	public BusinessUnit defineBusinessUnit(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		return new BusinessUnit(this.companyId, this.businessDomainId, businessUnitId);
	}
	
	public MemberType defineMemberType(MemberTypeId memberTypeId, String memberTypeName, MemberClass memberClass) {
		if(memberTypeId == null){
			throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
		}
		if(memberTypeName == null) {
			throw new IllegalArgumentException("The_memberTypeName_may_not_be_set_to_null");
		}
		if(memberClass == null) {
			throw new IllegalArgumentException("The_memberClass_may_not_be_set_to_null");
		}
		return new MemberType(this.companyId, this.businessDomainId, memberTypeId, memberTypeName, memberClass);
	}
	
	public PartnerType definePartnerType(PartnerTypeId partnerTypeId, String partnerTypeName) {
		if(partnerTypeId == null){
			throw new IllegalArgumentException("The_partnerypeId_may_not_be_set_to_null");
		}
		if(partnerTypeName == null) {
			throw new IllegalArgumentException("The_partnerypeName_may_not_be_set_to_null");
		}
		
		return new PartnerType(this.companyId, this.businessDomainId, partnerTypeId, partnerTypeName);
	}

}
