package com.culnou.mumu.company.infrastructure.persistence;

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

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobType;
import com.culnou.mumu.company.domain.model.Role;
import com.culnou.mumu.company.domain.model.Url;

import lombok.Data;


@Entity
@Table(name = "jobs")
@XmlRootElement
@Data
public class JobEntity {
	//必須
	@Id
	@Embedded
	private JobId jobId;
	@Embedded
	private CompanyId companyId;
	@Enumerated(EnumType.STRING)
	@Column(name = "job_type")
	private JobType jobType;
	@Column(name = "job_name")
	private String jobName;
	@ElementCollection
	private List<Role> roles = new ArrayList<>();
	//任意
	@Embedded
	private BusinessDomainId businessDomainId;
	@Column(name = "business_domain_name")
	private String businessDomainName;
	@Column(name = "job_description")
	private String jobDescription;
	@Embedded
	private Url url;
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;


}
