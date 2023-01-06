package com.culnou.mumu.company.infrastructure.persistence.financial.asset.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetType;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetTypeId;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetTypeRepository;

@Service("financialAssetTypeJpaRepository")
@Transactional
public class FinancialAssetTypeJpaRepository implements FinancialAssetTypeRepository {
	
	@Autowired
	private FinancialAssetTypeJpaDataRepository repository;

	@Override
	public FinancialAssetType findFinancialAssetTypeOfId(FinancialAssetTypeId financialAssetTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByFinancialAssetTypeId(financialAssetTypeId);
	}

	

	@Override
	public void save(FinancialAssetType financialAssetType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(financialAssetType);

	}

	@Override
	public void remove(FinancialAssetType financialAssetType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(financialAssetType);

	}



	@Override
	public List<FinancialAssetType> findFinancialAssetTypesOfBusinessDomain(String businessDomainId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findFinanicialAssetTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}



	@Override
	public List<FinancialAssetType> findFinancialAssetTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findFinanicialAssetTypesOfCompany(new CompanyId(companyId));
	}

}
