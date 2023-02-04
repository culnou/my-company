package com.culnou.mumu.company.domain.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class BusinessUnitService {
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	/*
	@Autowired
	private MemberCategoryApplicationService memberCategoryService;
	@Autowired
	private PartnerCategoryApplicationService partnerCategoryService;
	@Autowired
	private DataCategoryApplicationService dataCategoryService;
	@Autowired
	private ApplicationCategoryApplicationService applicationCategoryService;
	@Autowired
	private PlaceCategoryApplicationService placeCategoryService;
	@Autowired
	private FinancialAssetCategoryApplicationService financialAssetService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProgramService programService;
	*/
	
	public boolean isUsed(BusinessUnitId businessUnitId) throws Exception{
		//事業単位が顧客カテゴリ、製品カテゴリ、部門、プロジェクト、プログラムで使用されているかチェックする。
		boolean result = false;
		
		
		
		//使用中かどうかで判断するように変更。2023/2/3
		BusinessUnit businessUnit =businessUnitRepository.businessUnitOfId(businessUnitId);
		if(businessUnit.isUsed()) {
			result = true;
		}
		
		/*
		List<CustomerCategory> customers = customerCategoryRepository.customerCategoriesOfBusinessUnit(businessUnitId);
		if(customers.size() > 0) {
			result = true;
		}
		List<ProductCategory> products = productCategoryRepository.productCategoriesOfBusinessUnit(businessUnitId);
		if(products.size() > 0) {
			result = true;
		}
		
		List<Department> departments = departmentRepository.departmentsOfBusinessUnit(businessUnitId);
		if(departments.size() > 0) {
			result = true;
		}
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfBusinessUnit(businessUnitId);
		if(actionPlans.size() > 0) {
			result = true;
		}
		
		//メンバーカテゴリ
		List<MemberCategoryDto> memberCatgories = memberCategoryService.findMemberCategoriesOfBusinessUnit(businessUnitId.businessUnitId);
		if(memberCatgories.size() > 0) {
			result = true;
		}
		
		//パートナーカテゴリ
		List<PartnerCategoryDto> partnerCategories = partnerCategoryService.findPartnerCategoriesOfBusinessUnit(businessUnitId.businessUnitId);
		if(partnerCategories.size() > 0) {
			result = true;
		}
		//場所カテゴリ
		List<PlaceCategoryDto> placeCategories = placeCategoryService.findPlaceCategoriesOfBusinessUnit(businessUnitId.businessUnitId);
		if(placeCategories.size() > 0) {
			result = true;
		}
		//データカテゴリ
		List<DataCategoryDto> dataCategories = dataCategoryService.findDataCategoriesOfBusinessUnit(businessUnitId.businessUnitId);
		if(dataCategories.size() > 0) {
			result = true;
		}
		//アプリケーションカテゴリ
		List<ApplicationCategoryDto> applicationCategories = applicationCategoryService.findApplicationCategoriesOfBusinessUnit(businessUnitId.businessUnitId);
		if(applicationCategories.size() > 0) {
			result = true;
		}
		//財務資産カテゴリ
		List<FinancialAssetCategoryDto> financialAssetCategories = financialAssetService.findFinancialAssetCategoriesOfBusinessUnit(businessUnitId.businessUnitId);
		if(financialAssetCategories.size() > 0) {
			result = true;
		}
		
		//プロジェクト
		List<ProjectDto> projects = projectService.findProjectsOfBusinessUnit(businessUnitId.businessUnitId);
		if(projects.size() > 0) {
			result = true;
		}
		
		//プログラム
		List<ProgramDto> programs = programService.findProgramsOfBusinessUnit(businessUnitId.businessUnitId);
		if(programs.size() > 0) {
			result = true;
		}
		*/
		
		
		
		return result;
	}
	
	public boolean goalIsUsed(BusinessUnitId businessUnitId, Indicator indicator) throws Exception{
		boolean result = false;
		BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(businessUnitId);
		List<Achievement> achievements = businessUnit.getAchievements();
		for(Achievement achievement : achievements) {
			if(achievement.getGoal().getIndicator().equals(indicator)){
				result = true;
				break;
			}
		}
		
		return result;
	}
	

}
