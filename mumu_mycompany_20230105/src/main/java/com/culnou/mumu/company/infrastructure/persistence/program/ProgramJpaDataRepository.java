package com.culnou.mumu.company.infrastructure.persistence.program;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.program.Program;
import com.culnou.mumu.company.domain.model.program.ProgramId;



public interface ProgramJpaDataRepository extends JpaRepository<Program, ProgramId> {
	public Program findByProgramId(ProgramId programId);
	
	@Query(value = "select program from Program program where program.businessUnitId = :BusinessUnitId")
	public List<Program> findProgramsOfBusinessUnsit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	
	@Query(value = "select program from Program program where program.companyId = :CompanyId")
	public List<Program> findProgramsOfCompany(@Param("CompanyId") CompanyId companyId);

	@Query(value = "select program from Program program join program.associatedCustomerCategories customerCategories where customerCategories.customerCategoryId = :CustomerCategoryId")
	public List<Program> findProgramsOfCustomerCategory(@Param("CustomerCategoryId") String customerCategoryId);
	
	@Query(value = "select program from Program program join program.associatedProductCategories productCategories where productCategories.productCategoryId = :ProductCategoryId")
	public List<Program> findProgramsOfProductCategory(@Param("ProductCategoryId") String productCategoryId);

	@Query(value = "select program from Program program join program.associatedMemberCategories memberCategores where memberCategores.memberCategoryId = :MemberCategoryId")
	public List<Program> findProgramsOfMemberCategory(@Param("MemberCategoryId") String memberCategoryId);

	@Query(value = "select program from Program program join program.associatedPartnerCategories partnerCategories where partnerCategories.partnerCategoryId = :PartnerCategoryId")
	public List<Program> findProgramsOfPartnerCategory(@Param("PartnerCategoryId") String partnerCategoryId);

	@Query(value = "select program from Program program join program.associatedDataCategories dataCategories where dataCategories.dataCategoryId = :DataCategoryId")
	public List<Program> findProgramsOfDataCategory(@Param("DataCategoryId") String dataCategoryId);

	@Query(value = "select program from Program program join program.associatedApplicationCategories applicationCategories where applicationCategories.applicationCategoryId = :ApplicationCategoryId")
	public List<Program> findProgramsOfApplicationCategory(@Param("ApplicationCategoryId") String applicationCategoryId);

	@Query(value = "select program from Program program join program.associatedPlaceCategories placeCategories where placeCategories.placeCategoryId = :PlaceCategoryId")
	public List<Program> findProgramsOfPlaceCategory(@Param("PlaceCategoryId") String placeCategoryId);

	@Query(value = "select program from Program program join program.associatedActionPlans actionPlans where actionPlans.actionPlanId = :ActionPlanId")
	public List<Program> findProgramsOfActionPlan(@Param("ActionPlanId") String actionPlanId);

	@Query(value = "select program from Program program join program.associatedFinancialAssetCategories financialAssetCategories where financialAssetCategories.financialAssetCategoryId = :FinancialAssetCategoryId")
	public List<Program> findProgramsOfFinancialAssetCategory(@Param("FinancialAssetCategoryId") String financialAssetCategoryId);


}
