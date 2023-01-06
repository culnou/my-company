package com.culnou.mumu.company.domain.model.application.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import com.culnou.mumu.company.domain.model.product.instance.ProductId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunction;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionId;
import com.culnou.mumu.company.domain.model.application.instance.Application;
import com.culnou.mumu.company.domain.model.application.instance.ApplicationId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;
import com.culnou.mumu.company.domain.model.application.type.AssociatedApplicationType;

import lombok.Data;

@Entity
@Table(name = "application_categories")
@XmlRootElement
@Data
public class ApplicationCategory {
	@Id
	@Embedded
	private ApplicationCategoryId applicationCategoryId;
	@Column(name = "application_category_name")
	private String applicationCategoryName;
	@Column(name = "application_category_description")
	private String applicationCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	//未使用
	@Embedded
	private ApplicationTypeId applicationTypeId;
	@Embedded
	private Url url;
	@Embedded
	private ProductId productId;
	@Column(name = "product_name")
	private String productName;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@ElementCollection
	private List<AssociatedApplicationType> associatedApplicationTypes = new ArrayList<>();
	
	protected ApplicationCategory() {};
	
	public ApplicationCategory(ApplicationCategoryId applicationCategoryId, String applicationCategoryName, CompanyId companyId, BusinessUnitId businessUnitId) {
		this.setApplicationCategoryId(applicationCategoryId);
		this.setApplicationCategoryName(applicationCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setApplicationCategoryId(ApplicationCategoryId applicationCategoryId) {
		if(applicationCategoryId == null) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		this.applicationCategoryId = applicationCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	public ApplicationFunction defineApplicationFunction(ApplicationFunctionId applicationFunctionId, String applicationFunctionName) {
		return new ApplicationFunction(applicationFunctionId, this.companyId, applicationFunctionName, this.applicationCategoryId, this.applicationCategoryName);
	}
	
	
	public Application defineApplication(ApplicationId applicationId, String applicationName, CompanyId companyId, BusinessUnitId businessUnitId) throws Exception{
		if(applicationId == null) {
			throw new IllegalArgumentException("The_applicationId_may_not_be_set_to_null");
		}
		if(applicationName == null || applicationName.isEmpty()) {
			throw new IllegalArgumentException("The_applicationName_may_not_be_set_to_null");
		}
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		
		return new Application(applicationId, applicationName, companyId, businessUnitId, this.applicationCategoryId);
	}
	
	


}
