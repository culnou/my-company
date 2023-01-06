package com.culnou.mumu.company.infrastructure.persistence.place.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;



public interface PlaceCategoryJpaDataRepository extends JpaRepository<PlaceCategory, PlaceCategoryId> {
	public PlaceCategory findByPlaceCategoryId(PlaceCategoryId placeCategoryId);
	@Query(value = "select placeCategory from PlaceCategory placeCategory where placeCategory.companyId = :CompanyId")
	public List<PlaceCategory> findPlaceCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select placeCategory from PlaceCategory placeCategory join placeCategory.associatedPlaceTypes placeTypes where placeTypes.placeTypeId = :PlaceTypeId")
	public List<PlaceCategory> findPlaceCategoriesOfPlaceType(@Param("PlaceTypeId") String placeTypeId);
	@Query(value = "select placeCategory from PlaceCategory placeCategory where placeCategory.businessUnitId = :BusinessUnitId")
	public List<PlaceCategory> findPlaceCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);


}
