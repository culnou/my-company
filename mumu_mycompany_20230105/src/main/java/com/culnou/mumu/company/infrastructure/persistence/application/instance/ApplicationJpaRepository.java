package com.culnou.mumu.company.infrastructure.persistence.application.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.instance.Application;
import com.culnou.mumu.company.domain.model.application.instance.ApplicationId;
import com.culnou.mumu.company.domain.model.application.instance.ApplicationRepository;

@Service("applicationJpaRepository")
@Transactional
public class ApplicationJpaRepository implements ApplicationRepository {
	@Autowired
	private ApplicationJpaDataRepository reposapplicationory;

	@Override
	public void save(Application application) throws Exception {
		// TODO Auto-generated method stub
		reposapplicationory.save(application);
	}

	@Override
	public void remove(Application application) throws Exception {
		// TODO Auto-generated method stub
		reposapplicationory.delete(application);
	}

	@Override
	public Application applicationOfId(ApplicationId applicationId) throws Exception {
		// TODO Auto-generated method stub
		return reposapplicationory.findByApplicationId(applicationId);
	}

	@Override
	public List<Application> applicationsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return reposapplicationory.findApplicationsOfCompany(companyId);
	}

	@Override
	public List<Application> applicationsOfApplicationCategory(String applicationCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return reposapplicationory.findApplicationsOfApplicationCategory(new ApplicationCategoryId(applicationCategoryId));
	}

	@Override
	public List<Application> applicationsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return reposapplicationory.findApplicationsOfBusinessUnit(businessUnitId);
	}

	@Override
	public List<Application> applicationsOfIt(String ItId) throws Exception {
		// TODO Auto-generated method stub
		return reposapplicationory.findApplicationsOfIt(new com.culnou.mumu.company.domain.model.it.instance.ItId(ItId));
	}

}
