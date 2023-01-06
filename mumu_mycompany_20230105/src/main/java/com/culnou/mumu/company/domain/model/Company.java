package com.culnou.mumu.company.domain.model;

import java.util.List;

import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.common.Personality;


public class Company {
	//必須
	private CompanyId companyId;
	//必須
	private DomainName domainName;
	private Email email;
	private Address address;
	private IndustryGroup industryGroup;
	//必須
	private String companyName;
	//必須
	private String companyPassword;
	//必須
	private String eaName;
	//必須
	private String eaPassword;
	
	private String purpose;
	
	public Company(CompanyId companyId, DomainName domainName) {
		this.setCompanyId(companyId);
		this.setDomainName(domainName);
		
	}
	protected void setCompanyId(CompanyId companyId) {
		//不変性の保持。
		if(this.companyId != null) {
			throw new IllegalArgumentException("The_company_id_is_already_exist");
		}
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	
	public CompanyId companyId() {
		return this.companyId;
	}
	
	protected void setDomainName(DomainName domainName) {
		if(domainName == null) {
			throw new IllegalArgumentException("The domainName may not be set to null.");
		}
		this.domainName = domainName;
	}
	
	public DomainName domainName() {
		return this.domainName;
	}
	
	public void setAddress(Address address) {
		/*
		if(address == null) {
			throw new IllegalArgumentException("The address may not be set to null.");
		}
		*/
		this.address = address;
	}
	
	public Address address() {
		return this.address;
	}
	
	public void setEmail(Email email) {
		/*
		if(email == null) {
			throw new IllegalArgumentException("The email may not be set to null.");
		}
		*/
		this.email = email;
	}
	
	public Email email() {
		return this.email;
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
	
	
	public void setCompanyName(String companyName) {
		if(companyName == null) {
			throw new IllegalArgumentException("The_companyName_may_not_be_set_to_null");
		}
		if(companyName.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_companyName");
		}
		this.companyName = companyName;
	}
	
	public String companyName() {
		return this.companyName;
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
	
	public void setCompanyPassword(String companyPassword) {
		if(companyPassword == null) {
			throw new IllegalArgumentException("The_companyPassword_may_not_be_set_to_null");
		}
		if(companyPassword.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_company_password");
		}
		this.companyPassword = companyPassword;
	}
	
	public String companyPassword() {
		return this.companyPassword;
	}
	
	public void setEaName(String eaName) {
		if(eaName == null) {
			throw new IllegalArgumentException("The_eaName_may_not_be_set_to_null");
		}
		if(eaName.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_eaName");
		}
		this.eaName = eaName;
	}
	
	public String eaName() {
		return this.eaName;
	}
	
	public void setEaPassword(String eaPassword) {
		if(eaPassword == null) {
			throw new IllegalArgumentException("The_eaPassword_may_not_be_set_to_null");
		}
		if(eaPassword.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_eaPassword");
		}
		this.eaPassword = eaPassword;
	}
	
	public String eaPassword() {
		return this.eaPassword;
	}
	
	public BusinessDomain defineBusinessDomain(BusinessDomainId businessDomainId) {
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		return new BusinessDomain(this.companyId, businessDomainId);
	}
	
	public CustomerType defineCustomerType(CustomerTypeId customerTypeId, String customerTypeName, Personality personailty) {
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		if(customerTypeName == null) {
			throw new IllegalArgumentException("The customerTypeName may not be set to null.");
		}
		if(personailty == null) {
			throw new IllegalArgumentException("The personailty may not be set to null.");
		}
		return new CustomerType(this.companyId, customerTypeId, customerTypeName, personailty);
	}
	
	public ProductType defineProductType(ProductTypeId productTypeId) {
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		return new ProductType(this.companyId, productTypeId);
	}
	
	public Job defineJob(JobId jobId, JobType jobType, String jobName, List<Role> roles) {
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		if(jobType == null) {
			throw new IllegalArgumentException("The jobType may not be set to null.");
		}
		if(jobName == null) {
			throw new IllegalArgumentException("The jobName may not be set to null.");
		}
		if(roles == null) {
			throw new IllegalArgumentException("The roles may not be set to null.");
		}
		if(!(roles.size() > 0)) {
			throw new IllegalArgumentException("The role may not be set.");
		}
		return new Job(jobId, this.companyId, jobType, jobName, roles);
	}
	
	public BusinessProcess defineBusinessProcess(BusinessProcessId businessProcessId, BusinessProcessType businessProcessType, String businessProcessName) {
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		if(businessProcessType == null) {
			throw new IllegalArgumentException("The businessProcessType may not be set to null.");
		}
		if(businessProcessName == null) {
			throw new IllegalArgumentException("The businessProcessName may not be set to null.");
		}
		
		return new BusinessProcess(businessProcessId, this.companyId, businessProcessType, businessProcessName);
	}

}
