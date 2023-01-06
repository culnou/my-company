package com.culnou.mumu.company.domain.model.it.type;

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

import com.culnou.mumu.company.domain.model.it.type.ItType;


import lombok.Data;

@Entity
@Table(name = "it_types")
@XmlRootElement
@Data
public class ItType {
	
    protected ItType() {}
	
	protected ItType(ItTypeId itTypeId, CompanyId companyId, String itTypeName, ItClass financialClass) {
		this.setItTypeId(itTypeId);
		this.setCompanyId(companyId);
		this.setItTypeName(itTypeName);
		this.setFinancialClass(financialClass);
	}
	
	@Id
	@Embedded
	@NotNull
	private ItTypeId itTypeId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Column(name = "it_type_name")
	@NotNull
	@NotEmpty
	private String itTypeName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "it_class")
	private ItClass financialClass;
	
	@Column(name = "it_type_description")
	private String itTypeDescription;
	
	@Embedded
	private BusinessDomainId businessDomainId;
	
	@Column(name = "business_domain_name")
	private String businessDomainName;
	
	@Embedded
	private Url url;
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;


}
