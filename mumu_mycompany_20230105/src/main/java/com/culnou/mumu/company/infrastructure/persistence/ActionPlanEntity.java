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
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.ActionPlanType;
import com.culnou.mumu.company.domain.model.AssociatedAction;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;

import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.project.ProjectId;

import lombok.Data;



@Entity
@Table(name = "action_plans")
@XmlRootElement
@Data
public class ActionPlanEntity {
	//必須
	@Id
	@Embedded
	private ActionPlanId actionPlanId;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessProcessId businessProcessId;
	@Column(name = "business_process_name")
	private String businessProcessName;
	@Enumerated(EnumType.STRING)
	@Column(name = "action_plan_type")
	private ActionPlanType actionPlanType;
	@Column(name = "action_plan_name")
	private String actionPlanName;
	@Embedded
	private ProjectId projectId;
	//任意
	@Embedded
	private BusinessUnitId businessUnitId;
	@Column(name = "business_unit_name")
	private String businessUnitName;
	@Embedded
	private CustomerCategoryId customerCategoryId;
	@Column(name = "customer_category_name")
	private String customerCategoryName;
	@ElementCollection
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	@ElementCollection
	private List<AssociatedAction> associatedActions = new ArrayList<>();
	@Column(name = "action_plan_description")
	private String actionPlanDescription;
	@Embedded
	private Url url;
	@Column(name = "hypothesis")
	private String hypothesis;
	@Column(name = "result")
	private String result;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "business_state")
	@Enumerated(EnumType.STRING)
	private BusinessState businessState;

}
