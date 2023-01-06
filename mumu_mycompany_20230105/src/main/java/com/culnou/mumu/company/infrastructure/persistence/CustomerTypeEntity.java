package com.culnou.mumu.company.infrastructure.persistence;

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
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.Attribute;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.common.Personality;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import lombok.Data;

@Entity
@Table(name = "customer_types")
@XmlRootElement
@Data
public class CustomerTypeEntity {
	@Id
	@Embedded
	private CustomerTypeId customerTypeId;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessDomainId businessDomainId;
	@Column(name = "customer_type_name")
	private String customerTypeName;
	@Enumerated(EnumType.STRING)
	@Column(name = "personality")
	private Personality personality;
	@Column(name = "value")
	private String values;
	@Column(name = "cause")
	private String cause;
	@Column(name = "issue")
	private String issue;
	@Column(name = "problem")
	private String problem;
	@Embedded
	private Url url;
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	@ElementCollection
	private List<Attribute> attributes = new ArrayList<>();
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Embedded
	private DataTypeId dataTypeId;
	//データ関連
	@Column(name = "entity_name")
	private String entityName;
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

}
