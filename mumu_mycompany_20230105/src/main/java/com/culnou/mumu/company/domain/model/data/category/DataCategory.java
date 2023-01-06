package com.culnou.mumu.company.domain.model.data.category;

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
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;


import lombok.Data;
@Entity
@Table(name = "data_categories")
@XmlRootElement
@Data
public class DataCategory {
	@Id
	@Embedded
	private DataCategoryId dataCategoryId;
	@Column(name = "data_category_name")
	private String dataCategoryName;
	@Column(name = "data_category_description")
	private String dataCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	//未使用
	@Embedded
	private DataTypeId dataTypeId;
	@Enumerated(EnumType.STRING)
	@Column(name = "data_class")
	private DataClass dataClass;
	@Embedded
	private Url url;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@ElementCollection
	private List<AssociatedDataType> associatedDataTypes = new ArrayList<>();
	
	
	protected DataCategory() {};
	
	public DataCategory(DataCategoryId dataCategoryId, String dataCategoryName, CompanyId companyId, BusinessUnitId businessUnitId, DataClass dataClass) {
		this.setDataCategoryId(dataCategoryId);
		this.setDataCategoryName(dataCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		this.setDataClass(dataClass);
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setDataCategoryId(DataCategoryId dataCategoryId) {
		if(dataCategoryId == null) {
			throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
		}
		this.dataCategoryId = dataCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	private void setDataClass(DataClass dataClass) {
		if(dataClass == null) {
			throw new IllegalArgumentException("The_dataClass_may_not_be_set_to_null");
		}
		this.dataClass = dataClass;
	}
	
	

}
