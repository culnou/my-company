package com.culnou.mumu.company.infrastructure.persistence.it.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.it.type.ItType;
import com.culnou.mumu.company.domain.model.it.type.ItTypeId;
import com.culnou.mumu.company.domain.model.it.type.ItTypeRepository;


@Service("itTypeJpaRepository")
@Transactional
public class ItTypeJpaRepository implements ItTypeRepository {

	@Autowired
	private ItTypeJpaDataRepository repository;

	@Override
	public ItType findItTypeOfId(ItTypeId itTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByitTypeId(itTypeId);
	}

	@Override
	public List<ItType> findItTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItsTypesOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<ItType> findItTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findItTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}

	@Override
	public void save(ItType itType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(itType);

	}

	@Override
	public void remove(ItType itType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(itType);

	}


}
