package com.culnou.mumu.company.domain.model.application.function;



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
import com.culnou.mumu.company.domain.model.Function;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;

import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;


import lombok.Data;

@Entity
@Table(name = "application_functions")
@XmlRootElement
@Data
public class ApplicationFunction {
	
    public ApplicationFunction() {}
	
	public ApplicationFunction(ApplicationFunctionId applicationFunctionId, CompanyId companyId, String applicationFunctionName, ApplicationCategoryId applicationCategoryId, String applicationCategoryName) {
		this.setApplicationFunctionId(applicationFunctionId);
		this.setCompanyId(companyId);
		this.setApplicationFunctionName(applicationFunctionName);
		this.setApplicationCategoryId(applicationCategoryId);
		this.setApplicationCategoryName(applicationCategoryName);
	}
	
	@Id
	@Embedded
	@NotNull
	private ApplicationFunctionId applicationFunctionId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private ApplicationCategoryId applicationCategoryId;
	
	@Column(name = "application_category_name")
	@NotNull
	@NotEmpty
	private String applicationCategoryName;
	
	@Embedded
	@NotNull
	private ApplicationTaskId applicationTaskId;
	
	@Column(name = "application_task_name")
	@NotNull
	@NotEmpty
	private String applicationTaskName;
	
	
	@Column(name = "application_function_name")
	@NotNull
	@NotEmpty
	private String applicationFunctionName;
	
	@Column(name = "application_function_description")
	private String applicationFunctionDescription;
	
	@Embedded
	private Function function;
	
	@Embedded
	private Url url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	private void setApplicationFunctionId(ApplicationFunctionId applicationFunctionId){
		if(applicationFunctionId == null) {
			throw new IllegalArgumentException("The_applicationFunctionId_may_not_be_set_to_null");
		}
		this.applicationFunctionId = applicationFunctionId;
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	public void setApplicationFunctionName(String applicationFunctionName){
		if(applicationFunctionName == null) {
			throw new IllegalArgumentException("The_applicationFunctionName_may_not_be_set_to_null");
		}
		this.applicationFunctionName = applicationFunctionName;
	}
	private void setApplicationCategoryId(ApplicationCategoryId applicationCategoryId){
		if(applicationCategoryId == null) {
			throw new IllegalArgumentException("The_applicationCategoryId_may_not_be_set_to_null");
		}
		this.applicationCategoryId = applicationCategoryId;
	}
	private void setApplicationCategoryName(String applicationCategoryName){
		if(applicationFunctionName == null) {
			throw new IllegalArgumentException("The_applicationCategoryName_may_not_be_set_to_null");
		}
		this.applicationCategoryName = applicationCategoryName;
	}
	
	
	
	

}
