package com.culnou.mumu.company.domain.model.program;

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


import com.culnou.mumu.company.domain.model.project.BusinessUnitAdapter;

import com.culnou.mumu.company.domain.service.FindProgramIndicator;
import com.culnou.mumu.company.domain.service.IndicatorType;
import com.culnou.mumu.company.domain.service.ProgramChecker;

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
import com.culnou.mumu.compnay.controller.ProgramDto;




@Service
@Transactional
public class ProgramService {
	@Autowired
	private ProgramRegistry programRegistry;
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
	private FindProgramIndicator findProgramIndicator;
	@Autowired
	private ProgramChecker checker;
	
	
	public ProgramDto findProgramDtoOfId(String programId) throws Exception{
		return this.convertProgram(programRegistry.findProgramOfId(programId));
	}
	
	public Program findProgramOfId(String programId) throws Exception{
		return programRegistry.findProgramOfId(programId);
	}
	
	public List<Indicator> findProgramIndicators(String projectId, IndicatorType indicatorType) throws Exception{
		return findProgramIndicator.findIndicator(projectId, indicatorType);
	}
	
	public List<ProgramDto> findProgramsOfCustomerCategory(String customerCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfCustomerCategory(customerCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfProductCategory(String productCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfProductCategory(productCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfPartnerCategory(String partnerCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfPartnerCategory(partnerCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfPlaceCategory(String placeCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfPlaceCategory(placeCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfDataCategory(String dataCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfDataCategory(dataCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfApplicationCategory(String applicationCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfApplicationCategory(applicationCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfFinancialAssetCategory(String financialAssetCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfFinancialAssetCategory(financialAssetCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfMemberCategory(String memberCategoryId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfMemberCategory(memberCategoryId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfActionPlan(String actionPlanId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfActionPlan(actionPlanId);
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	/*
	 * 事業単位のプロジェクトを探す
	 */
	public List<ProgramDto> findProgramsOfBusinessUnit(String businessUnitId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			//事前条件の検証
			//BusinessUnitIdの検証
			BusinessUnit businessUnit = null;
			businessUnit = businessUnitAdapter.findBusinessUnitOfId(businessUnitId);
			if(businessUnit == null) {
				throw new Exception("The_business_unit_may_not_exist");
			}else {
				List<Program> programs = programRegistry.findProgramsOfBusinessUnit(new BusinessUnitId(businessUnitId));
				programDtos.addAll(this.convertPrograms(programs));
			}
		}catch(Exception ex) {
			throw new Exception("The_program_could_not_be_found");
		}
		return programDtos;
	}
	
	public List<ProgramDto> findProgramsOfCompany(String companyId) throws Exception{
		List<ProgramDto> programDtos = new ArrayList<>();
		try {
			List<Program> programs = programRegistry.findProgramsOfCompany(new CompanyId(companyId));
			programDtos.addAll(this.convertPrograms(programs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return programDtos;
	}
	
	
	/*
	 * プロジェクトを定義する
	 */
	public MessageDto defineProgram(ProgramDto programDto){
		
		MessageDto message = new MessageDto();
		try {
			//事前条件の検証
			//BusinessUnitIdの検証
			BusinessUnit businessUnit = null;
			businessUnit = businessUnitAdapter.findBusinessUnitOfId(programDto.getBusinessUnitId());
			if(businessUnit == null) {
				message.setErrorMessage("BusinessUnit may not be exit.");
			}else {
				Program program = this.convertProgramDto(programDto);
				program.setProgramId(programRegistry.nextIdentity());
				program.setCreatedAt(new Date());
				programRegistry.registerProgram(program);
				message.setReturnValue(program.getProgramId().getProgramId());
				message.setResult("OK");
			}
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateProgram(ProgramDto programDto){
		
		MessageDto message = new MessageDto();
		try {
			//事前条件の検証
			//BusinessUnitIdの検証
			BusinessUnit businessUnit = null;
			businessUnit = businessUnitAdapter.findBusinessUnitOfId(programDto.getBusinessUnitId());
			if(businessUnit == null) {
				message.setErrorMessage("BusinessUnit may not be exit.");
			}else {
				Program foundProgram = this.findProgramOfId(programDto.getProgramId());
				if(foundProgram == null) {
					message.setErrorMessage("porgram may not be exit.");
				}
				//編集・削除チェック。
				if(!checker.avarable(programDto.getProgramId()).equals("OK")) {
					throw new Exception(checker.avarable(programDto.getProgramId()));
				}
				Program program = this.convertProgramDto(programDto);
				program.setUpdatedAt(new Date());
					
				programRegistry.registerProgram(program);
				message.setReturnValue(program.getProgramId().getProgramId());
				message.setResult("OK");
			}
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeProgram(String  programId) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			//編集・削除チェック。
			if(!checker.avarable(programId).equals("OK")) {
				throw new Exception(checker.avarable(programId));
			}
			Program program = this.findProgramOfId(programId);
			programRegistry.removeProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addCustomerCategory(String programId, String customerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			CustomerCategoryDto customerCategory = companyService.findCustomerCategoryById(customerCategoryId);
			if(customerCategory == null) {
				throw new IllegalArgumentException("The_customerCategory_is_not_exist");
			}
			AssociatedCustomerCategory customer = new AssociatedCustomerCategory();
			customer.setCustomerCategoryId(customerCategory.getCustomerCategoryId());
			customer.setCustomerCategoryName(customerCategory.getCustomerCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addCustomerCategory(customer);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeCustomerCategory(String programId, String customerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			CustomerCategoryDto customerCategory = companyService.findCustomerCategoryById(customerCategoryId);
			if(customerCategory == null) {
				throw new IllegalArgumentException("The_customerCategory_is_not_exist");
			}
			AssociatedCustomerCategory customer = new AssociatedCustomerCategory();
			customer.setCustomerCategoryId(customerCategory.getCustomerCategoryId());
			customer.setCustomerCategoryName(customerCategory.getCustomerCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removeCustomerCategory(customer);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addActionPlan(String programId, String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			ActionPlanDto actionPlanDto = companyService.findActionPlanById(actionPlanId);
			if(actionPlanDto == null) {
				throw new IllegalArgumentException("The_actionPlan_is_not_exist");
			}
			AssociatedActionPlan actionPlan = new AssociatedActionPlan();
			actionPlan.setActionPlanId(actionPlanDto.getActionPlanId());
			actionPlan.setActionPlanName(actionPlanDto.getActionPlanName());
			Program program = this.findProgramOfId(programId);
			program.addActionPlan(actionPlan);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeActionPlan(String programId, String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			ActionPlanDto actionPlanDto = companyService.findActionPlanById(actionPlanId);
			if(actionPlanDto == null) {
				throw new IllegalArgumentException("The_actionPlan_is_not_exist");
			}
			AssociatedActionPlan actionPlan = new AssociatedActionPlan();
			actionPlan.setActionPlanId(actionPlanDto.getActionPlanId());
			actionPlan.setActionPlanName(actionPlanDto.getActionPlanName());
			Program program = this.findProgramOfId(programId);
			program.removeActionPlan(actionPlan);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addMemberCategory(String programId, String memberCategoryId) {
		MessageDto message = new MessageDto();
		try {
			MemberCategoryDto memberCategory = memberCategoryService.findMemberCategoryOfId(memberCategoryId);
			if(memberCategory == null) {
				throw new IllegalArgumentException("The_memberCategory_is_not_exist");
			}
			AssociatedMemberCategory member = new AssociatedMemberCategory();
			member.setMemberCategoryId(memberCategory.getMemberCategoryId());
			member.setMemberCategoryName(memberCategory.getMemberCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addMemberCategory(member);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeMemberCategory(String programId, String memberCategoryId) {
		MessageDto message = new MessageDto();
		try {
			MemberCategoryDto memberCategory = memberCategoryService.findMemberCategoryOfId(memberCategoryId);
			if(memberCategory == null) {
				throw new IllegalArgumentException("The_memberCategory_is_not_exist");
			}
			AssociatedMemberCategory member = new AssociatedMemberCategory();
			member.setMemberCategoryId(memberCategory.getMemberCategoryId());
			member.setMemberCategoryName(memberCategory.getMemberCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removeMemberCategory(member);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addPartnerCategory(String programId, String partnerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PartnerCategoryDto partnerCategory = partnerCategoryService.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new IllegalArgumentException("The_partnerCategory_is_not_exist");
			}
			AssociatedPartnerCategory partner = new AssociatedPartnerCategory();
			partner.setPartnerCategoryId(partnerCategory.getPartnerCategoryId());
			partner.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addPartnerCategory(partner);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removePartnerCategory(String programId, String partnerCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PartnerCategoryDto partnerCategory = partnerCategoryService.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new IllegalArgumentException("The_partnerCategory_is_not_exist");
			}
			AssociatedPartnerCategory partner = new AssociatedPartnerCategory();
			partner.setPartnerCategoryId(partnerCategory.getPartnerCategoryId());
			partner.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removePartnerCategory(partner);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addPlaceCategory(String programId, String placeCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PlaceCategoryDto placeCategory = placeCategoryService.findPlaceCategoryOfId(placeCategoryId);
			if(placeCategory == null) {
				throw new IllegalArgumentException("The_placeCategory_is_not_exist");
			}
			AssociatedPlaceCategory place = new AssociatedPlaceCategory();
			place.setPlaceCategoryId(placeCategory.getPlaceCategoryId());
			place.setPlaceCategoryName(placeCategory.getPlaceCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addPlaceCategory(place);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removePlaceCategory(String programId, String placeCategoryId) {
		MessageDto message = new MessageDto();
		try {
			PlaceCategoryDto placeCategory = placeCategoryService.findPlaceCategoryOfId(placeCategoryId);
			if(placeCategory == null) {
				throw new IllegalArgumentException("The_placeCategory_is_not_exist");
			}
			AssociatedPlaceCategory place = new AssociatedPlaceCategory();
			place.setPlaceCategoryId(placeCategory.getPlaceCategoryId());
			place.setPlaceCategoryName(placeCategory.getPlaceCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removePlaceCategory(place);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addDataCategory(String programId, String dataCategoryId) {
		MessageDto message = new MessageDto();
		try {
			DataCategoryDto dataCategory = dataCategoryService.findDataCategoryOfId(dataCategoryId);
			if(dataCategory == null) {
				throw new IllegalArgumentException("The_dataCategory_is_not_exist");
			}
			AssociatedDataCategory data = new AssociatedDataCategory();
			data.setDataCategoryId(dataCategory.getDataCategoryId());
			data.setDataCategoryName(dataCategory.getDataCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addDataCategory(data);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeDataCategory(String programId, String dataCategoryId) {
		MessageDto message = new MessageDto();
		try {
			DataCategoryDto dataCategory = dataCategoryService.findDataCategoryOfId(dataCategoryId);
			if(dataCategory == null) {
				throw new IllegalArgumentException("The_dataCategory_is_not_exist");
			}
			AssociatedDataCategory data = new AssociatedDataCategory();
			data.setDataCategoryId(dataCategory.getDataCategoryId());
			data.setDataCategoryName(dataCategory.getDataCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removeDataCategory(data);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addApplicationCategory(String programId, String applicationCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ApplicationCategoryDto applicationCategory = applicationCategoryService.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new IllegalArgumentException("The_applicationCategory_is_not_exist");
			}
			AssociatedApplicationCategory application = new AssociatedApplicationCategory();
			application.setApplicationCategoryId(applicationCategory.getApplicationCategoryId());
			application.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addApplicationCategory(application);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeApplicationCategory(String programId, String applicationCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ApplicationCategoryDto applicationCategory = applicationCategoryService.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new IllegalArgumentException("The_applicationCategory_is_not_exist");
			}
			AssociatedApplicationCategory application = new AssociatedApplicationCategory();
			application.setApplicationCategoryId(applicationCategory.getApplicationCategoryId());
			application.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removeApplicationCategory(application);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addFinancialAssetCategory(String programId, String financialAssetCategoryId) {
		MessageDto message = new MessageDto();
		try {
			FinancialAssetCategoryDto financialAssetCategory = financialAssetCategoryService.findFinancialAssetCategoryOfId(financialAssetCategoryId);
			if(financialAssetCategory == null) {
				throw new IllegalArgumentException("The_financialAssetCategory_is_not_exist");
			}
			AssociatedFinancialAssetCategory financialAsset = new AssociatedFinancialAssetCategory();
			financialAsset.setFinancialAssetCategoryId(financialAssetCategory.getFinancialAssetCategoryId());
			financialAsset.setFinancialAssetCategoryName(financialAssetCategory.getFinancialAssetCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addFinancialAssetCategory(financialAsset);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeFinancialAssetCategory(String programId, String financialAssetCategoryId) {
		MessageDto message = new MessageDto();
		try {
			FinancialAssetCategoryDto financialAssetCategory = financialAssetCategoryService.findFinancialAssetCategoryOfId(financialAssetCategoryId);
			if(financialAssetCategory == null) {
				throw new IllegalArgumentException("The_financialAssetCategory_is_not_exist");
			}
			AssociatedFinancialAssetCategory financialAsset = new AssociatedFinancialAssetCategory();
			financialAsset.setFinancialAssetCategoryId(financialAssetCategory.getFinancialAssetCategoryId());
			financialAsset.setFinancialAssetCategoryName(financialAssetCategory.getFinancialAssetCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removeFinancialAssetCategory(financialAsset);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addProductCategory(String programId, String productCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ProductCategoryDto productCategory = companyService.findProductCategoryById(productCategoryId);
			if(productCategory == null) {
				throw new IllegalArgumentException("The_productCategory_is_not_exist");
			}
			AssociatedProductCategory product = new AssociatedProductCategory();
			product.setProductCategoryId(productCategory.getProductCategoryId());
			product.setProductCategoryName(productCategory.getProductCategoryName());
			Program program = this.findProgramOfId(programId);
			program.addProductCategory(product);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto removeProductCategory(String programId, String productCategoryId) {
		MessageDto message = new MessageDto();
		try {
			ProductCategoryDto productCategory = companyService.findProductCategoryById(productCategoryId);
			if(productCategory == null) {
				throw new IllegalArgumentException("The_productCategory_is_not_exist");
			}
			AssociatedProductCategory product = new AssociatedProductCategory();
			product.setProductCategoryId(productCategory.getProductCategoryId());
			product.setProductCategoryName(productCategory.getProductCategoryName());
			Program program = this.findProgramOfId(programId);
			program.removeProductCategory(product);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addGoal(String programId, Goal goal) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			if(goal == null) {
				throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
			}
			
			Program program = this.findProgramOfId(programId);
			if(program == null) {
				throw new IllegalArgumentException("The_program_is_not_exist");
			}
			program.addGoal(goal);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto replaceGoal(String programId, Goal previous, Goal post) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			if(previous == null || post == null) {
				throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
			}
			
			Program program = this.findProgramOfId(programId);
			if(program == null) {
				throw new IllegalArgumentException("The_program_is_not_exist");
			}
			program.replaceGoal(previous, post);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	
	public MessageDto removeGoal(String programId, Goal goal) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			if(goal == null) {
				throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
			}
			
			Program program = this.findProgramOfId(programId);
			if(program == null) {
				throw new IllegalArgumentException("The_program_is_not_exist");
			}
			program.removeGoal(goal);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto addAchievement(String programId, Achievement achievement) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			if(achievement == null) {
				throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
			}
			
			Program program = this.findProgramOfId(programId);
			if(program == null) {
				throw new IllegalArgumentException("The_program_is_not_exist");
			}
			program.addAchievement(achievement);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto replaceAchievement(String programId, Achievement previous, Achievement post) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			if(previous == null || post == null) {
				throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
			}
			
			Program program = this.findProgramOfId(programId);
			if(program == null) {
				throw new IllegalArgumentException("The_program_is_not_exist");
			}
			program.replaceAchievement(previous, post);
			programRegistry.registerProgram(program);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	
	public MessageDto removeAchievement(String programId, Achievement achievement) {
		MessageDto message = new MessageDto();
		try {
			if(programId == null || programId.isEmpty()) {
				throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
			}
			if(achievement == null) {
				throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
			}
			
			Program program = this.findProgramOfId(programId);
			if(program == null) {
				throw new IllegalArgumentException("The_program_is_not_exist");
			}
			program.removeAchievement(achievement);
			programRegistry.registerProgram(program);
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
	List<Member> findAssignableMembersOfProgram(String departmentId){
		return null;
	}
	
	/*
	 * メンバーを割り当てる
	 */
	MessageDto assignMemberToProgram(String memberId, String programId) {
		MessageDto message = new MessageDto();
		
		return message;
	}
	
	private Program convertProgramDto(ProgramDto programDto) throws Exception{
		//事前条件の検証
		if(programDto.getCompanyId() == null) {
			throw new Exception("The_companyId_may_not_be_set_to_null");
		}
		if(programDto.getBusinessUnitId() == null) {
			throw new Exception("The businessUnitId may not be set to null.");
		}
		if(programDto.getProgramName() == null) {
			throw new Exception("The program name may not be set to null.");
		}
		if(programDto.getProgramName().isEmpty()) {
			throw new Exception("Must provide a program name.");
		}
		
		Program program = new Program();
		if(programDto.getProgramId() != null) {
			program.setProgramId(new ProgramId(programDto.getProgramId()));
		}
		program.setCompanyId(new CompanyId(programDto.getCompanyId()));
		program.setBusinessUnitId(new BusinessUnitId(programDto.getBusinessUnitId()));
		program.setProgramName(programDto.getProgramName());
		program.setProgramDescription(programDto.getProgramDescription());
		program.setVision(programDto.getVision());
		program.setHypothesis(programDto.getHypothesis());
		program.setResult(programDto.getResult());
		program.setSlogan(programDto.getSlogan());
		program.setUrl(programDto.getUrl());
		program.setGoals(programDto.getGoals());
		program.setAchievements(programDto.getAchievements());
		program.setAssociatedCustomerCategories(programDto.getAssociatedCustomerCategories());
		program.setAssociatedProductCategories(programDto.getAssociatedProductCategories());
		program.setAssociatedMemberCategories(programDto.getAssociatedMemberCategories());
		program.setAssociatedPartnerCategories(programDto.getAssociatedPartnerCategories());
		program.setAssociatedPlaceCategories(programDto.getAssociatedPlaceCategories());
		program.setAssociatedActionPlans(programDto.getAssociatedActionPlans());
		program.setAssociatedFinancialAssetCategories(programDto.getAssociatedFinancialAssetCategories());
		program.setAssociatedDataCategories(programDto.getAssociatedDataCategories());
		program.setAssociatedApplicationCategories(programDto.getAssociatedApplicationCategories());
		return program;
	}
	
	private ProgramDto convertProgram(Program program) {
		ProgramDto programDto = new ProgramDto();
		programDto.setProgramId(program.getProgramId().getProgramId());
		programDto.setCompanyId(program.getCompanyId().id());
		programDto.setBusinessUnitId(program.getBusinessUnitId().businessUnitId());
		programDto.setProgramName(program.getProgramName());
		programDto.setProgramDescription(program.getProgramDescription());
		programDto.setVision(program.getVision());
		programDto.setHypothesis(program.getHypothesis());
		programDto.setResult(program.getResult());
		programDto.setSlogan(program.getSlogan());
		programDto.setGoals(program.getGoals());
		programDto.setAchievements(program.getAchievements());
		programDto.setAssociatedCustomerCategories(program.getAssociatedCustomerCategories());
		programDto.setAssociatedProductCategories(program.getAssociatedProductCategories());
		programDto.setAssociatedMemberCategories(program.getAssociatedMemberCategories());
		programDto.setAssociatedPartnerCategories(program.getAssociatedPartnerCategories());
		programDto.setAssociatedPlaceCategories(program.getAssociatedPlaceCategories());
		programDto.setAssociatedActionPlans(program.getAssociatedActionPlans());
		programDto.setAssociatedFinancialAssetCategories(program.getAssociatedFinancialAssetCategories());
		programDto.setAssociatedDataCategories(program.getAssociatedDataCategories());
		programDto.setAssociatedApplicationCategories(program.getAssociatedApplicationCategories());
		
		return programDto;
	}
	
	private List<ProgramDto> convertPrograms(List<Program> programs){
		List<ProgramDto> programDtos = new ArrayList<>();
		for(Program program : programs) {
			programDtos.add(this.convertProgram(program));
		}
		return programDtos;
	}


}
