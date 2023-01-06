package com.culnou.mumu.company.domain.model.knowledge.guideline;

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
@Table(name = "guidelines")
@XmlRootElement
@Data
public class Guideline {
	@Id
	@Embedded
	@NotNull
	private GuidelineId guidelineId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "guideline_target")
	@NotNull
	private GuidelineTarget guidelineTarget;
	
	@Column(name = "target_id")
	@NotNull
	@NotEmpty
	private String targetId;
	
	@Column(name = "guideline_name")
	@NotNull
	@NotEmpty
	private String guidelineName;
	
	@Column(name = "guideline_description")
	@NotNull
	@NotEmpty
	private String guidelineDescription;
	
	@Column(name = "version")
	private String version;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
