package com.culnou.mumu.company.domain.model.knowledge.evaluation;

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
@Table(name = "evaluations")
@XmlRootElement
@Data
public class Evaluation {
	
	@Id
	@Embedded
	@NotNull
	private EvaluationId evaluationId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "evaluation_target")
	@NotNull
	private EvaluationTarget evaluationTarget;
	
	@Column(name = "target_id")
	@NotNull
	@NotEmpty
	private String targetId;
	
	@Column(name = "evaluation_name")
	@NotNull
	@NotEmpty
	private String evaluationName;
	
	@Column(name = "evaluation_number")
	@NotNull
	private int evaluationNumber;
	
	@Column(name = "evaluation_description")
	@NotNull
	@NotEmpty
	private String evaluationDescription;
	
	@Column(name = "url")
	private String url;
	
	
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
