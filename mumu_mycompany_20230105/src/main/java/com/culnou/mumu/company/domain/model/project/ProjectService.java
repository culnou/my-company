package com.culnou.mumu.company.domain.model.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.ApplicationCategoryApplicationService;
import com.culnou.mumu.company.application.DataCategoryApplicationService;
import com.culnou.mumu.company.application.FinancialAssetCategoryApplicationService;
import com.culnou.mumu.company.application.MemberCategoryApplicationService;
import com.culnou.mumu.company.application.PartnerCategoryApplicationService;
import com.culnou.mumu.company.application.PlaceCategoryApplicationService;
import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.AssociatedCustomerCategory;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.activity.action_plan.AssociatedActionPlan;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.data.category.AssociatedDataCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.AssociatedFinancialAssetCategory;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.category.AssociatedMemberCategory;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.place.category.AssociatedPlaceCategory;

import com.culnou.mumu.company.domain.service.FindProjectIndicator;
import com.culnou.mumu.company.domain.service.IndicatorType;
import com.culnou.mumu.company.domain.service.ProjectChecker;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.ActionPlanDto;
import com.culnou.mumu.compnay.controller.ApplicationCategoryDto;
import com.culnou.mumu.compnay.controller.CustomerCategoryDto;
import com.culnou.mumu.compnay.controller.DataCategoryDto;
import com.culnou.mumu.compnay.controller.FinancialAssetCategoryDto;
import com.culnou.mumu.compnay.controller.MemberCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.PartnerCategoryDto;
import com.culnou.mumu.compnay.controller.PlaceCategoryDto;
import com.culnou.mumu.compnay.controller.ProductCategoryDto;
import com.culnou.mumu.compnay.controller.ProjectDto;



@Service
@Transactional
public class ProjectService {
	@Autowired
	private ProjectRegistry projectRegistry;
	@Autowired
	private BusinessUnitAdapter businessUnitAdapter;
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	MemberCategoryApplicationService memberCategoryService;
	@Autowired
	PartnerCategoryApplicationService partnerCategoryService;
	@Autowired
	PlaceCategoryApplicationService placeCategoryService;
	@Autowired
	DataCategoryApplicationService dataCategoryService;
	@Autowired
	ApplicationCategoryApplicationService applicationCategoryService;
	@Autowired
	FinancialAssetCategoryApplicationService financialAssetCategoryService;
	@Autowired
	FindProjectIndicator findProjectIndicator;
	@Autowired
	private ProjectChecker checker;
	
	public ProjectDto findProjectDtoOfId(String projectId) throws Exception{
		return this.convertProject(projectRegistry.findProjectOfId(projectId));
	}
	
	public Project findProjectOfId(String projectId) throws Exception{
		return projectRegistry.findProjectOfId(projectId);
	}
	
	public List<Indicator> findProjectIndicators(String projectId, IndicatorType indicatorType) throws Exception{
		return findProjectIndicator.findIndicator(projectId, indicatorType);
	}
	
