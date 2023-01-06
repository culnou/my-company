package com.culnou.mumu.company.infrastructure.persistence;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.Url;

import lombok.Data;


@Entity
@Table(name = "actions")
@XmlRootElement
@Data
public class ActionEntity {
	//必須
	@Id
	@Embedded
	private ActionId actionId;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private TaskId taskId;
	@Column(name = "task_name")
	private String taskName;
	@Embedded
	private DepartmentId departmentId;
	@Column(name = "department_name")
	private String departmentName;
	@Column(name = "action_name")
	private String actionName;
	
	//任意
	@Column(name = "action_description")
	private String actionDescription;
	@Embedded
	private Url url;
	@Column(name = "application_url")
	private String applicationUrl;
	@Column(name = "person_url")
	private String personUrl;
	@Column(name = "application_product_id")
	private String applicationProductId;
	@Column(name = "person_product_id")
	private String personProductId;
	@Column(name = "application_category_id")
	private String applicationCategoryId;
	@Column(name = "partner_category_id")
	private String partnerCategoryId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	

}
