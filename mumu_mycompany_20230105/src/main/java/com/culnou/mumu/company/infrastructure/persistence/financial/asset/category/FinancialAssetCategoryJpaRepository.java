package com.culnou.mumu.company.infrastructure.persistence.financial.asset.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryId;

import com.culnou.mumu.company.domain.model.financial.asset.category.FinancialAssetCategoryRepository;
@Service("financialAssetCategoryJpaRepository")
@Transactional
public class FinancialAssetCategoryJpaRepository implements FinancialAssetCategoryRepository{
	@Autowired
	private FinancialAssetCategoryJpaDataRepository repository;

	@Override
	public void save(FinancialAssetCategory financialAssetCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(financialAssetCategory);

	}

	@Override
	public void remove(FinancialAssetCategory financialAssetCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(financialAssetCategory);

	}

	@Override
	public FinancialAssetCategory financialAssetCategoryOfId(FinancialAssetCategoryId financialAssetCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByFinancialAssetCategoryId(financialAssetCategoryId);
	}

	@Override
	public List<FinancialAssetCategory> financialAssetCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findFinancialAssetCategoriesOfCompany(companyId);
	}

	@Override
	public List<FinancialAssetCategory> financialAssetCategoriesOfFinancialAssetType(String financialAssetTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findFinancialAssetCategoriesOfFinancialAssetType(financialAssetTypeId);
	}

	@Override
	public List<FinancialAssetCategory> financialAssetCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findFinancialAssetCategoriesOfBusinessUnit(businessUnitId);
	}


}
