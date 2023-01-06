package com.culnou.mumu.company.domain.model.data.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.data.domain.DataDomainId;

import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import lombok.Data;

@Entity
@Table(name = "data_items")
@XmlRootElement
@Data
public class DataItem {
	
	protected DataItem() {}
	
	@Id
	@Embedded
	@NotNull
	private DataItemId dataItemId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private DataTypeId dataTypeId;
	
	@Column(name = "data_type_name")
	@NotNull
	@NotEmpty
	private String dataTypeName;
	
	@Embedded
	private DataDomainId dataDomainId;
	
	@Column(name = "data_domain_name")
	private String dataDomainName;
	
	@Column(name = "data_item_name")
	@NotNull
	@NotEmpty
	private String dataItemName;
	
	@Column(name = "data_item_description")
	private String dataItemDescription;
	
	@Column(name = "data_item_type")
	private String dataItemType;
	
	@Column(name = "data_item_digit")
	private String dataItemDigit;
	
	@Column(name = "data_item_constraints")
	private String dataItemConstraints;
	
	@Column(name = "data_owner_id")
	private String dataOwnerId;
	
	@Column(name = "primary_key")
	private boolean primaryKey;
	
	@Column(name = "foreign_key")
	private boolean foreignKey;
	
	//外部キーの場合、参照先の属性IDを指定する。
	@Column(name = "reference_attribute_Id")
	private String referenceAttributeId;
	
	//データ機密レベル
	@Column(name = "data_sensitivity_level")
	private String dataSensitivityLevel;
	
	//データ規制対象カテゴリ
	@Column(name = "data_restricted_category")
	private String dataRestrictedCategory;
	
	//監査日
	@Column(name = "audit_date")
	private  Date auditDate;
	
	@Embedded
	private Url url;
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	
	public void setIndicators(List<Indicator> indicators) {
		//同じ名前の指標は設定できない。
		List<String> indicatorNames = new ArrayList<>();
		
		for(Indicator indicator : indicators) {
			
			for(String name : indicatorNames) {
				if(indicator.getIndicatorName().equals(name)) {
					throw new  IllegalArgumentException("Same_indicator_can_not_set");
				}
			}
			indicatorNames.add(indicator.getIndicatorName());
		}
		this.indicators = indicators;
	}
	
	
	
	

}
