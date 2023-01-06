package com.culnou.mumu.company.domain.model.place.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;



public interface PlaceCategoryRepository {
	
	//コマンド
    public void save(PlaceCategory placeCategory) throws Exception;
	
	public void remove(PlaceCategory placeCategory) throws Exception;
	
	//クエリ
	public PlaceCategory placeCategoryOfId(PlaceCategoryId placeCategoryId) throws Exception;
	
	public List<PlaceCategory> placeCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<PlaceCategory> placeCategoriesOfPlaceType(String placeTypeId) throws Exception;
	
	public List<PlaceCategory> placeCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
