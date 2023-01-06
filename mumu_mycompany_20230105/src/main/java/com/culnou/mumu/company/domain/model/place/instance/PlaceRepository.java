package com.culnou.mumu.company.domain.model.place.instance;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


public interface PlaceRepository {
	
	//コマンド
    public void save(Place place) throws Exception;
	
	public void remove(Place place) throws Exception;
	
	//クエリ
	public Place placeOfId(PlaceId placeId) throws Exception;
	
	public List<Place> placesOfCompany(CompanyId companyId) throws Exception;
	
	public List<Place> placesOfPlaceCategory(String placeCategoryId) throws Exception;
	
	public List<Place> placesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
