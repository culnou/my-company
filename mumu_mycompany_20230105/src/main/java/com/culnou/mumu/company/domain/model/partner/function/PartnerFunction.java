package com.culnou.mumu.company.domain.model.partner.function;

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
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;

import lombok.Data;

@Entity
@Table(name = "partner_functions")
@XmlRootElement
@Data
public class PartnerFunction {
	
    public PartnerFunction() {}
	
	public PartnerFunction(PartnerFunctionId partnerFunctionId, CompanyId companyId, String partnerFunctionName, PartnerCategoryId partnerCategoryId, String partnerCategoryName) {
		this.setPartnerFunctionId(partnerFunctionId);
		this.setCompanyId(companyId);
		this.setPartnerFunctionName(partnerFunctionName);
		this.setPartnerCategoryId(partnerCategoryId);
		this.setPartnerCategoryName(partnerCategoryName);
	}
	
	@Id
	@Embedded
	@NotNull
	private PartnerFunctionId partnerFunctionId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private PartnerCategoryId partnerCategoryId;
	
	@Column(name = "partner_category_name")
	@NotNull
	@NotEmpty
	private String partnerCategoryName;
	
	@Embedded
	private TaskId taskId;
	
	@Column(name = "task_name")
	private String taskName;
	
	
	@Column(name = "partner_function_name")
	@NotNull
	@NotEmpty
	private String partnerFunctionName;
	
	@Column(name = "partner_function_description")
	private String partnerFunctionDescription;
	
	@Embedded
	private Function function;
	
	@Embedded
	private Url url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	private void setPartnerFunctionId(PartnerFunctionId partnerFunctionId){
		if(partnerFunctionId == null) {
			throw new IllegalArgumentException("The_partnerFunctionId_may_not_be_set_to_null");
		}
		this.partnerFunctionId = partnerFunctionId;
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	public void setPartnerFunctionName(String partnerFunctionName){
		if(partnerFunctionName == null) {
			throw new IllegalArgumentException("The_partnerFunctionName_may_not_be_set_to_null");
		}
		this.partnerFunctionName = partnerFunctionName;
	}
	private void setPartnerCategoryId(PartnerCategoryId partnerCategoryId){
		if(partnerCategoryId == null) {
			throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
		}
		this.partnerCategoryId = partnerCategoryId;
	}
	private void setPartnerCategoryName(String partnerCategoryName){
		if(partnerFunctionName == null) {
			throw new IllegalArgumentException("The_partnerCategoryName_may_not_be_set_to_null");
		}
		this.partnerCategoryName = partnerCategoryName;
	}
	


}
