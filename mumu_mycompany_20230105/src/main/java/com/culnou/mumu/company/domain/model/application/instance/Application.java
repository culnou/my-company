package com.culnou.mumu.company.domain.model.application.instance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;

import com.culnou.mumu.company.domain.model.it.instance.ItId;


import lombok.Data;
@Entity
@Table(name = "applications")
@XmlRootElement
@Data
public class Application {
	@Id
	@Embedded
	private ApplicationId applicationId;
	@Column(name = "application_name")
	@NotNull
	private String applicationName;
	@Column(name = "application_description")
	private String applicationDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private ItId itId;
	@Embedded
	private ApplicationCategoryId applicationCategoryId;
	@Column(name = "application_category_name")
	private String applicationCategoryName;
	@Embedded
	private Url url;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
    protected Application() {};
	
	public Application(ApplicationId applicationId, String applicationName, CompanyId companyId, BusinessUnitId businessUnitId, ApplicationCategoryId applicationCategoryId) {
		this.setApplicationId(applicationId);
		this.setApplicationName(applicationName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		this.setApplicationCategoryId(applicationCategoryId);
	}
	
	

}
