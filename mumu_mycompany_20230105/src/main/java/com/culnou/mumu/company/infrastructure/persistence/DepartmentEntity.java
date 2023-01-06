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

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentType;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;

import lombok.Data;



@Entity
@Table(name = "departments")
@XmlRootElement
@Data
public class DepartmentEntity {
	//必須
	@Id
	@Embedded
	private DepartmentId departmentId;
	@Embedded
	private CompanyId companyId;
	@Enumerated(EnumType.STRING)
	@Column(name = "department_type")
	private DepartmentType departmentType;
	@Embedded
	private JobId jobId;
	@Column(name = "job_name")
	private String jobName;
	@Column(name = "department_name")
	private String departmentName;
	//任意
	@Embedded
	private BusinessUnitId businessUnitId;
	@Column(name = "business_unit_name")
	private String businessUnitName;
	@Column(name = "department_description")
	private String departmentDescription;
	@Embedded
	private Url url;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "enddate")
	private Date endDate;
	
	@Column(name = "business_state")
	private BusinessState businessState;
	
	@ElementCollection
	private List<AssociatedApplicationCategory> associatedApplicationCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedPartnerCategory> associatedPartnerCategories = new ArrayList<>();
	
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

}
