package com.culnou.mumu.compnay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.culnou.mumu.company.application.SignUpService;
import com.culnou.mumu.company.application.TaskApplicationService;
import com.culnou.mumu.company.application.ActionApplicationService;
import com.culnou.mumu.company.application.ApplicationApplicationService;
import com.culnou.mumu.company.application.ApplicationCategoryApplicationService;
import com.culnou.mumu.company.application.ApplicationFunctionApplicationService;
import com.culnou.mumu.company.application.BusinessProcessApplicationService;
import com.culnou.mumu.company.application.CompanyService;
import com.culnou.mumu.company.application.DataApplicationService;
import com.culnou.mumu.company.application.DataCategoryApplicationService;
import com.culnou.mumu.company.application.DepartmentApplicationService;
import com.culnou.mumu.company.application.FinancialAssetCategoryApplicationService;
import com.culnou.mumu.company.application.ItApplicationService;
import com.culnou.mumu.company.application.ItCategoryApplicationService;
import com.culnou.mumu.company.application.MemberCategoryApplicationService;
import com.culnou.mumu.company.application.MemberTypeApplicationService;
import com.culnou.mumu.company.application.PartnerCategoryApplicationService;
import com.culnou.mumu.company.application.PartnerFunctionApplicationService;
import com.culnou.mumu.company.application.PartnerTypeApplicationService;
import com.culnou.mumu.company.application.PlaceApplicationService;
import com.culnou.mumu.company.application.PlaceCategoryApplicationService;
import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessDomainService;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentService;
import com.culnou.mumu.company.domain.model.DepartmentType;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobType;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTypeId;

import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.activity.action_plan.ActionPlanDomainService;
import com.culnou.mumu.company.domain.model.activity.work.WorkService;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowService;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.application.function.ApplicationData;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskService;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeService;
import com.culnou.mumu.company.domain.model.application.type.DeleteApplicationTypeChecker;
import com.culnou.mumu.company.domain.model.business.domain.BusinessDomainDomainService;
import com.culnou.mumu.company.domain.model.business.unit.BusinessUnitDomainService;
import com.culnou.mumu.company.domain.model.common.Attribute;
import com.culnou.mumu.company.domain.model.common.IndicatorSelector;
import com.culnou.mumu.company.domain.model.customer.category.CustomerCategoryDomainService;
import com.culnou.mumu.company.domain.model.customer.type.CustomerTypeDomainService;
import com.culnou.mumu.company.domain.model.data.domain.DataDomainService;
import com.culnou.mumu.company.domain.model.data.item.DataItemService;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.model.data.type.DataTypeService;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetTypeService;
import com.culnou.mumu.company.domain.model.it.type.ItTypeService;
import com.culnou.mumu.company.domain.model.knowledge.awareness.AwarenessService;
import com.culnou.mumu.company.domain.model.knowledge.comment.CommentService;
import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationService;
import com.culnou.mumu.company.domain.model.knowledge.guideline.GuidelineService;
import com.culnou.mumu.company.domain.model.knowledge.idea.IdeaService;
import com.culnou.mumu.company.domain.model.member.MemberService;
import com.culnou.mumu.company.domain.model.organization.job.DeleteJobChecker;
import com.culnou.mumu.company.domain.model.organization.job.EditJobChecker;
import com.culnou.mumu.company.domain.model.organization.job.JobDomainService;
import com.culnou.mumu.company.domain.model.organization.job.task.AddTaskChecker;
import com.culnou.mumu.company.domain.model.organization.job.task.DeleteTaskChecker;
import com.culnou.mumu.company.domain.model.organization.job.task.EditTaskChecker;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.place.type.PlaceTypeService;
import com.culnou.mumu.company.domain.model.product.category.ProductCategoryDomainService;
import com.culnou.mumu.company.domain.model.product.function.ProductFunctionDomainService;
import com.culnou.mumu.company.domain.model.product.instance.AssociatedProductFunction;
import com.culnou.mumu.company.domain.model.product.instance.ProductService;
import com.culnou.mumu.company.domain.model.product.task.ProductTaskDomainService;
import com.culnou.mumu.company.domain.model.product.type.ProductTypeDomainService;
import com.culnou.mumu.company.domain.model.product.work.ProductWorkService;
import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.company.domain.model.service.ProductServiceService;
import com.culnou.mumu.company.domain.service.IndicatorType;









@RestController
public class CompanyRestController {
	
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	ActionPlanDomainService actionPlanService;
	
	@Autowired
	WorkflowService workflowService;
	
	@Autowired
	WorkService workService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductWorkService productWorkService;
	
	@Autowired
	ProductServiceService productServiceService;
	
	@Autowired
	AwarenessService awarenessService;
	
	@Autowired
	BusinessDomainDomainService businessDomainDomainService;
	
	@Autowired
	CustomerTypeDomainService customerTypeDomainService;
	
	@Autowired
	ProductTypeDomainService productTypeDomainService;
	
	@Autowired
	JobDomainService jobDomainService;
	
	@Autowired
	BusinessUnitDomainService businessUnitDomainService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	EvaluationService evaluationService;
	
	@Autowired
	ProductTaskDomainService productTaskDomainService;
	
	@Autowired
	ProductCategoryDomainService productCategoryDomainService;
	
	@Autowired
	ProductFunctionDomainService productFunctionDomainService;
	
	@Autowired
	FinancialAssetTypeService financialAssetTypeService;
	
	@Autowired
	PlaceTypeService placeTypeService;
	
	@Autowired
	DataTypeService dataTypeService;
	
	@Autowired
	ApplicationTypeService applicationTypeService;
	
	@Autowired
	ItTypeService itTypeService;
	
	@Autowired
	ApplicationTaskService applicationTaskService;
	
	@Autowired
	DataDomainService dataDomainService;
	
	@Autowired
	DataItemService dataItemService;
	
	@Autowired
	GuidelineService guidelineService;
	
	@Autowired
	IdeaService ideaService;
	
	@Autowired
	SignUpService signUpService;
	
	@Autowired
	MemberTypeApplicationService memberTypeService;
	
	@Autowired
	PartnerTypeApplicationService partnerTypeService;
	
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskJpaRepository;
	
	@Autowired
	AddTaskChecker taskChecker;
	
	@Autowired
	private EditTaskChecker editTaskChecker;
	
	@Autowired
	private DeleteTaskChecker deleteTaskChecker;
	
	@Autowired
	private EditJobChecker editJobChecker;
	
	@Autowired
	private DeleteJobChecker deleteJobChecker;
	
	@Autowired
	private TaskApplicationService taskService;
	
	@Autowired
	private BusinessProcessApplicationService businessProcessApplicationService;
	
	@Autowired
	private BusinessDomainService businessDomainService;
	
	@Autowired
	private DeleteApplicationTypeChecker deleteApplicationTypeChecker;
	
	@Autowired
	private CustomerCategoryDomainService customerCategoryDomainService;
	
	@Autowired
	private ApplicationCategoryApplicationService applicationCategoryApplicationService;
	
	@Autowired
	private MemberCategoryApplicationService memberCategoryApplicationService;
	
	@Autowired
	private DataCategoryApplicationService dataCategoryApplicationService;
	
	@Autowired
	private PartnerCategoryApplicationService partnerCategoryApplicationService;
	
	@Autowired
	private FinancialAssetCategoryApplicationService financialAssetCategoryApplicationService;
	
	@Autowired
	private ItCategoryApplicationService itCategoryApplicationService;
	
	@Autowired
	private ItApplicationService itApplicationService;
	
	@Autowired
	private ApplicationApplicationService applicationApplicationService;
	
	@Autowired
	private PlaceCategoryApplicationService placeCategoryApplicationService;
	
	@Autowired
	private DepartmentApplicationService departmentApplicationService;
	
	@Autowired
	private ActionApplicationService actionApplicationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ApplicationFunctionApplicationService applicationFunctionService;
	
	@Autowired
	private PartnerFunctionApplicationService partnerFunctionService;
	
	@Autowired
	private DataApplicationService dataApplicationService;
	
