package com.culnou.mumu.company.domain.model.project;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;

@Service
@Transactional
public class BusinessUnitAdapter {
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	
	public BusinessUnit findBusinessUnitOfId(String businessUnitId) throws Exception{
		return businessUnitRepository.businessUnitOfId(new BusinessUnitId(businessUnitId));
	}

}
