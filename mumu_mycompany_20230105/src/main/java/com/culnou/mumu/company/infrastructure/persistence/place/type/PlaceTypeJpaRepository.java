package com.culnou.mumu.company.infrastructure.persistence.place.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.place.type.PlaceType;
import com.culnou.mumu.company.domain.model.place.type.PlaceTypeId;
import com.culnou.mumu.company.domain.model.place.type.PlaceTypeRepository;
@Service("placeTypeJpaRepository")
@Transactional
public class PlaceTypeJpaRepository implements PlaceTypeRepository {
	
	@Autowired
	private PlaceTypeJpaDataRepository repository;

	@Override
	public PlaceType findPlaceTypeOfId(PlaceTypeId placeTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByplaceTypeId(placeTypeId);
	}

	@Override
	public List<PlaceType> findPlaceTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findPlacesTypesOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<PlaceType> findPlaceTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findPlaceTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}

	@Override
	public void save(PlaceType placeType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(placeType);

	}

	@Override
	public void remove(PlaceType placeType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(placeType);

	}

}
