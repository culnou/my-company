package com.culnou.mumu.company.infrastructure.persistence.place.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;
import com.culnou.mumu.company.domain.model.place.instance.Place;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;
import com.culnou.mumu.company.domain.model.place.instance.PlaceRepository;

@Service("placeJpaRepository")
@Transactional
public class PlaceJpaRepository implements PlaceRepository {
	@Autowired
	private PlaceJpaDataRepository reposplaceory;

	@Override
	public void save(Place place) throws Exception {
		// TODO Auto-generated method stub
		reposplaceory.save(place);
	}

	@Override
	public void remove(Place place) throws Exception {
		// TODO Auto-generated method stub
		reposplaceory.delete(place);
	}

	@Override
	public Place placeOfId(PlaceId placeId) throws Exception {
		// TODO Auto-generated method stub
		return reposplaceory.findByPlaceId(placeId);
	}

	@Override
	public List<Place> placesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return reposplaceory.findPlacesOfCompany(companyId);
	}

	@Override
	public List<Place> placesOfPlaceCategory(String placeCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return reposplaceory.findPlacesOfPlaceCategory(new PlaceCategoryId(placeCategoryId));
	}

	@Override
	public List<Place> placesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return reposplaceory.findPlacesOfBusinessUnit(businessUnitId);
	}

}

