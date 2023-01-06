package com.culnou.mumu.company.domain.model.partner.type;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;


import lombok.Data;

@Entity
@Table(name = "partner_types")
@XmlRootElement
@Data
public class PartnerType {
	
	@Id
	@Embedded
	private PartnerTypeId partnerTypeId;
	
	@Embedded
	private CompanyId companyId;

	//任意
	//生成後、事業ドメインを設定する場合を想定。
	@Embedded
	private BusinessDomainId businessDomainId;
	
	//必須
	@Column(name = "partner_type_name")
	private String partnerTypeName;
	
	//変更可能
	@Column(name = "partner_type_description")
	private String partnerTypeDescription;
	
	@Embedded
	private Url url;
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Embedded
	private DataTypeId dataTypeId;
	
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "deleted_at")
	private Date deletedAt;
	
	protected PartnerType() {}
	
	//アプリケーションサービスが生成するのでpublicにする。
	public PartnerType(CompanyId companyId, BusinessDomainId businessDomainId, PartnerTypeId partnerTypeId, String partnerTypeName) {
		this.setCompanyId(companyId);
		this.setBusinessDomainId(businessDomainId);
		this.setPartnerTypeId(partnerTypeId);
		this.setPartnerTypeName(partnerTypeName);
		
	}
	//必須項目の事前条件検証
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	
	
	//識別子のセッターをprivateにすることで、生成後、第三者からIDが設定されないので、エンティティの識別子の不変性を保持し、エンティティの同一性を保証します
	private void setPartnerTypeId(PartnerTypeId partnerTypeId) {
		//識別のセッターで完全性（NotNull）を保証する。
		if(partnerTypeId == null) {
			throw new IllegalArgumentException("The_partnerypeId_may_not_be_set_to_null");
		}
		
		this.partnerTypeId = partnerTypeId;
	}
	
	//ビジネスメソッド（コマンド）
	public void defineIndicator(Indicator indicator){
		if(indicator == null) {
			throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
		}
		//指標はユニークであるようにする。
		int index = this.indicators.indexOf(indicator);
		if(index != -1) {
			throw new IllegalArgumentException("The_indicator_already_exist");
		}
		this.indicators.add(indicator);
	}
	
	public void replaceIndicator(Indicator previousIndicator, Indicator postIndicator) {
		int index = this.indicators.indexOf(previousIndicator);
		if(index == -1) {
			throw new IllegalArgumentException("The_indicator_dose_not_exist");
		}
		this.indicators.set(index, postIndicator);	
	}
	
	public void removeIndicator(Indicator indicator) {
		int index = this.indicators.indexOf(indicator);
		if(index == -1) {
			throw new IllegalArgumentException("The_indicator_dose_not_exist");
		}
		this.indicators.remove(index);	
	}
	

}
