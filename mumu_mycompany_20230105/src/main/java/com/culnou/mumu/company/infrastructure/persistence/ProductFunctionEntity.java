package com.culnou.mumu.company.infrastructure.persistence;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Function;
import com.culnou.mumu.company.domain.model.ProductFunctionId;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductTaskId;

import com.culnou.mumu.company.domain.model.Url;

import lombok.Data;


@Entity
@Table(name = "product_functions")
@XmlRootElement
@Data
public class ProductFunctionEntity {
	@Id
	@Embedded
	private ProductFunctionId productFunctionId;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private ProductCategoryId productCategoryId;
	@Embedded
	private ProductTaskId productTaskId;
	@Column(name = "product_task_name")
	private String productTaskName;
	@Column(name = "product_category_name")
	private String productCategoryName;
	@Column(name = "product_function_name")
	private String productFunctionName;
	@Column(name = "product_function_description")
	private String productFunctionDescription;
	@Embedded
	private Url url;
	@Embedded
	private Function function;

}
