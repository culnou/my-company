package com.culnou.mumu.company.infrastructure.persistence.place.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryId;


import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryRepository;

@Service("placeCategoryJpaRepository")
@Transactional
public class PlaceCategoryJpaRepository implements PlaceCategoryRepository {

	@Autowired
	private PlaceCategoryJpaDataRepository repository;

	@Override
	public void save(PlaceCategory placeCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(placeCategory);

	}

	@Override
	public void remove(PlaceCategory placeCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(placeCategory);

	}

	@Override
	public PlaceCategory placeCategoryOfId(PlaceCategoryId placeCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByPlaceCategoryId(placeCategoryId);
	}

	@Override
	public List<PlaceCategory> placeCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findPlaceCategoriesOfCompany(companyId);
	}

	@Override
	public List<PlaceCategory> placeCategoriesOfPlaceType(String placeTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findPlaceCategoriesOfPlaceType(placeTypeId);
	}

	@Override
	public List<PlaceCategory> placeCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findPlaceCategoriesOfBusinessUnit(businessUnitId);
	}


}
