package com.culnou.mumu.company.domain.model.activity.workflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.project.ProjectId;

import lombok.Data;

@Entity
@Table(name = "workflows")
@XmlRootElement
@Data
public class Workflow {
	
	@Id
	@Embedded
	@NotNull
	private WorkflowId workflowId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private ActionPlanId actionPlanId;
	
	@Embedded
	private ProjectId projectId;
	
	@Embedded
	private ProgramId programId;
	
	
	@Column(name = "workflow_name")
	String workflowName;
	
	@Column(name = "workflow_description")
	String workflowDescription;
	
	@Column(name = "hypothesis")
	private String hypothesis;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "enddate")
	private Date endDate;
	
	@Column(name = "url")
	String url;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	

}
