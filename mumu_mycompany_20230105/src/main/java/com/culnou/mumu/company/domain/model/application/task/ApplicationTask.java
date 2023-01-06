package com.culnou.mumu.company.domain.model.application.task;

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
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;


import lombok.Data;

import com.culnou.mumu.company.domain.model.Url;


@Entity
@Table(name = "application_tasks")
@XmlRootElement
@Data
public class ApplicationTask {
	
    protected ApplicationTask() {}
	
	protected ApplicationTask(ApplicationTaskId applicationTaskId, CompanyId companyId, String applicationTaskName) {
		this.setApplicationTaskId(applicationTaskId);
		this.setCompanyId(companyId);
		this.setApplicationTaskName(applicationTaskName);
	}
	
	@Id
	@Embedded
	@NotNull
	private ApplicationTaskId applicationTaskId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private ApplicationTypeId applicationTypeId;
	
	@Column(name = "application_type_name")
	@NotNull
	@NotEmpty
	private String applicationTypeName;
	
	@Column(name = "application_task_name")
	@NotNull
	@NotEmpty
	private String applicationTaskName;
	
	@Column(name = "application_task_description")
	private String applicationTaskDescription;
	
	@Embedded
	private TaskId taskId;
	
	@Column(name = "task_name")
	private String taskName;
	
	@Embedded
	private Url url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
