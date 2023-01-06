package com.culnou.mumu.company.domain.model.place.instance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;

import lombok.Data;

@Entity
@Table(name = "places")
@XmlRootElement
@Data
public class Place {
	@Id
	@Embedded
	private PlaceId placeId;
	@Column(name = "place_name")
	@NotNull
	private String placeName;
	@Column(name = "place_description")
	private String placeDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private PlaceCategoryId placeCategoryId;
	@Column(name = "place_category_name")
	private String placeCategoryName;
	@Embedded
	private Url url;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
    protected Place() {};
	
	public Place(PlaceId placeId, String placeName, CompanyId companyId, BusinessUnitId businessUnitId, PlaceCategoryId placeCategoryId) {
		this.setPlaceId(placeId);
		this.setPlaceName(placeName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		this.setPlaceCategoryId(placeCategoryId);
	}
	

}
