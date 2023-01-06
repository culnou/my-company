package com.culnou.mumu.company.infrastructure.persistence.data.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.domain.DataDomain;
import com.culnou.mumu.company.domain.model.data.domain.DataDomainId;
import com.culnou.mumu.company.domain.model.data.domain.DataDomainRepository;

@Service("dataDomainJpaRepository")
@Transactional
public class DataDomainJpaRepository implements DataDomainRepository {
	
	@Autowired
	private DataDomainJpaDataRepository repository;

	@Override
	public DataDomain findDataDomainOfId(DataDomainId dataDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findBydataDomainId(dataDomainId);
	}

	@Override
	public List<DataDomain> findDataDomainsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDatasDomainsOfCompany(new CompanyId(companyId));
	}

	

	@Override
	public void save(DataDomain dataDomain) throws Exception {
		// TODO Auto-generated method stub
		repository.save(dataDomain);

	}

	@Override
	public void remove(DataDomain dataDomain) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(dataDomain);

	}

}