	/*
	 * 事業単位のプロジェクトを探す
	 */
	public List<ProjectDto> findProjectsOfBusinessUnit(String businessUnitId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			//事前条件の検証
			//BusinessUnitIdの検証
			BusinessUnit businessUnit = null;
			businessUnit = businessUnitAdapter.findBusinessUnitOfId(businessUnitId);
			if(businessUnit == null) {
				throw new Exception("The_business_unit_may_not_exist");
			}else {
				List<Project> projects = projectRegistry.findProjectsOfBusinessUnit(new BusinessUnitId(businessUnitId));
				projectDtos.addAll(this.convertProjects(projects));
			}
		}catch(Exception ex) {
			throw new Exception("The_project_could_not_be_found");
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfCompany(String companyId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfCompany(new CompanyId(companyId));
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfCustomerCategory(String customerCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfCustomerCategory(customerCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfProductCategory(String productCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfProductCategory(productCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfMemberCategory(String memberCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfMemberCategory(memberCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	
	public List<ProjectDto> findProjectsOfPartnerCategory(String partnerCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfPartnerCategory(partnerCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfPlaceCategory(String placeCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfPlaceCategory(placeCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfDataCategory(String dataCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfDataCategory(dataCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfApplicationCategory(String applicationCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfApplicationCategory(applicationCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfFinancialAssetCategory(financialAssetCategoryId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	public List<ProjectDto> findProjectsOfActionPlan(String actionPlanId) throws Exception{
		List<ProjectDto> projectDtos = new ArrayList<>();
		try {
			List<Project> projects = projectRegistry.findProjectsOfActionPlan(actionPlanId);
			projectDtos.addAll(this.convertProjects(projects));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return projectDtos;
	}
	
	
	/*
	 * プロジェクトを定義する
	 */
	public MessageDto defineProject(ProjectDto projectDto){
		
		MessageDto message = new MessageDto();
		try {
			//事前条件の検証
			//BusinessUnitIdの検証
			BusinessUnit businessUnit = null;
			businessUnit = businessUnitAdapter.findBusinessUnitOfId(projectDto.getBusinessUnitId());
			if(businessUnit == null) {
				message.setErrorMessage("BusinessUnit may not be exit.");
			}else {
				Project project = this.convertProjectDto(projectDto);
				project.setProjectId(projectRegistry.nextIdentity());
				project.setCreatedAt(new Date());
				projectRegistry.registerProject(project);
				message.setReturnValue(project.getProjectId().getProjectId());
				message.setResult("OK");
			}
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateProject(ProjectDto projectDto){
		
		MessageDto message = new MessageDto();
		try {
			//事前条件の検証
			//BusinessUnitIdの検証
			BusinessUnit businessUnit = null;
			businessUnit = businessUnitAdapter.findBusinessUnitOfId(projectDto.getBusinessUnitId());
			if(businessUnit == null) {
				message.setErrorMessage("BusinessUnit may not be exit.");
			}else {
				Project foundProject = this.findProjectOfId(projectDto.getProjectId());
				if(foundProject == null) {
					message.setErrorMessage("porject may not be exit.");
				}
				//編集・削除チェック。
				if(!checker.avarable(projectDto.getProjectId()).equals("OK")) {
					throw new Exception(checker.avarable(projectDto.getProjectId()));
				}
				Project project = this.convertProjectDto(projectDto);
				project.setUpdatedAt(new Date());
				project.setCreatedAt(foundProject.getCreatedAt());
				projectRegistry.registerProject(project);
				message.setReturnValue(project.getProjectId().getProjectId());
				message.setResult("OK");
			}
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeProject(String  projectId) {
		MessageDto message = new MessageDto();
		try {
			
			
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			//編集・削除チェック。
			if(!checker.avarable(projectId).equals("OK")) {
				throw new Exception(checker.avarable(projectId));
			}
			Project project = this.findProjectOfId(projectId);
			projectRegistry.removeProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addCustomerCategory(String projectId, String customerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			CustomerCategoryDto customerCategory = companyService.findCustomerCategoryById(customerCategoryId);
			if(customerCategory == null) {
				throw new IllegalArgumentException("The_customerCategory_is_not_exist");
			}
			AssociatedCustomerCategory customer = new AssociatedCustomerCategory();
			customer.setCustomerCategoryId(customerCategory.getCustomerCategoryId());
			customer.setCustomerCategoryName(customerCategory.getCustomerCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addCustomerCategory(customer);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeCustomerCategory(String projectId, String customerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			CustomerCategoryDto customerCategory = companyService.findCustomerCategoryById(customerCategoryId);
			if(customerCategory == null) {
				throw new IllegalArgumentException("The_customerCategory_is_not_exist");
			}
			AssociatedCustomerCategory customer = new AssociatedCustomerCategory();
			customer.setCustomerCategoryId(customerCategory.getCustomerCategoryId());
			customer.setCustomerCategoryName(customerCategory.getCustomerCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removeCustomerCategory(customer);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addActionPlan(String projectId, String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			ActionPlanDto actionPlanDto = companyService.findActionPlanById(actionPlanId);
			if(actionPlanDto == null) {
				throw new IllegalArgumentException("The_actionPlan_is_not_exist");
			}
			AssociatedActionPlan actionPlan = new AssociatedActionPlan();
			actionPlan.setActionPlanId(actionPlanDto.getActionPlanId());
			actionPlan.setActionPlanName(actionPlanDto.getActionPlanName());
			Project project = this.findProjectOfId(projectId);
			project.addActionPlan(actionPlan);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeActionPlan(String projectId, String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			ActionPlanDto actionPlanDto = companyService.findActionPlanById(actionPlanId);
			if(actionPlanDto == null) {
				throw new IllegalArgumentException("The_actionPlan_is_not_exist");
			}
			AssociatedActionPlan actionPlan = new AssociatedActionPlan();
			actionPlan.setActionPlanId(actionPlanDto.getActionPlanId());
			actionPlan.setActionPlanName(actionPlanDto.getActionPlanName());
			Project project = this.findProjectOfId(projectId);
			project.removeActionPlan(actionPlan);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addMemberCategory(String projectId, String memberCategoryId) {
		MessageDto message = new MessageDto();
		try {
			MemberCategoryDto memberCategory = memberCategoryService.findMemberCategoryOfId(memberCategoryId);
			if(memberCategory == null) {
				throw new IllegalArgumentException("The_memberCategory_is_not_exist");
			}
			AssociatedMemberCategory member = new AssociatedMemberCategory();
			member.setMemberCategoryId(memberCategory.getMemberCategoryId());
			member.setMemberCategoryName(memberCategory.getMemberCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addMemberCategory(member);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeMemberCategory(String projectId, String memberCategoryId) {
		MessageDto message = new MessageDto();
		try {
			MemberCategoryDto memberCategory = memberCategoryService.findMemberCategoryOfId(memberCategoryId);
			if(memberCategory == null) {
				throw new IllegalArgumentException("The_memberCategory_is_not_exist");
			}
			AssociatedMemberCategory member = new AssociatedMemberCategory();
			member.setMemberCategoryId(memberCategory.getMemberCategoryId());
			member.setMemberCategoryName(memberCategory.getMemberCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removeMemberCategory(member);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addPartnerCategory(String projectId, String partnerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PartnerCategoryDto partnerCategory = partnerCategoryService.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new IllegalArgumentException("The_partnerCategory_is_not_exist");
			}
			AssociatedPartnerCategory partner = new AssociatedPartnerCategory();
			partner.setPartnerCategoryId(partnerCategory.getPartnerCategoryId());
			partner.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addPartnerCategory(partner);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removePartnerCategory(String projectId, String partnerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PartnerCategoryDto partnerCategory = partnerCategoryService.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new IllegalArgumentException("The_partnerCategory_is_not_exist");
			}
			AssociatedPartnerCategory partner = new AssociatedPartnerCategory();
			partner.setPartnerCategoryId(partnerCategory.getPartnerCategoryId());
			partner.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removePartnerCategory(partner);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addPlaceCategory(String projectId, String placeCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PlaceCategoryDto placeCategory = placeCategoryService.findPlaceCategoryOfId(placeCategoryId);
			if(placeCategory == null) {
				throw new IllegalArgumentException("The_placeCategory_is_not_exist");
			}
			AssociatedPlaceCategory place = new AssociatedPlaceCategory();
			place.setPlaceCategoryId(placeCategory.getPlaceCategoryId());
			place.setPlaceCategoryName(placeCategory.getPlaceCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addPlaceCategory(place);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removePlaceCategory(String projectId, String placeCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PlaceCategoryDto placeCategory = placeCategoryService.findPlaceCategoryOfId(placeCategoryId);
			System.out.println("*** ProjectService1 removePlaceCategory " + placeCategoryId);
			if(placeCategory == null) {
				throw new IllegalArgumentException("The_placeCategory_is_not_exist");
			}
			System.out.println("*** ProjectService2 removePlaceCategory " + placeCategoryId);
			AssociatedPlaceCategory place = new AssociatedPlaceCategory();
			place.setPlaceCategoryId(placeCategory.getPlaceCategoryId());
			place.setPlaceCategoryName(placeCategory.getPlaceCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removePlaceCategory(place);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addDataCategory(String projectId, String dataCategoryId) {
		MessageDto message = new MessageDto();
		try {
			DataCategoryDto dataCategory = dataCategoryService.findDataCategoryOfId(dataCategoryId);
			if(dataCategory == null) {
				throw new IllegalArgumentException("The_dataCategory_is_not_exist");
			}
			AssociatedDataCategory data = new AssociatedDataCategory();
			data.setDataCategoryId(dataCategory.getDataCategoryId());
			data.setDataCategoryName(dataCategory.getDataCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addDataCategory(data);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeDataCategory(String projectId, String dataCategoryId) {
		MessageDto message = new MessageDto();
		try {
			DataCategoryDto dataCategory = dataCategoryService.findDataCategoryOfId(dataCategoryId);
			if(dataCategory == null) {
				throw new IllegalArgumentException("The_dataCategory_is_not_exist");
			}
			AssociatedDataCategory data = new AssociatedDataCategory();
			data.setDataCategoryId(dataCategory.getDataCategoryId());
			data.setDataCategoryName(dataCategory.getDataCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removeDataCategory(data);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addApplicationCategory(String projectId, String applicationCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ApplicationCategoryDto applicationCategory = applicationCategoryService.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new IllegalArgumentException("The_applicationCategory_is_not_exist");
			}
			AssociatedApplicationCategory application = new AssociatedApplicationCategory();
			application.setApplicationCategoryId(applicationCategory.getApplicationCategoryId());
			application.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addApplicationCategory(application);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeApplicationCategory(String projectId, String applicationCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ApplicationCategoryDto applicationCategory = applicationCategoryService.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new IllegalArgumentException("The_applicationCategory_is_not_exist");
			}
			AssociatedApplicationCategory application = new AssociatedApplicationCategory();
			application.setApplicationCategoryId(applicationCategory.getApplicationCategoryId());
			application.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removeApplicationCategory(application);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addFinancialAssetCategory(String projectId, String financialAssetCategoryId) {
		MessageDto message = new MessageDto();
		try {
			FinancialAssetCategoryDto financialAssetCategory = financialAssetCategoryService.findFinancialAssetCategoryOfId(financialAssetCategoryId);
			if(financialAssetCategory == null) {
				throw new IllegalArgumentException("The_financialAssetCategory_is_not_exist");
			}
			AssociatedFinancialAssetCategory financialAsset = new AssociatedFinancialAssetCategory();
			financialAsset.setFinancialAssetCategoryId(financialAssetCategory.getFinancialAssetCategoryId());
			financialAsset.setFinancialAssetCategoryName(financialAssetCategory.getFinancialAssetCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addFinancialAssetCategory(financialAsset);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeFinancialAssetCategory(String projectId, String financialAssetCategoryId) {
		MessageDto message = new MessageDto();
		try {
			FinancialAssetCategoryDto financialAssetCategory = financialAssetCategoryService.findFinancialAssetCategoryOfId(financialAssetCategoryId);
			if(financialAssetCategory == null) {
				throw new IllegalArgumentException("The_financialAssetCategory_is_not_exist");
			}
			AssociatedFinancialAssetCategory financialAsset = new AssociatedFinancialAssetCategory();
			financialAsset.setFinancialAssetCategoryId(financialAssetCategory.getFinancialAssetCategoryId());
			financialAsset.setFinancialAssetCategoryName(financialAssetCategory.getFinancialAssetCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removeFinancialAssetCategory(financialAsset);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addProductCategory(String projectId, String productCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ProductCategoryDto productCategory = companyService.findProductCategoryById(productCategoryId);
			if(productCategory == null) {
				throw new IllegalArgumentException("The_productCategory_is_not_exist");
			}
			AssociatedProductCategory product = new AssociatedProductCategory();
			product.setProductCategoryId(productCategory.getProductCategoryId());
			product.setProductCategoryName(productCategory.getProductCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.addProductCategory(product);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeProductCategory(String projectId, String productCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ProductCategoryDto productCategory = companyService.findProductCategoryById(productCategoryId);
			if(productCategory == null) {
				throw new IllegalArgumentException("The_productCategory_is_not_exist");
			}
			AssociatedProductCategory product = new AssociatedProductCategory();
			product.setProductCategoryId(productCategory.getProductCategoryId());
			product.setProductCategoryName(productCategory.getProductCategoryName());
			Project project = this.findProjectOfId(projectId);
			project.removeProductCategory(product);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addGoal(String projectId, Goal goal) {
		MessageDto message = new MessageDto();
		try {
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			if(goal == null) {
				throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
			}
			
			Project project = this.findProjectOfId(projectId);
			if(project == null) {
				throw new IllegalArgumentException("The_project_is_not_exist");
			}
			project.addGoal(goal);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto replaceGoal(String projectId, Goal previous, Goal post) {
		MessageDto message = new MessageDto();
		try {
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			if(previous == null || post == null) {
				throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
			}
			
			Project project = this.findProjectOfId(projectId);
			if(project == null) {
				throw new IllegalArgumentException("The_project_is_not_exist");
			}
			project.replaceGoal(previous, post);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	
	public MessageDto removeGoal(String projectId, Goal goal) {
		MessageDto message = new MessageDto();
		try {
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			if(goal == null) {
				throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
			}
			
			Project project = this.findProjectOfId(projectId);
			if(project == null) {
				throw new IllegalArgumentException("The_project_is_not_exist");
			}
			project.removeGoal(goal);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addAchievement(String projectId, Achievement achievement) {
		MessageDto message = new MessageDto();
		try {
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			if(achievement == null) {
				throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
			}
			
			Project project = this.findProjectOfId(projectId);
			if(project == null) {
				throw new IllegalArgumentException("The_project_is_not_exist");
			}
			project.addAchievement(achievement);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto replaceAchievement(String projectId, Achievement previous, Achievement post) {
		MessageDto message = new MessageDto();
		try {
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			if(previous == null || post == null) {
				throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
			}
			
			Project project = this.findProjectOfId(projectId);
			if(project == null) {
				throw new IllegalArgumentException("The_project_is_not_exist");
			}
			project.replaceAchievement(previous, post);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	
	public MessageDto removeAchievement(String projectId, Achievement achievement) {
		MessageDto message = new MessageDto();
		try {
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			if(achievement == null) {
				throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
			}
			
			Project project = this.findProjectOfId(projectId);
			if(project == null) {
				throw new IllegalArgumentException("The_project_is_not_exist");
			}
			project.removeAchievement(achievement);
			projectRegistry.registerProject(project);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	
	/*
	 * 割当可能なメンバーを探す
	 */
	List<Member> findAssignableMembersOfProject(String departmentId){
		return null;
	}
	
	/*
	 * メンバーを割り当てる
	 */
	MessageDto assignMemberToProject(String memberId, String projectId) {
		MessageDto message = new MessageDto();
		
		return message;
	}
	
	private Project convertProjectDto(ProjectDto projectDto) throws Exception{
		//事前条件の検証
		if(projectDto.getCompanyId() == null) {
			throw new Exception("The_companyId_may_not_be_set_to_null");
		}
		if(projectDto.getBusinessUnitId() == null) {
			throw new Exception("The businessUnitId may not be set to null.");
		}
		if(projectDto.getProjectName() == null) {
			throw new Exception("The project name may not be set to null.");
		}
		if(projectDto.getProjectName().isEmpty()) {
			throw new Exception("Must provide a project name.");
		}
		
		Project project = new Project();
		if(projectDto.getProjectId() != null) {
			project.setProjectId(new ProjectId(projectDto.getProjectId()));
		}
		project.setCompanyId(new CompanyId(projectDto.getCompanyId()));
		project.setBusinessUnitId(new BusinessUnitId(projectDto.getBusinessUnitId()));
		project.setProjectName(projectDto.getProjectName());
		project.setProjectDescription(projectDto.getProjectDescription());
		project.setVision(projectDto.getVision());
		project.setHypothesis(projectDto.getHypothesis());
		project.setResult(projectDto.getResult());
		project.setSlogan(projectDto.getSlogan());
		project.setUrl(projectDto.getUrl());
		project.setGoals(projectDto.getGoals());
		project.setAchievements(projectDto.getAchievements());
		project.setAssociatedCustomerCategories(projectDto.getAssociatedCustomerCategories());
		project.setAssociatedProductCategories(projectDto.getAssociatedProductCategories());
		project.setAssociatedMemberCategories(projectDto.getAssociatedMemberCategories());
		project.setAssociatedPartnerCategories(projectDto.getAssociatedPartnerCategories());
		project.setAssociatedPlaceCategories(projectDto.getAssociatedPlaceCategories());
		project.setAssociatedActionPlans(projectDto.getAssociatedActionPlans());
		project.setAssociatedFinancialAssetCategories(projectDto.getAssociatedFinancialAssetCategories());
		project.setAssociatedDataCategories(projectDto.getAssociatedDataCategories());
		project.setAssociatedApplicationCategories(projectDto.getAssociatedApplicationCategories());
		
		return project;
	}
	
	private ProjectDto convertProject(Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(project.getProjectId().getProjectId());
		projectDto.setCompanyId(project.getCompanyId().id());
		projectDto.setBusinessUnitId(project.getBusinessUnitId().businessUnitId());
		projectDto.setProjectName(project.getProjectName());
		projectDto.setProjectDescription(project.getProjectDescription());
		projectDto.setVision(project.getVision());
		projectDto.setHypothesis(project.getHypothesis());
		projectDto.setResult(project.getResult());
		projectDto.setSlogan(project.getSlogan());
		projectDto.setGoals(project.getGoals());
		projectDto.setAchievements(project.getAchievements());
		projectDto.setAssociatedCustomerCategories(project.getAssociatedCustomerCategories());
		projectDto.setAssociatedProductCategories(project.getAssociatedProductCategories());
		projectDto.setAssociatedMemberCategories(project.getAssociatedMemberCategories());
		projectDto.setAssociatedPartnerCategories(project.getAssociatedPartnerCategories());
		projectDto.setAssociatedPlaceCategories(project.getAssociatedPlaceCategories());
		projectDto.setAssociatedActionPlans(project.getAssociatedActionPlans());
		projectDto.setAssociatedFinancialAssetCategories(project.getAssociatedFinancialAssetCategories());
		projectDto.setAssociatedDataCategories(project.getAssociatedDataCategories());
		projectDto.setAssociatedApplicationCategories(project.getAssociatedApplicationCategories());
		
		return projectDto;
	}
	
	private List<ProjectDto> convertProjects(List<Project> projects){
		List<ProjectDto> projectDtos = new ArrayList<>();
		for(Project project : projects) {
			projectDtos.add(this.convertProject(project));
		}
		return projectDtos;
	}

}
