package com.culnou.mumu.company.infrastructure.persistence.partner.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;


import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryRepository;

@Service("partnerCategoryJpaRepository")
@Transactional
public class PartnerCategoryJpaRepository implements PartnerCategoryRepository {
	@Autowired
	private PartnerCategoryJpaDataRepository repository;

	@Override
	public void save(PartnerCategory partnerCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(partnerCategory);

	}

	@Override
	public void remove(PartnerCategory partnerCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(partnerCategory);

	}

	@Override
	public PartnerCategory partnerCategoryOfId(PartnerCategoryId partnerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByPartnerCategoryId(partnerCategoryId);
	}

	@Override
	public List<PartnerCategory> partnerCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findPartnerCategoriesOfCompany(companyId);
	}

	@Override
	public List<PartnerCategory> partnerCategoriesOfPartnerType(String partnerTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findPartnerCategoriesOfPartnerType(partnerTypeId);
	}

	@Override
	public List<PartnerCategory> partnerCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findPartnerCategoriesOfBusinessUnit(businessUnitId);
	}


}
