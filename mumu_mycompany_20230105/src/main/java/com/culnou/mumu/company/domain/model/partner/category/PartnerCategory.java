package com.culnou.mumu.company.domain.model.partner.category;

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

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunction;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionId;
import com.culnou.mumu.company.domain.model.partner.type.AssociatedPartnerType;
import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeId;
import com.culnou.mumu.company.domain.model.product.instance.ProductId;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;

@Entity
@Table(name = "partner_categories")
@XmlRootElement
@Data
public class PartnerCategory {
	
	@Id
	@Embedded
	private PartnerCategoryId partnerCategoryId;
	@Column(name = "partner_category_name")
	private String partnerCategoryName;
	@Column(name = "partner_category_description")
	private String partnerCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private PartnerTypeId partnerTypeId;
	@Embedded
	private Url url;
	@Embedded
	private ProductId productId;
	@Column(name = "product_name")
	private String productName;
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
	private List<AssociatedPartnerType> associatedPartnerTypes = new ArrayList<>();
	
	protected PartnerCategory() {};
	
	public PartnerCategory(PartnerCategoryId partnerCategoryId, String partnerCategoryName, CompanyId companyId, BusinessUnitId businessUnitId) {
		this.setPartnerCategoryId(partnerCategoryId);
		this.setPartnerCategoryName(partnerCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setPartnerCategoryId(PartnerCategoryId partnerCategoryId) {
		if(partnerCategoryId == null) {
			throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
		}
		this.partnerCategoryId = partnerCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	public PartnerFunction definePartnerFunction(PartnerFunctionId partnerFunctionId, String partnerFunctionName) {
		return new PartnerFunction(partnerFunctionId, this.companyId, partnerFunctionName, this.partnerCategoryId, this.partnerCategoryName);
	}
	
	


}
