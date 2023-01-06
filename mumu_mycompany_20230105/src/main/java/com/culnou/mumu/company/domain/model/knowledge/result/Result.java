package com.culnou.mumu.company.domain.model.knowledge.result;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;


import lombok.Data;

@Entity
@Table(name = "results")
@XmlRootElement
@Data
public class Result {
	@Id
	@Embedded
	@NotNull
	private ResultId resultId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "result_target")
	@NotNull
	private ResultTarget resultTarget;
	
	@Column(name = "target_id")
	@NotNull
	@NotEmpty
	private String targetId;
	
	@Column(name = "result_name")
	@NotNull
	@NotEmpty
	private String resultName;
	
	@Column(name = "result_description")
	@NotNull
	@NotEmpty
	private String resultDescription;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
