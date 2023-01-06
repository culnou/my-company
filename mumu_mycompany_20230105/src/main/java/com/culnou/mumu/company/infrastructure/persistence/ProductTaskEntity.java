package com.culnou.mumu.company.infrastructure.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTypeId;

import com.culnou.mumu.company.domain.model.Url;

import lombok.Data;
@Entity
@Table(name = "product_tasks")
@XmlRootElement
@Data
public class ProductTaskEntity {
	@Id
	@Embedded
	private ProductTaskId productTaskId;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private ProductTypeId productTypeId;
	@Column(name = "product_type_name")
	private String productTypeName;
	@Column(name = "product_task_name")
	private String productTaskName;
	//任意
	@Column(name = "product_task_description")
	private String productTaskDescription;
	@Embedded
	private Url url;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

}
