package com.culnou.mumu.company.domain.model.data.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;


import lombok.Data;

@Entity
@Table(name = "data_domains")
@XmlRootElement
@Data
public class DataDomain {
	
	protected DataDomain() {}
	
	@Id
	@Embedded
	@NotNull
	private DataDomainId dataDomainId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Column(name = "data_domain_name")
	@NotNull
	@NotEmpty
	private String dataDomainName;
	
	@Column(name = "data_domain_description")
	private String dataDomainDescription;
	
	@Embedded
	private Url url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	

}
