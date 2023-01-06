package com.culnou.mumu.company.infrastructure.persistence.program;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.program.Program;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.program.ProgramRepository;



@Service("programJpaRepository")
@Transactional
public class ProgramJpaRepository implements ProgramRepository{
	@Autowired
	private ProgramJpaDataRepository repository;

	@Override
	public void save(Program program) throws Exception {
		// TODO Auto-generated method stub
		repository.save(program);
		
	}
	
	public Program findProgramOfId(ProgramId programId) throws Exception{
		return repository.findByProgramId(programId);
	}

	@Override
	public List<Program> findProgramsOfBusinessUnsit(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfBusinessUnsit(businessUnitId);
	}

	@Override
	public void remove(Program program) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(program);
		
	}

	@Override
	public List<Program> findProgramsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfCompany(companyId);
	}
	
	@Override
	public List<Program> findProgramsOfCustomerCategory(String customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfCustomerCategory(customerCategoryId);
	}

	@Override
	public List<Program> findProgramsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfProductCategory(productCategoryId);
	}

	@Override
	public List<Program> findProgramsOfMemberCategory(String memberCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfMemberCategory(memberCategoryId);
	}

	@Override
	public List<Program> findProgramsOfPartnerCategory(String partnerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfPartnerCategory(partnerCategoryId);
	}

	@Override
	public List<Program> findProgramsOfPlaceCategory(String placeCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfPlaceCategory(placeCategoryId);
	}

	@Override
	public List<Program> findProgramsOfDataCategory(String dataCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfDataCategory(dataCategoryId);
	}

	@Override
	public List<Program> findProgramsOfApplicationCategory(String applicationCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfApplicationCategory(applicationCategoryId);
	}

	@Override
	public List<Program> findProgramsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfFinancialAssetCategory(financialAssetCategoryId);
	}

	@Override
	public List<Program> findProgramsOfActionPlan(String actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findProgramsOfActionPlan(actionPlanId);
	}

	

}
