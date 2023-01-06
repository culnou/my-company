package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.common.Attribute;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.common.Personality;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import lombok.Getter;
import lombok.Setter;

public class CustomerType {
	
	private CompanyId companyId;
	private CustomerTypeId customerTypeId;
	private String customerTypeName;
	@Getter
	@Setter
	private BusinessDomainId businessDomainId;
	@Getter
	private Personality personality;
	//任意
	private String values;
	@Getter
	private String cause;
	@Getter
	private String issue;
	private String problem;
	@Getter
	private Url url;
	@Getter
	@Setter
	private List<Indicator> indicators = new ArrayList<>();
	@Getter
	@Setter
	private List<Attribute> attributes = new ArrayList<>();
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	@Setter
	@Getter
	private DataTypeId dataTypeId;
	
	//データ
	@Setter
	@Getter
	private String entityName;//エンティティ名
	@Setter
	@Getter
	private String entityEnglishName;//エンティティ英語名
	@Setter
	@Getter
	private String entityDescription;//エンティティ摘要
	@Setter
	@Getter
	private String dataOwner;//データオーナー
	@Setter
	@Getter
	private Email address;//データオーナーのメールアドレス
	@Setter
	@Getter
	private String associatedConstraint;//関連制約
	@Setter
	@Getter
	private String existenceConstraint;//存在制約
	@Setter
	@Getter
	private String dataAmount;//データ量
	
	
	
	protected CustomerType(CompanyId companyId, CustomerTypeId customerTypeId, String customerTypeName, Personality personality) {
		this.setCompanyId(companyId);
		this.setCustomerTypeId(customerTypeId);
		this.setCustomerTypeName(customerTypeName);
		this.setPersonality(personality);
	}
	
	protected void setCustomerTypeId(CustomerTypeId customerTypeId) {
		//不変性の保持。
		if(this.customerTypeId != null) {
				throw new IllegalArgumentException("The_customerTypeId_is_already_exist");
		}
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		this.customerTypeId = customerTypeId;
	}
	
	public CustomerTypeId customerTypeId() {
		return this.customerTypeId;
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
	
	public void setCustomerTypeName(String customerTypeName) {
		if(customerTypeName == null) {
			throw new IllegalArgumentException("The customerTypeName may not be set to null.");
		}
		this.customerTypeName = customerTypeName;
	}
	
	public String customerTypeName() {
		return this.customerTypeName;
	}
	
	public void setPersonality(Personality personality) {
		if(personality == null) {
			throw new IllegalArgumentException("The personality may not be set to null.");
		}
		this.personality = personality;
	}
	
	public void setValues(String values) {
		if(values == null) {
			throw new IllegalArgumentException("The values may not be set to null.");
		}
		this.values = values;
	}
	
	public String values() {
		return this.values;
	}
	
	public void setProblem(String problem) {
		if(problem == null) {
			throw new IllegalArgumentException("The problem may not be set to null.");
		}
		this.problem = problem;
	}
	
	public String problem() {
		return this.problem;
	}
	
	public void setCause(String cause) {
		if(cause == null) {
			throw new IllegalArgumentException("The cause may not be set to null.");
		}
		this.cause = cause;
	}
	public void setIssue(String issue) {
		if(issue == null) {
			throw new IllegalArgumentException("The issue may not be set to null.");
		}
		this.issue = issue;
	}
	public void  setUrl(Url url) {
		//NULL更新もあるので削除。2022/8/19
		/*
		if(url == null) {
			throw new IllegalArgumentException("The url may not be set to null.");
		}
		*/
		this.url = url;
	}
	
	public CustomerCategory defineCustomerCategory(CustomerCategoryId customerCategoryId) {
		return new CustomerCategory(this.companyId, this.customerTypeId, customerCategoryId);
	}

}
