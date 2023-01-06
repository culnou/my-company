package com.culnou.mumu.company.domain.model.it.instance;

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
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;

import lombok.Data;
@Entity
@Table(name = "its")
@XmlRootElement
@Data
public class It {
	@Id
	@Embedded
	private ItId itId;
	@Embedded
	private CompanyId companyId;
	@Column(name = "it_name")
	@NotNull
	private String itName;
	@Column(name = "it_description")
	private String itDescription;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private ItCategoryId itCategoryId;
	@Column(name = "it_category_name")
	private String itCategoryName;
	@Embedded
	private PlaceId placeId;
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
	
	protected It() {};
	
	public It(ItId itId, String itName, CompanyId companyId, BusinessUnitId businessUnitId, ItCategoryId itCategoryId) {
		this.setItId(itId);
		this.setItName(itName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		this.setItCategoryId(itCategoryId);
	}

}
