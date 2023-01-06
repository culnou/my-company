package com.culnou.mumu.company.infrastructure.persistence.application.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationType;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeId;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeRepository;
@Service("applicationTypeJpaRepository")
@Transactional
public class ApplicationTypeJpaRepository implements ApplicationTypeRepository {

	@Autowired
	private ApplicationTypeJpaDataRepository repository;

	@Override
	public ApplicationType findApplicationTypeOfId(ApplicationTypeId applicationTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByapplicationTypeId(applicationTypeId);
	}

	@Override
	public List<ApplicationType> findApplicationTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationsTypesOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<ApplicationType> findApplicationTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}

	@Override
	public void save(ApplicationType applicationType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(applicationType);

	}

	@Override
	public void remove(ApplicationType applicationType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(applicationType);

	}

	@Override
	public List<ApplicationType> findApplicationTypesOfJob(String jobId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findApplicationTypesOfJob(new JobId(jobId));
	}


}
