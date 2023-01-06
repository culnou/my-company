package com.culnou.mumu.company.domain.model.data.instance;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;






@Entity
@Table(name = "data")
@XmlRootElement
@lombok.Data
public class Data {
	
	@Id
	@Embedded
	private DataId dataId;
	@Column(name = "data_name")
	@NotNull
	private String dataName;
	@Column(name = "data_description")
	private String dataDescription;
	@Embedded
	@NotNull
	private CompanyId companyId;
	@Embedded
	private WorkId workId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private DataCategoryId dataCategoryId;
	@Column(name = "data_category_name")
	private String dataCategoryName;
	@Embedded
	private Url url;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	protected Data() {};
	
	public Data(CompanyId companyId, DataCategoryId dataCategoryId, WorkId workId, DataId dataId, String dataName) {
		this.setCompanyId(companyId);
		this.setDataCategoryId(dataCategoryId);
		this.setWorkId(workId);
		this.setDataId(dataId);
		this.setDataName(dataName);
		
	}
	
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	
	private void setWorkId(WorkId workId) {
		if(workId == null) {
			throw new IllegalArgumentException("The_workId_may_not_be_set_to_null");
		}
		this.workId = workId;
	}
	
	public void setDataCategoryId(DataCategoryId dataCategoryId) {
		if(dataCategoryId == null) {
			throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
		}
		this.dataCategoryId = dataCategoryId;
	}
	
	private void setDataId(DataId dataId) {
		if(dataId == null) {
			throw new IllegalArgumentException("The_dataId_may_not_be_set_to_null");
		}
		this.dataId = dataId;
	}
	
	public void setDataName(String dataName) {
		if(dataName == null || dataName.isEmpty()) {
			throw new IllegalArgumentException("The_dataName_may_not_be_set_to_null");
		}
		this.dataName = dataName;
	}

}
