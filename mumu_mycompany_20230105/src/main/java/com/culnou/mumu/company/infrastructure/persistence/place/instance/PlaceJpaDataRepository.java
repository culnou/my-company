package com.culnou.mumu.company.infrastructure.persistence.place.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;
import com.culnou.mumu.company.domain.model.place.instance.Place;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;

public interface PlaceJpaDataRepository extends JpaRepository<Place, PlaceId> {
	
	public Place findByPlaceId(PlaceId placeId);
	@Query(value = "select place from Place place where place.companyId = :CompanyId")
	public List<Place> findPlacesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select place from Place place where place.placeCategoryId = :PlaceCategoryId")
	public List<Place> findPlacesOfPlaceCategory(@Param("PlaceCategoryId") PlaceCategoryId placeCategoryId);
	@Query(value = "select place from Place place where place.businessUnitId = :BusinessUnitId")
	public List<Place> findPlacesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);

}
