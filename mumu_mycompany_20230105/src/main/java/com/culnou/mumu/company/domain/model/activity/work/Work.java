package com.culnou.mumu.company.domain.model.activity.work;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.member.MemberId;

import lombok.Data;

@Entity
@Table(name = "works")
@XmlRootElement
@Data
public class Work {
	
	@Id
	@Embedded
	@NotNull
	private WorkId workId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private MemberId memberId;
	
	@NotNull
	@NotEmpty
	@Column(name = "member_name")
	private String memberName;
	
	@Embedded
	@NotNull
	private WorkflowId workflowId;
	
	@Embedded
	@NotNull
	private ActionId actionId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "work_type")
	@NotNull
	private WorkType workType;
	
	@Column(name = "work_name")
	private String workName;
	
	@Column(name = "work_description")
	private String workDescription;
	
	//パーソナルサービスのURL
	@Column(name = "person_url")
	private String personUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	
	//アプリケーションサービスのURL
	@Column(name = "application_url")
	private String applicationUrl;
	
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "enddate")
	private Date endDate;
	
	@Column(name = "expended_time")
	private long expendedTime;
	
	@Column(name = "url")
	private String url;
	
	@Transient
	private String averageScore;

}
