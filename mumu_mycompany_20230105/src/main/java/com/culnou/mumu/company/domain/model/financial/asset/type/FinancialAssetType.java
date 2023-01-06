package com.culnou.mumu.company.domain.model.financial.asset.type;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import lombok.Data;


@Entity
@Table(name = "financial_asset_types")
@XmlRootElement
@Data
public class FinancialAssetType {
	
protected FinancialAssetType() {}
	
	protected FinancialAssetType(FinancialAssetTypeId financialAssetTypeId, CompanyId companyId, String financialAssetTypeName, FinancialAssetClass financialClass) {
		this.setFinancialAssetTypeId(financialAssetTypeId);
		this.setCompanyId(companyId);
		this.setFinancialAssetTypeName(financialAssetTypeName);
		this.setFinancialClass(financialClass);
	}
	
	@Id
	@Embedded
	@NotNull
	private FinancialAssetTypeId financialAssetTypeId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Column(name = "financial_asset_type_name")
	@NotNull
	@NotEmpty
	private String financialAssetTypeName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "financial_asset_class")
	private FinancialAssetClass financialClass;
	
	@Column(name = "financial_asset_type_description")
	private String financialAssetTypeDescription;
	
	@Embedded
	private BusinessDomainId businessDomainId;
	
	@Column(name = "business_domain_name")
	private String businessDomainName;
	
	@Embedded
	private DataTypeId dataTypeId;
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Embedded
	private Url url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	

}
