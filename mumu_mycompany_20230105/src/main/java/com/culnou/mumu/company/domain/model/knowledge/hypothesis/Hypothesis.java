package com.culnou.mumu.company.domain.model.knowledge.hypothesis;

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
@Table(name = "hypotheses")
@XmlRootElement
@Data
public class Hypothesis {
	
	@Id
	@Embedded
	@NotNull
	private HypothesisId hypothesisId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "hypothesis_target")
	@NotNull
	private HypothesisTarget hypothesisTarget;
	
	@Column(name = "target_id")
	@NotNull
	@NotEmpty
	private String targetId;
	
	@Column(name = "hypothesis_name")
	@NotNull
	@NotEmpty
	private String hypothesisName;
	
	@Column(name = "hypothesis_description")
	@NotNull
	@NotEmpty
	private String hypothesisDescription;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
