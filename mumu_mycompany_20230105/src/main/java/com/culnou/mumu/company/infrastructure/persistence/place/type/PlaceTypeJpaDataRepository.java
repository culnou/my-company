package com.culnou.mumu.company.infrastructure.persistence.place.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.place.type.PlaceType;
import com.culnou.mumu.company.domain.model.place.type.PlaceTypeId;

public interface PlaceTypeJpaDataRepository extends JpaRepository<PlaceType, PlaceTypeId> {
    
	public PlaceType findByplaceTypeId(PlaceTypeId placeTypeId);
	
	@Query(value = "select placeType from PlaceType placeType where placeType.companyId = :CompanyId")
	public List<PlaceType> findPlacesTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select placeType from PlaceType placeType where placeType.businessDomainId = :BusinessDomainId")
	public List<PlaceType> findPlaceTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
}
