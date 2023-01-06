package com.culnou.mumu.company.domain.model.data.type;



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
import com.culnou.mumu.company.domain.model.common.Email;

import lombok.Data;
@Entity
@Table(name = "data_types")
@XmlRootElement
@Data
public class DataType {
	
	protected DataType() {}
	
	protected DataType(DataTypeId dataTypeId, CompanyId companyId, String dataTypeName, DataClass dataClass) {
		this.setDataTypeId(dataTypeId);
		this.setCompanyId(companyId);
		this.setDataTypeName(dataTypeName);
		this.setDataClass(dataClass);
	}
	
	@Id
	@Embedded
	@NotNull
	private DataTypeId dataTypeId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Column(name = "data_type_name")
	@NotNull
	@NotEmpty
	private String dataTypeName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "data_class")
	private DataClass dataClass;
	
	@Column(name = "data_type_description")
	private String dataTypeDescription;
	
	@Embedded
	private Url url;
	
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Embedded
	private BusinessDomainId businessDomainId;
	
	@Column(name = "business_domain_name")
	private String businessDomainName;
	
	@Column(name = "data_owner_id")
	private String dataOwnerId;
	
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
	
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	

}
