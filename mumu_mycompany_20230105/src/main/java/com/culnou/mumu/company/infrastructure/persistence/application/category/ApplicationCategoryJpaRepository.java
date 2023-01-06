package com.culnou.mumu.company.infrastructure.persistence.application.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryRepository;


@Service("applicationCategoryJpaRepository")
@Transactional
public class ApplicationCategoryJpaRepository implements ApplicationCategoryRepository {
	@Autowired
	private ApplicationCategoryJpaDataRepository repository;

	@Override
	public void save(ApplicationCategory applicationCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(applicationCategory);

	}

	@Override
	public void remove(ApplicationCategory applicationCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(applicationCategory);

	}

	@Override
	public ApplicationCategory applicationCategoryOfId(ApplicationCategoryId applicationCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByApplicationCategoryId(applicationCategoryId);
	}

	@Override
	public List<ApplicationCategory> applicationCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationCategoriesOfCompany(companyId);
	}

	@Override
	public List<ApplicationCategory> applicationCategoriesOfApplicationType(String applicationTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationCategoriesOfApplicationType(applicationTypeId);
	}

	@Override
	public List<ApplicationCategory> applicationCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationCategoriesOfBusinessUnit(businessUnitId);
	}

}
