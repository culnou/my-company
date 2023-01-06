package com.culnou.mumu.company.domain.model.program;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;



public interface ProgramRepository {
	public void save(Program program) throws Exception;
	
	public void remove(Program program) throws Exception;
	
	public Program findProgramOfId(ProgramId programId) throws Exception;
	
	public List<Program> findProgramsOfBusinessUnsit(BusinessUnitId businessUnitId) throws Exception;

	public List<Program> findProgramsOfCompany(CompanyId companyId) throws Exception;
	
	public List<Program> findProgramsOfCustomerCategory(String customerCategoryId) throws Exception;
	
	public List<Program> findProgramsOfProductCategory(String productCategoryId) throws Exception;
	
	public List<Program> findProgramsOfMemberCategory(String memberCategoryId) throws Exception;
	
	public List<Program> findProgramsOfPartnerCategory(String partnerCategoryId) throws Exception;
	
	public List<Program> findProgramsOfPlaceCategory(String placeCategoryId) throws Exception;
	
	public List<Program> findProgramsOfDataCategory(String dataCategoryId) throws Exception;
	
	public List<Program> findProgramsOfApplicationCategory(String applicationCategoryId) throws Exception;
	
	public List<Program> findProgramsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception;
	
	public List<Program> findProgramsOfActionPlan(String actionPlanId) throws Exception;



}
