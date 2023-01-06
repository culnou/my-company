package com.culnou.mumu.company.infrastructure.persistence.it.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;

import com.culnou.mumu.company.domain.model.it.category.ItCategoryRepository;
@Service("itCategoryJpaRepository")
@Transactional
public class ItCategoryJpaRepository implements ItCategoryRepository{
	@Autowired
	private ItCategoryJpaDataRepository repository;

	@Override
	public void save(ItCategory itCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(itCategory);

	}

	@Override
	public void remove(ItCategory itCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(itCategory);

	}

	@Override
	public ItCategory itCategoryOfId(ItCategoryId itCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByItCategoryId(itCategoryId);
	}

	@Override
	public List<ItCategory> itCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItCategoriesOfCompany(companyId);
	}

	@Override
	public List<ItCategory> itCategoriesOfItType(String itTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findItCategoriesOfItType(itTypeId);
	}

	@Override
	public List<ItCategory> itCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findItCategoriesOfBusinessUnit(businessUnitId);
	}


}
