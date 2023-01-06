package com.culnou.mumu.company.infrastructure.persistence.it.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;
import com.culnou.mumu.company.domain.model.it.instance.It;
import com.culnou.mumu.company.domain.model.it.instance.ItId;
import com.culnou.mumu.company.domain.model.it.instance.ItRepository;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;
@Service("itJpaRepository")
@Transactional
public class ItJpaRepository implements ItRepository {
	@Autowired
	private ItJpaDataRepository repository;

	@Override
	public void save(It it) throws Exception {
		// TODO Auto-generated method stub
		repository.save(it);
	}

	@Override
	public void remove(It it) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(it);
	}

	@Override
	public It itOfId(ItId itId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByItId(itId);
	}

	@Override
	public List<It> itsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItsOfCompany(companyId);
	}

	@Override
	public List<It> itsOfItCategory(String itCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItsOfItCategory(new ItCategoryId(itCategoryId));
	}

	@Override
	public List<It> itsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItsOfBusinessUnit(businessUnitId);
	}

	@Override
	public List<It> itsOfPlace(String placeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItsOfPlace(new PlaceId(placeId));
	}

}