	@Autowired
	private PlaceApplicationService placeService;
	
	
	
	
	@PostMapping("/companies")
	@CrossOrigin
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) throws Exception{
		return ResponseEntity.ok().body((CompanyDto)this.companyService.addCompany(companyDto));
	}
	
	@PutMapping("/companies")
	@CrossOrigin
	public HttpStatus updateCompany(@RequestBody CompanyDto companyDto) throws Exception{
		this.companyService.updateCompany(companyDto);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/companies/{id}")
	@CrossOrigin
	public HttpStatus deleteCompany(@PathVariable String id) throws Exception{
		this.companyService.deleteCompany(id);
		return HttpStatus.OK;
	}
	
	@GetMapping("/companies/{id}")
	@CrossOrigin
	public ResponseEntity<CompanyDto> findCompanyById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((CompanyDto)this.companyService.findCompanyById(id));
	}
	
	/*
	 * 事業ドメイン
	 */
	/*
	@PostMapping("/businessDomains")
	@CrossOrigin
	public ResponseEntity<BusinessDomainDto> createBusinessDomain(@RequestBody BusinessDomainDto businessDomainDto) throws Exception{
		return ResponseEntity.ok().body((BusinessDomainDto)this.companyService.addBusinessDomain(businessDomainDto));
	}
	*/
	@PostMapping("/businessDomains")
	@CrossOrigin
	public ResponseEntity<MessageDto> createBusinessDomain(@RequestBody BusinessDomainDto businessDomainDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessDomainDomainService.defineBusinessDomain(businessDomainDto));
	}
	/*
	@PutMapping("/businessDomains")
	@CrossOrigin
	public HttpStatus updateBusinessDomain(@RequestBody BusinessDomainDto businessDomainDto) throws Exception{
		this.companyService.updateBusinessDomain(businessDomainDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/businessDomains")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateBusinessDomain(@RequestBody BusinessDomainDto businessDomainDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessDomainDomainService.updateBusinessDomain(businessDomainDto));
	}
	
	/*
	@DeleteMapping("/businessDomains/{id}")
	@CrossOrigin
	public HttpStatus deleteBusinessDomain(@PathVariable String id) throws Exception{
		this.companyService.deleteBusinessDomain(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/businessDomains/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteBusinessDomain(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessDomainDomainService.removeBusinessDomain(id));
	}
	
	@GetMapping("/businessDomains/{id}")
	@CrossOrigin
	public ResponseEntity<BusinessDomainDto> findBusinessDomainById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((BusinessDomainDto)this.companyService.findBusinessDomainById(id));
	}
	
	@GetMapping("/businessDomains/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessDomainDto>> findBusinessDomainByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessDomainDto>)this.companyService.findBusinessDomainsOfCompany(companyId));
	}
	
	@GetMapping("/enterpriseDomains/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<BusinessDomainDto> findEnterpriseDomain(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((BusinessDomainDto)this.companyService.findEnterpriseDomain(companyId));
		
	}
	
	
	@GetMapping("/businessDomains/customer_type/{customerTypeId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessDomainDto>> findBusinessDomainByCustomerTypeId(@PathVariable String customerTypeId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessDomainDto>)this.companyService.findBusinessDomainsOfCustomerType(customerTypeId));
	}
	
	@GetMapping("/businessDomains/product_type/{productTypeId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessDomainDto>> findBusinessDomainByProductTypeId(@PathVariable String productTypeId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessDomainDto>)this.companyService.findBusinessDomainsOfProductType(productTypeId));
	}
	
	@GetMapping("/businessDomains/used/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isBusinessDomainUsed(@PathVariable String businessDomainId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isBusinessDomainUsed(new BusinessDomainId(businessDomainId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/businessDomains/{businessDomainId}/indicator/name/{indicatorName}/unit/{unit}/description/{description}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isBusinessIndicatorkUsed(@PathVariable String businessDomainId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isBusinessIndicatorUsed(new BusinessDomainId(businessDomainId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	
	
	/*
	 * 事業単位
	 */
	/*
	@PostMapping("/businessUnits")
	@CrossOrigin
	public ResponseEntity<BusinessUnitDto> createBusinessUnit(@RequestBody BusinessUnitDto businessUnitDto) throws Exception{
		return ResponseEntity.ok().body((BusinessUnitDto)this.companyService.addBusinessUnit(businessUnitDto));
	}
	*/
	@PostMapping("/businessUnits")
	@CrossOrigin
	public ResponseEntity<MessageDto> createBusinessUnit(@RequestBody BusinessUnitDto businessUnitDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessUnitDomainService.defineBusinessUnit(businessUnitDto));
	}
	/*
	@PutMapping("/businessUnits")
	@CrossOrigin
	public HttpStatus updateBusinessUnit(@RequestBody BusinessUnitDto businessUnitDto) throws Exception{
		this.companyService.updateBusinessUnit(businessUnitDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/businessUnits")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateBusinessUnit(@RequestBody BusinessUnitDto businessUnitDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessUnitDomainService.updateBusinessUnit(businessUnitDto));
	}
	/*
	@DeleteMapping("/businessUnits/{id}")
	@CrossOrigin
	public HttpStatus deleteBusinessUnit(@PathVariable String id) throws Exception{
		this.companyService.deleteBusinessUnit(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/businessUnits/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteBusinessUnit(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessUnitDomainService.removeBusinessUnit(id));
	}
	
	@GetMapping("/businessUnits/{id}")
	@CrossOrigin
	public ResponseEntity<BusinessUnitDto> findBusinessUnitById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((BusinessUnitDto)this.companyService.findBusinessUnitById(id));
	}
	
	@GetMapping("/businessUnits/business_domain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessUnitDto>> findBusinessUnitByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessUnitDto>)this.companyService.findBusinessUnitsOfDomain(businessDomainId));
	}
	
	@GetMapping("/businessUnits/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessUnitDto>> findBusinessUnitByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessUnitDto>)this.companyService.findBusinessUnitsOfCompany(companyId));
	}
	
	@GetMapping("/businessUnits/customer_category/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessUnitDto>> findBusinessUnitByCustomerCategoryId(@PathVariable String customerCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessUnitDto>)this.companyService.findBusinessUnitsOfCustomerCategory(customerCategoryId));
	}
	
	@GetMapping("/businessUnits/product_category/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessUnitDto>> findBusinessUnitByProductCategoryId(@PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessUnitDto>)this.companyService.findBusinessUnitsOfProductCategory(productCategoryId));
	}
	
	@GetMapping("/businessUnits/used/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isBusinessUnitUsed(@PathVariable String businessUnitId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isBusinessUnitUsed(new BusinessUnitId(businessUnitId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/businessUnits/{businessUnitId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isBusinessGoalUsed(@PathVariable String businessUnitId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isBusinessGoalUsed(new BusinessUnitId(businessUnitId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	/*
	 * 顧客タイプ
	 */
	/*
	@PostMapping("/customerTypes")
	@CrossOrigin
	public ResponseEntity<CustomerTypeDto> createCustomerType(@RequestBody CustomerTypeDto customerTypeDto) throws Exception{
		return ResponseEntity.ok().body((CustomerTypeDto)this.companyService.addCustomerType(customerTypeDto));
	}
	*/
	
	@PostMapping("/customerTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createCustomerType(@RequestBody CustomerTypeDto customerTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.customerTypeDomainService.defineCustomerType(customerTypeDto));
	}
	
	/*
	@PutMapping("/customerTypes")
	@CrossOrigin
	public HttpStatus updateCustomerType(@RequestBody CustomerTypeDto customerTypeDto) throws Exception{		
		this.companyService.updateCustomerType(customerTypeDto);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/customerTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateCustomerType(@RequestBody CustomerTypeDto customerTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.customerTypeDomainService.updateCustomerType(customerTypeDto));
	}
	
	/*
	@DeleteMapping("/customerTypes/{id}")
	@CrossOrigin
	public HttpStatus deleteCustomerType(@PathVariable String id) throws Exception{
		this.companyService.deleteCustomerType(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/customerTypes/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteCustomerType(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.customerTypeDomainService.removeCustomerType(id));
	}
	
	
	@GetMapping("/customerTypes/{id}")
	@CrossOrigin
	public ResponseEntity<CustomerTypeDto> findCustomerTypeById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((CustomerTypeDto)this.companyService.findCustomerTypeById(id));
	}
	
	@GetMapping("/customerTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<CustomerTypeDto>> findCustomerTypesByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<CustomerTypeDto>)this.companyService.findCustomerTypesOfCompany(companyId));
	}
	
	@GetMapping("/customerTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<CustomerTypeDto>> findCustomerTypesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<CustomerTypeDto>)this.companyService.findCustomerTypesOfBusinessDomain(businessDomainId));
	}
	
	
	@GetMapping("/customerTypes/used/{customerTypeId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isCustomerTypeUsed(@PathVariable String customerTypeId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isCustomerTypeUsed(new CustomerTypeId(customerTypeId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/customerTypes/{customerTypeId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isCustomerIndicatorkUsed(@PathVariable String customerTypeId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isCustomerIndicatorUsed(new CustomerTypeId(customerTypeId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	/*
	 * メンバータイプ
	 */
	@PostMapping("/memberTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> createMemberType(@PathVariable String businessDomainId, @RequestBody MemberTypeInfoDto memberTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.addMemberTypeInfo(businessDomainId, memberTypeDto));
	}
	
	@PutMapping("/memberTypes/memberType/{memberTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateMemberType(@PathVariable String memberTypeId, @RequestBody MemberTypeInfoDto memberTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.modifyMemberTypeInfo(memberTypeId, memberTypeDto));
	}
	
	@GetMapping("/memberTypes/memberType/{memberTypeId}")
	@CrossOrigin
	public ResponseEntity<MemberTypeInfoDto> findMemberTypeOfId(@PathVariable String memberTypeId) throws Exception{
		return ResponseEntity.ok().body((MemberTypeInfoDto)this.memberTypeService.findMemberTypeOfId(memberTypeId));
	}
	
	@GetMapping("/memberTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<MemberTypeInfoDto>> findMemberTypesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<MemberTypeInfoDto>)this.memberTypeService.confirmyMemberTypeList(businessDomainId));
	}
	
	@DeleteMapping("/memberTypes/memberType/{memberTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteMemberType(@PathVariable String memberTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.removeMemberTypeInfo(memberTypeId));
	}
	
	@PostMapping("/memberTypes/memberType/{memberTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> createMemberTypeIndicator(@PathVariable String memberTypeId, @RequestBody Indicator indicator) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.defineMemberTypeIndicator(memberTypeId, indicator));
	}
	
	@PutMapping("/memberTypes/memberType/{memberTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateMemberTypeIndicator(@PathVariable String memberTypeId, @RequestBody IndicatorsDto indicators) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.modifyMemberTypeIndicatorInfo(memberTypeId, indicators));
	}
	
	@DeleteMapping("/memberTypes/memberType/{memberTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteMemberTypeIndicator(@PathVariable String memberTypeId, @RequestBody Indicator indicator) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.removeMemberTypeIndicatorInfo(memberTypeId, indicator));
	}
	
	@PostMapping("/memberTypes/memberType/{memberTypeId}/attribute")
	@CrossOrigin
	public ResponseEntity<MessageDto> createMemberTypeAttribute(@PathVariable String memberTypeId, @RequestBody Attribute attribute) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.defineMemberTypeAttribute(memberTypeId, attribute));
	}
	
	@PutMapping("/memberTypes/memberType/{memberTypeId}/attribute")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateMemberTypeAttribute(@PathVariable String memberTypeId, @RequestBody AttributesDto attributes) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.modifyMemberTypeAttribute(memberTypeId, attributes));
	}
	
	@DeleteMapping("/memberTypes/memberType/{memberTypeId}/attribute")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteMemberTypeAttribute(@PathVariable String memberTypeId, @RequestBody Attribute attribute) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberTypeService.removeMemberTypeAttribute(memberTypeId, attribute));
	}
	
	@GetMapping("/memberTypes/memberType/{memberTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<List<Indicator>> findIndicatorsOfMemberType(@PathVariable String memberTypeId){
		return ResponseEntity.ok().body((List<Indicator>)this.memberTypeService.findIndicatorsOfMemberType(memberTypeId));
	}
	
	@GetMapping("/memberTypes/memberType/{memberTypeId}/attribute")
	@CrossOrigin
	public ResponseEntity<List<Attribute>> findAttributesOfMemberType(@PathVariable String memberTypeId){
		return ResponseEntity.ok().body((List<Attribute>)this.memberTypeService.findAttributesOfMemberType(memberTypeId));
	}
	
	/*
	 * パートナータイプ
	 */
	@PostMapping("/partnerTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPartnerType(@PathVariable String businessDomainId, @RequestBody PartnerTypeDto partnerTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerTypeService.addPartnerType(businessDomainId, partnerTypeDto));
	}
	
	@PutMapping("/partnerTypes/partnerType/{partnerTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePartnerType(@PathVariable String partnerTypeId, @RequestBody PartnerTypeDto partnerTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerTypeService.modifyPartnerType(partnerTypeId, partnerTypeDto));
	}
	
	@GetMapping("/partnerTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerTypeDto>> findPartnerTypesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerTypeDto>)this.partnerTypeService.confirmyPartnerTypeList(businessDomainId));
	}
	
	@DeleteMapping("/partnerTypes/partnerType/{partnerTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePartnerType(@PathVariable String partnerTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerTypeService.removePartnerTypeInfo(partnerTypeId));
	}
	
	@PostMapping("/partnerTypes/partnerType/{partnerTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPartnerTypeIndicator(@PathVariable String partnerTypeId, @RequestBody Indicator indicator) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerTypeService.definePartnerTypeIndicator(partnerTypeId, indicator));
	}
	
	@PutMapping("/partnerTypes/partnerType/{partnerTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePartnerTypeIndicator(@PathVariable String partnerTypeId, @RequestBody IndicatorsDto indicators) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerTypeService.modifyPartnerTypeIndicatorInfo(partnerTypeId, indicators));
	}
	
	@DeleteMapping("/partnerTypes/partnerType/{partnerTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePartnerTypeIndicator(@PathVariable String partnerTypeId, @RequestBody Indicator indicator) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerTypeService.removePartnerTypeIndicatorInfo(partnerTypeId, indicator));
	}
	
	@GetMapping("/partnerTypes/partnerType/{partnerTypeId}/indicator")
	@CrossOrigin
	public ResponseEntity<List<Indicator>> findIndicatorsOfPartnerType(@PathVariable String partnerTypeId){
		return ResponseEntity.ok().body((List<Indicator>)this.partnerTypeService.findIndicatorsOfPartnerType(partnerTypeId));
	}

	
	
	/*
	 * 製品タイプ
	 */
	/*
	@PostMapping("/productTypes")
	@CrossOrigin
	public ResponseEntity<ProductTypeDto> createProductType(@RequestBody ProductTypeDto productTypeDto) throws Exception{
		return ResponseEntity.ok().body((ProductTypeDto)this.companyService.addProductType(productTypeDto));
	}
	*/
	@PostMapping("/productTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createProductType(@RequestBody ProductTypeDto productTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productTypeDomainService.defineProductType(productTypeDto));
	}
	
	/*
	@PutMapping("/productTypes")
	@CrossOrigin
	public HttpStatus updateProductType(@RequestBody ProductTypeDto productTypeDto) throws Exception{
		this.companyService.updateProductType(productTypeDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/productTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProductType(@RequestBody ProductTypeDto productTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productTypeDomainService.updateProductType(productTypeDto));
	}
	/*
	@DeleteMapping("/productTypes/{id}")
	@CrossOrigin
	public HttpStatus deleteProductType(@PathVariable String id) throws Exception{
		this.companyService.deleteProductType(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/productTypes/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteProductType(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productTypeDomainService.removeProductType(id));
	}
	
	@GetMapping("/productTypes/{id}")
	@CrossOrigin
	public ResponseEntity<ProductTypeDto> findProductTypeById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ProductTypeDto)this.companyService.findProductTypeById(id));
	}
	
	@GetMapping("/productTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ProductTypeDto>> findProductTypesByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ProductTypeDto>)this.companyService.findProductTypesOfCompany(companyId));
	}
	
	@GetMapping("/productTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<ProductTypeDto>> findProductTypesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<ProductTypeDto>)this.companyService.findProductTypesOfBusinessDomain(businessDomainId));
	}
	
	//製品タイプで使用されている場合、顧客タイプを編集、削除できないようにするために必要。
	@GetMapping("/productTypes/customer_type/{customerTypeId}")
	@CrossOrigin
	public ResponseEntity<List<ProductTypeDto>> findProductTypesByCustomerTypeId(@PathVariable String customerTypeId) throws Exception{
		return ResponseEntity.ok().body((List<ProductTypeDto>)this.companyService.findProductTypesOfCustomerType(customerTypeId));
	}
	@GetMapping("/productTypes/used/{productTypeId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isProductTypeUsed(@PathVariable String productTypeId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isProductTypeUsed(new ProductTypeId(productTypeId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/productTasks/used/{productTaskId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isProductTaskUsed(@PathVariable String productTaskId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isProductTaskUsed(new ProductTaskId(productTaskId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/productTypes/{productTypeId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isProductIndicatorkUsed(@PathVariable String productTypeId, @PathVariable String indicatorName,  @PathVariable String unit,@PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isProductIndicatorUsed(new ProductTypeId(productTypeId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	
	/*
	 * 顧客カテゴリ
	 */
	/*
	@PostMapping("/customerCategories")
	@CrossOrigin
	public ResponseEntity<CustomerCategoryDto> createCustomerCategory(@RequestBody CustomerCategoryDto customerCategoryDto) throws Exception{
		return ResponseEntity.ok().body((CustomerCategoryDto)this.companyService.addCustomerCategory(customerCategoryDto));
	}
	*/
	
	@PostMapping("/customerCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createCustomerCategory(@RequestBody CustomerCategoryDto customerCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.customerCategoryDomainService.defineCustomerCategory(customerCategoryDto));
	}
	/*
	@PutMapping("/customerCategories")
	@CrossOrigin
	public HttpStatus updateCustomerCategory(@RequestBody CustomerCategoryDto customerCategoryDto) throws Exception{
		this.companyService.updateCustomerCategory(customerCategoryDto);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/customerCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateCustomerCategory(@RequestBody CustomerCategoryDto customerCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.customerCategoryDomainService.updateCustomerCategory(customerCategoryDto));
	}
	/*
	@DeleteMapping("/customerCategories/{id}")
	@CrossOrigin
	public HttpStatus deleteCustomerCategory(@PathVariable String id) throws Exception{
		this.companyService.deleteCustomerCategory(id);
		return HttpStatus.OK;
	}
	*/
	
	@DeleteMapping("/customerCategories/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteCustomerCategory(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.customerCategoryDomainService.deleteCustomerCategory(id));
	}
	
	@GetMapping("/customerCategories/{id}")
	@CrossOrigin
	public ResponseEntity<CustomerCategoryDto> findCustomerCategoryById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((CustomerCategoryDto)this.companyService.findCustomerCategoryById(id));
	}
	
	@GetMapping("/customerCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<CustomerCategoryDto>> findCustomerCategoriesByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<CustomerCategoryDto>)this.companyService.findCustomerCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/customerCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<CustomerCategoryDto>> findCustomerCategoriesByBusinessUnitId(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<CustomerCategoryDto>)this.companyService.findCustomerCategoriesOfBusinessUnit(businessUnitId));
	}
	
	
	@GetMapping("/customerCategories/customer_type/{customerTypeId}")
	@CrossOrigin
	public ResponseEntity<List<CustomerCategoryDto>> findCustomerCategoriesByCustomerTypeId(@PathVariable String customerTypeId) throws Exception{
		return ResponseEntity.ok().body((List<CustomerCategoryDto>)this.companyService.findCustomerCategoriesOfCustomerType(customerTypeId));
	}
	
	@GetMapping("/customerCategories/business_domain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<CustomerCategoryDto>> findCustomerCategoriesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<CustomerCategoryDto>)this.companyService.findCustomerCategoriesByBusinessDomain(businessDomainId));
	}
	
	
	@GetMapping("/customerCategories/used/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isCustomerCategoryUsed(@PathVariable String customerCategoryId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isCustomerCategoryUsed(new CustomerCategoryId(customerCategoryId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/customerCategories/{customerCategoryId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isCustomerGoalUsed(@PathVariable String customerCategoryId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isCustomerGoalUsed(new CustomerCategoryId(customerCategoryId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}

	
	/*
	 * 製品カテゴリ
	 */
	/*
	@PostMapping("/productCategories")
	@CrossOrigin
	public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) throws Exception{
		return ResponseEntity.ok().body((ProductCategoryDto)this.companyService.addProductCategory(productCategoryDto));
	}
	*/
	@PostMapping("/productCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productCategoryDomainService.defineProductCategory(productCategoryDto));
	}
	/*
	@PutMapping("/productCategories")
	@CrossOrigin
	public HttpStatus updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) throws Exception{
		this.companyService.updateProductCategory(productCategoryDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/productCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productCategoryDomainService.updateProductCategory(productCategoryDto));
	}
	/*
	@DeleteMapping("/productCategories/{id}")
	@CrossOrigin
	public HttpStatus deleteProductCategory(@PathVariable String id) throws Exception{
		this.companyService.deleteProductCategory(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/productCategories/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteProductCategory(@PathVariable String id) throws Exception{
		
		return ResponseEntity.ok().body((MessageDto)this.productCategoryDomainService.deleteProductCategory(id));
	}
	
	@GetMapping("/productCategories/{id}")
	@CrossOrigin
	public ResponseEntity<ProductCategoryDto> findProductCategoryById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ProductCategoryDto)this.companyService.findProductCategoryById(id));
	}
	
	@GetMapping("/productCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ProductCategoryDto>> findProductCategoriesByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ProductCategoryDto>)this.companyService.findProductCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/productCategories/businessUnit/{businessUnitId}/productClass/{productClass}")
	@CrossOrigin
	public ResponseEntity<List<ProductCategoryDto>> findProductCategoriesOfProductClass(@PathVariable String businessUnitId, @PathVariable ProductClass productClass) throws Exception{
		return ResponseEntity.ok().body((List<ProductCategoryDto>)this.companyService.findProductCategoriesOfProductClass(businessUnitId, productClass));
	}
	
	@GetMapping("/productCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ProductCategoryDto>> findProductCategoriesByBusinessUnitId(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ProductCategoryDto>)this.companyService.findProductCategoriesOfBusinessUnit(businessUnitId));
	}
	
	
	@GetMapping("/productCategories/product_type/{productTypeId}")
	@CrossOrigin
	public ResponseEntity<List<ProductCategoryDto>> findProductCategoriesByCustomerTypeId(@PathVariable String productTypeId) throws Exception{
		return ResponseEntity.ok().body((List<ProductCategoryDto>)this.companyService.findProductCategoriesOfProductType(productTypeId));
	}
	
	@GetMapping("/productCategories/customer_category/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<ProductCategoryDto>> findProductCategoriesByCustomerCategoryId(@PathVariable String customerCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<ProductCategoryDto>)this.companyService.findProductCategoriesOfCustomerCategory(customerCategoryId));
	}
	
	@GetMapping("/productCategories/business_domain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<ProductCategoryDto>> findProductCategoriesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<ProductCategoryDto>)this.companyService.findProductCategoriesByBusinessDomain(businessDomainId));
	}
	
	@GetMapping("/productCategories/used/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isProductCategoryUsed(@PathVariable String productCategoryId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isProductCategoryUsed(new ProductCategoryId(productCategoryId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/productCategories/{productCategoryId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isProductGoalUsed(@PathVariable String productCategoryId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isProductGoalUsed(new ProductCategoryId(productCategoryId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	/*
	 * ジョブ
	 */
	/*
	@PostMapping("/jobs")
	@CrossOrigin
	public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) throws Exception{
		return ResponseEntity.ok().body((JobDto)this.companyService.addJob(jobDto));
	}
	*/
	@PostMapping("/jobs")
	@CrossOrigin
	public ResponseEntity<MessageDto> createJob(@RequestBody JobDto jobDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.jobDomainService.defineJob(jobDto));
	}
	/*
	@PutMapping("/jobs")
	@CrossOrigin
	public HttpStatus updateJob(@RequestBody JobDto jobDto) throws Exception{
		this.companyService.updateJob(jobDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/jobs")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateJob(@RequestBody JobDto jobDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.jobDomainService.updateJob(jobDto));
	}
	/*
	@DeleteMapping("/jobs/{id}")
	@CrossOrigin
	public HttpStatus deleteJob(@PathVariable String id) throws Exception{
		this.companyService.deleteJob(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/jobs/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteJob(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.jobDomainService.removeJob(id));
	}
	
	@GetMapping("/jobs/{id}")
	@CrossOrigin
	public ResponseEntity<JobDto> findJobById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((JobDto)this.companyService.findJobById(id));
	}
	
	@GetMapping("/jobs/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<JobDto>> findJobsByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<JobDto>)this.companyService.findJobsOfCompany(companyId));
	}
	
	@GetMapping("/jobs/business_domain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<JobDto>> findJobsByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<JobDto>)this.companyService.findJobsOfBusinessDomain(businessDomainId));
	}
	
	@GetMapping("/jobs/job_type/{jobType}")
	@CrossOrigin
	public ResponseEntity<List<JobDto>> findJobsByCompanyId(@PathVariable JobType jobType) throws Exception{
		return ResponseEntity.ok().body((List<JobDto>)this.companyService.findJobsOfJobType(jobType));
	}
	//ジョブが削除可能か検証する。
	//アプリケーションタイプも含めてジョブが使用されているかチェックする。
	@GetMapping("/jobs/used/{jobId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isJobUsed(@PathVariable String jobId) throws Exception{
		ResultDto result = new ResultDto();
		//boolean check = this.companyService.isJobUsed(new JobId(jobId));
		boolean check = deleteJobChecker.removable(jobId);
		if(check) {
			result.setResult("NG");
		}else {
			result.setResult("OK");
		}
		return ResponseEntity.ok().body(result);
	}
	
	//ジョブが編集可能か検証する。
	//アプリケーションタイプ以外でジョブが使用されているかチェックする。
	@GetMapping("/jobs/used/exceptApp/{jobId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isJobUsedExceptApp(@PathVariable String jobId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = editJobChecker.editable(jobId);
		if(check) {
			result.setResult("NG");
		}else {
			result.setResult("OK");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/jobs/{jobId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isJobIndicatorkUsed(@PathVariable String jobId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isJobIndicatorUsed(new JobId(jobId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	/*
	 * タスク
	 */
	/*
	@PostMapping("/tasks")
	@CrossOrigin
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) throws Exception{
		//return ResponseEntity.ok().body((TaskDto)this.companyService.addTask(taskDto));
		return ResponseEntity.ok().body((TaskDto)this.companyService.addTask(taskDto));
	}
	*/
	
	@PostMapping("/tasks")
	@CrossOrigin
	public ResponseEntity<MessageDto> defineTask(@RequestBody TaskDto taskDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.taskService.defineTask(taskDto));
	}
	
	
	/*
	@PutMapping("/tasks")
	@CrossOrigin
	public HttpStatus updateTask(@RequestBody TaskDto taskDto) throws Exception{
		this.companyService.updateTask(taskDto);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/tasks")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateTask(@RequestBody TaskDto taskDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.taskService.updateTask(taskDto));
	}
	
	/*
	@DeleteMapping("/tasks/{id}")
	@CrossOrigin
	public HttpStatus deleteTask(@PathVariable String id) throws Exception{
		this.companyService.deleteTask(id);
		return HttpStatus.OK;
	}
	*/
	
	@DeleteMapping("/tasks/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteTask(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.taskService.removeTask(id));
	}
	
	@PutMapping("/tasks/dataType/{taskId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignDataType(@PathVariable String taskId, @RequestBody AssociatedDataType dataType) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.taskService.assignDataType(taskId, dataType));
	}
	
	@DeleteMapping("/tasks/dataType/{taskId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releaseDataType(@PathVariable String taskId, @RequestBody AssociatedDataType dataType) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.taskService.releaseDataType(taskId, dataType));
	}
	
	
	@GetMapping("/tasks/{id}")
	@CrossOrigin
	public ResponseEntity<TaskDto> findTaskById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((TaskDto)this.companyService.findTaskById(id));
	}
	
	@GetMapping("/tasks/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<TaskDto>> findTasksByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<TaskDto>)this.companyService.findTasksOfCompany(companyId));
	}
	
	@GetMapping("/tasks/job/{jobId}")
	@CrossOrigin
	public ResponseEntity<List<TaskDto>> findTasksByJob(@PathVariable String jobId) throws Exception{
		return ResponseEntity.ok().body((List<TaskDto>)this.companyService.findTasksOfJob(jobId));
	}
	
	@GetMapping("/tasks/used/{taskId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isTaskUsed(@PathVariable String taskId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isTaskUsed(new TaskId(taskId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/tasks/jobId/{jobId}/taskName/{taskName}")
	@CrossOrigin
	public ResponseEntity<ResultDto> addTaskCheck(@PathVariable String jobId, @PathVariable String taskName) throws Exception{
		ResultDto result = new ResultDto();
		
		
		boolean check = taskChecker.addable(jobId, taskName);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/tasks/editable/{taskId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isTaskEditable(@PathVariable String taskId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.editTaskChecker.editable(taskId);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/tasks/removable/{taskId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isTaskRemovable(@PathVariable String taskId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.deleteTaskChecker.removable(taskId);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	
	
	/*
	 * 製品タスク
	 */
	/*
	@PostMapping("/product_tasks")
	@CrossOrigin
	public ResponseEntity<ProductTaskDto> createProductTask(@RequestBody ProductTaskDto productTaskDto) throws Exception{
		return ResponseEntity.ok().body((ProductTaskDto)this.companyService.addProductTask(productTaskDto));
	}
	*/
	@PostMapping("/product_tasks")
	@CrossOrigin
	public ResponseEntity<MessageDto> createProductTask(@RequestBody ProductTaskDto productTaskDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productTaskDomainService.definProductTask(productTaskDto));
	}
	/*
	@PutMapping("/product_tasks")
	@CrossOrigin
	public HttpStatus updateProductTask(@RequestBody ProductTaskDto productTaskDto) throws Exception{
		this.companyService.updateProductTask(productTaskDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/product_tasks")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProductTask(@RequestBody ProductTaskDto productTaskDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productTaskDomainService.updateProductTask(productTaskDto));
	}
	/*
	@DeleteMapping("/product_tasks/{id}")
	@CrossOrigin
	public HttpStatus deleteProductTask(@PathVariable String id) throws Exception{
		this.companyService.deleteProductTask(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/product_tasks/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteProductTask(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productTaskDomainService.deleteProductTask(id));
	}
	
	@GetMapping("/product_tasks/{id}")
	@CrossOrigin
	public ResponseEntity<ProductTaskDto> findProductTaskById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ProductTaskDto)this.companyService.findProductTaskById(id));
	}
	
	@GetMapping("/product_tasks/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ProductTaskDto>> findProductTasksByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ProductTaskDto>)this.companyService.findProductTasksOfCompany(companyId));
	}
	
	@GetMapping("/product_tasks/product_type/{productTypeId}")
	@CrossOrigin
	public ResponseEntity<List<ProductTaskDto>> findProductTasksByProductType(@PathVariable String productTypeId) throws Exception{
		return ResponseEntity.ok().body((List<ProductTaskDto>)this.companyService.findProductTasksOfProductType(productTypeId));
	}
	
	
	
	/*
	 * 部門
	 */
	/*
	@PostMapping("/departments")
	@CrossOrigin
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) throws Exception{
		return ResponseEntity.ok().body((DepartmentDto)this.companyService.addDepartment(departmentDto));
	}
	*/
	@PostMapping("/departments")
	@CrossOrigin
	public ResponseEntity<MessageDto> createDepartment(@RequestBody DepartmentDto departmentDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.departmentApplicationService.addDepartment(departmentDto));
	}
	/*
	@PutMapping("/departments")
	@CrossOrigin
	public HttpStatus updateDepartment(@RequestBody DepartmentDto departmentDto) throws Exception{
		this.companyService.updateDepartment(departmentDto);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/departments")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateDepartment(@RequestBody DepartmentDto departmentDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.departmentApplicationService.updateDepartment(departmentDto));
	}
	/*
	@DeleteMapping("/departments/{id}")
	@CrossOrigin
	public HttpStatus deleteDepartment(@PathVariable String id) throws Exception{
		this.companyService.deleteDepartment(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/departments/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteDepartment(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.departmentApplicationService.removeDepartment(id));
	}
	
	@PutMapping("/departments/application/{departmentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignApplicationToDepartment(@PathVariable String departmentId, @RequestBody AssociatedApplicationCategory application) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.companyService.assignApplicationToDepartment(departmentId, application));
	}
	
	@PutMapping("/departments/partner/{departmentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignPartnerToDepartment(@PathVariable String departmentId, @RequestBody AssociatedPartnerCategory partner) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.companyService.assignPartnerToDepartment(departmentId, partner));
	}
	
	@DeleteMapping("/departments/application/{departmentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releaseApplicationFromDepartment(@PathVariable String departmentId, @RequestBody AssociatedApplicationCategory application) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.companyService.releaseApplicationFromDepartment(departmentId, application));
	}
	
	
	
	@DeleteMapping("/departments/partner/{departmentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releasePartnerFromDepartment(@PathVariable String departmentId, @RequestBody AssociatedPartnerCategory partner) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.companyService.releasePartnerFromDepartment(departmentId, partner));
	}
	
	
	@GetMapping("/departments/{id}")
	@CrossOrigin
	public ResponseEntity<DepartmentDto> findDepartmentById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((DepartmentDto)this.companyService.findDepartmentById(id));
	}
	
	@GetMapping("/departments/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<DepartmentDto>> findDepartmentsByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<DepartmentDto>)this.companyService.findDepartmentsOfCompany(companyId));
	}
	
	@GetMapping("/departments/application/{departmentId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationCategoryDto>> finAssignableApplicationOfDepartment(@PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationCategoryDto>)this.departmentService.findAssignableApplicationsOfDepartment(departmentId));
	}
	
	@GetMapping("/departments/job/{jobId}")
	@CrossOrigin
	public ResponseEntity<List<DepartmentDto>> findDepartmentsByJob(@PathVariable String jobId) throws Exception{
		return ResponseEntity.ok().body((List<DepartmentDto>)this.companyService.findDepartmentsOfJob(jobId));
	}
	
	@GetMapping("/departments/business_unit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<DepartmentDto>> findDepartmentsByBusinessUnitId(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<DepartmentDto>)this.companyService.findDepartmentsOfBusinessUnit(businessUnitId));
	}
	
	@GetMapping("/departments/department_type/{departmentType}")
	@CrossOrigin
	public ResponseEntity<List<DepartmentDto>> findDepartmentsByDepartmentType(@PathVariable DepartmentType departmentType) throws Exception{
		return ResponseEntity.ok().body((List<DepartmentDto>)this.companyService.findDepartmentsOfDepartmentType(departmentType));
	}
	
	@GetMapping("/departments/not_business_unit/")
	@CrossOrigin
	public ResponseEntity<List<DepartmentDto>> findDepartmentsByNotBusinessUnitId() throws Exception{
		return ResponseEntity.ok().body((List<DepartmentDto>)this.companyService.findDepartmentsOfNotBusinessUnit());
	}
	
	@GetMapping("/departments/used/{departmentId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isDepartmentUsed(@PathVariable String departmentId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isDepartmentUsed(new DepartmentId(departmentId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/departments/{departmentId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isDepartmentIndicatorkUsed(@PathVariable String departmentId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isDepartmentGoalUsed(new DepartmentId(departmentId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	

	/*
	 * アクション
	 */
	/*
	@PostMapping("/actions")
	@CrossOrigin
	public ResponseEntity<ActionDto> createAction(@RequestBody ActionDto actionDto) throws Exception{
		return ResponseEntity.ok().body((ActionDto)this.companyService.addAction(actionDto));
	}
	*/
	
	@PostMapping("/actions")
	@CrossOrigin
	public ResponseEntity<MessageDto> createAction(@RequestBody ActionDto actionDto)  throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionApplicationService.addAction(actionDto));
	}
	/*
	@PutMapping("/actions")
	@CrossOrigin
	public HttpStatus updateAction(@RequestBody ActionDto actionDto) throws Exception{
		this.companyService.updateAction(actionDto);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/actions")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateAction(@RequestBody ActionDto actionDto)  throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionApplicationService.updateAction(actionDto));
	}
	/*
	@DeleteMapping("/actions/{id}")
	@CrossOrigin
	public HttpStatus deleteAction(@PathVariable String id) throws Exception{
		this.companyService.deleteAction(id);
		return HttpStatus.OK;
	}
	*/
	
	@DeleteMapping("/actions/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteAction(@PathVariable String id)  throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionApplicationService.removeAction(id));
	}
	
	@GetMapping("/actions/{id}")
	@CrossOrigin
	public ResponseEntity<ActionDto> findActionById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ActionDto)this.companyService.findActionById(id));
	}
	
	@GetMapping("/actions/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ActionDto>> findActionsByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ActionDto>)this.companyService.findActionsOfCompany(companyId));
	}
	
	@GetMapping("/actions/department/{departmentId}")
	@CrossOrigin
	public ResponseEntity<List<ActionDto>> findActionsByDepartment(@PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((List<ActionDto>)this.companyService.findActionsOfDepartment(departmentId));
	}
	
	/*
	 * ビジネスプロセス
	 */
	/*
	@PostMapping("/businessProcesses")
	@CrossOrigin
	public ResponseEntity<BusinessProcessDto> createBusinessProcess(@RequestBody BusinessProcessDto businessProcessDto) throws Exception{
		return ResponseEntity.ok().body((BusinessProcessDto)this.companyService.addBusinessProcess(businessProcessDto));
	}
	*/
	
	@PostMapping("/businessProcesses")
	@CrossOrigin
	public ResponseEntity<MessageDto> createBusinessProcess(@RequestBody BusinessProcessDto businessProcessDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessProcessApplicationService.defineBusinessProcess(businessProcessDto));
	}
	/*
	@PutMapping("/businessProcesses")
	@CrossOrigin
	public HttpStatus updateBusinessProcess(@RequestBody BusinessProcessDto businessProcessDto) throws Exception{
		this.companyService.updateBusinessProcess(businessProcessDto);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/businessProcesses")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateBusinessProcess(@RequestBody BusinessProcessDto businessProcessDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessProcessApplicationService.updateBusinessProcess(businessProcessDto));
	}
	/*
	@PutMapping("/businessProcesses/task/businessProcessId/{businessProcessId}")
	@CrossOrigin
	public HttpStatus assignTaskToBusinessProcess(@RequestBody TaskDto task, @PathVariable String businessProcessId) throws Exception{
		this.companyService.assignTaskToBusinessProcess(task, businessProcessId);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/businessProcesses/task/businessProcessId/{businessProcessId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignTaskToBusinessProcess(@RequestBody TaskDto task, @PathVariable String businessProcessId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessProcessApplicationService.assignTaskToBusinessProcess(task, businessProcessId));
	}
	/*
	@PutMapping("/task/businessProcesses/businessProcessId/{businessProcessId}")
	@CrossOrigin
	public HttpStatus releaseTaskFromBusinessProcess(@RequestBody TaskDto task, @PathVariable String businessProcessId) throws Exception{
		this.companyService.releaseTaskFromBusinessProcess(task, businessProcessId);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/task/businessProcesses/businessProcessId/{businessProcessId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releaseTaskFromBusinessProcess(@RequestBody TaskDto task, @PathVariable String businessProcessId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessProcessApplicationService.releaseTaskFromBusinessProcess(task, businessProcessId));
	}
	/*
	@PutMapping("/businessProcesses/{businessProcessId}/sourceTaskId/{sourceTaskId}/sourceTaskOrder/{sourceTaskOrder}/targetTaskId/{targetTaskId}/targetTaskOrder/{targetTaskOrder}")
	@CrossOrigin
	public HttpStatus replaceTaskOrder(@PathVariable String businessProcessId, @PathVariable String sourceTaskId,  @PathVariable String sourceTaskOrder,  @PathVariable String targetTaskId,  @PathVariable String targetTaskOrder) throws Exception{
		TaskDto source = new TaskDto();
		source.setTaskId(sourceTaskId);
		source.setTaskOrder(Integer.parseInt(sourceTaskOrder));	
		TaskDto target = new TaskDto();
		target.setTaskId(targetTaskId);
		target.setTaskOrder(Integer.parseInt(targetTaskOrder));	
		this.companyService.replaceTaskOrder(source, target, new BusinessProcessId(businessProcessId));
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/businessProcesses/{businessProcessId}/sourceTaskId/{sourceTaskId}/sourceTaskOrder/{sourceTaskOrder}/targetTaskId/{targetTaskId}/targetTaskOrder/{targetTaskOrder}")
	@CrossOrigin
	public ResponseEntity<MessageDto> replaceTaskOrder(@PathVariable String businessProcessId, @PathVariable String sourceTaskId,  @PathVariable String sourceTaskOrder,  @PathVariable String targetTaskId,  @PathVariable String targetTaskOrder) throws Exception{
		TaskDto source = new TaskDto();
		source.setTaskId(sourceTaskId);
		source.setTaskOrder(Integer.parseInt(sourceTaskOrder));	
		TaskDto target = new TaskDto();
		target.setTaskId(targetTaskId);
		target.setTaskOrder(Integer.parseInt(targetTaskOrder));	
		//this.companyService.replaceTaskOrder(source, target, new BusinessProcessId(businessProcessId));
		return ResponseEntity.ok().body((MessageDto)this.businessProcessApplicationService.replaceTaskOrder(source, target, new BusinessProcessId(businessProcessId)));
	}
	/*
	@DeleteMapping("/businessProcesses/{id}")
	@CrossOrigin
	public HttpStatus deleteBusinessProcess(@PathVariable String id) throws Exception{
		this.companyService.deleteBusinessProcess(id);
		return HttpStatus.OK;
	}
	*/
	
	@DeleteMapping("/businessProcesses/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteBusinessProcess(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessProcessApplicationService.removeBusinessProcess(id));
	}
	
	@GetMapping("/businessProcesses/{id}")
	@CrossOrigin
	public ResponseEntity<BusinessProcessDto> findBusinessProcessById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((BusinessProcessDto)this.companyService.findBusinessProcessById(id));
	}
	
	@GetMapping("/businessProcesses/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessProcessDto>> findBusinessProcessesByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessProcessDto>)this.companyService.findBusinessProcessesOfCompany(companyId));
	}
	
	@GetMapping("/businessProcesses/business_domain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessProcessDto>> findBusinessProcessesByBusinessDomainId(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessProcessDto>)this.companyService.findBusinessProcessesOfBusinessDomain(businessDomainId));
	}
	
	
	@GetMapping("/businessProcesses/task/{taskId}")
	@CrossOrigin
	public ResponseEntity<List<BusinessProcessDto>> findBusinessProcessesByTask(@PathVariable String taskId) throws Exception{
		return ResponseEntity.ok().body((List<BusinessProcessDto>)this.companyService.findBusinessProcessesOfTask(taskId));
	}
	
	@GetMapping("/businessProcesses/used/{businessProcessId}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isBusinessProcessUsed(@PathVariable String businessProcessId) throws Exception{
		ResultDto result = new ResultDto();
		boolean check = this.companyService.isBusinessProcessUsed(new BusinessProcessId(businessProcessId));
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/businessProcesses/{businessProcessId}/indicator/name/{indicatorName}/unit/{unit}")
	@CrossOrigin
	public ResponseEntity<ResultDto> isBusinessProcessIndicatorkUsed(@PathVariable String businessProcessId, @PathVariable String indicatorName,  @PathVariable String unit, @PathVariable String description) throws Exception{
		ResultDto result = new ResultDto();
		Indicator indicator = new Indicator(indicatorName, unit, description);
		boolean check = this.companyService.isBusinessProcessIndicatorUsed(new BusinessProcessId(businessProcessId), indicator);
		if(check) {
			result.setResult("OK");
		}else {
			result.setResult("NG");
		}
		return ResponseEntity.ok().body(result);
	}
	
	/*
	 * アクションプラン
	 */
	/*
	@PostMapping("/actionPlans")
	@CrossOrigin
	public ResponseEntity<ActionPlanDto> createActionPlan(@RequestBody ActionPlanDto actionPlanDto) throws Exception{
		return ResponseEntity.ok().body((ActionPlanDto)this.companyService.addActionPlan(actionPlanDto));
	}
	*/
	
	@PostMapping("/actionPlans")
	@CrossOrigin
	public ResponseEntity<MessageDto> createActionPlan(@RequestBody ActionPlanDto actionPlanDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionPlanService.addActionPlan(actionPlanDto));
		
	}
	/*
	@PutMapping("/actionPlans")
	@CrossOrigin
	public HttpStatus updateActionPlan(@RequestBody ActionPlanDto actionPlanDto) throws Exception{
		this.companyService.updateActionPlan(actionPlanDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/actionPlans")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateActionPlan(@RequestBody ActionPlanDto actionPlanDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionPlanService.updateActionPlan(actionPlanDto));
		
	}
	/*
	@PutMapping("/actionPlans/action/actionPlanId/{actionPlanId}")
	@CrossOrigin
	public HttpStatus assignActionToActionPlan(@RequestBody ActionDto action, @PathVariable String actionPlanId) throws Exception{
		//this.companyService.assignActionToActionPlan(action, actionPlanId);
		this.actionPlanService.assignActionToActionPlan(action, actionPlanId);
		return HttpStatus.OK;
	}
	*/
	
	@PutMapping("/actionPlans/action/actionPlanId/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignActionToActionPlan(@RequestBody ActionDto action, @PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionPlanService.assignActionToActionPlan(action, actionPlanId));
		
	}
	
	/*
	@PutMapping("/action/actionPlans/actionPlanId/{actionPlanId}")
	@CrossOrigin
	public HttpStatus releaseActionFromActionPlan(@RequestBody ActionDto action, @PathVariable String actionPlanId) throws Exception{
		this.companyService.releaseActionFromActionPlan(action, actionPlanId);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/action/actionPlans/actionPlanId/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releaseActionFromActionPlan(@RequestBody ActionDto action, @PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionPlanService.releaseActionFromActionPlan(action, actionPlanId));
		
	}
	
	/*
	@DeleteMapping("/actionPlans/{id}")
	@CrossOrigin
	public HttpStatus deleteActionPlan(@PathVariable String id) throws Exception{
		this.companyService.deleteActionPlan(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/actionPlans/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteActionPlan(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.actionPlanService.removeActionPlan(id));
		
	}
	
	@GetMapping("/actionPlans/{id}")
	@CrossOrigin
	public ResponseEntity<ActionPlanDto> findActionPlanById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ActionPlanDto)this.companyService.findActionPlanById(id));
	}
	
	@GetMapping("/actionPlans/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ActionPlanDto>> findActionPlansByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ActionPlanDto>)this.companyService.findActionPlansOfCompany(companyId));
	}
	
	@GetMapping("/actionPlans/project/{projectId}")
	@CrossOrigin
	public ResponseEntity<List<ActionPlanDto>> findActionPlansByProjectId(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((List<ActionPlanDto>)this.companyService.findActionPlansOfProject(projectId));
	}
	
	
	@GetMapping("/actionPlans/businessProcess/{businessProcessId}")
	@CrossOrigin
	public ResponseEntity<List<ActionPlanDto>> findActionPlansByBusinessProcess(@PathVariable String businessProcessId) throws Exception{
		return ResponseEntity.ok().body((List<ActionPlanDto>)this.companyService.findActionPlansOfBusinessProcess(businessProcessId));
	}
	
	@GetMapping("/actionPlans/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ActionPlanDto>> findActionPlansOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ActionPlanDto>)this.companyService.findActionPlansOfBusinessUnit(businessUnitId));
	}
	
	@GetMapping("/actionPlans/action/{actionId}")
	@CrossOrigin
	public ResponseEntity<List<ActionPlanDto>> findActionPlansByAction(@PathVariable String actionId) throws Exception{
		return ResponseEntity.ok().body((List<ActionPlanDto>)this.companyService.findActionPlansOfAction(actionId));
	}
	
	@GetMapping("/actions/actionPlan/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<List<ActionDto>> findActionsByActionPlanId(@PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((List<ActionDto>)this.companyService.findActionsOfActionPlan(actionPlanId));
	}
	
	/*
	 * 製品アクション
	 */
	/*
	@PostMapping("/productFunctions")
	@CrossOrigin
	public ResponseEntity<ProductFunctionDto> createProductAction(@RequestBody ProductFunctionDto productActionDto) throws Exception{
		return ResponseEntity.ok().body((ProductFunctionDto)this.companyService.addProductFunction(productActionDto));
	}
	*/
	@PostMapping("/productFunctions")
	@CrossOrigin
	public ResponseEntity<MessageDto> createProductAction(@RequestBody ProductFunctionDto productActionDto)  throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productFunctionDomainService.defineProductFunction(productActionDto));
	}
	/*
	@PutMapping("/productFunctions")
	@CrossOrigin
	public HttpStatus updateProductAction(@RequestBody ProductFunctionDto productActionDto) throws Exception{
		this.companyService.updateProductFunction(productActionDto);
		return HttpStatus.OK;
	}
	*/
	@PutMapping("/productFunctions")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProductAction(@RequestBody ProductFunctionDto productActionDto)  throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productFunctionDomainService.updateProductFunction(productActionDto));
	}
	/*
	@DeleteMapping("/productFunctions/{id}")
	@CrossOrigin
	public HttpStatus deleteProductAction(@PathVariable String id) throws Exception{
		this.companyService.deleteProductFunction(id);
		return HttpStatus.OK;
	}
	*/
	@DeleteMapping("/productFunctions/{id}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteProductAction(@PathVariable String id)  throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productFunctionDomainService.deleteProductFunction(id));
	}
	
	@GetMapping("/productFunctions/{id}")
	@CrossOrigin
	public ResponseEntity<ProductFunctionDto> findProductActionById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ProductFunctionDto)this.companyService.findProductFunctionById(id));
	}
	
	@GetMapping("/productFunctions/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ProductFunctionDto>> findProductActionsByCompanyId(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ProductFunctionDto>)this.companyService.findProductFunctionsOfCompany(companyId));
	}
	
	@GetMapping("/productFunctions/product_category/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<ProductFunctionDto>> findProductActionsByProductCategory(@PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<ProductFunctionDto>)this.companyService.findProductFunctionsOfProductCategory(productCategoryId));
	}
	
	/*
	 * Members
	 */
	//メンバーを会社に登録する。
	@PostMapping("/members")
	@CrossOrigin
	public ResponseEntity<MessageDto>  registerMember(@RequestBody MemberDto memberDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.registerMember(memberDto));
	}
	
	@PutMapping("/members")
	@CrossOrigin
	public ResponseEntity<MessageDto>  udateMember(@RequestBody MemberDto memberDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.updateMember(memberDto));
	}
	//メンバーの解約
	@DeleteMapping("/members/{memberId}")
	@CrossOrigin
	public ResponseEntity<MessageDto>  releaseMember(@PathVariable String memberId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.releaseMember(memberId));
	}
	//部門メンバーの解除
	@DeleteMapping("/members/department/release/{memberId}")
	@CrossOrigin
	public ResponseEntity<MessageDto>  releaseDepartmentMember(@PathVariable String memberId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.releaseDepartmentMember(memberId));
	}
	//プロジェクトメンバーの解除
	@DeleteMapping("/members/project/release/{memberId}")
	@CrossOrigin
	public ResponseEntity<MessageDto>  releaseProjectMember(@PathVariable String memberId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.releaseProjectMember(memberId));
	}
	
	@DeleteMapping("/members/program/release/{memberId}")
	@CrossOrigin
	public ResponseEntity<MessageDto>  releaseProgramMember(@PathVariable String memberId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.releaseProgramMember(memberId));
	}
	
	
	
	@GetMapping("/members/memberCategory/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>>  findMembersOfMemberCatgory(@PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findMembersOfMemberCategory(memberCategoryId));
	}
	
	 
	
	@GetMapping("/members/{memberId}")
	@CrossOrigin
	public ResponseEntity<MemberDto>  findMemberOfMemberId(@PathVariable String memberId) throws Exception{
		return ResponseEntity.ok().body((MemberDto)this.memberService.findMemberOfMemberId(memberId));
	}
	
	@GetMapping("/members/email/{email}/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<MemberDto>  findMemberOfEmail(@PathVariable String email, @PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((MemberDto)this.memberService.findMemberOfEmail(email, companyId));
	}
	
	@GetMapping("/members/department/{departmentId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>>  findMembersOfDepartment(@PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findMembersOfDepartment(departmentId));
	}
	
	@GetMapping("/members/project/{projectId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>>  findMembersOfProject(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findMembersOfProject(projectId));
	}
	
	@GetMapping("/members/program/{programId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>>  findMembersOfProgram(@PathVariable String programId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findMembersOfProgram(programId));
	}
	
	//部門にアサイン可能な市場のメンバーを探す。POST
	@PostMapping("/members/roles/industry/{industryId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>> findMembersOfAssignable(@RequestBody List<RoleDto> roles, @PathVariable String industryId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findAssignableMembers(roles, industryId));
	}
	//部門にアサイン可能は会社のメンバーを探す。GET
	@GetMapping("/assignable_members/department/{departmentId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>> findMembersOfCompanyOfAssignable(@PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findAssignableMembersOfCompany(departmentId));
	}
	//プロジェクトに割当可能なメンバーを探す。
	@GetMapping("/assignable_members/project/{projectId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>>  findProjectAssignableMembers(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findProjectAssignableMembers(projectId));
	}
	
	//プロジェクトに割当可能なメンバーを探す。
	@GetMapping("/assignable_members/program/{programId}")
	@CrossOrigin
	public ResponseEntity<List<MemberDto>>  findProgramAssignableMembers(@PathVariable String programId) throws Exception{
		return ResponseEntity.ok().body((List<MemberDto>)this.memberService.findProgramAssignableMembers(programId));
	}
	
	//市場のメンバーを部門に割当てる。POST
	@PostMapping("/assign/member/department/{departmentId}/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignMemberToDepartment(@RequestBody MemberDto memberDto, @PathVariable String departmentId, @PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.assignMemberToDepartment(memberDto, departmentId, companyId));
	}
	//割当可能なメンバーをプロジェクトに割り当てる POST
	@PostMapping("/assign/member/project/{projectId}/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignMemberToProject(@RequestBody MemberDto memberDto, @PathVariable String projectId, @PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.assignMemberToProject(memberDto, projectId, companyId));
	}
	
	//割当可能なメンバーをプロジェクトに割り当てる POST
	@PostMapping("/assign/member/program/{programId}/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignMemberToProgram(@RequestBody MemberDto memberDto, @PathVariable String programId, @PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.assignMemberToProgram(memberDto, programId, companyId));
	}
	
	
	//会社のメンバーを部門に割当てる。PUT
	@PutMapping("/assign/member/department/{departmentId}/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignMemberOfCompanyToDepartment(@RequestBody MemberDto memberDto, @PathVariable String departmentId, @PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.assignMemberOfCompanyToDepartment(memberDto, departmentId, companyId));
	}
	//割当可能な会社のメンバーをプロジェクトに割り当てる PUT
	@PutMapping("/assign/member/project/{projectId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignMemberOfCompanyToProject(@RequestBody MemberDto memberDto, @PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.assignMemberOfCompanyToProject(memberDto, projectId));
	}
	
	/*
	 * Project
	 */
	@GetMapping("/project/business_unit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ProjectDto>>  findProjectsOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ProjectDto>)this.projectService.findProjectsOfBusinessUnit(businessUnitId));
	}
	@GetMapping("/project/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ProjectDto>>  findProjectsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ProjectDto>)this.projectService.findProjectsOfCompany(companyId));
	}
	
	@GetMapping("/project/indicator/{indicatorId}/indicatorType/{indicatorType}")
	@CrossOrigin
	public ResponseEntity<List<Indicator>>  findProjectIndicators(@PathVariable String indicatorId, @PathVariable IndicatorType indicatorType) throws Exception{
		return ResponseEntity.ok().body((List<Indicator>)this.projectService.findProjectIndicators(indicatorId, indicatorType));
	}
	
	
	@PostMapping("/projects")
	@CrossOrigin
	public ResponseEntity<MessageDto> defineProject(@RequestBody ProjectDto projectDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.defineProject(projectDto));
	}
	
	@PutMapping("/projects")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProject(@RequestBody ProjectDto projectDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.updateProject(projectDto));
	}
	
	@GetMapping("/projects/{projectId}")
	@CrossOrigin
	public ResponseEntity<ProjectDto> findProjectOfId(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((ProjectDto)this.projectService.findProjectDtoOfId(projectId));
	}
	
	@DeleteMapping("/projects/{projectId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteProject(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeProject(projectId));
	}
	
	@PostMapping("/projects/{projectId}/customerCategory/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addCustomerCategoryToProject(@PathVariable String projectId, @PathVariable String customerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addCustomerCategory(projectId, customerCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/customerCategory/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeCustomerCategoryToProject(@PathVariable String projectId, @PathVariable String customerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeCustomerCategory(projectId, customerCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/actionPlan/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addActionPlanToProject(@PathVariable String projectId, @PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addActionPlan(projectId, actionPlanId));
	}
	
	@DeleteMapping("/projects/{projectId}/actionPlan/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeActionPlanToProject(@PathVariable String projectId, @PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeActionPlan(projectId, actionPlanId));
	}
	
	@PostMapping("/projects/{projectId}/memberCategory/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addMemberCategoryToProject(@PathVariable String projectId, @PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addMemberCategory(projectId, memberCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/memberCategory/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeMemberCategoryToProject(@PathVariable String projectId, @PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeMemberCategory(projectId, memberCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/placeCategory/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addPlaceCategoryToProject(@PathVariable String projectId, @PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addPlaceCategory(projectId, placeCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/placeCategory/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removePlaceCategoryToProject(@PathVariable String projectId, @PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removePlaceCategory(projectId, placeCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/partnerCategory/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addPartnerCategoryToProject(@PathVariable String projectId, @PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addPartnerCategory(projectId, partnerCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/partnerCategory/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removePartnerCategoryToProject(@PathVariable String projectId, @PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removePartnerCategory(projectId, partnerCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/dataCategory/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addDataCategoryToProject(@PathVariable String projectId, @PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addDataCategory(projectId, dataCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/dataCategory/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeDataCategoryToProject(@PathVariable String projectId, @PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeDataCategory(projectId, dataCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/applicationCategory/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addApplicationCategoryToProject(@PathVariable String projectId, @PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addApplicationCategory(projectId, applicationCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/applicationCategory/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeApplicationCategoryToProject(@PathVariable String projectId, @PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeApplicationCategory(projectId, applicationCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/financialAssetCategory/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addFinancialAssetCategoryToProject(@PathVariable String projectId, @PathVariable String financialAssetCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addFinancialAssetCategory(projectId, financialAssetCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/financialAssetCategory/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeFinancialAssetCategoryToProject(@PathVariable String projectId, @PathVariable String financialAssetCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeFinancialAssetCategory(projectId, financialAssetCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/productCategory/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addProductProductToProject(@PathVariable String projectId, @PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addProductCategory(projectId, productCategoryId));
	}
	
	@DeleteMapping("/projects/{projectId}/productCategory/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeProductCategoryToProject(@PathVariable String projectId, @PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeProductCategory(projectId, productCategoryId));
	}
	
	@PostMapping("/projects/{projectId}/goal")
	@CrossOrigin
	public ResponseEntity<MessageDto> addGoalToProject(@PathVariable String projectId, @RequestBody Goal goal) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addGoal(projectId, goal));
	}
	
	@PutMapping("/projects/{projectId}/goal")
	@CrossOrigin
	public ResponseEntity<MessageDto> replaceGoalToProject(@PathVariable String projectId, @RequestBody ReplaceGoal goal) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.replaceGoal(projectId, goal.getPrevious(), goal.getPost()));
	}
	
	@DeleteMapping("/projects/{projectId}/goal")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteGoalToProject(@PathVariable String projectId, @RequestBody Goal goal) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeGoal(projectId, goal));
	}
	
	
	
	@PostMapping("/projects/{projectId}/achievement")
	@CrossOrigin
	public ResponseEntity<MessageDto> addAchievmentToProject(@PathVariable String projectId, @RequestBody Achievement achievement) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.addAchievement(projectId, achievement));
	}
	
	@PutMapping("/projects/{projectId}/achievement")
	@CrossOrigin
	public ResponseEntity<MessageDto> replaceAchievmentToProject(@PathVariable String projectId, @RequestBody ReplaceAchievement achievement) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.replaceAchievement(projectId, achievement.getPrevious(), achievement.getPost()));
	}
	
	
	@DeleteMapping("/projects/{projectId}/achievement")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteGoalToAchievement(@PathVariable String projectId, @RequestBody Achievement achievement) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.projectService.removeAchievement(projectId, achievement));
	}
	
	
	/*
	 * Workflow
	 */
	@GetMapping("/workflow/actin_plan/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<List<WorkflowDto>>  findWorkflowsOfActionPlan(@PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((List<WorkflowDto>)this.workflowService.findWorkflowsOfActionPlan(actionPlanId));
	}
	@GetMapping("/workflow/project/{projectId}")
	@CrossOrigin
	public ResponseEntity<List<WorkflowDto>>  findWorkflowsOfProject(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((List<WorkflowDto>)this.workflowService.findWorkflowOfProject(projectId));
	}
	@GetMapping("/workflow/program/{programId}")
	@CrossOrigin
	public ResponseEntity<List<WorkflowDto>>  findWorkflowsOfProgram(@PathVariable String programId) throws Exception{
		return ResponseEntity.ok().body((List<WorkflowDto>)this.workflowService.findWorkflowOfProgram(programId));
	}
	@GetMapping("/workflow/{workflowId}")
	@CrossOrigin
	public ResponseEntity<WorkflowDto>  findWorkflowsOfId(@PathVariable String workflowId) throws Exception{
		return ResponseEntity.ok().body((WorkflowDto)this.workflowService.findWorkflowDtoOfId(workflowId));
	}
	@PostMapping("/workflows")
	@CrossOrigin
	public ResponseEntity<MessageDto> createWorkflow(@RequestBody WorkflowDto workflowtDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workflowService.createWorkflow(workflowtDto));
	}
	@PostMapping("/workflows/project")
	@CrossOrigin
	public ResponseEntity<MessageDto> addWorkflowToProject(@RequestBody WorkflowDto workflowtDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workflowService.addWorkflowToProject(workflowtDto));
	}
	@PostMapping("/workflows/program")
	@CrossOrigin
	public ResponseEntity<MessageDto> addWorkflowToProram(@RequestBody WorkflowDto workflowtDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workflowService.addWorkflowToProgram(workflowtDto));
	}
	@PutMapping("/workflows")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateWorkflow(@RequestBody WorkflowDto workflowtDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workflowService.updateWorkflow(workflowtDto));
	}
	@DeleteMapping("/workflows/{workflowId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteWorkflow(@PathVariable String workflowId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workflowService.removeWorkflow(workflowId));
	}
	
	/*
	 * Work
	 */
	@GetMapping("/works/{workId}")
	@CrossOrigin
	public ResponseEntity<WorkDto>  findWorkOfId(@PathVariable String workId) throws Exception{
		return ResponseEntity.ok().body((WorkDto)this.workService.findWorkOfId(workId));
	}
	@GetMapping("/works/todo/{workflowId}")
	@CrossOrigin
	public ResponseEntity<List<WorkDto>>  findToDoWorks(@PathVariable String workflowId) throws Exception{
		return ResponseEntity.ok().body((List<WorkDto>)this.workService.findTodoWorks(workflowId));
	}
	@GetMapping("/works/doing/{workflowId}")
	@CrossOrigin
	public ResponseEntity<List<WorkDto>>  findDoingWorks(@PathVariable String workflowId) throws Exception{
		return ResponseEntity.ok().body((List<WorkDto>)this.workService.findDoingWorks(workflowId));
	}
	@GetMapping("/works/done/{workflowId}")
	@CrossOrigin
	public ResponseEntity<List<WorkDto>>  findDoneWorks(@PathVariable String workflowId) throws Exception{
		return ResponseEntity.ok().body((List<WorkDto>)this.workService.findDoneWorks(workflowId));
	}
	
	@PostMapping("/works")
	@CrossOrigin
	public ResponseEntity<MessageDto> createWork(@RequestBody WorkDto workDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workService.createWork(workDto));
	}
	@PutMapping("/works/doing")
	@CrossOrigin
	public ResponseEntity<MessageDto> executeWork(@RequestBody WorkDto workDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workService.executeWork(workDto));
	}
	@PutMapping("/works/product/done")
	@CrossOrigin
	public ResponseEntity<MessageDto> completeWork(@RequestBody CompleteWorkDto workDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workService.completeWork(workDto));
	}
	@PutMapping("/works/done")
	@CrossOrigin
	public ResponseEntity<MessageDto> completeWorkWithoutProduct(@RequestBody CompleteWorkDto workDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workService.completeWorkWithoutProduct(workDto));
	}
	@PutMapping("/works/evaluated")
	@CrossOrigin
	public ResponseEntity<MessageDto> evaluatedWorkWithoutProduct(@RequestBody WorkDto workDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.workService.evaluatedWork(workDto));
	}
	
	/*
	 * product
	 */
	@GetMapping("/products/{productId}")
	@CrossOrigin
	public ResponseEntity<ProductDto>  findProductOfId(@PathVariable String productId) throws Exception{
		return ResponseEntity.ok().body((ProductDto)this.productService.findProductOfId(productId));
	}
	
	@GetMapping("/products/productCategory/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<ProductDto>>  findProductsOfProductCategory(@PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<ProductDto>)this.productService.findProductsOfProductCategory(productCategoryId));
	}
	
	@PostMapping("/products")
	@CrossOrigin
	public ResponseEntity<MessageDto> createProduct(@RequestBody ProductDto productDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.createProduct(productDto));
	}
	
	@PutMapping("/products")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProduct(@RequestBody ProductDto productDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.updateProduct(productDto));
	}
	
	@DeleteMapping("/products/{productId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeProduct(@PathVariable String productId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.removeProduct(productId));
	}
	
	@PutMapping("/register/products/{productId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> registerProduct(@PathVariable String productId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.registerProductToMarket(productId));
	}
	
	@PostMapping("/products/roles/industry/{industryId}")
	@CrossOrigin
	public ResponseEntity<List<ProductDto>> findProductsOfAssignable(@RequestBody List<RoleDto> roles, @PathVariable String industryId) throws Exception{
		return ResponseEntity.ok().body((List<ProductDto>)this.productService.findAssignableProducts(roles, industryId));
	}
	
	@PostMapping("/products/applicationService/roles/industry/{industryId}")
	@CrossOrigin
	public ResponseEntity<List<ProductDto>> findApplicationServicesOfAssignable(@RequestBody List<RoleDto> roles, @PathVariable String industryId) throws Exception{
		return ResponseEntity.ok().body((List<ProductDto>)this.productService.findAssignableApplicationServices(roles, industryId));
	}
	
	@PostMapping("/products/personalService/roles/industry/{industryId}")
	@CrossOrigin
	public ResponseEntity<List<ProductDto>> findPersonalServicesOfAssignable(@RequestBody List<RoleDto> roles, @PathVariable String industryId) throws Exception{
		return ResponseEntity.ok().body((List<ProductDto>)this.productService.findAssignablePersonalServices(roles, industryId));
	}
	
	
	@PostMapping("/assign/applicationService/department/{departmentId}/company/{companyId}/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignApplicationServiceToDepartment(@RequestBody ProductDto productDto, @PathVariable String departmentId, @PathVariable String companyId, @PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.assignApplicationServiceToDepartment(productDto, departmentId, companyId, businessUnitId));
	}
	
	@PostMapping("/assign/personalService/department/{departmentId}/company/{companyId}/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignPersonalServiceToDepartment(@RequestBody ProductDto productDto, @PathVariable String departmentId, @PathVariable String companyId, @PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.assignPersonalServiceToDepartment(productDto, departmentId, companyId, businessUnitId));
	}
	
	@PutMapping("/release/products/{productId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releaseProduct(@PathVariable String productId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.releaseProduct(productId));
	}
	
	@DeleteMapping("/release/application/{applicationCategoryId}/department/{departmentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releaseApplicationCategoryFromDep(@PathVariable String applicationCategoryId, @PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.releaseApplicationService(departmentId, applicationCategoryId));
	}
	
	/*
	 * ProductService
	 */
	@PostMapping("/assign/product/department/{departmentId}/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> assignProductToDepartment(@RequestBody ProductDto productDto, @PathVariable String departmentId, @PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productServiceService.assignProductToDepartment(productDto, departmentId, companyId));
	}
	
	@GetMapping("/product_services/department/{departmentId}")
	@CrossOrigin
	public ResponseEntity<List<ProductServiceDto>>  findProductServicesOfDepartment(@PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((List<ProductServiceDto>)this.productServiceService.findProductsOfDepartment(departmentId));
	}
	
	@GetMapping("/product_services/product/{productId}")
	@CrossOrigin
	public ResponseEntity<List<ProductServiceDto>>  findProductServicesOfProduct(@PathVariable String productId) throws Exception{
		return ResponseEntity.ok().body((List<ProductServiceDto>)this.productServiceService.findProductsOfProduct(productId));
	}
	
	@GetMapping("/product_services/application_service_function/{actionId}")
	@CrossOrigin
	public ResponseEntity<List<AssociatedProductFunction>>  findApplitacationServiceFunctionOfAction(@PathVariable String actionId) throws Exception{
		return ResponseEntity.ok().body((List<AssociatedProductFunction>)this.productServiceService.findApplitacationServiceFunctionOfAction(actionId));
	}
	
	
	@PostMapping("/product_works")
	@CrossOrigin
	public MessageDto createProductWork(@RequestBody ProductWorkDto productWorkDto) throws Exception{
		return this.productWorkService.createProductWork(productWorkDto);
	}
	
	/*
	 * Awareness
	 */
	@GetMapping("/awarenesses/{memberId}")
	@CrossOrigin
	public ResponseEntity<List<AwarenessDto>>  findAwarenessesOfMember(@PathVariable String memberId) throws Exception{
		return ResponseEntity.ok().body((List<AwarenessDto>)this.awarenessService.findAwarenessesOfMember(memberId));
	}
	
	@PostMapping("/awarenesses")
	@CrossOrigin
	public ResponseEntity<MessageDto> createAwareness(@RequestBody AwarenessDto awarenessDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.awarenessService.registerAwareness(awarenessDto));
	}
	
	@PutMapping("/awarenesses")
	@CrossOrigin
	public ResponseEntity<MessageDto> upateAwareness(@RequestBody AwarenessDto awarenessDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.awarenessService.updateAwareness(awarenessDto));
	}
	
	@DeleteMapping("/awarenesses/{awarenessId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteAwareness(@PathVariable String awarenessId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.awarenessService.removeAwareness(awarenessId));
	}
	
	/*
	 * Comment
	 */
	@GetMapping("/comments/{targetId}")
	@CrossOrigin
	public ResponseEntity<List<CommentDto>>  findCommentsOfTarget(@PathVariable String targetId) throws Exception{
		return ResponseEntity.ok().body((List<CommentDto>)this.commentService.findCommentsOfTarget(targetId));
	}
	
	@PostMapping("/comments")
	@CrossOrigin
	public ResponseEntity<MessageDto> createComment(@RequestBody CommentDto commentDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.commentService.registerComment(commentDto));
	}
	
	@PutMapping("/comments")
	@CrossOrigin
	public ResponseEntity<MessageDto> upateComment(@RequestBody CommentDto commentDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.commentService.updateComment(commentDto));
	}
	
	@DeleteMapping("/comments/{commentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteComment(@PathVariable String commentId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.commentService.removeComment(commentId));
	}
	
	/*
	 * Evaluation
	 */
	@GetMapping("/evaluations/{targetId}")
	@CrossOrigin
	public ResponseEntity<List<EvaluationDto>>  findEvaluationsOfTarget(@PathVariable String targetId) throws Exception{
		return ResponseEntity.ok().body((List<EvaluationDto>)this.evaluationService.findEvaluationsOfTarget(targetId));
	}
	
	@PostMapping("/evaluations")
	@CrossOrigin
	public ResponseEntity<MessageDto> createEvaluation(@RequestBody EvaluationDto evaluationDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.evaluationService.registerEvaluation(evaluationDto));
	}
	
	@PutMapping("/evaluations")
	@CrossOrigin
	public ResponseEntity<MessageDto> upateEvaluation(@RequestBody EvaluationDto evaluationDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.evaluationService.updateEvaluation(evaluationDto));
	}
	
	@DeleteMapping("/evaluations/{evaluationId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteEvaluation(@PathVariable String evaluationId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.evaluationService.removeEvaluation(evaluationId));
	}
	
	/*
	 * FiancialAssetTYpe
	 */
	@GetMapping("/financialAssetTypes/{id}")
	@CrossOrigin
	public ResponseEntity<FinancialAssetTypeDto>  findFinancialAssetTypesOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((FinancialAssetTypeDto)this.financialAssetTypeService.findFinancialAssetTypesOfId(id));
	}
	
	@GetMapping("/financialAssetTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<FinancialAssetTypeDto>>  findFinancialAssetTypesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<FinancialAssetTypeDto>)this.financialAssetTypeService.findFinancialAssetTypesOfCompany(companyId));
	}
	@GetMapping("/financialAssetTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<FinancialAssetTypeDto>>  findFinancialAssetTypesOfBusinessDomain(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<FinancialAssetTypeDto>)this.financialAssetTypeService.findFinancialAssetTypesOfBusinessDomain(businessDomainId));
	}
	
	@PostMapping("/financialAssetTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createFinancialAssetType(@RequestBody FinancialAssetTypeDto financialAssetTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.financialAssetTypeService.defineFinancialAssetType(financialAssetTypeDto));
	}
	
	@PutMapping("/financialAssetTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateFinancialAssetType(@RequestBody FinancialAssetTypeDto financialAssetTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.financialAssetTypeService.updateFinancialAssetType(financialAssetTypeDto));
	}
	
	@DeleteMapping("/financialAssetTypes/{financialAssetTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteFinancialAssetType(@PathVariable String financialAssetTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.financialAssetTypeService.removeFinancialAssetType(financialAssetTypeId));
	}
	
	/*
	 * PlaceType
	 */
	@GetMapping("/placeTypes/{id}")
	@CrossOrigin
	public ResponseEntity<PlaceTypeDto>  findPlaceTypesOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((PlaceTypeDto)this.placeTypeService.findPlaceTypesOfId(id));
	}
	
	@GetMapping("/placeTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceTypeDto>>  findPlaceTypesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceTypeDto>)this.placeTypeService.findPlaceTypesOfCompany(companyId));
	}
	@GetMapping("/placeTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceTypeDto>>  findPlaceTypesOfBusinessDomain(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceTypeDto>)this.placeTypeService.findPlaceTypesOfBusinessDomain(businessDomainId));
	}
	
	@PostMapping("/placeTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPlaceType(@RequestBody PlaceTypeDto placeTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeTypeService.definePlaceType(placeTypeDto));
	}
	
	@PutMapping("/placeTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePlaceType(@RequestBody PlaceTypeDto placeTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeTypeService.updatePlaceType(placeTypeDto));
	}
	
	@DeleteMapping("/placeTypes/{placeTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePlaceType(@PathVariable String placeTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeTypeService.removePlaceType(placeTypeId));
	}
	
	/*
	 * DataType
	 */
	@GetMapping("/dataTypes/{id}")
	@CrossOrigin
	public ResponseEntity<DataTypeDto>  findDataTypesOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((DataTypeDto)this.dataTypeService.findDataTypesOfId(id));
	}
	
	@GetMapping("/dataTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<DataTypeDto>>  findDataTypesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<DataTypeDto>)this.dataTypeService.findDataTypesOfCompany(companyId));
	}
	@GetMapping("/dataTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<DataTypeDto>>  findDataTypesOfBusinessDomain(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<DataTypeDto>)this.dataTypeService.findDataTypesOfBusinessDomain(businessDomainId));
	}
	
	@GetMapping("/dataTypes/businessDomain/{businessDomainId}/dataClass/{dataClass}")
	@CrossOrigin
	public ResponseEntity<List<DataTypeDto>>  findDataTypesOfBusinessDomainAndDataClass(@PathVariable String businessDomainId, @PathVariable DataClass dataClass) throws Exception{
		return ResponseEntity.ok().body((List<DataTypeDto>)this.dataTypeService.findDataTypesOfBusinessDomainAndDataClass(businessDomainId, dataClass));
	}
	
	@PostMapping("/dataTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createDataType(@RequestBody DataTypeDto dataTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataTypeService.defineDataType(dataTypeDto));
	}
	
	@PutMapping("/dataTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateDataType(@RequestBody DataTypeDto dataTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataTypeService.updateDataType(dataTypeDto));
	}
	
	@DeleteMapping("/dataTypes/{dataTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteDataType(@PathVariable String dataTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataTypeService.removeDataType(dataTypeId));
	}
	
	/*
	 * ApplicationType
	 */
	@GetMapping("/applicationTypes/{id}")
	@CrossOrigin
	public ResponseEntity<ApplicationTypeDto>  findApplicationTypesOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ApplicationTypeDto)this.applicationTypeService.findApplicationTypesOfId(id));
	}
	
	@GetMapping("/applicationTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationTypeDto>>  findApplicationTypesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationTypeDto>)this.applicationTypeService.findApplicationTypesOfCompany(companyId));
	}
	@GetMapping("/applicationTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationTypeDto>>  findApplicationTypesOfBusinessDomain(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationTypeDto>)this.applicationTypeService.findApplicationTypesOfBusinessDomain(businessDomainId));
	}
	
	@GetMapping("/applicationTypes/job/{jobId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationTypeDto>>  findApplicationTypesOfjob(@PathVariable String jobId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationTypeDto>)this.applicationTypeService.findApplicationTypesOfJob(jobId));
	}
	
	@PostMapping("/applicationTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createApplicationType(@RequestBody ApplicationTypeDto applicationTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationTypeService.defineApplicationType(applicationTypeDto));
	}
	
	@PutMapping("/applicationTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateApplicationType(@RequestBody ApplicationTypeDto applicationTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationTypeService.updateApplicationType(applicationTypeDto));
	}
	
	@DeleteMapping("/applicationTypes/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationTypeService.removeApplicationType(applicationTypeId));
	}
	
	    //ジョブが削除可能か検証する。
		//アプリケーションタイプも含めてジョブが使用されているかチェックする。
		@GetMapping("/applicationTypes/used/{applicationTypeId}")
		@CrossOrigin
		public ResponseEntity<ResultDto> isApplicationTypeUsed(@PathVariable String applicationTypeId) throws Exception{
			
			ResultDto result = new ResultDto();
			//boolean check = this.companyService.isJobUsed(new JobId(jobId));
			boolean check = deleteApplicationTypeChecker.removable(applicationTypeId);
			if(check) {
				result.setResult("NG");
			}else {
				result.setResult("OK");
			}
			return ResponseEntity.ok().body(result);
		}
	
	/*
	 * ItType
	 */
	@GetMapping("/itTypes/{id}")
	@CrossOrigin
	public ResponseEntity<ItTypeDto>  findItTypesOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ItTypeDto)this.itTypeService.findItTypesOfId(id));
	}
	
	@GetMapping("/itTypes/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ItTypeDto>>  findItTypesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ItTypeDto>)this.itTypeService.findItTypesOfCompany(companyId));
	}
	@GetMapping("/itTypes/businessDomain/{businessDomainId}")
	@CrossOrigin
	public ResponseEntity<List<ItTypeDto>>  findItTypesOfBusinessDomain(@PathVariable String businessDomainId) throws Exception{
		return ResponseEntity.ok().body((List<ItTypeDto>)this.itTypeService.findItTypesOfBusinessDomain(businessDomainId));
	}
	
	@PostMapping("/itTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> createItType(@RequestBody ItTypeDto itTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itTypeService.defineItType(itTypeDto));
	}
	
	@PutMapping("/itTypes")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateItType(@RequestBody ItTypeDto itTypeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itTypeService.updateItType(itTypeDto));
	}
	
	@DeleteMapping("/itTypes/{itTypeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteItType(@PathVariable String itTypeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itTypeService.removeItType(itTypeId));
	}
	
	/*
	 * ApplicationTask
	 */
	@GetMapping("/applicationTasks/{id}")
	@CrossOrigin
	public ResponseEntity<ApplicationTaskDto>  findApplicationTasksOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ApplicationTaskDto)this.applicationTaskService.findApplicationTasksOfId(id));
	}
	
	@GetMapping("/applicationTasks/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationTaskDto>>  findApplicationTasksOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationTaskDto>)this.applicationTaskService.findApplicationTasksOfCompany(companyId));
	}
	@GetMapping("/applicationTasks/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationTaskDto>>  findApplicationTasksOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationTaskDto>)this.applicationTaskService.findApplicationTasksOfApplicationType(applicationTypeId));
	}
	
	@PostMapping("/applicationTasks")
	@CrossOrigin
	public ResponseEntity<MessageDto> createApplicationTask(@RequestBody ApplicationTaskDto applicationTaskDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationTaskService.defineApplicationTask(applicationTaskDto));
	}
	
	@PutMapping("/applicationTasks")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateApplicationTask(@RequestBody ApplicationTaskDto applicationTaskDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationTaskService.updateApplicationTask(applicationTaskDto));
	}
	
	@DeleteMapping("/applicationTasks/{applicationTaskId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteApplicationTask(@PathVariable String applicationTaskId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationTaskService.removeApplicationTask(applicationTaskId));
	}
	
	/*
	 * DataDomain
	 */
	@GetMapping("/dataDomains/{id}")
	@CrossOrigin
	public ResponseEntity<DataDomainDto>  findDataDomainsOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((DataDomainDto)this.dataDomainService.findDataDomainsOfId(id));
	}
	
	@GetMapping("/dataDomains/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<DataDomainDto>>  findDataDomainsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<DataDomainDto>)this.dataDomainService.findDataDomainsOfCompany(companyId));
	}
	
	
	@PostMapping("/dataDomains")
	@CrossOrigin
	public ResponseEntity<MessageDto> createDataDomain(@RequestBody DataDomainDto dataDomainDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataDomainService.defineDataDomain(dataDomainDto));
	}
	
	@PutMapping("/dataDomains")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateDataDomain(@RequestBody DataDomainDto dataDomainDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataDomainService.updateDataDomain(dataDomainDto));
	}
	
	@DeleteMapping("/dataDomains/{dataDomainId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteDataDomain(@PathVariable String dataDomainId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataDomainService.removeDataDomain(dataDomainId));
	}
	
	/*
	 * DataItem
	 */
	@GetMapping("/dataItems/{id}")
	@CrossOrigin
	public ResponseEntity<DataItemDto>  findDataItemsOfId(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((DataItemDto)this.dataItemService.findDataItemsOfId(id));
	}
	
	@GetMapping("/dataItems/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<DataItemDto>>  findDataItemsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<DataItemDto>)this.dataItemService.findDataItemsOfCompany(companyId));
	}
	@GetMapping("/dataItems/dataType/{dataTypeId}")
	@CrossOrigin
	public ResponseEntity<List<DataItemDto>>  findDataItemsOfDataType(@PathVariable String dataTypeId) throws Exception{
		return ResponseEntity.ok().body((List<DataItemDto>)this.dataItemService.findDataItemsOfDataType(dataTypeId));
	}
	
	@PostMapping("/dataItems")
	@CrossOrigin
	public ResponseEntity<MessageDto> createDataItem(@RequestBody DataItemDto dataItemDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataItemService.defineDataItem(dataItemDto));
	}
	
	@PutMapping("/dataItems")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateDataItem(@RequestBody DataItemDto dataItemDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataItemService.updateDataItem(dataItemDto));
	}
	
	@DeleteMapping("/dataItems/{dataItemId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteDataItem(@PathVariable String dataItemId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataItemService.removeDataItem(dataItemId));
	}
	
	
	/*
	 * Guideline
	 */
	@GetMapping("/guidelines/{targetId}")
	@CrossOrigin
	public ResponseEntity<List<GuidelineDto>>  findGuidelinesOfTarget(@PathVariable String targetId) throws Exception{
		return ResponseEntity.ok().body((List<GuidelineDto>)this.guidelineService.findGuidelinesOfTarget(targetId));
	}
	@GetMapping("/guidelines/work/{workId}")
	@CrossOrigin
	public ResponseEntity<List<GuidelineDto>>  findGuidelinesOfWork(@PathVariable String workId) throws Exception{
		return ResponseEntity.ok().body((List<GuidelineDto>)this.guidelineService.findGuidelinesOfWork(workId));
	}
	
	@PostMapping("/guidelines")
	@CrossOrigin
	public ResponseEntity<MessageDto> createGuideline(@RequestBody GuidelineDto guidelineDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.guidelineService.registerGuideline(guidelineDto));
	}
	
	@PutMapping("/guidelines")
	@CrossOrigin
	public ResponseEntity<MessageDto> upateGuideline(@RequestBody GuidelineDto guidelineDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.guidelineService.updateGuideline(guidelineDto));
	}
	
	@DeleteMapping("/guidelines/{guidelineId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteGuideline(@PathVariable String guidelineId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.guidelineService.removeGuideline(guidelineId));
	}
	
	
	
	
	
	
	
	/*
	 * Idea
	 */
	@GetMapping("/ideas/{targetId}")
	@CrossOrigin
	public ResponseEntity<List<IdeaDto>>  findIdeasOfTarget(@PathVariable String targetId) throws Exception{
		return ResponseEntity.ok().body((List<IdeaDto>)this.ideaService.findIdeasOfTarget(targetId));
	}
	@GetMapping("/ideas/work/{workId}")
	@CrossOrigin
	public ResponseEntity<List<IdeaDto>>  findIdeasOfWork(@PathVariable String workId) throws Exception{
		return ResponseEntity.ok().body((List<IdeaDto>)this.ideaService.findIdeasOfWork(workId));
	}
	@GetMapping("/ideas/idea/{ideaId}")
	@CrossOrigin
	public ResponseEntity<IdeaDto>  findIdeaOfId(@PathVariable String ideaId) throws Exception{
		return ResponseEntity.ok().body((IdeaDto)this.ideaService.findIdeaOfId(ideaId));
	}
	
	@PostMapping("/ideas")
	@CrossOrigin
	public ResponseEntity<MessageDto> createIdea(@RequestBody IdeaDto ideaDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.ideaService.registerIdea(ideaDto));
	}
	
	@PutMapping("/ideas")
	@CrossOrigin
	public ResponseEntity<MessageDto> upateIdea(@RequestBody IdeaDto ideaDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.ideaService.updateIdea(ideaDto));
	}
	
	@DeleteMapping("/ideas/{ideaId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteIdea(@PathVariable String ideaId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.ideaService.removeIdea(ideaId));
	}
    
	//Indicators
	@GetMapping("/indicators/{indicatroType}/{locale}")
	@CrossOrigin
	public ResponseEntity<List<Indicator>> findIndicators(@PathVariable String indicatroType, @PathVariable String locale) throws Exception{
		IndicatorSelector selector = IndicatorSelector.getIndicatorSelector();
		List<Indicator> indicators = selector.getIndicators(indicatroType, locale);
		return ResponseEntity.ok().body(indicators);
	}
	
	
	@PostMapping("/signUp")
	@CrossOrigin
	public ResponseEntity<MessageDto> signUp(@RequestBody SignUpDto singnUpDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.signUpService.signUp(singnUpDto));
	}
	
	@PostMapping("/signIn")
	@CrossOrigin
	public ResponseEntity<MessageDto> signIn(@RequestBody SignInDto singnInDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.signUpService.signIn(singnInDto));
	}
	
	@PostMapping("/member/signIn")
	@CrossOrigin
	public ResponseEntity<MessageDto> memberSignIn(@RequestBody MemberSignInDto singnInDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberService.memberSignIn(singnInDto));
	}
	
	/*
	 * 事業ドメインの指標
	 */
	@PostMapping("/businessDomains/{businessDomainId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> createBusinessDomainIndicator(@PathVariable String businessDomainId, @RequestBody Indicator indicator) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessDomainService.defineBusinessDomainIndicator(businessDomainId, indicator));
	}
	
	@PutMapping("/businessDomains/{businessDomainId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateBusinessDomaineIndicator(@PathVariable String businessDomainId, @RequestBody IndicatorsDto indicators) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessDomainService.replaceBusinessDomainIndicator(businessDomainId, indicators.getPreviousIndicator(), indicators.getPostIndicator()));
	}
	
	@DeleteMapping("/businessDomains/{businessDomainId}/indicator")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteBusinessDomainIndicator(@PathVariable String businessDomainId, @RequestBody Indicator indicator) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.businessDomainService.removeBusinessDomainIndicator(businessDomainId, indicator));
	}
	
	/*
	 * ApplicationCategory
	 */
	@GetMapping("/applicationCategories/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<ApplicationCategoryDto>  findApplicationCategoryOfId(@PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((ApplicationCategoryDto)this.applicationCategoryApplicationService.findApplicationCategoryOfId(applicationCategoryId));
	}
	
	@GetMapping("/applicationCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationCategoryDto>>  findApplicationCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationCategoryDto>)this.applicationCategoryApplicationService.findApplicationCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/applicationCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationCategoryDto>>  findApplicationCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationCategoryDto>)this.applicationCategoryApplicationService.findApplicationCategoriesOfApplicationType(applicationTypeId));
	}
	
	@GetMapping("/applicationCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationCategoryDto>>  findApplicationCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationCategoryDto>)this.applicationCategoryApplicationService.findApplicationCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/applicationCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createApplicationCategory(@RequestBody ApplicationCategoryDto applicationCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationCategoryApplicationService.addApplicationCategory(applicationCategoryDto));
	}
	
	@PutMapping("/applicationCategories/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateApplicationCategory(@PathVariable String applicationCategoryId, @RequestBody ApplicationCategoryDto applicationCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationCategoryApplicationService.updateApplicationCategory(applicationCategoryId, applicationCategoryDto));
	}
	
	@DeleteMapping("/applicationCategories/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteApplicationCategory(@PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationCategoryApplicationService.removeApplicationCategory(applicationCategoryId));
	}
	
	/*
	 * ApplicationFunction
	 */
	@GetMapping("/applicationFunctions/{applicationFunctionId}")
	@CrossOrigin
	public ResponseEntity<ApplicationFunctionDto>  findApplicationFunctionOfId(@PathVariable String applicationFunctionId) throws Exception{
		return ResponseEntity.ok().body((ApplicationFunctionDto)this.applicationFunctionService.findApplicationFunctionOfId(applicationFunctionId));
	}
	
	@GetMapping("/applicationFunctions/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationFunctionDto>>  findApplicationFunctionsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationFunctionDto>)this.applicationFunctionService.findApplicationFunctionsOfCompany(companyId));
	}
	
	@GetMapping("/applicationFunctions/applicationTask/{applicationTaskId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationFunctionDto>>  findApplicationFunctionOfApplicationTask(@PathVariable String applicationTaskId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationFunctionDto>)this.applicationFunctionService.findApplicationFunctionsOfApplicationTask(applicationTaskId));
	}
	
	@GetMapping("/applicationFunctions/applicationCategory/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationFunctionDto>>  findApplicationFunctionsOfApplicationCategory(@PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationFunctionDto>)this.applicationFunctionService.findApplicationFunctionsOfApplicationCategory(applicationCategoryId));
	}
	
	//アプリケーション機能がアクセスすべきデータとアクセス方法を取得する
	@GetMapping("/applicationFunctions/applicationData/{applicationFunctionId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationData>>  findApplicationData(@PathVariable String applicationFunctionId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationData>)this.applicationFunctionService.findApplicationData(applicationFunctionId));
	}
	
	@PostMapping("/applicationFunctions")
	@CrossOrigin
	public ResponseEntity<MessageDto> createApplicationFunction(@RequestBody ApplicationFunctionDto applicationFunctionDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationFunctionService.addApplicationFunction(applicationFunctionDto));
	}
	
	@PutMapping("/applicationFunctions")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateApplicationFunction(@RequestBody ApplicationFunctionDto applicationFunctionDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationFunctionService.updateApplicationFunction(applicationFunctionDto));
	}
	
	@DeleteMapping("/applicationFunctions/{applicationFunctionId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteApplicationFunction(@PathVariable String applicationFunctionId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationFunctionService.removeApplicationFunction(applicationFunctionId));
	}
	
	
	/*
	 * MemberCategory
	 */
	@GetMapping("/memberCategories/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MemberCategoryDto>  findMemberCategoryOfId(@PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((MemberCategoryDto)this.memberCategoryApplicationService.findMemberCategoryOfId(memberCategoryId));
	}
	
	@GetMapping("/memberCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<MemberCategoryDto>>  findMemberCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<MemberCategoryDto>)this.memberCategoryApplicationService.findMemberCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/memberCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<MemberCategoryDto>>  findMemberCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<MemberCategoryDto>)this.memberCategoryApplicationService.findMemberCategoriesOfMemberType(applicationTypeId));
	}
	
	@GetMapping("/memberCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<MemberCategoryDto>>  findMemberCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<MemberCategoryDto>)this.memberCategoryApplicationService.findMemberCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/memberCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createMemberCategory(@RequestBody MemberCategoryDto memberCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberCategoryApplicationService.addMemberCategory(memberCategoryDto));
	}
	
	@PutMapping("/memberCategories/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateMemberCategory(@PathVariable String memberCategoryId, @RequestBody MemberCategoryDto memberCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberCategoryApplicationService.updateMemberCategory(memberCategoryId, memberCategoryDto));
	}
	
	@DeleteMapping("/memberCategories/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteMemberCategory(@PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.memberCategoryApplicationService.removeMemberCategory(memberCategoryId));
	}
	
	
	/*
	 * DataCategory
	 */
	@GetMapping("/dataCategories/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<DataCategoryDto>  findDataCategoryOfId(@PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((DataCategoryDto)this.dataCategoryApplicationService.findDataCategoryOfId(dataCategoryId));
	}
	
	@GetMapping("/dataCategories/dataType/{dataTypeId}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findDataCategoryOfDataType(@PathVariable String dataTypeId) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findDataCategoriesOfDataType(dataTypeId));
	}
	
	
	
	@GetMapping("/dataCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findDataCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findDataCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/dataCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findDataCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findDataCategoriesOfDataType(applicationTypeId));
	}
	
	//ワークの成果物候補を探す。
	@GetMapping("/dataCategories/work/output/{workId}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findDeliverables(@PathVariable String workId) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findDeliverables(workId));
	}
	
	//ワークのインプットデータを探す。
	@GetMapping("/dataCategories/work/input/{workId}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findInputData(@PathVariable String workId) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findInputData(workId));
	}
	
	
	
	@GetMapping("/dataCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findDataCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findDataCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/dataCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createDataCategory(@RequestBody DataCategoryDto dataCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataCategoryApplicationService.addDataCategory(dataCategoryDto));
	}
	
	@PutMapping("/dataCategories/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateDataCategory(@PathVariable String dataCategoryId, @RequestBody DataCategoryDto dataCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataCategoryApplicationService.updateDataCategory(dataCategoryId, dataCategoryDto));
	}
	
	@DeleteMapping("/dataCategories/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteDataCategory(@PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataCategoryApplicationService.removeDataCategory(dataCategoryId));
	}
	
	@GetMapping("/dataCategories/businessUnit/{businessUnitId}/dataClass/{dataClass}")
	@CrossOrigin
	public ResponseEntity<List<DataCategoryDto>>  findDataCategoriesOfBusinessUnitAndDataClass(@PathVariable String businessUnitId, @PathVariable DataClass dataClass) throws Exception{
		return ResponseEntity.ok().body((List<DataCategoryDto>)this.dataCategoryApplicationService.findDataCategoriesOfBusinessUnitAndDataClass(businessUnitId, dataClass));
	}
	
	/*
	 * PartnerCategory
	 */
	@GetMapping("/partnerCategories/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<PartnerCategoryDto>  findPartnerCategoryOfId(@PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((PartnerCategoryDto)this.partnerCategoryApplicationService.findPartnerCategoryOfId(partnerCategoryId));
	}
	
	@GetMapping("/partnerCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerCategoryDto>>  findPartnerCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerCategoryDto>)this.partnerCategoryApplicationService.findPartnerCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/partnerCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerCategoryDto>>  findPartnerCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerCategoryDto>)this.partnerCategoryApplicationService.findPartnerCategoriesOfPartnerType(applicationTypeId));
	}
	
	@GetMapping("/partnerCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerCategoryDto>>  findPartnerCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerCategoryDto>)this.partnerCategoryApplicationService.findPartnerCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/partnerCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPartnerCategory(@RequestBody PartnerCategoryDto partnerCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerCategoryApplicationService.addPartnerCategory(partnerCategoryDto));
	}
	
	@PutMapping("/partnerCategories/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePartnerCategory(@PathVariable String partnerCategoryId, @RequestBody PartnerCategoryDto partnerCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerCategoryApplicationService.updatePartnerCategory(partnerCategoryId, partnerCategoryDto));
	}
	
	@DeleteMapping("/partnerCategories/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePartnerCategory(@PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerCategoryApplicationService.removePartnerCategory(partnerCategoryId));
	}
	
	@DeleteMapping("/release/partner/{partnerCategoryId}/department/{departmentId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> releasePartnerCategoryFromDep(@PathVariable String partnerCategoryId, @PathVariable String departmentId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.productService.releasePersonalService(departmentId, partnerCategoryId));
	}
	
	/*
	 * FinancialAssetCategory
	 */
	@GetMapping("/financialAssetCategories/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<FinancialAssetCategoryDto>  findFinancialAssetCategoryOfId(@PathVariable String financialAssetCategoryId) throws Exception{
		return ResponseEntity.ok().body((FinancialAssetCategoryDto)this.financialAssetCategoryApplicationService.findFinancialAssetCategoryOfId(financialAssetCategoryId));
	}
	
	@GetMapping("/financialAssetCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<FinancialAssetCategoryDto>>  findFinancialAssetCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<FinancialAssetCategoryDto>)this.financialAssetCategoryApplicationService.findFinancialAssetCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/financialAssetCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<FinancialAssetCategoryDto>>  findFinancialAssetCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<FinancialAssetCategoryDto>)this.financialAssetCategoryApplicationService.findFinancialAssetCategoriesOfFinancialAssetType(applicationTypeId));
	}
	
	@GetMapping("/financialAssetCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<FinancialAssetCategoryDto>>  findFinancialAssetCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<FinancialAssetCategoryDto>)this.financialAssetCategoryApplicationService.findFinancialAssetCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/financialAssetCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createFinancialAssetCategory(@RequestBody FinancialAssetCategoryDto financialAssetCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.financialAssetCategoryApplicationService.addFinancialAssetCategory(financialAssetCategoryDto));
	}
	
	@PutMapping("/financialAssetCategories/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateFinancialAssetCategory(@PathVariable String financialAssetCategoryId, @RequestBody FinancialAssetCategoryDto financialAssetCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.financialAssetCategoryApplicationService.updateFinancialAssetCategory(financialAssetCategoryId, financialAssetCategoryDto));
	}
	
	@DeleteMapping("/financialAssetCategories/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteFinancialAssetCategory(@PathVariable String financialAssetCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.financialAssetCategoryApplicationService.removeFinancialAssetCategory(financialAssetCategoryId));
	}
	
	
	/*
	 * ItCategory
	 */
	@GetMapping("/itCategories/{itCategoryId}")
	@CrossOrigin
	public ResponseEntity<ItCategoryDto>  findItCategoryOfId(@PathVariable String itCategoryId) throws Exception{
		return ResponseEntity.ok().body((ItCategoryDto)this.itCategoryApplicationService.findItCategoryOfId(itCategoryId));
	}
	
	@GetMapping("/itCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ItCategoryDto>>  findItCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ItCategoryDto>)this.itCategoryApplicationService.findItCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/itCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<ItCategoryDto>>  findItCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<ItCategoryDto>)this.itCategoryApplicationService.findItCategoriesOfItType(applicationTypeId));
	}
	
	@GetMapping("/itCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ItCategoryDto>>  findItCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ItCategoryDto>)this.itCategoryApplicationService.findItCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/itCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createItCategory(@RequestBody ItCategoryDto itCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itCategoryApplicationService.addItCategory(itCategoryDto));
	}
	
	@PutMapping("/itCategories/{itCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateItCategory(@PathVariable String itCategoryId, @RequestBody ItCategoryDto itCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itCategoryApplicationService.updateItCategory(itCategoryId, itCategoryDto));
	}
	
	@DeleteMapping("/itCategories/{itCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteItCategory(@PathVariable String itCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itCategoryApplicationService.removeItCategory(itCategoryId));
	}
	
	/*
	 * PlaceCategory
	 */
	@GetMapping("/placeCategories/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<PlaceCategoryDto>  findPlaceCategoryOfId(@PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((PlaceCategoryDto)this.placeCategoryApplicationService.findPlaceCategoryOfId(placeCategoryId));
	}
	
	@GetMapping("/placeCategories/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceCategoryDto>>  findPlaceCategoriesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceCategoryDto>)this.placeCategoryApplicationService.findPlaceCategoriesOfCompany(companyId));
	}
	
	@GetMapping("/placeCategories/applicationType/{applicationTypeId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceCategoryDto>>  findPlaceCategoriesOfApplicationType(@PathVariable String applicationTypeId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceCategoryDto>)this.placeCategoryApplicationService.findPlaceCategoriesOfPlaceType(applicationTypeId));
	}
	
	@GetMapping("/placeCategories/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceCategoryDto>>  findPlaceCategoriesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceCategoryDto>)this.placeCategoryApplicationService.findPlaceCategoriesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/placeCategories")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPlaceCategory(@RequestBody PlaceCategoryDto placeCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeCategoryApplicationService.addPlaceCategory(placeCategoryDto));
	}
	
	@PutMapping("/placeCategories/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePlaceCategory(@PathVariable String placeCategoryId, @RequestBody PlaceCategoryDto placeCategoryDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeCategoryApplicationService.updatePlaceCategory(placeCategoryId, placeCategoryDto));
	}
	
	@DeleteMapping("/placeCategories/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePlaceCategory(@PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeCategoryApplicationService.removePlaceCategory(placeCategoryId));
	}
	
	/*
	 * Data
	 */
	@GetMapping("/data/{dataId}")
	@CrossOrigin
	public ResponseEntity<DataDto>  findDataOfId(@PathVariable String dataId) throws Exception{
		return ResponseEntity.ok().body((DataDto)this.dataApplicationService.findDataOfId(dataId));
	}
	
	@GetMapping("/data/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<DataDto>>  findPDataOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<DataDto>)this.dataApplicationService.findDataOfCompany(companyId));
	}
	
	@GetMapping("/data/dataCategory/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<DataDto>>  findDataOfDataCategory(@PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<DataDto>)this.dataApplicationService.findDataOfDataCategory(dataCategoryId));
	}
	
	@GetMapping("/data/work/{workId}")
	@CrossOrigin
	public ResponseEntity<List<DataDto>>  findDataOfWork(@PathVariable String workId) throws Exception{
		return ResponseEntity.ok().body((List<DataDto>)this.dataApplicationService.findDataOfWork(workId));
	}
	
	@PostMapping("/data")
	@CrossOrigin
	public ResponseEntity<MessageDto> createData(@RequestBody DataDto dataDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataApplicationService.addData(dataDto));
	}
	
	@PutMapping("/data")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateData(@RequestBody DataDto dataDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataApplicationService.updateData(dataDto));
	}
	
	@DeleteMapping("/data/{dataId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteData(@PathVariable String dataId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.dataApplicationService.removeData(dataId));
	}
	
	@GetMapping("/partnerFunctions/partnerCategory/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerFunctionDto>>  findPartnerFunctionsOfPartnerCategory(@PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerFunctionDto>)this.partnerFunctionService.findPartnerFunctionsOfPartnerCategory(partnerCategoryId));
	}
	
	
	/*
	 * Program
	 */
	@GetMapping("/program/business_unit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ProgramDto>>  findProgramsOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ProgramDto>)this.programService.findProgramsOfBusinessUnit(businessUnitId));
	}
	@GetMapping("/program/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ProgramDto>>  findProgramsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ProgramDto>)this.programService.findProgramsOfCompany(companyId));
	}
	
	@GetMapping("/program/indicator/{indicatorId}/indicatorType/{indicatorType}")
	@CrossOrigin
	public ResponseEntity<List<Indicator>>  findProgramIndicators(@PathVariable String indicatorId, @PathVariable IndicatorType indicatorType) throws Exception{
		return ResponseEntity.ok().body((List<Indicator>)this.programService.findProgramIndicators(indicatorId, indicatorType));
	}
	
	
	@PostMapping("/programs")
	@CrossOrigin
	public ResponseEntity<MessageDto> defineProgram(@RequestBody ProgramDto programDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.defineProgram(programDto));
	}
	
	@PutMapping("/programs")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateProgram(@RequestBody ProgramDto programDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.updateProgram(programDto));
	}
	
	@GetMapping("/programs/{programId}")
	@CrossOrigin
	public ResponseEntity<ProgramDto> findProgramOfId(@PathVariable String programId) throws Exception{
		return ResponseEntity.ok().body((ProgramDto)this.programService.findProgramDtoOfId(programId));
	}
	
	@DeleteMapping("/programs/{programId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteProgram(@PathVariable String programId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeProgram(programId));
	}
	
	@PostMapping("/programs/{programId}/customerCategory/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addCustomerCategoryToProgram(@PathVariable String programId, @PathVariable String customerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addCustomerCategory(programId, customerCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/customerCategory/{customerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeCustomerCategoryToProgram(@PathVariable String programId, @PathVariable String customerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeCustomerCategory(programId, customerCategoryId));
	}
	
	@PostMapping("/programs/{programId}/actionPlan/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addActionPlanToProgram(@PathVariable String programId, @PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addActionPlan(programId, actionPlanId));
	}
	
	@DeleteMapping("/programs/{programId}/actionPlan/{actionPlanId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeActionPlanToProgram(@PathVariable String programId, @PathVariable String actionPlanId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeActionPlan(programId, actionPlanId));
	}
	
	@PostMapping("/programs/{programId}/memberCategory/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addMemberCategoryToProgram(@PathVariable String programId, @PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addMemberCategory(programId, memberCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/memberCategory/{memberCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeMemberCategoryToProgram(@PathVariable String programId, @PathVariable String memberCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeMemberCategory(programId, memberCategoryId));
	}
	
	@PostMapping("/programs/{programId}/placeCategory/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addPlaceCategoryToProgram(@PathVariable String programId, @PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addPlaceCategory(programId, placeCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/placeCategory/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removePlaceCategoryToProgram(@PathVariable String programId, @PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removePlaceCategory(programId, placeCategoryId));
	}
	
	@PostMapping("/programs/{programId}/partnerCategory/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addPartnerCategoryToProgram(@PathVariable String programId, @PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addPartnerCategory(programId, partnerCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/partnerCategory/{partnerCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removePartnerCategoryToProgram(@PathVariable String programId, @PathVariable String partnerCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removePartnerCategory(programId, partnerCategoryId));
	}
	
	@PostMapping("/programs/{programId}/dataCategory/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addDataCategoryToProgram(@PathVariable String programId, @PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addDataCategory(programId, dataCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/dataCategory/{dataCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeDataCategoryToProgram(@PathVariable String programId, @PathVariable String dataCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeDataCategory(programId, dataCategoryId));
	}
	
	@PostMapping("/programs/{programId}/applicationCategory/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addApplicationCategoryToProgram(@PathVariable String programId, @PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addApplicationCategory(programId, applicationCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/applicationCategory/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeApplicationCategoryToProgram(@PathVariable String programId, @PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeApplicationCategory(programId, applicationCategoryId));
	}
	
	@PostMapping("/programs/{programId}/financialAssetCategory/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addFinancialAssetCategoryToProgram(@PathVariable String programId, @PathVariable String financialAssetCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addFinancialAssetCategory(programId, financialAssetCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/financialAssetCategory/{financialAssetCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeFinancialAssetCategoryToProgram(@PathVariable String programId, @PathVariable String financialAssetCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeFinancialAssetCategory(programId, financialAssetCategoryId));
	}
	
	@PostMapping("/programs/{programId}/productCategory/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> addProductProductToProgram(@PathVariable String programId, @PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addProductCategory(programId, productCategoryId));
	}
	
	@DeleteMapping("/programs/{programId}/productCategory/{productCategoryId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> removeProductCategoryToProgram(@PathVariable String programId, @PathVariable String productCategoryId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeProductCategory(programId, productCategoryId));
	}
	
	@PostMapping("/programs/{programId}/goal")
	@CrossOrigin
	public ResponseEntity<MessageDto> addGoalToProgram(@PathVariable String programId, @RequestBody Goal goal) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addGoal(programId, goal));
	}
	
	@PutMapping("/programs/{programId}/goal")
	@CrossOrigin
	public ResponseEntity<MessageDto> replaceGoalToProgram(@PathVariable String programId, @RequestBody ReplaceGoal goal) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.replaceGoal(programId, goal.getPrevious(), goal.getPost()));
	}
	
	@DeleteMapping("/programs/{programId}/goal")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteGoalToProgram(@PathVariable String programId, @RequestBody Goal goal) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeGoal(programId, goal));
	}
	
	
	
	@PostMapping("/programs/{programId}/achievement")
	@CrossOrigin
	public ResponseEntity<MessageDto> addAchievmentToProgram(@PathVariable String programId, @RequestBody Achievement achievement) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.addAchievement(programId, achievement));
	}
	
	@PutMapping("/programs/{programId}/achievement")
	@CrossOrigin
	public ResponseEntity<MessageDto> replaceAchievmentToProgram(@PathVariable String programId, @RequestBody ReplaceAchievement achievement) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.replaceAchievement(programId, achievement.getPrevious(), achievement.getPost()));
	}
	
	
	@DeleteMapping("/programs/{programId}/achievement")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteGoalToAchievementOfProgram(@PathVariable String programId, @RequestBody Achievement achievement) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.programService.removeAchievement(programId, achievement));
	}

	/*
	 * It
	 */
	@GetMapping("/its/{itId}")
	@CrossOrigin
	public ResponseEntity<ItDto>  findItOfId(@PathVariable String itId) throws Exception{
		return ResponseEntity.ok().body((ItDto)this.itApplicationService.findItOfId(itId));
	}
	
	@GetMapping("/its/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ItDto>>  findItsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ItDto>)this.itApplicationService.findItsOfCompany(companyId));
	}
	
	@GetMapping("/its/itCategory/{itCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<ItDto>>  findItsOfItCategory(@PathVariable String itCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<ItDto>)this.itApplicationService.findItsOfItCategory(itCategoryId));
	}
	
	@GetMapping("/its/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ItDto>>  findItsOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ItDto>)this.itApplicationService.findItsOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/its")
	@CrossOrigin
	public ResponseEntity<MessageDto> createIt(@RequestBody ItDto itDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itApplicationService.addIt(itDto));
	}
	
	@PutMapping("/its/{itId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateIt(@RequestBody ItDto itDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itApplicationService.updateIt(itDto));
	}
	
	@DeleteMapping("/its/{itId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteIt(@PathVariable String itId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.itApplicationService.removeIt(itId));
	}
	
	
	/*
	 * Application
	 */
	@GetMapping("/applications/{applicationId}")
	@CrossOrigin
	public ResponseEntity<ApplicationDto>  findApplicationOfId(@PathVariable String applicationId) throws Exception{
		return ResponseEntity.ok().body((ApplicationDto)this.applicationApplicationService.findApplicationOfId(applicationId));
	}
	
	@GetMapping("/applications/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationDto>>  findApplicationsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationDto>)this.applicationApplicationService.findApplicationsOfCompany(companyId));
	}
	
	@GetMapping("/applications/applicationCategory/{applicationCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationDto>>  findApplicationsOfApplicationCategory(@PathVariable String applicationCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationDto>)this.applicationApplicationService.findApplicationsOfApplicationCategory(applicationCategoryId));
	}
	
	@GetMapping("/applications/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<ApplicationDto>>  findApplicationsOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<ApplicationDto>)this.applicationApplicationService.findApplicationsOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/applications")
	@CrossOrigin
	public ResponseEntity<MessageDto> createApplication(@RequestBody ApplicationDto applicationDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationApplicationService.addApplication(applicationDto));
	}
	
	@PutMapping("/applications/{applicationId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updateApplication(@RequestBody ApplicationDto applicationDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationApplicationService.updateApplication(applicationDto));
	}
	
	@DeleteMapping("/applications/{applicationId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deleteApplication(@PathVariable String applicationId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.applicationApplicationService.removeApplication(applicationId));
	}
	
	
	
	/*
	 * Place
	 */
	@GetMapping("/places/{placeId}")
	@CrossOrigin
	public ResponseEntity<PlaceDto>  findPlaceOfId(@PathVariable String placeId) throws Exception{
		return ResponseEntity.ok().body((PlaceDto)this.placeService.findPlaceOfId(placeId));
	}
	
	@GetMapping("/places/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceDto>>  findPlacesOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceDto>)this.placeService.findPlacesOfCompany(companyId));
	}
	
	@GetMapping("/places/placeCategory/{placeCategoryId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceDto>>  findPlacesOfPlaceCategory(@PathVariable String placeCategoryId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceDto>)this.placeService.findPlacesOfPlaceCategory(placeCategoryId));
	}
	
	@GetMapping("/places/businessUnit/{businessUnitId}")
	@CrossOrigin
	public ResponseEntity<List<PlaceDto>>  findPlacesOfBusinessUnit(@PathVariable String businessUnitId) throws Exception{
		return ResponseEntity.ok().body((List<PlaceDto>)this.placeService.findPlacesOfBusinessUnit(businessUnitId));
	}
	
	@PostMapping("/places")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPlace(@RequestBody PlaceDto placeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeService.addPlace(placeDto));
	}
	
	@PutMapping("/places/{placeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePlace(@RequestBody PlaceDto placeDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeService.updatePlace(placeDto));
	}
	
	@DeleteMapping("/places/{placeId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePlace(@PathVariable String placeId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.placeService.removePlace(placeId));
	}
	
	
	/*
	 * PartnerFunction
	 */
	@GetMapping("/partnerFunctions/{partnerFunctionId}")
	@CrossOrigin
	public ResponseEntity<PartnerFunctionDto>  findPartnerFunctionOfId(@PathVariable String partnerFunctionId) throws Exception{
		return ResponseEntity.ok().body((PartnerFunctionDto)this.partnerFunctionService.findPartnerFunctionOfId(partnerFunctionId));
	}
	
	@GetMapping("/partnerFunctions/company/{companyId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerFunctionDto>>  findPartnerFunctionsOfCompany(@PathVariable String companyId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerFunctionDto>)this.partnerFunctionService.findPartnerFunctionsOfCompany(companyId));
	}
	
	@GetMapping("/partnerFunctions/partnerTask/{partnerTaskId}")
	@CrossOrigin
	public ResponseEntity<List<PartnerFunctionDto>>  findPartnerFunctionOfPartnerTask(@PathVariable String partnerTaskId) throws Exception{
		return ResponseEntity.ok().body((List<PartnerFunctionDto>)this.partnerFunctionService.findPartnerFunctionsOfPartnerTask(partnerTaskId));
	}
	
	@PostMapping("/partnerFunctions")
	@CrossOrigin
	public ResponseEntity<MessageDto> createPartnerFunction(@RequestBody PartnerFunctionDto partnerFunctionDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerFunctionService.addPartnerFunction(partnerFunctionDto));
	}
	
	@PutMapping("/partnerFunctions")
	@CrossOrigin
	public ResponseEntity<MessageDto> updatePartnerFunction(@RequestBody PartnerFunctionDto partnerFunctionDto) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerFunctionService.updatePartnerFunction(partnerFunctionDto));
	}
	
	@DeleteMapping("/partnerFunctions/{partnerFunctionId}")
	@CrossOrigin
	public ResponseEntity<MessageDto> deletePartnerFunction(@PathVariable String partnerFunctionId) throws Exception{
		return ResponseEntity.ok().body((MessageDto)this.partnerFunctionService.removePartnerFunction(partnerFunctionId));
	}
	
	
	
	
	
	
	
	
	
	
}
