package com.culnou.mumu.company.domain.model.application.type;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.application.type.ApplicationType;


import lombok.Data;

@Entity
@Table(name = "application_types")
@XmlRootElement
@Data
public class ApplicationType {
	
	
	protected ApplicationType() {}
	
	protected ApplicationType(ApplicationTypeId applicationTypeId, CompanyId companyId, String applicationTypeName, ApplicationClass financialClass) {
		this.setApplicationTypeId(applicationTypeId);
		this.setCompanyId(companyId);
		this.setApplicationTypeName(applicationTypeName);
		this.setFinancialClass(financialClass);
	}
	
	@Id
	@Embedded
	@NotNull
	private ApplicationTypeId applicationTypeId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Column(name = "application_type_name")
	@NotNull
	@NotEmpty
	private String applicationTypeName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "application_class")
	private ApplicationClass financialClass;
	
	@Column(name = "application_type_description")
	private String applicationTypeDescription;
	
	@Embedded
	private BusinessDomainId businessDomainId;
	
	@Column(name = "business_domain_name")
	private String businessDomainName;
	
	@Embedded
	private JobId jobId;
	
	@Column(name = "job_name")
	private String jobName;
	
	@Embedded
	private Url url;
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;


}
