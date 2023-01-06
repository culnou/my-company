package com.culnou.mumu.company.domain.model.financial.asset.category;

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
import com.culnou.mumu.company.domain.model.financial.asset.type.AssociatedFinancialAssetType;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetTypeId;

import lombok.Data;

@Entity
@Table(name = "financial_asset_categories")
@XmlRootElement
@Data
public class FinancialAssetCategory {
	
	@Id
	@Embedded
	private FinancialAssetCategoryId financialAssetCategoryId;
	@Column(name = "financialAsset_category_name")
	private String financialAssetCategoryName;
	@Column(name = "financialAsset_category_description")
	private String financialAssetCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private FinancialAssetTypeId financialAssetTypeId;
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
	private List<AssociatedFinancialAssetType> associatedFinancialAssetTypes = new ArrayList<>();
	
	protected FinancialAssetCategory() {};
	
	public FinancialAssetCategory(FinancialAssetCategoryId financialAssetCategoryId, String financialAssetCategoryName, CompanyId companyId, BusinessUnitId businessUnitId) {
		this.setFinancialAssetCategoryId(financialAssetCategoryId);
		this.setFinancialAssetCategoryName(financialAssetCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);this.setFinancialAssetTypeId(financialAssetTypeId);
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setFinancialAssetCategoryId(FinancialAssetCategoryId financialAssetCategoryId) {
		if(financialAssetCategoryId == null) {
			throw new IllegalArgumentException("The_financialAssetCategoryId_may_not_be_set_to_null");
		}
		this.financialAssetCategoryId = financialAssetCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	


}
