package com.culnou.mumu.company.domain.model.member.type;



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

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.Attribute;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import lombok.Data;

@Entity
@Table(name = "member_types")
@XmlRootElement
@Data
public class MemberType{
	@Id
	@Embedded
	private MemberTypeId memberTypeId;
	
	@Embedded
	private CompanyId companyId;
	
	
	//任意
	//生成後、事業ドメインを設定する場合を想定。
	@Embedded
	private BusinessDomainId businessDomainId;
	//必須
	@Column(name = "member_type_name")
	private String memberTypeName;
	//必須
	@Enumerated(EnumType.STRING)
	@Column(name = "member_class")
	private MemberClass memberClass;
	//未使用
	@Embedded
	private JobId jobId;
	@Column(name = "job_name")
	private String jobName;
	//ジョブは複数関連するので以下を使用。
	@ElementCollection
	private List<AssociatedJob> associatedJobs = new ArrayList<>();
	//変更可能
	@Column(name = "value")
	private String values;
	//変更可能
	@Column(name = "issue")
	private String issue;
	//変更可能
	@Column(name = "member_type_description")
	private String memberTypeDescription;
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	//未使用
	@ElementCollection
	private List<Attribute> attributes = new ArrayList<>();
	@Embedded
	private DataTypeId dataTypeId;
	
	//メタデータ
	//変更可能
	@Column(name = "entity_name")
	private String entityName;//エンティティ名
	@Column(name = "entity_english_name")
	private String entityEnglishName;//エンティティ英語名
	@Column(name = "entity_description")
	private String entityDescription;//エンティティ摘要
	@Column(name = "data_owner")
	private String dataOwner;//データオーナー
	@Embedded
	private Email address;//データオーナーのメールアドレス
	@Column(name = "associated_constraint")
	private String associatedConstraint;//関連制約
	@Column(name = "existence_constraint")
	private String existenceConstraint;//存在制約
	@Column(name = "data_amount")
	private String dataAmount;//データ量
	@Embedded
	private Url url;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "deleted_at")
	private Date deletedAt;
	protected MemberType() {}
	
	//アプリケーションサービスが生成するのでpublicにする。
	public MemberType(CompanyId companyId, BusinessDomainId businessDomainId, MemberTypeId memberTypeId, String memberTypeName, MemberClass memberClass) {
		this.setCompanyId(companyId);
		this.setBusinessDomainId(businessDomainId);
		this.setMemberTypeId(memberTypeId);
		this.setMemberTypeName(memberTypeName);
		this.setMemberClass(memberClass);
	}
	//必須項目の事前条件検証
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	
	
	//識別子のセッターをprivateにすることで、生成後、第三者からIDが設定されないので、エンティティの識別子の不変性を保持し、エンティティの同一性を保証します
	private void setMemberTypeId(MemberTypeId memberTypeId) {
		//識別のセッターで完全性（NotNull）を保証する。
		if(memberTypeId == null) {
			throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
		}
		//エンティティの識別子の不変性を保持し、エンティティの同一性を保証します。
		//privateにすることによって不要。
		/*
		if(this.memberTypeId != null) {
			throw new IllegalArgumentException("The_memberTypeId_is_already_exist");
		}
		*/
		this.memberTypeId = memberTypeId;
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
	
	
	public void defineAttribute(Attribute attribute) {
		if(attribute == null) {
			throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
		}
		this.attributes.add(attribute);
	}
	
	public void replaceAttribute(Attribute previousAttribute, Attribute postAttribute) {
		int index = this.attributes.indexOf(previousAttribute);
		if(index == -1) {
			throw new IllegalArgumentException("The_attribute_dose_not_exist");
		}
		this.attributes.set(index, postAttribute);	
	}
	
	public void removeAttribute(Attribute attribute) {
		int index = this.attributes.indexOf(attribute);
		if(index == -1) {
			throw new IllegalArgumentException("The_attribute_dose_not_exist");
		}
		this.attributes.remove(index);	
	}
	
	

}
