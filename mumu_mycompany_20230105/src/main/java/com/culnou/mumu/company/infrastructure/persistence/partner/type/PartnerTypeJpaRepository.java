package com.culnou.mumu.company.infrastructure.persistence.partner.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.partner.type.PartnerType;
import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeId;


import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeRepository;


@Service("partnerTypeJpaRepository")
@Transactional
public class PartnerTypeJpaRepository implements PartnerTypeRepository{

	@Autowired
	private PartnerTypeJpaDataRepository repository;

	@Override
	public PartnerType findPartnerTypeOfId(PartnerTypeId partnerTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByPartnerTypeId(partnerTypeId);
	}

	@Override
	public List<PartnerType> findPartnerTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findPartnerTypesOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<PartnerType> findPartnerTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findPartnerTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}

	@Override
	public void save(PartnerType partnerType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(partnerType);

	}

	@Override
	public void remove(PartnerType partnerType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(partnerType);

	}


}
