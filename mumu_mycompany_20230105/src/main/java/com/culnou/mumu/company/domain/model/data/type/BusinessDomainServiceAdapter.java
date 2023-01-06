package com.culnou.mumu.company.domain.model.data.type;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.infrastructure.persistence.BusinessDomainJpaRepository;

@Service
@Transactional
public class BusinessDomainServiceAdapter {
	
	@Autowired
	private BusinessDomainJpaRepository businessDomainRepository;
	
	public BusinessDomain findBusinessDomain(String businessDomainId) throws Exception{
		return businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
	}

}
