package com.culnou.mumu.company.domain.model.it.category;

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

import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.instance.It;
import com.culnou.mumu.company.domain.model.it.instance.ItId;
import com.culnou.mumu.company.domain.model.it.type.AssociatedItType;
import com.culnou.mumu.company.domain.model.it.type.ItTypeId;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;
@Entity
@Table(name = "it_categories")
@XmlRootElement
@Data
public class ItCategory {
	@Id
	@Embedded
	private ItCategoryId itCategoryId;
	@Column(name = "it_category_name")
	private String itCategoryName;
	@Column(name = "it_category_description")
	private String itCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private ItTypeId itTypeId;
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
	private List<AssociatedItType> associatedItTypes = new ArrayList<>();
	
	protected ItCategory() {};
	
	public ItCategory(ItCategoryId itCategoryId, String itCategoryName, CompanyId companyId, BusinessUnitId businessUnitId) {
		this.setItCategoryId(itCategoryId);
		this.setItCategoryName(itCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setItCategoryId(ItCategoryId itCategoryId) {
		if(itCategoryId == null) {
			throw new IllegalArgumentException("The_itCategoryId_may_not_be_set_to_null");
		}
		this.itCategoryId = itCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	public It defineIt(ItId itId, String itName, CompanyId companyId, BusinessUnitId businessUnitId) throws Exception{
		if(itId == null) {
			throw new IllegalArgumentException("The_itId_may_not_be_set_to_null");
		}
		if(itName == null || itName.isEmpty()) {
			throw new IllegalArgumentException("The_itName_may_not_be_set_to_null");
		}
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		
		return new It(itId, itName, companyId, businessUnitId, this.itCategoryId);
	}
	
	


}
