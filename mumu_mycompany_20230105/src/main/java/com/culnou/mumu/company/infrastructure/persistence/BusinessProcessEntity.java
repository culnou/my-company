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

import com.culnou.mumu.company.domain.model.AssociatedTask;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessProcessClass;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessProcessType;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Url;

import lombok.Data;


@Entity
@Table(name = "business_processes")
@XmlRootElement
@Data
public class BusinessProcessEntity {
	//必須
	@Id
	@Embedded
	private BusinessProcessId businessProcessId;
	@Embedded
	private CompanyId companyId;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_process_type")
	private BusinessProcessType businessProcessType;
	@Column(name = "business_process_name")
	private String businessProcessName;
	//任意
	@Embedded
	private BusinessDomainId businessDomainId;
	@Column(name = "business_domain_name")
	private String businessDomainName;
	@ElementCollection
	private List<AssociatedTask> associatedTasks = new ArrayList<>();
	@Column(name = "business_process_description")
	private String businessProcessDescription;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_process_class")
	private BusinessProcessClass businessProcessClass;
	@Embedded
	private Url url;
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

}
