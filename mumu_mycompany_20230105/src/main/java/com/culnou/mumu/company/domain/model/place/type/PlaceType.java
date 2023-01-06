package com.culnou.mumu.company.domain.model.place.type;

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
@Table(name = "place_types")
@XmlRootElement
@Data
public class PlaceType {
	
    protected PlaceType() {}
	
	protected PlaceType(PlaceTypeId placeTypeId, CompanyId companyId, String placeTypeName, PlaceClass financialClass) {
		this.setPlaceTypeId(placeTypeId);
		this.setCompanyId(companyId);
		this.setPlaceTypeName(placeTypeName);
		this.setFinancialClass(financialClass);
	}
	
	@Id
	@Embedded
	@NotNull
	private PlaceTypeId placeTypeId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Column(name = "place_type_name")
	@NotNull
	@NotEmpty
	private String placeTypeName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "place_class")
	private PlaceClass financialClass;
	
	@Column(name = "place_type_description")
	private String placeTypeDescription;
	
	@Embedded
	private BusinessDomainId businessDomainId;
	
	@Column(name = "business_domain_name")
	private String businessDomainName;
	
	@Embedded
	private Url url;
	
	@Embedded
	private DataTypeId dataTypeId;
	
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
