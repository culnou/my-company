package com.culnou.mumu.company.infrastructure.persistence;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Function;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;

import lombok.Data;


@Entity
@Table(name = "tasks")
@XmlRootElement
@Data
public class TaskEntity {
	//必須
	@Id
	@Embedded
	private TaskId taskId;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private JobId jobId;
	@Column(name = "job_name")
	private String jobName;
	@Embedded
	private Function function;
	@Column(name = "task_name")
	private String taskName;
	//任意
	@Column(name = "task_description")
	private String taskDescription;
	@Embedded
	private Url url;
	@ElementCollection
	private List<AssociatedDataType> associatedDataTypes = new ArrayList<>();
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

}
