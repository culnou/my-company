package com.culnou.mumu.company.domain.model.place.category;

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

import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.instance.Place;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;
import com.culnou.mumu.company.domain.model.place.type.AssociatedPlaceType;
import com.culnou.mumu.company.domain.model.place.type.PlaceTypeId;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;

@Entity
@Table(name = "place_categories")
@XmlRootElement
@Data
public class PlaceCategory {
	@Id
	@Embedded
	private PlaceCategoryId placeCategoryId;
	@Column(name = "place_category_name")
	private String placeCategoryName;
	@Column(name = "place_category_description")
	private String placeCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private PlaceTypeId placeTypeId;
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
	private List<AssociatedPlaceType> associatedPlaceTypes = new ArrayList<>();
	
	protected PlaceCategory() {};
	
	public PlaceCategory(PlaceCategoryId placeCategoryId, String placeCategoryName, CompanyId companyId, BusinessUnitId businessUnitId) {
		this.setPlaceCategoryId(placeCategoryId);
		this.setPlaceCategoryName(placeCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setPlaceCategoryId(PlaceCategoryId placeCategoryId) {
		if(placeCategoryId == null) {
			throw new IllegalArgumentException("The_placeCategoryId_may_not_be_set_to_null");
		}
		this.placeCategoryId = placeCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	public Place definePlace(PlaceId placeId, String placeName, CompanyId companyId, BusinessUnitId businessUnitId) throws Exception{
		if(placeId == null) {
			throw new IllegalArgumentException("The_placeId_may_not_be_set_to_null");
		}
		if(placeName == null || placeName.isEmpty()) {
			throw new IllegalArgumentException("The_placeName_may_not_be_set_to_null");
		}
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		
		return new Place(placeId, placeName, companyId, businessUnitId, this.placeCategoryId);
	}
	
	

}
