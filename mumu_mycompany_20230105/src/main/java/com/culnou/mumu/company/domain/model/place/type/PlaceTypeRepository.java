package com.culnou.mumu.company.domain.model.place.type;

import java.util.List;



public interface PlaceTypeRepository {
	
    public PlaceType findPlaceTypeOfId(PlaceTypeId placeTypeId) throws Exception;
	
    public List<PlaceType> findPlaceTypesOfCompany(String companyId) throws Exception;
	
	public List<PlaceType> findPlaceTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public void save(PlaceType placeType) throws Exception;
	
	public void remove(PlaceType placeType) throws Exception;

}
