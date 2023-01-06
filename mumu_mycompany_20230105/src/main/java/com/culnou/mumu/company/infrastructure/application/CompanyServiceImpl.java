package com.culnou.mumu.company.infrastructure.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.application.CompanyService;
import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.ActionPlanRepository;
import com.culnou.mumu.company.domain.model.ActionPlanService;
import com.culnou.mumu.company.domain.model.ActionRepository;
import com.culnou.mumu.company.domain.model.Address;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.BusinessDomainService;
import com.culnou.mumu.company.domain.model.BusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessProcessRepository;
import com.culnou.mumu.company.domain.model.BusinessProcessService;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.BusinessUnitService;
import com.culnou.mumu.company.domain.model.Company;
import com.culnou.mumu.company.domain.model.CompanyFactory;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CompanyRepository;
import com.culnou.mumu.company.domain.model.CustomerCategory;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerCategoryRepository;
import com.culnou.mumu.company.domain.model.CustomerCategoryService;
import com.culnou.mumu.company.domain.model.CustomerType;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.CustomerTypeRepository;
import com.culnou.mumu.company.domain.model.CustomerTypeService;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentRepository;
import com.culnou.mumu.company.domain.model.DepartmentService;
import com.culnou.mumu.company.domain.model.DepartmentType;
import com.culnou.mumu.company.domain.model.DomainName;
import com.culnou.mumu.company.domain.model.Function;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Industry;
import com.culnou.mumu.company.domain.model.IndustryGroup;
import com.culnou.mumu.company.domain.model.IndustrySubGroup;
import com.culnou.mumu.company.domain.model.Job;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobRepository;
import com.culnou.mumu.company.domain.model.JobService;
import com.culnou.mumu.company.domain.model.JobType;
import com.culnou.mumu.company.domain.model.ProductFunction;
import com.culnou.mumu.company.domain.model.ProductFunctionId;
import com.culnou.mumu.company.domain.model.ProductFunctionRepository;
import com.culnou.mumu.company.domain.model.ProductIndicatorService;
import com.culnou.mumu.company.domain.model.ProductCategory;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductCategoryRepository;
import com.culnou.mumu.company.domain.model.ProductCategoryService;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductTask;
import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTaskRepository;
import com.culnou.mumu.company.domain.model.ProductTaskService;
import com.culnou.mumu.company.domain.model.ProductType;
import com.culnou.mumu.company.domain.model.ProductTypeId;
import com.culnou.mumu.company.domain.model.ProductTypeRepository;
import com.culnou.mumu.company.domain.model.ProductTypeService;
import com.culnou.mumu.company.domain.model.Role;
import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.TaskService;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskService;

import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeService;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.project.ProjectId;

import com.culnou.mumu.company.domain.service.DepartmentChecker;
import com.culnou.mumu.compnay.controller.ActionDto;
import com.culnou.mumu.compnay.controller.ActionPlanDto;
import com.culnou.mumu.compnay.controller.ApplicationTaskDto;
import com.culnou.mumu.compnay.controller.ApplicationTypeDto;
import com.culnou.mumu.compnay.controller.BusinessDomainDto;
import com.culnou.mumu.compnay.controller.BusinessProcessDto;
import com.culnou.mumu.compnay.controller.BusinessUnitDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.CustomerCategoryDto;
import com.culnou.mumu.compnay.controller.CustomerTypeDto;
import com.culnou.mumu.compnay.controller.DepartmentDto;
import com.culnou.mumu.compnay.controller.JobDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductFunctionDto;
import com.culnou.mumu.compnay.controller.ProductCategoryDto;
import com.culnou.mumu.compnay.controller.ProductTaskDto;
import com.culnou.mumu.compnay.controller.ProductTypeDto;
import com.culnou.mumu.compnay.controller.TaskDto;


@Service("companyServiceImpl")
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Qualifier("companyJpaRepository")
	@Autowired
	private CompanyRepository companyRepository;
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	
	@Qualifier("customerTypeJpaRepository")
	@Autowired
	private CustomerTypeRepository customerTypeRepository;
	
	@Qualifier("productTypeJpaRepository")
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Qualifier("productTaskJpaRepository")
	@Autowired
	private ProductTaskRepository productTaskRepository;
	
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Qualifier("jobJpaRepository")
	@Autowired
	private JobRepository jobRepository;
	
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	@Qualifier("productFunctionJpaRepository")
	@Autowired
	private ProductFunctionRepository productFunctionRepository;
	
	@Autowired
	private BusinessProcessService businessProcessService;
	
	@Autowired
	private ActionPlanService actionPlanService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductTaskService productTaskService;
	
	@Autowired
	private ProductIndicatorService productIndicatorService;
	
	@Autowired
	private CustomerTypeService customerTypeService;
	
	@Autowired
	private BusinessDomainService businessDomainService;
	
	@Autowired
	private CustomerCategoryService customerCategoryService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private BusinessUnitService businessUnitService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ApplicationTypeService applicationTypeService;
	
	@Autowired
	private ApplicationTaskService applicationTaskService;
	
	@Autowired
	private DepartmentChecker departmentChecker;
	
	
	
	
	
	
	
	/*
	 * コンバート処理
	 */
	private BusinessDomainDto convertBusinessDomainToBusinessDomainDto(BusinessDomain businessDomain) {
		BusinessDomainDto dto = new BusinessDomainDto();
		dto.setBusinessDomainId(businessDomain.businessDomainId().businessDomainId());
		dto.setCompanyId(businessDomain.companyId().id());
		if(businessDomain.businessDomainName() != null) {
			dto.setBusinessDomainName(businessDomain.businessDomainName());
		}
		if(businessDomain.industryGroup() != null) {
			dto.setIndustryGroupId(businessDomain.industryGroup().industryGroupId());
			dto.setIndustryGroupName(businessDomain.industryGroup().industryGroupName());
		}
		if(businessDomain.industry() != null) {
			dto.setIndustryId(businessDomain.industry().id());
			dto.setIndustryName(businessDomain.industry().name());
		}
		if(businessDomain.industrySubGroup() != null) {
			dto.setIndustrySubGroupId(businessDomain.industrySubGroup().id());
			dto.setIndustrySubGroupName(businessDomain.industrySubGroup().name());
		}
		if(businessDomain.purpose() != null) {
			dto.setPurpose(businessDomain.purpose());
		}
		dto.setEnterprise(businessDomain.isEnterprise());
		if(businessDomain.url() != null) {
			dto.setUrl(businessDomain.url().getUrl());
		}
		if(businessDomain.getBusinessModel() != null) {
			dto.setBusinessModel(businessDomain.getBusinessModel());
		}
		if(businessDomain.getCustomerType() != null) {
			dto.setCustomerType(businessDomain.getCustomerType());
		}
		if(businessDomain.getProductType() != null) {
			dto.setProductType(businessDomain.getProductType());
		}
		if(businessDomain.getAssociatedProductTypes() != null) {
			dto.setAssociatedProductTypes(businessDomain.getAssociatedProductTypes());	
		}
		if(businessDomain.getAssociatedCustomerTypes() != null) {
			dto.setAssociatedCustomerTypes(businessDomain.getAssociatedCustomerTypes());	
		}
		if(businessDomain.getAssociatedUrls() != null) {
			dto.setAssociatedUrls(businessDomain.getAssociatedUrls());	
		}
		if(businessDomain.customerTypeId() != null) {
			dto.setCustomerTypeId(businessDomain.customerTypeId().customerTypeId());
		}
		if(businessDomain.customerTypeName() != null) {
			dto.setCustomerTypeName(businessDomain.customerTypeName());
		}
		if(businessDomain.productTypeId() != null) {
			dto.setProductTypeId(businessDomain.productTypeId().productTypeId());
		}
		if(businessDomain.productTypeName() != null) {
			dto.setProductTypeName(businessDomain.productTypeName());
		}
		if(businessDomain.getIndicators() != null) {
			dto.setIndicators(businessDomain.getIndicators());
		}
		return dto;
	}
	
	private List<BusinessDomainDto> convertBusinessDomainsToBusinessDomainDtos(List<BusinessDomain> businessDomains){
		List<BusinessDomainDto> dtos = new ArrayList<>();
		for(BusinessDomain businessDomain : businessDomains) {
			dtos.add(this.convertBusinessDomainToBusinessDomainDto(businessDomain));
		}
		return dtos;
	}
	
	private BusinessUnitDto convertBusinessUnitToBusinessUnitDto(BusinessUnit businessUnit) {
		BusinessUnitDto dto = new BusinessUnitDto();
		dto.setBusinessUnitId(businessUnit.businessUnitId().businessUnitId());
		dto.setBusinessDomainId(businessUnit.businessDomainId().businessDomainId());
		dto.setBusinessDomainName(businessUnit.businessDomainName());
		dto.setCompanyId(businessUnit.companyId().id());
		if(businessUnit.businessUnitName() != null) {
			dto.setBusinessUnitName(businessUnit.businessUnitName());
		}
		if(businessUnit.vision() != null) {
			dto.setVision(businessUnit.vision());
		}
		if(businessUnit.getHypothesis() != null) {
			dto.setHypothesis(businessUnit.getHypothesis());
		}
		if(businessUnit.getResult() != null) {
			dto.setResult(businessUnit.getResult());
		}
		if(businessUnit.getBusinessStrategy() != null) {
			dto.setBusinessStrategy(businessUnit.getBusinessStrategy());
		}
		if(businessUnit.getCustomerCategory() != null) {
			dto.setCustomerCategory(businessUnit.getCustomerCategory());
		}
		if(businessUnit.getProductCategory() != null) {
			dto.setProductCategory(businessUnit.getProductCategory());
		}
		if(businessUnit.getBusinessState() != null) {
			dto.setBusinessState(businessUnit.getBusinessState());
		}
		
		if(businessUnit.customerCategoryId() != null) {
			dto.setCustomerCategoryId(businessUnit.customerCategoryId().customerCategoryId());
		}
		if(businessUnit.customerCategoryName() != null) {
			dto.setCustomerCategoryName(businessUnit.customerCategoryName());
		}
		if(businessUnit.productCategoryId() != null) {
			dto.setProductCategoryId(businessUnit.productCategoryId().productCategoryId());
		}
		if(businessUnit.getUrl() != null) {
			dto.setUrl(businessUnit.getUrl().getUrl());
		}
		
		if(businessUnit.productCategoryName() != null) {
			dto.setProductCategoryName(businessUnit.productCategoryName());
		}
		if(businessUnit.getAssociatedProductCategories() != null) {
			dto.setAssociatedProductCategories(businessUnit.getAssociatedProductCategories());	
		}
		
		if(businessUnit.getAssociatedCustomerCategories() != null) {
			dto.setAssociatedCustomerCategories(businessUnit.getAssociatedCustomerCategories());	
		}
		
		if(businessUnit.getGoals() != null) {
			dto.setGoals(businessUnit.getGoals());	
		}
		
		if(businessUnit.getAchievements() != null) {
			dto.setAchievements(businessUnit.getAchievements());	
		}
		if(businessUnit.getIndustry() != null) {
			dto.setIndustryId(businessUnit.getIndustry().id());
			dto.setIndustryName(businessUnit.getIndustry().name());	
		}
		if(businessUnit.getStartDate() != null) {
			dto.setStartDate(businessUnit.getStartDate());		
		}
		if(businessUnit.getEndDate() != null) {
			dto.setEndDate(businessUnit.getEndDate());		
		}
		
		
		return dto;
	}
	
	private List<BusinessUnitDto> convertBusinessUnitsToBusinessUnitDtos(List<BusinessUnit> businessUnits){
		List<BusinessUnitDto> dtos = new ArrayList<>();
		for(BusinessUnit businessUnit : businessUnits) {
			dtos.add(this.convertBusinessUnitToBusinessUnitDto(businessUnit));
		}
		return dtos;
	}
	
	private CustomerTypeDto convertCustomerTypeToCustomerTypeDto(CustomerType customerType) {
		CustomerTypeDto dto = new CustomerTypeDto();
		dto.setCustomerTypeId(customerType.customerTypeId().customerTypeId());
		dto.setCompanyId(customerType.companyId().id());
		if(customerType.customerTypeName() != null) {
			dto.setCustomerTypeName(customerType.customerTypeName());
		}
		if(customerType.values() != null) {
			dto.setValues(customerType.values());
		}
		if(customerType.getPersonality() != null) {
			dto.setPersonality(customerType.getPersonality());
		}
		if(customerType.getCause() != null) {
			dto.setCause(customerType.getCause());
		}
		if(customerType.getIssue() != null) {
			dto.setIssue(customerType.getIssue());
		}
		if(customerType.problem() != null) {
			dto.setProblem(customerType.problem());
		}
		if(customerType.getUrl() != null) {
			dto.setUrl(customerType.getUrl().getUrl());
		}
		if(customerType.getIndicators() != null) {
			dto.setIndicators(customerType.getIndicators());
		}
		if(customerType.getAttributes() != null) {
			dto.setAttributes(customerType.getAttributes());
		}
		if(customerType.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(customerType.getBusinessDomainId().businessDomainId());
		}
		if(customerType.getEntityName() != null) {
			dto.setEntityName(customerType.getEntityName());
		}
		if(customerType.getEntityEnglishName() != null) {
			dto.setEntityEnglishName(customerType.getEntityEnglishName());
		}
		if(customerType.getEntityDescription() != null) {
			dto.setEntityDescription(customerType.getEntityDescription());
		}
		if(customerType.getDataOwner() != null) {
			dto.setDataOwner(customerType.getDataOwner());
		}
		if(customerType.getAddress() != null) {
			dto.setAddress(customerType.getAddress().address());
		}
		if(customerType.getAssociatedConstraint() != null) {
			dto.setAssociatedConstraint(customerType.getAssociatedConstraint());
		}
		if(customerType.getExistenceConstraint() != null) {
			dto.setExistenceConstraint(customerType.getExistenceConstraint());
		}
		if(customerType.getDataAmount() != null) {
			dto.setDataAmount(customerType.getDataAmount());
		}
		if(customerType.getDataTypeId() != null) {
			dto.setDataTypeId(customerType.getDataTypeId().getDataTypeId());
		}
		
		return dto;
	}
	
	private List<CustomerTypeDto> convertCustomerTypesToCustomerTypeDtos(List<CustomerType> customerTypes){
		List<CustomerTypeDto> dtos = new ArrayList<>();
		for(CustomerType customerType : customerTypes) {
			dtos.add(this.convertCustomerTypeToCustomerTypeDto(customerType));
		}
		return dtos;
	}
	
	private ProductTypeDto convertProductTypeToProductTypeDto(ProductType productType) {
		ProductTypeDto dto = new ProductTypeDto();
		dto.setProductTypeId(productType.productTypeId().productTypeId());
		dto.setCompanyId(productType.companyId().id());
		if(productType.productTypeName() != null) {
			dto.setProductTypeName(productType.productTypeName());
		}
		if(productType.customerTypeId() != null) {
			dto.setCustomerTypeId(productType.customerTypeId().customerTypeId());
		}
		if(productType.customerTypeName() != null) {
			dto.setCustomerTypeName(productType.customerTypeName());
		}
		if(productType.getProductClass() != null) {
			dto.setProductClass(productType.getProductClass());
		}
		if(productType.getValueProposition() != null) {
			dto.setValueProposition(productType.getValueProposition());
		}
		if(productType.solution() != null) {
			dto.setSolution(productType.solution());
		}
		if(productType.problem() != null) {
			dto.setProblem(productType.problem());
		}
		if(productType.getUrl() != null) {
			dto.setUrl(productType.getUrl().getUrl());
		}
		if(productType.getIndicators() != null) {
			dto.setIndicators(productType.getIndicators());
		}
		if(productType.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(productType.getBusinessDomainId().businessDomainId());
		}
		if(productType.getAttributes() != null) {
			dto.setAttributes(productType.getAttributes());
		}
		if(productType.getEntityName() != null) {
			dto.setEntityName(productType.getEntityName());
		}
		if(productType.getEntityEnglishName() != null) {
			dto.setEntityEnglishName(productType.getEntityEnglishName());
		}
		if(productType.getEntityDescription() != null) {
			dto.setEntityDescription(productType.getEntityDescription());
		}
		if(productType.getDataOwner() != null) {
			dto.setDataOwner(productType.getDataOwner());
		}
		if(productType.getAddress() != null) {
			dto.setAddress(productType.getAddress().address());
		}
		if(productType.getAssociatedConstraint() != null) {
			dto.setAssociatedConstraint(productType.getAssociatedConstraint());
		}
		if(productType.getExistenceConstraint() != null) {
			dto.setExistenceConstraint(productType.getExistenceConstraint());
		}
		if(productType.getDataAmount() != null) {
			dto.setDataAmount(productType.getDataAmount());
		}
		if(productType.getDataTypeId() != null) {
			dto.setDataTypeId(productType.getDataTypeId().getDataTypeId());
		}
	
		
		return dto;
	}
	
	private List<ProductTypeDto> convertProductTypesToProductTypeDtos(List<ProductType> productTypes){
		List<ProductTypeDto> dtos = new ArrayList<>();
		for(ProductType productType : productTypes) {
			dtos.add(this.convertProductTypeToProductTypeDto(productType));
		}
		return dtos;
	}
	
	ProductTaskDto convertProductTaskToProductTaskDto(ProductTask productTask) {
		ProductTaskDto dto = new ProductTaskDto();
		dto.setProductTaskId(productTask.getProductTaskId().getProductTaskId());
		dto.setCompanyId(productTask.getCompanyId().id());
		dto.setProductTypeId(productTask.getProductTypeId().productTypeId());
		dto.setProductTypeName(productTask.getProductTypeName());
		dto.setProductTaskName(productTask.getProductTaskName());
		if(productTask.getProductTaskDescription() != null) {
			dto.setProductTaskDescription(productTask.getProductTaskDescription());
		}
		if(productTask.getUrl() != null) {
			dto.setUrl(productTask.getUrl().getUrl());
		}
		return dto;
	}
	private List<ProductTaskDto> convertProductTasksToProductTaskDtos(List<ProductTask> productTasks){
		List<ProductTaskDto> dtos = new ArrayList<>();
		for(ProductTask productTask : productTasks) {
			dtos.add(this.convertProductTaskToProductTaskDto(productTask));
		}
		return dtos;
	}
	
	ProductFunctionDto convertProductFunctionToProductFunctionDto(ProductFunction productFunction) {
		ProductFunctionDto dto = new ProductFunctionDto();
		dto.setProductFunctionId(productFunction.getProductFunctionId().getProductFunctionId());
		dto.setCompanyId(productFunction.getCompanyId().id());
		dto.setProductCategoryId(productFunction.getProductCategoryId().productCategoryId());
		dto.setProductCategoryName(productFunction.getProductCategoryName());
		dto.setProductTaskId(productFunction.getProductTaskId().getProductTaskId());
		dto.setProductTaskName(productFunction.getProductTaskName());
		dto.setProductFunctionName(productFunction.getProductFunctionName());
		if(productFunction.getProductFunctionDescription() != null) {
			dto.setProductFunctionDescription(productFunction.getProductFunctionDescription());
		}
		if(productFunction.getUrl() != null) {
			dto.setUrl(productFunction.getUrl().getUrl());
		}
		if(productFunction.getFunction() != null) {
			dto.setFunctionId(productFunction.getFunction().getFunctionId());
			dto.setFunctionName(productFunction.getFunction().getFunctionName());	
		}
		return dto;
	}
	private List<ProductFunctionDto> convertProductFunctionsToProductFunctionDtos(List<ProductFunction> productFunctions){
		List<ProductFunctionDto> dtos = new ArrayList<>();
		for(ProductFunction productFunction : productFunctions) {
			dtos.add(this.convertProductFunctionToProductFunctionDto(productFunction));
		}
		return dtos;
	}
	
	private CustomerCategoryDto convertCustomerCategoryToCustomerCategoryDto(CustomerCategory customerCategory) {
		CustomerCategoryDto dto = new CustomerCategoryDto();
		dto.setCustomerCategoryId(customerCategory.customerCategoryId().customerCategoryId());
		dto.setCompanyId(customerCategory.companyId().id());
		dto.setCustomerTypeId(customerCategory.customerTypeId().customerTypeId());
		if(customerCategory.customerTypeName() != null) {
			dto.setCustomerTypeName(customerCategory.customerTypeName());
		}
		if(customerCategory.customerCategoryName() != null) {
			dto.setCustomerCategoryName(customerCategory.customerCategoryName());
		}
		if(customerCategory.getCustomerCategoryDescription() != null) {
			dto.setCustomerCategoryDescription(customerCategory.getCustomerCategoryDescription());
		}
		if(customerCategory.getUrl() != null) {
			dto.setUrl(customerCategory.getUrl().getUrl());
		}
		if(customerCategory.industryGroup() != null) {
			dto.setIndustryGroupId(customerCategory.industryGroup().industryGroupId());
			dto.setIndustryGroupName(customerCategory.industryGroup().industryGroupName());
		}
		if(customerCategory.industry() != null) {
			dto.setIndustryId(customerCategory.industry().id());
			dto.setIndustryName(customerCategory.industry().name());
		}
		if(customerCategory.industrySubGroup() != null) {
			dto.setIndustrySubGroupId(customerCategory.industrySubGroup().id());
			dto.setIndustrySubGroupName(customerCategory.industrySubGroup().name());
		}
		
		if(customerCategory.gender() != null) {
			dto.setGender(customerCategory.gender());
		}
		if(customerCategory.personality() != null) {
			dto.setPersonality(customerCategory.personality());
		}
		if(customerCategory.size() != null) {
			dto.setSize(customerCategory.size());
		}
		if(customerCategory.countries() != null) {
			dto.setCountries(customerCategory.countries());
		}
		if(customerCategory.ages() != null) {
			dto.setAges(customerCategory.ages());
		}
		if(customerCategory.getGoals() != null) {
			dto.setGoals(customerCategory.getGoals());
		}
		if(customerCategory.getAchievements() != null) {
			dto.setAchievements(customerCategory.getAchievements());
		}
		if(customerCategory.getBusinessUnitId() != null) {
			dto.setBusinessUnitId(customerCategory.getBusinessUnitId().businessUnitId());
		}
		
		return dto;
	}
	private List<CustomerCategoryDto> convertCustomerCategoriesToCustomerTypeDtos(List<CustomerCategory> customerCategories){
		List<CustomerCategoryDto> dtos = new ArrayList<>();
		for(CustomerCategory customerCategory : customerCategories) {
			dtos.add(this.convertCustomerCategoryToCustomerCategoryDto(customerCategory));
		}
		return dtos;
	}
	
	private ProductCategoryDto convertProductCategoryToProductCategoryDto(ProductCategory productCategory) {
		ProductCategoryDto dto = new ProductCategoryDto();
		dto.setProductCategoryId(productCategory.productCategoryId().productCategoryId());
		dto.setCompanyId(productCategory.companyId().id());
		dto.setProductTypeId(productCategory.productTypeId().productTypeId());
		if(productCategory.productTypeName() != null) {
			dto.setProductTypeName(productCategory.productTypeName());
		}
		if(productCategory.productCategoryName() != null) {
			dto.setProductCategoryName(productCategory.productCategoryName());
		}
		if(productCategory.productCategoryDescription() != null) {
			dto.setProductCategoryDescription(productCategory.productCategoryDescription());
		}
		if(productCategory.productCategoryPosition() != null) {
			dto.setProductCategoryPosition(productCategory.productCategoryPosition());
		}
		if(productCategory.customerCategoryId() != null) {
			dto.setCustomerCategoryId(productCategory.customerCategoryId().customerCategoryId());
		}
		if(productCategory.customerCategoryName() != null) {
			dto.setCustomerCategoryName(productCategory.customerCategoryName());
		}
		
		if(productCategory.industryGroup() != null) {
			dto.setIndustryGroupId(productCategory.industryGroup().industryGroupId());
			dto.setIndustryGroupName(productCategory.industryGroup().industryGroupName());
		}
		if(productCategory.industry() != null) {
			dto.setIndustryId(productCategory.industry().id());
			dto.setIndustryName(productCategory.industry().name());
		}
		if(productCategory.industrySubGroup() != null) {
			dto.setIndustrySubGroupId(productCategory.industrySubGroup().id());
			dto.setIndustrySubGroupName(productCategory.industrySubGroup().name());
		}
		
		if(productCategory.productClass() != null) {
			dto.setProductClass(productCategory.productClass());
		}
		
		if(productCategory.roles() != null) {
			dto.setRoles(productCategory.roles());
		}
		
		if(productCategory.goodses() != null) {
			dto.setGoodses(productCategory.goodses());
		}
		
		if(productCategory.services() != null) {
			dto.setServices(productCategory.services());
		}
		if(productCategory.personality() != null) {
			dto.setPersonality(productCategory.personality());
		}
		if(productCategory.serviceType() != null) {
			dto.setServiceType(productCategory.serviceType());
		}
		if(productCategory.service() != null) {
			dto.setService(productCategory.service());
		}
		if(productCategory.getGoals() != null) {
			dto.setGoals(productCategory.getGoals());
		}
		if(productCategory.getAchievements() != null) {
			dto.setAchievements(productCategory.getAchievements());
		}
		if(productCategory.getUrl() != null) {
			dto.setUrl(productCategory.getUrl().getUrl());
		}
		if(productCategory.getCustomerJourney() != null) {
			dto.setCustomerJourney(productCategory.getCustomerJourney());
		}
		if(productCategory.getServiceScenario() != null) {
			dto.setServiceScenario(productCategory.getServiceScenario());
		}
		if(productCategory.getDomainModel() != null) {
			dto.setDomainModel(productCategory.getDomainModel());
		}
		if(productCategory.getDemo() != null) {
			dto.setDemo(productCategory.getDemo());
		}
		if(productCategory.getBusinessUnitId() != null) {
			dto.setBusinessUnitId(productCategory.getBusinessUnitId().businessUnitId());
		}
		
		
		return dto;
	}
	private List<ProductCategoryDto> convertProductCategoriesToProductCategoryDtos(List<ProductCategory> productCategories){
		List<ProductCategoryDto> dtos = new ArrayList<>();
		for(ProductCategory productCategory : productCategories) {
			dtos.add(this.convertProductCategoryToProductCategoryDto(productCategory));
		}
		return dtos;
	}
	
	//Jobの変換
	private JobDto convertJobToJobDto(Job job) {
		JobDto dto = new JobDto();
		dto.setJobId(job.getJobId().jobId());
		dto.setCompanyId(job.getCompanyId().id());
		dto.setJobType(job.getJobType());
		dto.setJobName(job.getJobName());
		dto.setRoles(job.getRoles());
		//任意項目の場合NULLのチェックをする。
		if(job.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(job.getBusinessDomainId().businessDomainId());
		}
		if(job.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(job.getBusinessDomainName());
		}
		if(job.getJobDescription() != null) {
			dto.setJobDescription(job.getJobDescription());
		}
		if(job.getUrl() != null) {
			dto.setUrl(job.getUrl().getUrl());
		}
		if(job.getIndicators() != null) {
			dto.setIndicators(job.getIndicators());
		}
		return dto;
	}
	
	private List<JobDto> convertJobsToJobDtos(List<Job> jobs){
		List<JobDto> dtos = new ArrayList<>();
		for(Job job : jobs) {
			dtos.add(this.convertJobToJobDto(job));
		}
		return dtos;
	}
	
	//Taskの変換
	TaskDto convertTaskToTaskDto(Task task) {
		TaskDto dto = new TaskDto();
		dto.setTaskId(task.getTaskId().taskId());
		dto.setCompanyId(task.getCompanyId().id());
		dto.setJobId(task.getJobId().jobId());
		dto.setJobName(task.getJobName());
		dto.setFunctionId(task.getFunction().getFunctionId());
		dto.setFunctionName(task.getFunction().getFunctionName());
		dto.setTaskName(task.getTaskName());
		if(task.getTaskDescription() != null) {
			dto.setTaskDescription(task.getTaskDescription());
		}
		if(task.getUrl() != null) {
			dto.setUrl(task.getUrl().getUrl());
		}
		dto.setAssociatedDataTypes(task.getAssociatedDataTypes());
		
		return dto;
	}
	private List<TaskDto> convertTasksToTaskDtos(List<Task> tasks){
		List<TaskDto> dtos = new ArrayList<>();
		for(Task task : tasks) {
			dtos.add(this.convertTaskToTaskDto(task));
		}
		return dtos;
	}
	
	//Departmentの変換
	private DepartmentDto convertDepartmentToDepartmentDto(Department department) {
		DepartmentDto dto = new DepartmentDto();
		dto.setDepartmentId(department.getDepartmentId().departmentId());
		dto.setCompanyId(department.getCompanyId().id());
		dto.setDepartmentType(department.getDepartmentType());
		dto.setJobId(department.getJobId().jobId());
		dto.setJobName(department.getJobName());
		dto.setDepartmentName(department.getDepartmentName());
		if(department.getBusinessUnitId() != null) {
			dto.setBusinessUnitId(department.getBusinessUnitId().businessUnitId());
		}
		if(department.getBusinessUnitName() != null) {
			dto.setBusinessUnitName(department.getBusinessUnitName());
		}
		if(department.getDepartmentDescription() != null) {
			dto.setDepartmentDescription(department.getDepartmentDescription());
		}
		if(department.getUrl() != null) {
			dto.setUrl(department.getUrl().getUrl());		
		}
		if(department.getGoals() != null) {
			dto.setGoals(department.getGoals());
		}
		if(department.getAchievements() != null) {
			dto.setAchievements(department.getAchievements());
		}
		dto.setAssociatedApplicationCategories(department.getAssociatedApplicationCategories());
		dto.setAssociatedPartnerCategories(department.getAssociatedPartnerCategories());
		
		
		return dto;
	}
	
	private List<DepartmentDto> convertDepartmentsToDepartmentDtos(List<Department> departments){
		List<DepartmentDto> dtos = new ArrayList<>();
		for(Department department : departments) {
			dtos.add(this.convertDepartmentToDepartmentDto(department));
		}
		return dtos;
	}
	
	//Actionの変換
	private ActionDto convertActionToActionDto(Action action) {
		ActionDto dto = new ActionDto();
		dto.setActionId(action.getActionId().actionId());
		dto.setCompanyId(action.getCompanyId().id());
		dto.setTaskId(action.getTaskId().taskId());
		dto.setTaskName(action.getTaskName());
		dto.setDepartmentId(action.getDepartmentId().departmentId());
		dto.setDepartmentName(action.getDepartmentName());
		dto.setActionName(action.getActionName());
		if(action.getActionDescription() != null) {
			dto.setActionDescription(action.getActionDescription());
		}
		if(action.getUrl() != null) {
			dto.setUrl(action.getUrl().getUrl());
		}
		if(action.getApplicationUrl() != null) {
			dto.setApplicationUrl(action.getApplicationUrl());
		}
		if(action.getApplicationProductId() != null) {
			dto.setApplicationProductId(action.getApplicationProductId());
		}
		if(action.getPersonUrl() != null) {
			dto.setPersonUrl(action.getPersonUrl());
		}
		if(action.getPersonProductId() != null) {
			dto.setPersonProductId(action.getPersonProductId());
		}
		if(action.getApplicationCategoryId() != null) {
			dto.setApplicationCategoryId(action.getApplicationCategoryId());
		}
		if(action.getPartnerCategoryId() != null) {
			dto.setPartnerCategoryId(action.getPartnerCategoryId());
		}
		return dto;
	}
	private List<ActionDto> convertActionsToActionDtos(List<Action> actions){
		List<ActionDto> dtos = new ArrayList<>();
		for(Action action : actions) {
			dtos.add(this.convertActionToActionDto(action));
		}
		return dtos;
	}
	
	//BusinessProcessの変換
	private BusinessProcessDto convertBusinessProcessToBusinessProcessDto(BusinessProcess businessProcess) {
		BusinessProcessDto dto = new BusinessProcessDto();
		dto.setBusinessProcessId(businessProcess.getBusinessProcessId().businessProcessId());
		dto.setCompanyId(businessProcess.getCompanyId().id());
		dto.setBusinessProcessType(businessProcess.getBusinessProcessType());
		dto.setBusinessProcessName(businessProcess.getBusinessProcessName());
		if(businessProcess.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(businessProcess.getBusinessDomainId().businessDomainId());
		}
		if(businessProcess.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(businessProcess.getBusinessDomainName());
		}
		if(businessProcess.getAssociatedTasks() != null) {
			//タスクをソートする。
			businessProcess.sortAssociatedTasks();
			dto.setAssociatedTasks(businessProcess.getAssociatedTasks());
		}
		if(businessProcess.getBusinessProcessDescription() != null) {
			dto.setBusinessProcessDescription(businessProcess.getBusinessProcessDescription());
		}
		if(businessProcess.getBusinessProcessClass() != null) {
			dto.setBusinessProcessClass(businessProcess.getBusinessProcessClass());
		}
		if(businessProcess.getUrl() != null) {
			dto.setUrl(businessProcess.getUrl().getUrl());
		}
		if(businessProcess.getIndicators() != null) {
			dto.setIndicators(businessProcess.getIndicators());
		}
		return dto;
	}
	private List<BusinessProcessDto> convertBusinessProcessesToBusinessProcessDtos(List<BusinessProcess> businessProcesses){
		List<BusinessProcessDto> dtos = new ArrayList<>();
		for(BusinessProcess businessProcess : businessProcesses) {
			dtos.add(this.convertBusinessProcessToBusinessProcessDto(businessProcess));
		}
		return dtos;
	}
	
	//ActionPlanの変換
	private ActionPlanDto convertActionPlanToActionPlanDto(ActionPlan actionPlan) {
		ActionPlanDto dto = new ActionPlanDto();
		dto.setActionPlanId(actionPlan.getActionPlanId().actionPlanId());
		dto.setCompanyId(actionPlan.getCompanyId().id());
		dto.setBusinessProcessId(actionPlan.getBusinessProcessId().businessProcessId());
		dto.setBusinessProcessName(actionPlan.getBusinessProcessName());
		dto.setActionPlanType(actionPlan.getActionPlanType());
		dto.setActionPlanName(actionPlan.getActionPlanName());
		if(actionPlan.getBusinessUnitId() != null) {
			dto.setBusinessUnitId(actionPlan.getBusinessUnitId().businessUnitId());
		}
		if(actionPlan.getBusinessUnitName() != null) {
			dto.setBusinessUnitName(actionPlan.getBusinessUnitName());
		}
		if(actionPlan.getHypothesis() != null) {
			dto.setHypothesis(actionPlan.getHypothesis());
		}
		if(actionPlan.getResult() != null) {
			dto.setResult(actionPlan.getResult());
		}
		if(actionPlan.getCustomerCategoryId() != null) {
			dto.setCustomerCategoryId(actionPlan.getCustomerCategoryId().customerCategoryId());
		}
		if(actionPlan.getCustomerCategoryName() != null) {
			dto.setCustomerCategoryName(actionPlan.getCustomerCategoryName());
		}
		if(actionPlan.getAssociatedProductCategories() != null) {
			dto.setAssociatedProductCategories(actionPlan.getAssociatedProductCategories());
		}
		if(actionPlan.getAssociatedActions() != null) {
			dto.setAssociatedActions(actionPlan.getAssociatedActions());
		}
		if(actionPlan.getActionPlanDescription() != null) {
			dto.setActionPlanDescription(actionPlan.getActionPlanDescription());
		}
		if(actionPlan.getUrl() != null) {
			dto.setUrl(actionPlan.getUrl().getUrl());
		}
		if(actionPlan.getProjectId() != null) {
			dto.setProjectId(actionPlan.getProjectId().getProjectId());
		}
		if(actionPlan.getStartDate() != null) {
			dto.setStartDate(actionPlan.getStartDate());
		}
		
		if(actionPlan.getEndDate() != null) {
			dto.setEndDate(actionPlan.getEndDate());
		}
		if(actionPlan.getGoals() != null) {
			dto.setGoals(actionPlan.getGoals());
		}
		return dto;
	
	}
	
	private List<ActionPlanDto> convertActionPlansToActionPlanDtos(List<ActionPlan> actionPlans){
		List<ActionPlanDto> dtos = new ArrayList<>();
		for(ActionPlan actionPlan : actionPlans) {
			dtos.add(this.convertActionPlanToActionPlanDto(actionPlan));
		}
		return dtos;
	}
    /*
     * 会社
     */
	@Override
	public CompanyDto addCompany(CompanyDto companyDto) throws Exception {
		// TODO Auto-generated method stub
		//事前条件
		if(companyDto.getDomainName() == null) {
			throw new IllegalArgumentException("The domain name may not be set to null.");
		}
		if(companyDto.getDomainName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a domain name.");
		}
		if(companyDto.getCompanyPassword() == null) {
			throw new IllegalArgumentException("The company password may not be set to null.");
		}
		if(companyDto.getCompanyPassword().isEmpty()) {
			throw new IllegalArgumentException("Must provide a company password.");
		}
		if(companyDto.getEaName() == null) {
			throw new IllegalArgumentException("The ea name may not be set to null.");
		}
		if(companyDto.getEaName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a ea name.");
		}
		if(companyDto.getEaPassword() == null) {
			throw new IllegalArgumentException("The ea password may not be set to null.");
		}
		if(companyDto.getEaPassword().isEmpty()) {
			throw new IllegalArgumentException("Must provide a ea password.");
		}
		if(companyDto.getCompanyName() == null) {
			throw new IllegalArgumentException("The ea companyName may not be set to null.");
		}
		if(companyDto.getCompanyName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a ea companyName.");
		}
		
		//不変条件
		//同じドメイン名の会社は登録できない。
		List<CompanyDto> companies = this.findCompaniesOfDomainName(companyDto.getDomainName());
		if(companies.size() > 0) {
			throw new Exception("The domain name is already exist.");
		}
		
		DomainName domainName = new DomainName(companyDto.getDomainName());
		Company company = CompanyFactory.createCompany(domainName.name());
		company.setCompanyPassword(companyDto.getCompanyPassword());
		company.setEaName(companyDto.getEaName());
		company.setEaPassword(companyDto.getEaPassword());
		company.setCompanyName(companyDto.getCompanyName());
		
		if(companyDto.getZipcode() != null && companyDto.getCountry() != null && companyDto.getState() != null && companyDto.getCity() != null && companyDto.getStreet() != null) {
			Address address = new Address(companyDto.getZipcode(), companyDto.getCountry(), companyDto.getState(), companyDto.getCity(), companyDto.getStreet());
			company.setAddress(address);
		}
		if(companyDto.getAddress() != null) {
			Email email = new Email(companyDto.getAddress());
			company.setEmail(email);
		}
		if(companyDto.getIndustryGroupId() != null && companyDto.getIndustryGroupName() != null) {
			IndustryGroup industryGroup = new IndustryGroup(companyDto.getIndustryGroupId(), companyDto.getIndustryGroupName());
			company.setIndustryGroup(industryGroup);
		}
		if(companyDto.getPurpose() != null) {
			company.setPurpose(companyDto.getPurpose());
		}
		
		
		
		companyRepository.save(company);
		
		//事後条件
		companyDto.setCompanyId(company.companyId().id());
        return companyDto;
	}
	
	@Override
	public void updateCompany(CompanyDto companyDto) throws Exception {
		// TODO Auto-generated method stub
		if(companyDto == null) {
			throw new IllegalArgumentException("The companyDto may not be set to null.");
		}
		String companyId = companyDto.getCompanyId();
		//検索キーの存在チェック(事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		Company company = companyRepository.companyOfId(new CompanyId(companyId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		//基準となる項目（Companyの場合、CompanyIdとDomainName）は更新しない
		if(companyDto.getZipcode()!= null ){
			Address address = new Address(companyDto.getZipcode(), companyDto.getCountry(), companyDto.getState(), companyDto.getCity(), companyDto.getStreet());
			company.setAddress(address);
		}
		if(companyDto.getAddress() != null ) {
			Email email = new Email(companyDto.getAddress());
			company.setEmail(email);
		}
		if(companyDto.getCompanyName() != null ) {
			company.setCompanyName(companyDto.getCompanyName());
		}
		if(companyDto.getPurpose()!= null ) {
			company.setPurpose(companyDto.getPurpose());
		}
		if(companyDto.getIndustryGroupId() != null ) {
			IndustryGroup industryGroup = new IndustryGroup(companyDto.getIndustryGroupId(), companyDto.getIndustryGroupName());
			company.setIndustryGroup(industryGroup);
		}
		if(companyDto.getCompanyPassword() != null && !companyDto.getCompanyPassword().isEmpty() && !companyDto.getCompanyPassword().equals(company.companyPassword())) {
			company.setCompanyPassword(companyDto.getCompanyPassword());
		}
		if(companyDto.getEaName() != null && !companyDto.getEaName().isEmpty() && !companyDto.getEaName().equals(company.eaName())) {
			company.setEaName(companyDto.getEaName());
		}
		if(companyDto.getEaPassword() != null && !companyDto.getEaPassword().isEmpty() && !companyDto.getEaPassword().equals(company.eaPassword())) {
			company.setEaPassword(companyDto.getEaPassword());
		}
		
		
		companyRepository.save(company);
	}
    //原則、会社の削除はできない。
	@Override
	public void deleteCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		Company company = companyRepository.companyOfId(new CompanyId(companyId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		companyRepository.remove(company);
	}


	@Override
	public CompanyDto findCompanyById(String companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーの存在チェック(事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		Company company = companyRepository.companyOfId(new CompanyId(companyId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		CompanyDto dto = new CompanyDto();
		//必須項目が存在できていなければ保存できていないので必須項目存在（事前条件）チェックはしなくてよい。
		dto.setCompanyId(company.companyId().id());
		dto.setDomainName(company.domainName().name());
		dto.setCompanyPassword(company.companyPassword());
		dto.setEaName(company.eaName());
		dto.setEaPassword(company.eaPassword());
		//任意項目の存在チェック
		if(company.address() != null) {
			dto.setZipcode(company.address().zipcode());
			dto.setCountry(company.address().country());
			dto.setState(company.address().state());
			dto.setCity(company.address().city());
			dto.setState(company.address().street());
		}
		if(company.email()!= null) {
			dto.setAddress(company.email().address());
		}
		if(company.industryGroup() != null) {
			dto.setIndustryGroupId(company.industryGroup().industryGroupId());
			dto.setIndustryGroupName(company.industryGroup().industryGroupName());
		}
		if(company.companyName() != null) {
			dto.setCompanyName(company.companyName());
		}
		if(company.purpose() != null) {
			dto.setPurpose(company.purpose());
		}
		return dto;
	}
	
	@Override
	public List<CompanyDto> findCompaniesOfDomainName(String domainName) throws Exception {
		// TODO Auto-generated method stub
		//検索キーの存在チェック(事前条件チェック）
		if(domainName == null) {
			throw new IllegalArgumentException("The domainName may not be set to null.");
		}
		if(domainName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a domainName.");
		}
		List<Company> companies = companyRepository.companiesOfDomainName(new DomainName(domainName));
		List<CompanyDto> dtos = new ArrayList<>();
		for(Company company : companies) {
			CompanyDto dto = new CompanyDto();
			//必須項目が存在できていなければ保存できていないので必須項目存在（事前条件）チェックはしなくてよい。
			dto.setCompanyId(company.companyId().id());
			dto.setDomainName(company.domainName().name());
			dto.setCompanyPassword(company.companyPassword());
			dto.setEaName(company.eaName());
			dto.setEaPassword(company.eaPassword());
			//任意項目の存在チェック
			if(company.address() != null) {
				dto.setZipcode(company.address().zipcode());
				dto.setCountry(company.address().country());
				dto.setState(company.address().state());
				dto.setCity(company.address().city());
				dto.setState(company.address().street());
			}
			if(company.email() != null) {
				dto.setAddress(company.email().address());
			}
			if(company.industryGroup() != null) {
				dto.setIndustryGroupId(company.industryGroup().industryGroupId());
				dto.setIndustryGroupName(company.industryGroup().industryGroupName());
			}
			if(company.companyName() != null) {
				dto.setCompanyName(company.companyName());
			}
			if(company.purpose() != null) {
				dto.setPurpose(company.purpose());
			}
			dtos.add(dto);
		}
		return dtos;
	}

	/*
	 * 事業ドメイン
	 */
	@Override
	public BusinessDomainDto addBusinessDomain(BusinessDomainDto businessDomainDto) throws Exception {
		// TODO Auto-generated method stub
		
		//検索キーの存在チェック
		if(businessDomainDto.getCompanyId() == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(businessDomainDto.getCompanyId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		//ビジネスドメイン名は必須にする。
		if(businessDomainDto.getBusinessDomainName() == null) {
			throw new IllegalArgumentException("The business domain name may not be set to null.");
		}
		if(businessDomainDto.getBusinessDomainName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a business domain name.");
		}
		Company company = companyRepository.companyOfId(new CompanyId(businessDomainDto.getCompanyId()));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		BusinessDomainId businessDomainId = businessDomainRepository.nextIdentity();
		BusinessDomain businessDomain = company.defineBusinessDomain(businessDomainId);
		businessDomain.setBusinessDomainName(businessDomainDto.getBusinessDomainName());
		//IndustryがMarketに存在してるかはチェックしていない。
		if(businessDomainDto.getIndustryGroupId() != null && businessDomainDto.getIndustryGroupName() != null) {
			businessDomain.setIndustryGroup(new IndustryGroup(businessDomainDto.getIndustryGroupId(), businessDomainDto.getIndustryGroupName()));
		}
		if(businessDomainDto.getIndustryId() != null && businessDomainDto.getIndustryName() != null) {
			businessDomain.setIndustry(new Industry(businessDomainDto.getIndustryId(), businessDomainDto.getIndustryName()));
		}
		if(businessDomainDto.getIndustrySubGroupId() != null && businessDomainDto.getIndustrySubGroupName() != null) {
			businessDomain.setIndustrySubGroup(new IndustrySubGroup(businessDomainDto.getIndustrySubGroupId(), businessDomainDto.getIndustrySubGroupName()));
		}
		if(businessDomainDto.getPurpose() != null) {
			businessDomain.setPurpose(businessDomainDto.getPurpose());
		}
		if(businessDomainDto.getUrl() != null) {
			businessDomain.setUrl(new Url(businessDomainDto.getUrl()));
		}
		if(businessDomainDto.getBusinessModel() != null) {
			businessDomain.setBusinessModel(businessDomainDto.getBusinessModel());
		}
		if(businessDomainDto.getCustomerType() != null) {
			businessDomain.setCustomerType(businessDomainDto.getCustomerType());
		}
		if(businessDomainDto.getProductType() != null) {
			businessDomain.setProductType(businessDomainDto.getProductType());
		}
		if(businessDomainDto.getCustomerTypeId() != null) {
			businessDomain.setCustomerTypeId(new CustomerTypeId(businessDomainDto.getCustomerTypeId()));
		}
		if(businessDomainDto.getAssociatedProductTypes() != null) {
			businessDomain.setAssociatedProductTypes(businessDomainDto.getAssociatedProductTypes());
		}
		if(businessDomainDto.getAssociatedCustomerTypes() != null) {
			businessDomain.setAssociatedCustomerTypes(businessDomainDto.getAssociatedCustomerTypes());
		}
		
		if(businessDomainDto.getAssociatedUrls() != null) {
			businessDomain.setAssociatedUrls(businessDomainDto.getAssociatedUrls());
		}
		
		if(businessDomainDto.getCustomerTypeName() != null) {
			businessDomain.setCustomerTypeName(businessDomainDto.getCustomerTypeName());
		}
		if(businessDomainDto.getProductTypeId() != null) {
			businessDomain.setProductTypeId(new ProductTypeId(businessDomainDto.getProductTypeId()));
		}
		if(businessDomainDto.getProductTypeName() != null) {
			businessDomain.setProductTypeName(businessDomainDto.getProductTypeName());
		}
		if(businessDomainDto.getIndicators() != null) {
			businessDomain.setIndicators(businessDomainDto.getIndicators());
		}
		businessDomain.setEnterprise(businessDomainDto.isEnterprise());
		businessDomain.setCreatedAt(new Date());
		businessDomainRepository.save(businessDomain);
		businessDomainDto.setBusinessDomainId(businessDomain.businessDomainId().businessDomainId());
		return businessDomainDto;
	}

	@Override
	public BusinessDomainDto findBusinessDomainById(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId name may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessDomain == null) {
			throw new Exception("The businessDomain name may not be set to null.");
		}
		return this.convertBusinessDomainToBusinessDomainDto(businessDomain);
	}

	@Override
	public List<BusinessDomainDto> findBusinessDomainsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainOfCompany(new CompanyId(companyId));
		return this.convertBusinessDomainsToBusinessDomainDtos(businessDomains);
	}
	
	public BusinessDomainDto findEnterpriseDomain(String companyId) throws Exception{
		
		BusinessDomain businessDomain = null;
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<BusinessDomain> businessDomains = businessDomainRepository.findEnterprises(true, new CompanyId(companyId));
		if(businessDomains.size() > 0 ){
			businessDomain = businessDomains.get(0);
		}
		if(businessDomain != null) {
			return this.convertBusinessDomainToBusinessDomainDto(businessDomain);
		}else {
			return null;
		}
		
	}
	
	
	
	@Override
	public List<BusinessDomainDto> findBusinessDomainsOfCustomerType(String customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfCustomerType(new CustomerTypeId(customerTypeId));
		return this.convertBusinessDomainsToBusinessDomainDtos(businessDomains);
	}
	
	@Override
	public List<BusinessDomainDto> findBusinessDomainsOfProductType(String productTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		if(productTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfProductType(new ProductTypeId(productTypeId));
		return this.convertBusinessDomainsToBusinessDomainDtos(businessDomains);
	}
	

	@Override
	public void updateBusinessDomain(BusinessDomainDto businessDomainDto) throws Exception {
		// TODO Auto-generated method stub
		if(businessDomainDto == null) {
			throw new IllegalArgumentException("The businessDomainDto name may not be set to null.");
		}
		String businessDomainId = businessDomainDto.getBusinessDomainId();
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId name may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessDomain == null) {
			throw new Exception("The businessDomain name may not be set to null.");
		}
		//基準となる項目（CompanyId、BusinessDomainId）は更新しない
		//それ以外は、属性の内容に変更があれば更新するようにする（任意項目の更新）
		//ビジネスドメイン名は必須項目なので、isEmptyはNG。
		if(businessDomainDto.getBusinessDomainName() != null && !businessDomainDto.getBusinessDomainName().isEmpty() && !businessDomainDto.getBusinessDomainName().equals(businessDomain.businessDomainName())) {

			businessDomain.setBusinessDomainName(businessDomainDto.getBusinessDomainName());
		}
		//事業目的は任意項目なので、設定しないというケースもあるのでisEmptyはOK。
		if(businessDomainDto.getPurpose() != null && !businessDomainDto.getPurpose().equals(businessDomain.purpose())) {
			businessDomain.setPurpose(businessDomainDto.getPurpose());
		}
		if(businessDomainDto.getUrl() != null ) {
			businessDomain.setUrl(new Url(businessDomainDto.getUrl()));
		}
		if(businessDomainDto.getBusinessModel() != null) {
			businessDomain.setBusinessModel(businessDomainDto.getBusinessModel());
		}
		if(businessDomainDto.getCustomerType() != null) {
			businessDomain.setCustomerType(businessDomainDto.getCustomerType());
		}
		if(businessDomainDto.getProductType() != null) {
			businessDomain.setProductType(businessDomainDto.getProductType());
		}
		if(businessDomainDto.getIndicators() != null ) {
			businessDomain.setIndicators(businessDomainDto.getIndicators());
		}
		//IndustryがMarketに存在してるかはチェックしていない。
		//Industryの設定がない場合もあるので、IDの同一性チェック!businessDomainDto.getIndustryGroupId().equals(businessDomain.industryGroup().industryGroupId()などはなずします。2021/12/24
		//if(businessDomainDto.getIndustryGroupId() != null && businessDomainDto.getIndustryGroupName() != null && !businessDomainDto.getIndustryGroupId().equals(businessDomain.industryGroup().industryGroupId())) {
		if(businessDomainDto.getIndustryGroupId() != null && businessDomainDto.getIndustryGroupName() != null) {
				
			businessDomain.setIndustryGroup(new IndustryGroup(businessDomainDto.getIndustryGroupId(), businessDomainDto.getIndustryGroupName()));
		}
		if(businessDomainDto.getIndustryId() != null && businessDomainDto.getIndustryName() != null ) {
			businessDomain.setIndustry(new Industry(businessDomainDto.getIndustryId(), businessDomainDto.getIndustryName()));
		}
		if(businessDomainDto.getIndustrySubGroupId() != null && businessDomainDto.getIndustrySubGroupName() != null ) {
			businessDomain.setIndustrySubGroup(new IndustrySubGroup(businessDomainDto.getIndustrySubGroupId(), businessDomainDto.getIndustrySubGroupName()));
		}
		//企業ドメインがTrueからFalseになるので削除した。2022/8/16
		//businessDomain.setEnterprise(businessDomainDto.isEnterprise());
		/*
		if(!businessDomainDto.getCustomerTypeId().equals(businessDomain.customerTypeId().customerTypeId())) {
			businessDomain.setCustomerTypeId(new CustomerTypeId(businessDomainDto.getCustomerTypeId()));
		}
		if(!businessDomainDto.getCustomerTypeName().equals(businessDomain.customerTypeName())) {
			businessDomain.setCustomerTypeName(businessDomainDto.getCustomerTypeName());
		}
		*/
		if(businessDomainDto.getAssociatedProductTypes() != null) {
			businessDomain.setAssociatedProductTypes(businessDomainDto.getAssociatedProductTypes());
		}
		if(businessDomainDto.getAssociatedCustomerTypes() != null) {
			businessDomain.setAssociatedCustomerTypes(businessDomainDto.getAssociatedCustomerTypes());
		}
		if(businessDomainDto.getAssociatedUrls() != null) {
			businessDomain.setAssociatedUrls(businessDomainDto.getAssociatedUrls());
		}
		//製品タイプは複数設定するように変更した。2022/1/13
		/*
		if(!businessDomainDto.getProductTypeId().equals(businessDomain.productTypeId().productTypeId())) {
			businessDomain.setProductTypeId(new ProductTypeId(businessDomainDto.getProductTypeId()));
		}
		if(!businessDomainDto.getProductTypeName().equals(businessDomain.productTypeName())) {
			businessDomain.setProductTypeName(businessDomainDto.getProductTypeName());
		}
		*/
		
		//事後条件
		//事業単位で使用されている場合は編集、削除できない。→BusinessDomainService.validateEdit
		//BusinessUnitServiceに移行。2022/1/16
		/*
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfDomain(new BusinessDomainId(businessDomainDto.getBusinessDomainId()));
		if(businessUnits.size() > 0) {
			throw new Exception("The business domain is already used by business units.");
		}
		//ジョブで使用されている場合は編集、削除できない。→BusinessDomainService.validateEdit
		List<Job> jobs = jobRepository.jobsOfBusinessDomain(new BusinessDomainId(businessDomainDto.getBusinessDomainId()));
		if(jobs.size() > 0) {
			throw new Exception("The business domain is already used by jobs.");
		}
		*/
		businessDomain.setUpdatedAt(new Date());
		businessDomainRepository.save(businessDomain);
	}

	@Override
	public void deleteBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId name may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessDomain == null) {
			throw new Exception("The businessDomain name may not be set to null.");
		}
		//事後条件
		//事業単位で使用されている場合は編集、削除できない。→BusinessDomainService.validateRemove
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfDomain(new BusinessDomainId(businessDomainId));
		if(businessUnits.size() > 0) {
			throw new Exception("The business domain is already used by business units.");
		}
		//ジョブで使用されている場合は編集、削除できない。→BusinessDomainService.validateEdit
		List<Job> jobs = jobRepository.jobsOfBusinessDomain(new BusinessDomainId(businessDomainId));
		if(jobs.size() > 0) {
			throw new Exception("The business domain is already used by jobs.");
		}
		
		businessDomainRepository.remove(businessDomain);
		
	}
	/*
	 * 事業単位
	 */
	@Override
	public BusinessUnitDto addBusinessUnit(BusinessUnitDto businessUnitDto) throws Exception {
		// TODO Auto-generated method stub
		//検索キーの存在チェック
		if(businessUnitDto.getBusinessDomainId() == null) {
			throw new IllegalArgumentException("The businessDomainId  may not be set to null.");
		}
		if(businessUnitDto.getBusinessDomainId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		if(businessUnitDto.getBusinessUnitName() == null) {
			throw new IllegalArgumentException("The business unit name may not be set to null.");
		}
		if(businessUnitDto.getBusinessUnitName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a business unit name.");
		}
		
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessUnitDto.getBusinessDomainId()));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessDomain == null) {
			throw new Exception("The businessDomain may not exist.");
		}
		BusinessUnitId businessUnitId = businessUnitRepository.nextIdentity();
		BusinessUnit businessUnit = businessDomain.defineBusinessUnit(businessUnitId);
		businessUnit.setBusinessDomainName(businessDomain.businessDomainName());
		businessUnit.setBusinessUnitName(businessUnitDto.getBusinessUnitName());
		if(businessUnitDto.getVision() != null) {
			businessUnit.setVision(businessUnitDto.getVision());
		}
		if(businessUnitDto.getHypothesis() != null) {
			businessUnit.setHypothesis(businessUnitDto.getHypothesis());
		}
		if(businessUnitDto.getResult() != null) {
			businessUnit.setResult(businessUnitDto.getResult());
		}
		if(businessUnitDto.getBusinessStrategy() != null) {
			businessUnit.setBusinessStrategy(businessUnitDto.getBusinessStrategy());
		}
		if(businessUnitDto.getCustomerCategory() != null) {
			businessUnit.setCustomerCategory(businessUnitDto.getCustomerCategory());
		}
		if(businessUnitDto.getProductCategory() != null) {
			businessUnit.setProductCategory(businessUnitDto.getProductCategory());
		}
		if(businessUnitDto.getBusinessState() != null) {
			businessUnit.setBusinessState(businessUnitDto.getBusinessState());
		}
		
		if(businessUnitDto.getCustomerCategoryId() != null) {
			businessUnit.setCustomerCategoryId(new CustomerCategoryId(businessUnitDto.getCustomerCategoryId()));
		}
		if(businessUnitDto.getCustomerCategoryName() != null) {
			businessUnit.setCustomerCategoryName(businessUnitDto.getCustomerCategoryName());
		}
		if(businessUnitDto.getProductCategoryId() != null) {
			businessUnit.setProductCategoryId(new ProductCategoryId(businessUnitDto.getProductCategoryId()));
		}
		if(businessUnitDto.getProductCategoryName() != null) {
			businessUnit.setProductCategoryName(businessUnitDto.getProductCategoryName());
		}
		if(businessUnitDto.getAssociatedProductCategories() != null) {
			businessUnit.setAssociatedProductCategories(businessUnitDto.getAssociatedProductCategories());
		}
		if(businessUnitDto.getAssociatedCustomerCategories() != null) {
			businessUnit.setAssociatedCustomerCategories(businessUnitDto.getAssociatedCustomerCategories());
		}
		if(businessUnitDto.getGoals() != null) {
			businessUnit.setGoals(businessUnitDto.getGoals());
		}
		if(businessUnitDto.getAchievements() != null) {
			businessUnit.setAchievements(businessUnitDto.getAchievements());
		}
		if(businessUnitDto.getUrl() != null) {
			businessUnit.setUrl(new Url(businessUnitDto.getUrl()));
		}
		if(businessUnitDto.getStartDate() != null) {
			businessUnit.setStartDate(businessUnitDto.getStartDate());
		}
		if(businessUnitDto.getEndDate() != null) {
			businessUnit.setEndDate(businessUnitDto.getEndDate());
		}
		if(businessUnitDto.getIndustryId() != null) {
			Industry industry = new Industry(businessUnitDto.getIndustryId(),businessUnitDto.getIndustryName());
			businessUnit.setIndustry(industry);
		}
		businessUnit.setCreatedAt(new Date());	
		businessUnitRepository.save(businessUnit);
		businessUnitDto.setBusinessUnitId(businessUnit.businessUnitId().businessUnitId());
		businessUnitDto.setCompanyId(businessUnit.companyId().id());
		businessUnitDto.setBusinessDomainName(businessUnit.businessDomainName());
		return businessUnitDto;

	}

	@Override
	public void updateBusinessUnit(BusinessUnitDto businessUnitDto) throws Exception {
		// TODO Auto-generated method stub
		if(businessUnitDto == null) {
			throw new IllegalArgumentException("The businessUnitDto name may not be set to null.");
		}
		String businessUnitId = businessUnitDto.getBusinessUnitId();
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId name may not be set to null.");
		}
		if(businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessUnitId.");
		}
		BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(businessUnitId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessUnit == null) {
			throw new Exception("The businessUnit name may not be set to null.");
		}
		//基準となる項目（CompanyId、BusinessUnitId）は更新しない
		//それ以外は、属性の内容に変更があれば更新するようにする（任意項目の更新）
		//事業ドメインのチェック
		String businessDomainId = businessUnitDto.getBusinessDomainId();
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
		if(businessDomain == null) {
			throw new Exception("The businessDomain name may not be exist.");
		}
		businessUnit.setBusinessDomainId(businessDomain.businessDomainId());
		businessUnit.setBusinessDomainName(businessDomain.businessDomainName());
		if(businessUnitDto.getBusinessUnitName() != null && !businessUnitDto.getBusinessUnitName().isEmpty() && !businessUnitDto.getBusinessUnitName().equals(businessUnit.businessUnitName())) {
			businessUnit.setBusinessUnitName(businessUnitDto.getBusinessUnitName());
		}
		//Visionは任意項目なので、設定しないというケースもあるのでisEmptyはOK。
		if(businessUnitDto.getVision() != null ) {
			businessUnit.setVision(businessUnitDto.getVision());
		}
		
		if(businessUnitDto.getBusinessState() != null) {
			businessUnit.setBusinessState(businessUnitDto.getBusinessState());
		}
		
		if(businessUnitDto.getHypothesis() != null) {
			businessUnit.setHypothesis(businessUnitDto.getHypothesis());
		}
		if(businessUnitDto.getResult() != null) {
			businessUnit.setResult(businessUnitDto.getResult());
		}
		if(businessUnitDto.getBusinessStrategy() != null) {
			businessUnit.setBusinessStrategy(businessUnitDto.getBusinessStrategy());
		}
		if(businessUnitDto.getCustomerCategory() != null) {
			businessUnit.setCustomerCategory(businessUnitDto.getCustomerCategory());
		}
		if(businessUnitDto.getProductCategory() != null) {
			businessUnit.setProductCategory(businessUnitDto.getProductCategory());
		}
		
		//顧客タイプが設定されておらずbusinessDomain.customerTypeId()がNULLになる場合があるので同一性チェックははずします。2021/12/24
		if(businessUnitDto.getCustomerCategoryId() != null) {
			//if(!businessUnitDto.getCustomerCategoryId().equals(businessDomain.customerTypeId().customerTypeId())) {
				businessUnit.setCustomerCategoryId(new CustomerCategoryId(businessUnitDto.getCustomerCategoryId()));
			//}
		}
		if(businessUnitDto.getCustomerCategoryName() != null) {
			//if(!businessUnitDto.getCustomerCategoryName().equals(businessDomain.customerTypeName())) {
				businessUnit.setCustomerCategoryName(businessUnitDto.getCustomerCategoryName());
			//}
		}
		if(businessUnitDto.getProductCategoryId() != null) {
			//if(!businessUnitDto.getProductCategoryId().equals(businessDomain.productTypeId().productTypeId())) {
				businessUnit.setProductCategoryId(new ProductCategoryId(businessUnitDto.getProductCategoryId()));
			//}
		}
		if(businessUnitDto.getProductCategoryName() != null) {
			//if(!businessUnitDto.getProductCategoryName().equals(businessDomain.productTypeName())) {
				businessUnit.setProductCategoryName(businessUnitDto.getProductCategoryName());
			//}
		}
		if(businessUnitDto.getAssociatedProductCategories() != null) {
			businessUnit.setAssociatedProductCategories(businessUnitDto.getAssociatedProductCategories());		
		}
		if(businessUnitDto.getAssociatedCustomerCategories() != null) {
			businessUnit.setAssociatedCustomerCategories(businessUnitDto.getAssociatedCustomerCategories());		
		}
		if(businessUnitDto.getGoals() != null) {
			businessUnit.setGoals(businessUnitDto.getGoals());		
		}
		if(businessUnitDto.getAchievements() != null) {
			businessUnit.setAchievements(businessUnitDto.getAchievements());		
		}
		if(businessUnitDto.getUrl() != null) {
			businessUnit.setUrl(new Url(businessUnitDto.getUrl()));
		//ユースケースとしてURLを設定しない場合、明示的にNULLを設定する。
		}else {
			businessUnit.setUrl(null);
		}
		if(businessUnitDto.getStartDate() != null) {
			businessUnit.setStartDate(businessUnitDto.getStartDate());
		}
		if(businessUnitDto.getEndDate() != null) {
			businessUnit.setEndDate(businessUnitDto.getEndDate());
		}
		
		if(businessUnitDto.getIndustryId() != null) {
			Industry industry = new Industry(businessUnitDto.getIndustryId(),businessUnitDto.getIndustryName());
			businessUnit.setIndustry(industry);
		}
		
		businessUnit.setUpdatedAt(new Date());	
		businessUnitRepository.save(businessUnit);

		
	}

	@Override
	public void deleteBusinessUnit(String businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		//関連する部門、場所カテゴリが設定されている場合は削除できない。
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId name may not be set to null.");
		}
		if(businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessUnitId.");
		}
		BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(businessUnitId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessUnit == null) {
			throw new Exception("The businessUnit name may not be set to null.");
		}
		businessUnitRepository.remove(businessUnit);
		
	}

	@Override
	public BusinessUnitDto findBusinessUnitById(String businessUnitId) throws Exception {
		//検索キーのチェック（事前条件チェック）
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId name may not be set to null.");
		}
		if(businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessUnitId.");
		}
		BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(businessUnitId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(businessUnit == null) {
			throw new Exception("The businessUnit name may not be set to null.");
		}
		return this.convertBusinessUnitToBusinessUnitDto(businessUnit);
	}

	@Override
	public List<BusinessUnitDto> findBusinessUnitsOfDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfDomain(new BusinessDomainId(businessDomainId));
		return this.convertBusinessUnitsToBusinessUnitDtos(businessUnits);
	}
	
	@Override
	public List<BusinessUnitDto> findBusinessUnitsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfCompany(new CompanyId(companyId));
		return this.convertBusinessUnitsToBusinessUnitDtos(businessUnits);
	}

	@Override
	public List<BusinessUnitDto> findBusinessUnitsOfCustomerCategory(String customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		if(customerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerCategoryId.");
		}
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfCustomerCategory(new CustomerCategoryId(customerCategoryId));
		return this.convertBusinessUnitsToBusinessUnitDtos(businessUnits);
	}

	@Override
	public List<BusinessUnitDto> findBusinessUnitsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		if(productCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productCategoryId.");
		}
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfProductCategory(productCategoryId);
		return this.convertBusinessUnitsToBusinessUnitDtos(businessUnits);
	}
	
	/*
	 * 顧客タイプ
	 */
	@Override
	public CustomerTypeDto addCustomerType(CustomerTypeDto customerTypeDto) throws Exception {
		// TODO Auto-generated method stub
		//検索キーの存在チェック
		if(customerTypeDto.getCompanyId() == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(customerTypeDto.getCompanyId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		//名前は必須にする。
		if(customerTypeDto.getCustomerTypeName() == null) {
			throw new IllegalArgumentException("The customer type name may not be set to null.");
		}
		if(customerTypeDto.getCustomerTypeName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a customer type name.");
		}
		if(customerTypeDto.getPersonality() == null){
			throw new IllegalArgumentException("The personality may not be set to null.");
		}
		Company company = companyRepository.companyOfId(new CompanyId(customerTypeDto.getCompanyId()));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		
		CustomerTypeId customerTypeId = customerTypeRepository.nextIdentity();
		CustomerType customerType = company.defineCustomerType(customerTypeId,customerTypeDto.getCustomerTypeName(),customerTypeDto.getPersonality());
		
		customerType.setCustomerTypeName(customerTypeDto.getCustomerTypeName());
		if(customerTypeDto.getValues() != null) {
			customerType.setValues(customerTypeDto.getValues());
		}
		
		if(customerTypeDto.getCause() != null) {
			customerType.setCause(customerTypeDto.getCause());
		}
		
		if(customerTypeDto.getIssue() != null) {
			customerType.setIssue(customerTypeDto.getIssue());
		}
		
		if(customerTypeDto.getProblem() != null) {
			customerType.setProblem(customerTypeDto.getProblem());
		}
		
		if(customerTypeDto.getUrl() != null) {
			customerType.setUrl(new Url(customerTypeDto.getUrl()));
		}
		
		
		if(customerTypeDto.getIndicators() != null) {
			customerType.setIndicators(customerTypeDto.getIndicators());
		}
		
		if(customerTypeDto.getAttributes() != null) {
			customerType.setAttributes(customerTypeDto.getAttributes());
		}
		//データ関連
		if(customerTypeDto.getEntityName() != null) {
			customerType.setEntityName(customerTypeDto.getEntityName());
		}
		if(customerTypeDto.getEntityEnglishName() != null) {
			customerType.setEntityEnglishName(customerTypeDto.getEntityEnglishName());
		}
		if(customerTypeDto.getEntityDescription() != null) {
			customerType.setEntityDescription(customerTypeDto.getEntityDescription());
		}
		if(customerTypeDto.getDataOwner() != null) {
			customerType.setDataOwner(customerTypeDto.getDataOwner());
		}
		if(customerTypeDto.getAddress() != null) {
			customerType.setAddress(new Email(customerTypeDto.getAddress()));
		}
		if(customerTypeDto.getAssociatedConstraint() != null) {
			customerType.setAssociatedConstraint(customerTypeDto.getAssociatedConstraint());
		}
		if(customerTypeDto.getExistenceConstraint() != null) {
			customerType.setExistenceConstraint(customerTypeDto.getExistenceConstraint());
		}
		if(customerTypeDto.getDataAmount() != null) {
			customerType.setDataAmount(customerTypeDto.getDataAmount());
		}
		if(customerTypeDto.getDataTypeId() != null) {
			customerType.setDataTypeId(new DataTypeId(customerTypeDto.getDataTypeId()));
		}
		
		
		if(customerTypeDto.getBusinessDomainId() != null) {
			customerType.setBusinessDomainId(new BusinessDomainId(customerTypeDto.getBusinessDomainId()));
		}
		customerType.setCreatedAt(new Date());	
		
		customerTypeRepository.save(customerType);
		
		customerTypeDto.setCustomerTypeId(customerType.customerTypeId().customerTypeId());
		return customerTypeDto;
	}

	@Override
	public void updateCustomerType(CustomerTypeDto customerTypeDto) throws Exception {
		// TODO Auto-generated method stub
		if(customerTypeDto == null) {
			throw new IllegalArgumentException("The customerTypeDto name may not be set to null.");
		}
		String customerTypeId = customerTypeDto.getCustomerTypeId();
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId name may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(customerTypeId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(customerType == null) {
			throw new Exception("The customerType name may not be set to null.");
		}
		//基準となる項目（CompanyId、customerTypeId）は更新しない
		//それ以外は、属性の内容に変更があれば更新するようにする（任意項目の更新）
		//ビジネスドメイン名は必須項目なので、isEmptyはNG。
		if(customerTypeDto.getCustomerTypeName() != null && !customerTypeDto.getCustomerTypeName().isEmpty() && !customerTypeDto.getCustomerTypeName().equals(customerType.customerTypeName())) {
			customerType.setCustomerTypeName(customerTypeDto.getCustomerTypeName());
		}
		if(customerTypeDto.getPersonality() != null){
			customerType.setPersonality(customerTypeDto.getPersonality());
		}
		//価値観と課題は任意項目なので、設定しないというケースもあるのでisEmptyはOK。
		if(customerTypeDto.getValues() != null ) {
			customerType.setValues(customerTypeDto.getValues());
		}
		if(customerTypeDto.getCause() != null ) {
			customerType.setCause(customerTypeDto.getCause());
		}
		if(customerTypeDto.getIssue() != null ) {
			customerType.setIssue(customerTypeDto.getIssue());
		}
		if(customerTypeDto.getProblem() != null ) {
			customerType.setProblem(customerTypeDto.getProblem());
		}
		
		if(customerTypeDto.getUrl() != null) {
			customerType.setUrl(new Url(customerTypeDto.getUrl()));
		//ユースケースとしてURLを設定しない場合、明示的にNULLを設定する。
		}else {
			customerType.setUrl(null);
		}
		
		if(customerTypeDto.getIndicators() != null ) {
			customerType.setIndicators(customerTypeDto.getIndicators());
		}
		if(customerTypeDto.getAttributes() != null ) {
			customerType.setAttributes(customerTypeDto.getAttributes());
		}
		//データ関連
		if(customerTypeDto.getEntityName() != null) {
			customerType.setEntityName(customerTypeDto.getEntityName());
		}
		if(customerTypeDto.getEntityEnglishName() != null) {
			customerType.setEntityEnglishName(customerTypeDto.getEntityEnglishName());
		}
		if(customerTypeDto.getEntityDescription() != null) {
			customerType.setEntityDescription(customerTypeDto.getEntityDescription());
		}
		if(customerTypeDto.getDataOwner() != null) {
			customerType.setDataOwner(customerTypeDto.getDataOwner());
		}
		if(customerTypeDto.getAddress() != null) {
			customerType.setAddress(new Email(customerTypeDto.getAddress()));
		}else {
			customerType.setAddress(null);
		}
		if(customerTypeDto.getAssociatedConstraint() != null) {
			customerType.setAssociatedConstraint(customerTypeDto.getAssociatedConstraint());
		}
		if(customerTypeDto.getExistenceConstraint() != null) {
			customerType.setExistenceConstraint(customerTypeDto.getExistenceConstraint());
		}
		if(customerTypeDto.getDataAmount() != null) {
			customerType.setDataAmount(customerTypeDto.getDataAmount());
		}
		if(customerTypeDto.getDataTypeId() != null) {
			customerType.setDataTypeId(new DataTypeId(customerTypeDto.getDataTypeId()));
		}else {
			customerType.setDataTypeId(null);
		}

		if(customerTypeDto.getBusinessDomainId() != null) {
			customerType.setBusinessDomainId(new BusinessDomainId(customerTypeDto.getBusinessDomainId()));
		}
		
		//事後条件
		//製品タイプ、顧客カテゴリ、事業ドメインで使用されている場合は編集できない。→CustomerTypeService.validateEdit
		//CustomerTypeServiceに移行。2022/1/13
		/*
		List<ProductType> productTypes = productTypeRepository.productTypesOfCustomerType(new CustomerTypeId(customerTypeDto.getCustomerTypeId()));
		if(productTypes.size() > 0) {
			throw new Exception("The customerType already used by product types."); 
		}
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfCustomerType(new CustomerTypeId(customerTypeDto.getCustomerTypeId()));
		if(customerCategories.size() > 0) {
			throw new Exception("The customerType already used by customer categories."); 
		}
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfCustomerType(new CustomerTypeId(customerTypeDto.getCustomerTypeId()));
		if(businessDomains.size() > 0) {
			throw new Exception("The customerType already used by business domains."); 
		}
		*/
		customerType.setUpdatedAt(new Date());
		customerTypeRepository.save(customerType);
		
	}

	@Override
	public void deleteCustomerType(String customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId name may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(customerTypeId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(customerType == null) {
			throw new Exception("The customerType name may not be set to null.");
		}
		//事後条件
		//製品タイプ、顧客カテゴリ、事業ドメインで使用されている場合は削除できない。->CustomerTypeService.validateRemove
		List<ProductType> productTypes = productTypeRepository.productTypesOfCustomerType(new CustomerTypeId(customerTypeId));
		if(productTypes.size() > 0) {
			throw new Exception("The customerType already used by product types."); 
		}
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfCustomerType(new CustomerTypeId(customerTypeId));
		if(customerCategories.size() > 0) {
			throw new Exception("The customerType already used by customer categories."); 
		}
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfCustomerType(new CustomerTypeId(customerTypeId));
		if(businessDomains.size() > 0) {
			throw new Exception("The customerType already used by business domains."); 
		}

		
		customerTypeRepository.remove(customerType);

		
	}

	@Override
	public CustomerTypeDto findCustomerTypeById(String customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId name may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(customerTypeId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(customerType == null) {
			throw new Exception("The customerType name may not be set to null.");
		}
		return this.convertCustomerTypeToCustomerTypeDto(customerType);

	}

	@Override
	public List<CustomerTypeDto> findCustomerTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<CustomerType> customerTypes = customerTypeRepository.customerTypesOfCompany(new CompanyId(companyId));
		return this.convertCustomerTypesToCustomerTypeDtos(customerTypes);
	}
	
	public List<CustomerTypeDto> findCustomerTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		List<CustomerType> customerTypes = customerTypeRepository.customerTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
		return this.convertCustomerTypesToCustomerTypeDtos(customerTypes);
	}

	/*
	 * 製品タイプ
	 */
	@Override
	public ProductTypeDto addProductType(ProductTypeDto productTypeDto) throws Exception {
		
		// TODO Auto-generated method stub
		//検索キーの存在チェック
		if(productTypeDto.getCompanyId() == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(productTypeDto.getCompanyId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		//名前は必須にする。
		if(productTypeDto.getProductTypeName() == null) {
			throw new IllegalArgumentException("The product type name may not be set to null.");
		}
		if(productTypeDto.getProductTypeName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a product type name.");
		}
		Company company = companyRepository.companyOfId(new CompanyId(productTypeDto.getCompanyId()));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		ProductTypeId productTypeId = productTypeRepository.nextIdentity();
		ProductType productType = company.defineProductType(productTypeId);
		productType.setProductTypeName(productTypeDto.getProductTypeName());
		//ProductClassは必須にする。
		if(productTypeDto.getProductClass() == null) {
			throw new IllegalArgumentException("The product class may not be set to null.");
		}
		productType.setProductClass(productTypeDto.getProductClass());	//顧客タイプは必須にする。←やめる。2022/1/5
		/*
		if(productTypeDto.getCustomerTypeId() == null) {
			throw new IllegalArgumentException("The customer type may not be set to null.");
		}
		if(productTypeDto.getCustomerTypeId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a customer type.");
		}
		*/
		
		if(productTypeDto.getCustomerTypeId() != null) {
			CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(productTypeDto.getCustomerTypeId()));
			
			if(customerType == null) {
				throw new Exception("The customer type may not exist.");
			}
			
			productType.setCustomerTypeId(customerType.customerTypeId());
			productType.setCustomerTypeName(customerType.customerTypeName());
			//CustomerTypeのProblemは使用しなくなったのでissueに変更する 2022/5/21
			productType.setProblem(customerType.getIssue());
		}
		
		if(productTypeDto.getValueProposition() != null) {
			productType.setValueProposition(productTypeDto.getValueProposition());
		}
		
		if(productTypeDto.getSolution() != null) {
			productType.setSolution(productTypeDto.getSolution());
		}
		
		if(productTypeDto.getUrl() != null) {
			productType.setUrl(new Url(productTypeDto.getUrl()));
		}
		if(productTypeDto.getIndicators() != null) {
			productType.setIndicators(productTypeDto.getIndicators());
		}
		if(productTypeDto.getBusinessDomainId() != null) {
			productType.setBusinessDomainId(new BusinessDomainId(productTypeDto.getBusinessDomainId()));
		}
		if(productTypeDto.getAttributes() != null) {
			productType.setAttributes(productTypeDto.getAttributes());
		}
		//データ関連
		if(productTypeDto.getEntityName() != null) {
			productType.setEntityName(productTypeDto.getEntityName());
		}
		if(productTypeDto.getEntityEnglishName() != null) {
			productType.setEntityEnglishName(productTypeDto.getEntityEnglishName());
		}
		if(productTypeDto.getEntityDescription() != null) {
			productType.setEntityDescription(productTypeDto.getEntityDescription());
		}
		if(productTypeDto.getDataOwner() != null) {
			productType.setDataOwner(productTypeDto.getDataOwner());
		}
		if(productTypeDto.getAddress() != null) {
			productType.setAddress(new Email(productTypeDto.getAddress()));
		}
		if(productTypeDto.getAssociatedConstraint() != null) {
			productType.setAssociatedConstraint(productTypeDto.getAssociatedConstraint());
		}
		if(productTypeDto.getExistenceConstraint() != null) {
			productType.setExistenceConstraint(productTypeDto.getExistenceConstraint());
		}
		if(productTypeDto.getDataAmount() != null) {
			productType.setDataAmount(productTypeDto.getDataAmount());
		}
		if(productTypeDto.getDataTypeId() != null) {
			productType.setDataTypeId(new DataTypeId(productTypeDto.getDataTypeId()));
		}
		
		
		
		productType.setCreatedAt(new Date());	
		productTypeRepository.save(productType);
		productTypeDto.setProductTypeId(productType.productTypeId().productTypeId());
		return productTypeDto;
	}

	@Override
	public void updateProductType(ProductTypeDto productTypeDto) throws Exception {
		// TODO Auto-generated method stub
		if(productTypeDto == null) {
			throw new IllegalArgumentException("The productTypeDto name may not be set to null.");
		}
		String productTypeId = productTypeDto.getProductTypeId();
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId name may not be set to null.");
		}
		if(productTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}
		ProductType productType = productTypeRepository.productTypeOfId(new ProductTypeId(productTypeId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(productType == null) {
			throw new Exception("The productType name may not be set to null.");
		}
		//基準となる項目（CompanyId、productTypeId）は更新しない
		//それ以外は、属性の内容に変更があれば更新するようにする（任意項目の更新）
		//名前は必須項目なので、isEmptyはNG。
		if(productTypeDto.getProductTypeName() != null && !productTypeDto.getProductTypeName().isEmpty() && !productTypeDto.getProductTypeName().equals(productType.productTypeName())) {
			productType.setProductTypeName(productTypeDto.getProductTypeName());
		}
		//CustomerTypeIdは必須項目なのでisEmptyはNG。←やめる。2022/1/5
		/*
		if(productTypeDto.getCustomerTypeId() != null && !productTypeDto.getCustomerTypeId().isEmpty() && !productTypeDto.getCustomerTypeId().equals(productType.customerTypeId().customerTypeId())) {
			CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(productTypeDto.getCustomerTypeId()));
			if(customerType == null) {
				throw new Exception("The customer type may not exist.");
			}
			productType.setCustomerTypeId(customerType.customerTypeId());
			productType.setCustomerTypeName(customerType.customerTypeName());
			productType.setProblem(customerType.problem());
		}
		*/
		if(productType.customerTypeId() != null) {
			CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(productTypeDto.getCustomerTypeId()));
			if(customerType == null) {
				throw new Exception("The customer type may not exist.");
			}
			productType.setCustomerTypeId(customerType.customerTypeId());
			productType.setCustomerTypeName(customerType.customerTypeName());
			//CustomerTypeのProblemは使用しなくなったのでissueに変更する 2022/5/21
			productType.setProblem(customerType.getIssue());
		}
		if(productTypeDto.getProductClass() != null) {
			productType.setProductClass(productTypeDto.getProductClass());
		}
		//Solutionは設定しないというケースもあるのでisEmptyはOK。
		if(productTypeDto.getSolution() != null ) {
			productType.setSolution(productTypeDto.getSolution());
		}
		if(productTypeDto.getValueProposition() != null ) {
			productType.setValueProposition(productTypeDto.getValueProposition());
		}
		if(productTypeDto.getUrl() != null ) {
			productType.setUrl(new Url(productTypeDto.getUrl()));
		}else {
			productType.setUrl(null);
		}
		if(productTypeDto.getIndicators() != null ) {
			productType.setIndicators(productTypeDto.getIndicators());
		}
		if(productTypeDto.getBusinessDomainId() != null) {
			productType.setBusinessDomainId(new BusinessDomainId(productTypeDto.getBusinessDomainId()));
		}
		if(productTypeDto.getAttributes() != null ) {
			productType.setAttributes(productTypeDto.getAttributes());
		}
		//データ関連
		if(productTypeDto.getEntityName() != null) {
			productType.setEntityName(productTypeDto.getEntityName());
		}
		if(productTypeDto.getEntityEnglishName() != null) {
			productType.setEntityEnglishName(productTypeDto.getEntityEnglishName());
		}
		if(productTypeDto.getEntityDescription() != null) {
			productType.setEntityDescription(productTypeDto.getEntityDescription());
		}
		if(productTypeDto.getDataOwner() != null) {
			productType.setDataOwner(productTypeDto.getDataOwner());
		}
		if(productTypeDto.getAddress() != null) {
			productType.setAddress(new Email(productTypeDto.getAddress()));
		}else {
			productType.setAddress(null);
		}
		if(productTypeDto.getAssociatedConstraint() != null) {
			productType.setAssociatedConstraint(productTypeDto.getAssociatedConstraint());
		}
		if(productTypeDto.getExistenceConstraint() != null) {
			productType.setExistenceConstraint(productTypeDto.getExistenceConstraint());
		}
		if(productTypeDto.getDataAmount() != null) {
			productType.setDataAmount(productTypeDto.getDataAmount());
		}
		if(productTypeDto.getDataTypeId() != null) {
			productType.setDataTypeId(new DataTypeId(productTypeDto.getDataTypeId()));
		}else {
			productType.setDataTypeId(null);
		}

		//事後条件
		//事業ドメインで使用されている場合は編集、削除できない。->ProductService.validateProductTypeEdit
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfProductType(new ProductTypeId(productTypeDto.getProductTypeId()));
		if(businessDomains.size() > 0) {
			throw new Exception("The ProductType already used by business domains."); 
		}
		productType.setUpdatedAt(new Date());		
		productTypeRepository.save(productType);
		
	}

	@Override
	public void deleteProductType(String productTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId name may not be set to null.");
		}
		if(productTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}
		ProductType productType = productTypeRepository.productTypeOfId(new ProductTypeId(productTypeId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(productType == null) {
			throw new Exception("The productType name may not be set to null.");
		}
		//事後条件
		//事業ドメインで使用されている場合は編集、削除できない。
		List<BusinessDomain> businessDomains = businessDomainRepository.businessDomainsOfProductType(new ProductTypeId(productTypeId));
		if(businessDomains.size() > 0) {
			throw new Exception("The ProductType already used by business domains."); 
		}

		productTypeRepository.remove(productType);
		
	}

	@Override
	public ProductTypeDto findProductTypeById(String productTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId name may not be set to null.");
		}
		if(productTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}
		ProductType productType = productTypeRepository.productTypeOfId(new ProductTypeId(productTypeId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(productType == null) {
			throw new Exception("The productType name may not be set to null.");
		}
		return this.convertProductTypeToProductTypeDto(productType);

	}

	@Override
	public List<ProductTypeDto> findProductTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<ProductType> productTypes = productTypeRepository.productTypesOfCompany(new CompanyId(companyId));
		return this.convertProductTypesToProductTypeDtos(productTypes);
	}
	
	public List<ProductTypeDto> findProductTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		if(businessDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessDomainId.");
		}
		List<ProductType> productTypes = productTypeRepository.productTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
		return this.convertProductTypesToProductTypeDtos(productTypes);
	}
	

	@Override
	public List<ProductTypeDto> findProductTypesOfCustomerType(String customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		List<ProductType> productTypes = productTypeRepository.productTypesOfCustomerType(new CustomerTypeId(customerTypeId));
		return this.convertProductTypesToProductTypeDtos(productTypes);
	}
	
	public boolean isProductTypeUsed(ProductTypeId productTypeId) throws Exception{
		return productTypeService.isUsed(productTypeId);
	}

	/*
	 * 顧客カテゴリ
	 */
	@Override
	public CustomerCategoryDto addCustomerCategory(CustomerCategoryDto customerCategoryDto) throws Exception {
		// TODO Auto-generated method stub
		//検索キーの存在チェック
		if(customerCategoryDto.getCompanyId() == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(customerCategoryDto.getCompanyId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		if(customerCategoryDto.getCustomerTypeId() == null) {
			throw new IllegalArgumentException("The customerTypeId name may not be set to null.");
		}
		if(customerCategoryDto.getCustomerTypeId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		//名前は必須にする。
		if(customerCategoryDto.getCustomerCategoryName() == null) {
			throw new IllegalArgumentException("The customer category name may not be set to null.");
		}
		if(customerCategoryDto.getCustomerCategoryName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a customer category name.");
		}
		
		Company company = companyRepository.companyOfId(new CompanyId(customerCategoryDto.getCompanyId()));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		CustomerType customerType = customerTypeRepository.customerTypeOfId(new CustomerTypeId(customerCategoryDto.getCustomerTypeId()));
		if(customerType == null) {
			throw new Exception("The customerType may not exist.");
		}
		CustomerCategoryId customerCategoryId = customerCategoryRepository.nextIdentity();
		CustomerCategory customerCategory = customerType.defineCustomerCategory(customerCategoryId);
		customerCategory.setCustomerCategoryName(customerCategoryDto.getCustomerCategoryName());
		
		if(customerCategoryDto.getCustomerTypeName() != null) {
			customerCategory.setCustomerTypeName(customerCategoryDto.getCustomerTypeName());
		}
		if(customerCategoryDto.getIndustryGroupId() != null && customerCategoryDto.getIndustryGroupName() != null) {
			customerCategory.setIndustryGroup(new IndustryGroup(customerCategoryDto.getIndustryGroupId(), customerCategoryDto.getIndustryGroupName()));
		}
		if(customerCategoryDto.getIndustryId() != null && customerCategoryDto.getIndustryName() != null) {
			customerCategory.setIndustry(new Industry(customerCategoryDto.getIndustryId(), customerCategoryDto.getIndustryName()));
		}
		if(customerCategoryDto.getIndustrySubGroupId() != null && customerCategoryDto.getIndustrySubGroupName() != null) {
			customerCategory.setIndustrySubGroup(new IndustrySubGroup(customerCategoryDto.getIndustrySubGroupId(), customerCategoryDto.getIndustrySubGroupName()));
		}
		if(customerCategoryDto.getGender() != null) {
			customerCategory.setGender(customerCategoryDto.getGender());
		}
		if(customerCategoryDto.getPersonality() != null) {
			customerCategory.setPersonality(customerCategoryDto.getPersonality());
		}
		if(customerCategoryDto.getSize() != null) {
			customerCategory.setSize(customerCategoryDto.getSize());
		}
		if(customerCategoryDto.getCountries() != null) {
			customerCategory.setCoutries(customerCategoryDto.getCountries());		
		}
		if(customerCategoryDto.getAges() != null) {
			customerCategory.setAges(customerCategoryDto.getAges());
		}
		if(customerCategoryDto.getGoals() != null) {
			customerCategory.setGoals(customerCategoryDto.getGoals());
		}
		if(customerCategoryDto.getAchievements() != null) {
			customerCategory.setAchievements(customerCategoryDto.getAchievements());
		}
		if(customerCategoryDto.getUrl() != null ) {
			customerCategory.setUrl(new Url(customerCategoryDto.getUrl()));
		}
		if(customerCategoryDto.getCustomerCategoryDescription() != null ) {
			customerCategory.setCustomerCategoryDescription(customerCategoryDto.getCustomerCategoryDescription());
		}
		if(customerCategoryDto.getBusinessUnitId() != null) {
			customerCategory.setBusinessUnitId(new BusinessUnitId(customerCategoryDto.getBusinessUnitId()));
        }


		customerCategoryRepository.save(customerCategory);
		customerCategoryDto.setCustomerCategoryId(customerCategory.customerCategoryId().customerCategoryId());
		return customerCategoryDto;
	}

	@Override
	public void updateCustomerCategory(CustomerCategoryDto customerCategoryDto) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryDto == null) {
			throw new IllegalArgumentException("The customerCategoryDto name may not be set to null.");
		}
		String customerCategoryId = customerCategoryDto.getCustomerCategoryId();
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId name may not be set to null.");
		}
		if(customerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerCategoryId.");
		}
		CustomerCategory customerCategory = customerCategoryRepository.customerCategoryOfId(new CustomerCategoryId(customerCategoryId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(customerCategory == null) {
			throw new Exception("The customerCategory name may not be set to null.");
		}
		//基準となる項目（CompanyId、customerTypeId, customerCategoryId）は更新しない
		//それ以外は、属性の内容に変更があれば更新するようにする（任意項目の更新）
		//ビジネスドメイン名は必須項目なので、isEmptyはNG。
		if(customerCategoryDto.getCustomerCategoryName() != null && !customerCategoryDto.getCustomerCategoryName().isEmpty() && !customerCategoryDto.getCustomerCategoryName().equals(customerCategory.customerCategoryName())) {
			customerCategory.setCustomerCategoryName(customerCategoryDto.getCustomerCategoryName());
		}
		//任意項目の場合、設定されていない場合もあるので、customerCategory.customerTypeName()がNULLの場合もあり、!customerCategoryDto.getCustomerTypeName().equals(customerCategory.customerTypeName()のような同一性チェックはしなようにする。
		//if(customerCategoryDto.getCustomerTypeName() != null && !customerCategoryDto.getCustomerTypeName().equals(customerCategory.customerTypeName())) {
		if(customerCategoryDto.getCustomerTypeName() != null ) {
			customerCategory.setCustomerTypeName(customerCategoryDto.getCustomerTypeName());
		}
		if(customerCategoryDto.getIndustryGroupId() != null && customerCategoryDto.getIndustryGroupName() != null ) {
			customerCategory.setIndustryGroup(new IndustryGroup(customerCategoryDto.getIndustryGroupId(), customerCategoryDto.getIndustryGroupName()));
		}
		if(customerCategoryDto.getIndustryId() != null && customerCategoryDto.getIndustryName() != null ) {
			customerCategory.setIndustry(new Industry(customerCategoryDto.getIndustryId(), customerCategoryDto.getIndustryName()));
		}
		if(customerCategoryDto.getIndustrySubGroupId() != null && customerCategoryDto.getIndustrySubGroupName() != null ) {
			customerCategory.setIndustrySubGroup(new IndustrySubGroup(customerCategoryDto.getIndustrySubGroupId(), customerCategoryDto.getIndustrySubGroupName()));
		}
		customerCategory.setGender(customerCategoryDto.getGender());
		/*
		if(customerCategoryDto.getGender() != null ) {
			customerCategory.setGender(customerCategoryDto.getGender());
		}
		*/
		if(customerCategoryDto.getPersonality() != null ) {
			customerCategory.setPersonality(customerCategoryDto.getPersonality());
		}
		if(customerCategoryDto.getSize() != null ) {
			customerCategory.setSize(customerCategoryDto.getSize());
		}
		if(customerCategoryDto.getCountries() != null) {
			customerCategory.setCoutries(customerCategoryDto.getCountries());		
		}
		if(customerCategoryDto.getAges() != null) {
			customerCategory.setAges(customerCategoryDto.getAges());
		}
		if(customerCategoryDto.getGoals() != null) {
			customerCategory.setGoals(customerCategoryDto.getGoals());
		}
		if(customerCategoryDto.getAchievements() != null) {
			customerCategory.setAchievements(customerCategoryDto.getAchievements());
		}
		if(customerCategoryDto.getUrl() != null ) {
			customerCategory.setUrl(new Url(customerCategoryDto.getUrl()));
		}
		if(customerCategoryDto.getCustomerCategoryDescription() != null ) {
			customerCategory.setCustomerCategoryDescription(customerCategoryDto.getCustomerCategoryDescription());
		}
		if(customerCategoryDto.getBusinessUnitId() != null) {
			customerCategory.setBusinessUnitId(new BusinessUnitId(customerCategoryDto.getBusinessUnitId()));
        }
		//事後条件
		//製品カテゴリ、事業単位で使用されている場合は編集、削除できない。->CustomerCategory.validateEdit
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfCustomerCategory(new CustomerCategoryId(customerCategoryDto.getCustomerCategoryId()));
		if(productCategories.size() > 0) {
			throw new Exception("The customer category already used by product categories."); 
		}
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfCustomerCategory(new CustomerCategoryId(customerCategoryDto.getCustomerCategoryId()));
		if(businessUnits.size() > 0) {
			throw new Exception("The customer category already used by business domains."); 
		}

		customerCategoryRepository.save(customerCategory);
	}

	@Override
	public void deleteCustomerCategory(String customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId name may not be set to null.");
		}
		if(customerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerCategoryId.");
		}
		CustomerCategory customerCategory = customerCategoryRepository.customerCategoryOfId(new CustomerCategoryId(customerCategoryId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(customerCategory == null) {
			throw new Exception("The customerCategory name may not be set to null.");
		}
		//事後条件
		//製品カテゴリ、事業単位で使用されている場合は編集、削除できない。->CustomerCategoryService.validateRemove
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfCustomerCategory(new CustomerCategoryId(customerCategoryId));
		if(productCategories.size() > 0) {
			throw new Exception("The customer category already used by product categories."); 
		}
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfCustomerCategory(new CustomerCategoryId(customerCategoryId));
		if(businessUnits.size() > 0) {
			throw new Exception("The customer category already used by business domains."); 
		}
		customerCategoryRepository.remove(customerCategory);

		
	}

	@Override
	public CustomerCategoryDto findCustomerCategoryById(String customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId name may not be set to null.");
		}
		if(customerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerCategoryId.");
		}
		CustomerCategory customerCategory = customerCategoryRepository.customerCategoryOfId(new CustomerCategoryId(customerCategoryId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(customerCategory == null) {
			throw new Exception("The customerCategory name may not be set to null.");
		}
		return this.convertCustomerCategoryToCustomerCategoryDto(customerCategory);

	}

	@Override
	public List<CustomerCategoryDto> findCustomerCategoriesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfCompany(new CompanyId(companyId));
		return this.convertCustomerCategoriesToCustomerTypeDtos(customerCategories);

	}
	
	public List<CustomerCategoryDto> findCustomerCategoriesOfBusinessUnit(String businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		if(businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessUnitId.");
		}
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
		return this.convertCustomerCategoriesToCustomerTypeDtos(customerCategories);

	}
	

	@Override
	public List<CustomerCategoryDto> findCustomerCategoriesOfCustomerType(String customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		//検索キーのチェック（事前条件チェック）
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		if(customerTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerTypeId.");
		}
		List<CustomerCategory> customerCategories = customerCategoryRepository.customerCategoriesOfCustomerType(new CustomerTypeId(customerTypeId));
		return this.convertCustomerCategoriesToCustomerTypeDtos(customerCategories);
	}

	/*
	 * 製品カテゴリ
	 */
	@Override
	public ProductCategoryDto addProductCategory(ProductCategoryDto productCategoryDto) throws Exception {
		// TODO Auto-generated method stub
		//検索キーの存在チェック
		if(productCategoryDto.getCompanyId() == null) {
			throw new IllegalArgumentException("The companyId name may not be set to null.");
		}
		if(productCategoryDto.getCompanyId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		if(productCategoryDto.getProductTypeId() == null) {
			throw new IllegalArgumentException("The productTypeId name may not be set to null.");
		}
		if(productCategoryDto.getProductTypeId().isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}
		//名前は必須にする。
		if(productCategoryDto.getProductCategoryName() == null) {
			throw new IllegalArgumentException("The product category name may not be set to null.");
		}
		if(productCategoryDto.getProductCategoryName().isEmpty()) {
			throw new IllegalArgumentException("Must provide a product category name.");
		}
		
		Company company = companyRepository.companyOfId(new CompanyId(productCategoryDto.getCompanyId()));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(company == null) {
			throw new Exception("The company may not exist.");
		}
		ProductType productType = productTypeRepository.productTypeOfId(new ProductTypeId(productCategoryDto.getProductTypeId()));
		ProductCategoryId productCategoryId = productCategoryRepository.nextIdentity();
		ProductCategory productCategory = productType.defineProductCategory(productCategoryId);
		productCategory.setProductCategoryName(productCategoryDto.getProductCategoryName());
		
		if(productCategoryDto.getProductTypeName() != null) {
			productCategory.setProductTypeName(productCategoryDto.getProductTypeName());
		}
		if(productCategoryDto.getProductCategoryDescription() != null) {
			productCategory.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
		}
		if(productCategoryDto.getProductCategoryPosition() != null) {
			productCategory.setProductCategoryPosition(productCategoryDto.getProductCategoryPosition());
		}
		if(productCategoryDto.getCustomerCategoryId() != null) {
			productCategory.setCustomerCategoryId(new CustomerCategoryId(productCategoryDto.getCustomerCategoryId()));
		}
		if(productCategoryDto.getCustomerCategoryName() != null) {
			productCategory.setCustomerCategoryName(productCategoryDto.getCustomerCategoryName());
		}
		if(productCategoryDto.getIndustryGroupId() != null && productCategoryDto.getIndustryGroupName() != null) {
			productCategory.setIndustryGroup(new IndustryGroup(productCategoryDto.getIndustryGroupId(), productCategoryDto.getIndustryGroupName()));
		}
		if(productCategoryDto.getIndustryId() != null && productCategoryDto.getIndustryName() != null) {
			productCategory.setIndustry(new Industry(productCategoryDto.getIndustryId(), productCategoryDto.getIndustryName()));
		}
		if(productCategoryDto.getIndustrySubGroupId() != null && productCategoryDto.getIndustrySubGroupName() != null) {
			productCategory.setIndustrySubGroup(new IndustrySubGroup(productCategoryDto.getIndustrySubGroupId(), productCategoryDto.getIndustrySubGroupName()));
		}
		if(productCategoryDto.getProductClass() != null) {
			productCategory.setProductClass(productCategoryDto.getProductClass());
		}
		
		if(productCategoryDto.getRoles() != null) {
			productCategory.setRoles(productCategoryDto.getRoles());		
		}
		
		if(productCategoryDto.getGoodses() != null) {
			productCategory.setGoodses(productCategoryDto.getGoodses());		
		}
		if(productCategoryDto.getServices() != null) {
			productCategory.setServices(productCategoryDto.getServices());		
		}
		if(productCategoryDto.getPersonality() != null) {
			productCategory.setPersonality(productCategoryDto.getPersonality());
		}
		if(productCategoryDto.getServiceType() != null) {
			productCategory.setServiceType(productCategoryDto.getServiceType());
		}
		if(productCategoryDto.getService() != null) {
			productCategory.setService(productCategoryDto.getService());
		}
		if(productCategoryDto.getGoals() != null) {
			productCategory.setGoals(productCategoryDto.getGoals());
		}
		if(productCategoryDto.getAchievements() != null) {
			productCategory.setAchievements(productCategoryDto.getAchievements());
		}
		if(productCategoryDto.getUrl() != null ) {
			productCategory.setUrl(new Url(productCategoryDto.getUrl()));
		}
		if(productCategoryDto.getCustomerJourney() != null) {
			productCategory.setCustomerJourney(productCategoryDto.getCustomerJourney());
		}
		if(productCategoryDto.getServiceScenario() != null) {
			productCategory.setServiceScenario(productCategoryDto.getServiceScenario());
		}
		if(productCategoryDto.getDomainModel() != null) {
			productCategory.setDomainModel(productCategoryDto.getDomainModel());
		}
		if(productCategoryDto.getDemo() != null) {
			productCategory.setDemo(productCategoryDto.getDemo());
		}
		
		if(productCategoryDto.getBusinessUnitId() != null) {
			productCategory.setBusinessUnitId(new BusinessUnitId(productCategoryDto.getBusinessUnitId()));
        }

		productCategoryRepository.save(productCategory);
		productCategoryDto.setCustomerCategoryId(productCategory.productCategoryId().productCategoryId());
		return productCategoryDto;

	}

	@Override
	public void updateProductCategory(ProductCategoryDto productCategoryDto) throws Exception {
		// TODO Auto-generated method stub
		if(productCategoryDto == null) {
			throw new IllegalArgumentException("The productCategoryDto name may not be set to null.");
		}
		String productCategoryId = productCategoryDto.getProductCategoryId();
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId name may not be set to null.");
		}
		if(productCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productCategoryId.");
		}
		ProductCategory productCategory = productCategoryRepository.productCategoryOfId(new ProductCategoryId(productCategoryId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(productCategory == null) {
			throw new Exception("The productCategory name may not be set to null.");
		}
		//基準となる項目（CompanyId、customerTypeId, customerCategoryId）は更新しない
		//それ以外は、属性の内容に変更があれば更新するようにする（任意項目の更新）
		//ビジネスドメイン名は必須項目なので、isEmptyはNG。
		if(productCategoryDto.getProductCategoryName() != null && !productCategoryDto.getProductCategoryName().isEmpty() && !productCategoryDto.getProductCategoryName().equals(productCategory.productCategoryName())) {
			productCategory.setProductCategoryName(productCategoryDto.getProductCategoryName());
		}
		//任意項目の場合、設定しておらずNULLの場合もあるので同一性チェックはしない。
		//if(productCategoryDto.getProductCategoryDescription() != null && !productCategoryDto.getProductCategoryDescription().equals(productCategory.productCategoryDescription())) {
		if(productCategoryDto.getProductCategoryDescription() != null ) {
			productCategory.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
		}
		if(productCategoryDto.getProductCategoryPosition() != null ) {
			productCategory.setProductCategoryPosition(productCategoryDto.getProductCategoryPosition());
		}
		if(productCategoryDto.getCustomerCategoryId() != null && !productCategoryDto.getCustomerCategoryId().isEmpty() ) {
			productCategory.setCustomerCategoryId(new CustomerCategoryId(productCategoryDto.getCustomerCategoryId()));
		}
		if(productCategoryDto.getCustomerCategoryName() != null  ) {
			productCategory.setCustomerCategoryName(productCategoryDto.getCustomerCategoryName());
		}
		if(productCategoryDto.getProductTypeName() != null ) {
			productCategory.setProductTypeName(productCategoryDto.getProductTypeName());
		}
		if(productCategoryDto.getIndustryGroupId() != null && productCategoryDto.getIndustryGroupName() != null ) {
			productCategory.setIndustryGroup(new IndustryGroup(productCategoryDto.getIndustryGroupId(), productCategoryDto.getIndustryGroupName()));
		}
		if(productCategoryDto.getIndustryId() != null && productCategoryDto.getIndustryName() != null ) {
			productCategory.setIndustry(new Industry(productCategoryDto.getIndustryId(), productCategoryDto.getIndustryName()));
		}
		if(productCategoryDto.getIndustrySubGroupId() != null && productCategoryDto.getIndustrySubGroupName() != null ) {
			productCategory.setIndustrySubGroup(new IndustrySubGroup(productCategoryDto.getIndustrySubGroupId(), productCategoryDto.getIndustrySubGroupName()));
		}
		if(productCategoryDto.getProductClass() != null) {
			productCategory.setProductClass(productCategoryDto.getProductClass());
		}
		//サービスから商品に更新する場合、DBからサービスデータを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		//if(productCategoryDto.getRoles() != null) {
			productCategory.setRoles(productCategoryDto.getRoles());		
		//}
		//商品からサービスに更新する場合、DBから商品データを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		//if(productCategoryDto.getGoodses() != null) {
			productCategory.setGoodses(productCategoryDto.getGoodses());		
		//}
		
		if(productCategoryDto.getServices() != null) {
			productCategory.setServices(productCategoryDto.getServices());		
		}
		if(productCategoryDto.getPersonality() != null) {
			productCategory.setPersonality(productCategoryDto.getPersonality());
		}
		if(productCategoryDto.getUrl() != null ) {
			productCategory.setUrl(new Url(productCategoryDto.getUrl()));
		}else {
			productCategory.setUrl(null);
		}
		if(productCategoryDto.getCustomerJourney() != null) {
			productCategory.setCustomerJourney(productCategoryDto.getCustomerJourney());
		}
		if(productCategoryDto.getServiceScenario() != null) {
			productCategory.setServiceScenario(productCategoryDto.getServiceScenario());
		}
		if(productCategoryDto.getDomainModel() != null) {
			productCategory.setDomainModel(productCategoryDto.getDomainModel());
		}
		if(productCategoryDto.getDemo() != null) {
			productCategory.setDemo(productCategoryDto.getDemo());
		}
		if(productCategoryDto.getBusinessUnitId() != null) {
			productCategory.setBusinessUnitId(new BusinessUnitId(productCategoryDto.getBusinessUnitId()));
        }
		//サービスから商品に更新する場合、DBからサービスタイプデータを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		//if(productCategoryDto.getServiceType() != null) {
			productCategory.setServiceType(productCategoryDto.getServiceType());
		//}
		//サービスから商品に更新する場合、DBからサービスデータを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		//if(productCategoryDto.getService() != null) {
			productCategory.setService(productCategoryDto.getService());
		//}
			
			productCategory.setGoals(productCategoryDto.getGoals());
			productCategory.setAchievements(productCategoryDto.getAchievements());
		
		//事後条件
		//事業単位で使用されている場合は編集、削除できない。->ProductCategoryService.validateEdit
		//ProductCategoryServiceに移行。2022/1/14
		/*
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfProductCategory(productCategoryDto.getProductCategoryId());
		if(businessUnits.size() > 0) {
			throw new Exception("The product category already used by business domains."); 
	     }
	     */
	 
		productCategoryRepository.save(productCategory);

		
	}

	@Override
	public void deleteProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId name may not be set to null.");
		}
		if(productCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productCategoryId.");
		}
		ProductCategory productCategory = productCategoryRepository.productCategoryOfId(new ProductCategoryId(productCategoryId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(productCategory == null) {
			throw new Exception("The productCategory name may not be set to null.");
		}
		
		//事後条件
		//事業単位で使用されている場合は編集、削除できない。->ProductCategoryService.validateRemove
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfProductCategory(productCategoryId);
		if(businessUnits.size() > 0) {
			throw new Exception("The product category already used by business domains."); 
	     }
		
		productCategoryRepository.remove(productCategory);

		
	}

	@Override
	public ProductCategoryDto findProductCategoryById(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId name may not be set to null.");
		}
		if(productCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productCategoryId.");
		}
		ProductCategory productCategory = productCategoryRepository.productCategoryOfId(new ProductCategoryId(productCategoryId));
		//Repositoryで存在チェックは行っているが、アプリケーションサービスをRepository非依存にするために存在チェックをする。
		if(productCategory == null) {
			throw new Exception("The productCategory name may not be set to null.");
		}
		return this.convertProductCategoryToProductCategoryDto(productCategory);

	}

	@Override
	public List<ProductCategoryDto> findProductCategoriesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		if(companyId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfCompany(new CompanyId(companyId));
		return this.convertProductCategoriesToProductCategoryDtos(productCategories);

	}
	
	public List<ProductCategoryDto> findProductCategoriesOfBusinessUnit(String businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		if(businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a businessUnitId.");
		}
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfBusinessUnit(new BusinessUnitId(businessUnitId));
		return this.convertProductCategoriesToProductCategoryDtos(productCategories);

	}

	@Override
	public List<ProductCategoryDto> findProductCategoriesOfCustomerCategory(String customerCategoryId)
			throws Exception {
		// TODO Auto-generated method stub
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		if(customerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a customerCategoryId.");
		}
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfCustomerCategory(new CustomerCategoryId(customerCategoryId));
		return this.convertProductCategoriesToProductCategoryDtos(productCategories);
	}
	
	public List<ProductCategoryDto> findProductCategoriesOfProductClass(String businessUnitId, ProductClass productClass) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		if(productClass == null) {
			throw new IllegalArgumentException("The_productClass_may_not_be_set_to_null");
		}
		return this.convertProductCategoriesToProductCategoryDtos(productCategoryRepository.productCategoriesOfProductClass(new BusinessUnitId(businessUnitId), productClass));
	}

	@Override
	public List<ProductCategoryDto> findProductCategoriesOfProductType(String productTypeId) throws Exception {
		// TODO Auto-generated method stub
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		if(productTypeId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a productTypeId.");
		}
		List<ProductCategory> productCategories = productCategoryRepository.productCategoriesOfProductType(new ProductTypeId(productTypeId));
		return this.convertProductCategoriesToProductCategoryDtos(productCategories);
	
	}

	/*
	 * ジョブ
	 */
	@Override
	public JobDto addJob(JobDto jobDto) throws Exception {
		// TODO Auto-generated method stub
		Company company = companyRepository.companyOfId(new CompanyId(jobDto.getCompanyId()));
		if(company == null) {
			throw new Exception("The company does not exist.");
		}
		//必須項目の検証はPojoで行っているのでアプリケーションサービスで行う必要はない（Pojoの不変条件）
		JobId jobId = jobRepository.nextIdentity();
		JobType jobType = jobDto.getJobType();
		String jobName = jobDto.getJobName();
		List<Role> roles = jobDto.getRoles();
		Job job = company.defineJob(jobId, jobType, jobName, roles);
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの値オブジェクトに対する事前条件）
		if(jobDto.getBusinessDomainId() != null) {
			job.setBusinessDomainId(new BusinessDomainId(jobDto.getBusinessDomainId()));
		}
		if(jobDto.getBusinessDomainName() != null) {
			job.setBusinessDomainName(jobDto.getBusinessDomainName());
		}
		if(jobDto.getUrl() != null) {
			job.setUrl(new Url(jobDto.getUrl()));
		}
		if(jobDto.getJobDescription() != null) {
			job.setJobDescription(jobDto.getJobDescription());
		}
		if(jobDto.getIndicators() != null) {
			job.setIndicators(jobDto.getIndicators());
		}
		job.setCreatedAt(new Date());	
		jobRepository.save(job);
		jobDto.setJobId(jobId.jobId());
		
		//アプリケーションタイプを自動生成する。2022/9/3
		ApplicationTypeDto appDto = new ApplicationTypeDto();
		appDto.setApplicationTypeName(job.getJobName());
		appDto.setJobId(jobId.jobId());
		appDto.setJobName(job.getJobName());
		appDto.setBusinessDomainId(job.getBusinessDomainId().businessDomainId());
		appDto.setBusinessDomainName(job.getBusinessDomainName());
		appDto.setCompanyId(job.getCompanyId().id());
		applicationTypeService.defineApplicationType(appDto);
		
		
		return jobDto;
	}

	@Override
	public void updateJob(JobDto jobDto) throws Exception {
		// TODO Auto-generated method stub
		Job job = jobRepository.jobOfId(new JobId(jobDto.getJobId()));
		if(job == null) {
			throw new Exception("The job does not exist.");
		}
		
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(jobDto.getRoles() != null) {
			job.setRoles(jobDto.getRoles());
		}
		if(jobDto.getJobName() != null && !jobDto.getJobDescription().isEmpty()) {
			job.setJobName(jobDto.getJobName());
		}
		if(jobDto.getJobType() != null) {
			job.setJobType(jobDto.getJobType());
		}
		if(jobDto.getBusinessDomainId() != null) {
			job.setBusinessDomainId(new BusinessDomainId(jobDto.getBusinessDomainId()));
		//ユースケースとして事業ドメインを設定しない場合、明示的にNULLを設定する。
		}else {
			job.setBusinessDomainId(null);
		}
		//BusinessDomainNameの場合、ユースケースとしてNULLを設定したい場合がある
		job.setBusinessDomainName(jobDto.getBusinessDomainName());
		if(jobDto.getUrl() != null) {
			job.setUrl(new Url(jobDto.getUrl()));
		//ユースケースとしてURLを設定しない場合、明示的にNULLを設定する。
		}else {
			job.setUrl(null);
		}
		//Descriptionの場合、ユースケースとしてNULLを設定したい場合がある
		//if(jobDto.getJobDescription() != null) {
			job.setJobDescription(jobDto.getJobDescription());
		//}
			job.setIndicators(jobDto.getIndicators());
		job.setUpdatedAt(new Date());
		jobRepository.save(job);
	}

	@Override
	public void deleteJob(String jobId) throws Exception {
		// TODO Auto-generated method stub
		Job job = jobRepository.jobOfId(new JobId(jobId));
		if(job == null) {
			throw new Exception("The job does not exist.");
		}
		
		
		jobRepository.remove(job);
	}

	@Override
	public JobDto findJobById(String jobId) throws Exception {
		// TODO Auto-generated method stub
		Job job = jobRepository.jobOfId(new JobId(jobId));
		if(job == null) {
			throw new Exception("The job does not exist.");
		}
		return this.convertJobToJobDto(job);
	}

	@Override
	public List<JobDto> findJobsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<Job> jobs = jobRepository.jobsOfCompany(new CompanyId(companyId));
		return this.convertJobsToJobDtos(jobs);
	}
    public List<JobDto> findJobsOfBusinessDomain(String businessDomainId) throws Exception{
    	List<Job> jobs = jobRepository.jobsOfBusinessDomain(new BusinessDomainId(businessDomainId));
		return this.convertJobsToJobDtos(jobs);
    }
	
	public List<JobDto> findJobsOfJobType(JobType jobType) throws Exception{
		List<Job> jobs = jobRepository.jobsOfJobType(jobType);
		return this.convertJobsToJobDtos(jobs);
	}
	
	/*
	 * タスク
	 */
	@Override
	public TaskDto addTask(TaskDto taskDto) throws Exception {
		// TODO Auto-generated method stub
		Job job = jobRepository.jobOfId(new JobId(taskDto.getJobId()));
		if(job == null) {
			throw new Exception("The job does not exist.");
		}
		
		TaskId taskId = taskRepository.nextIdentity();
		Function function = new Function(taskDto.getFunctionId(), taskDto.getFunctionName());
		Task task = job.defineTask(taskId, function, taskDto.getTaskName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(taskDto.getTaskDescription() != null) {
			task.setTaskDescription(taskDto.getTaskDescription());
		}
		if(taskDto.getUrl() != null) {
			task.setUrl(new Url(taskDto.getUrl()));
		}
		
		task.setAssociatedDataTypes(taskDto.getAssociatedDataTypes());
		task.setCreatedAt(new Date());
		taskRepository.save(task);
		taskDto.setTaskId(task.getTaskId().taskId());
		
		//アプリケーションタスクを自動的に生成する。2022/9/3
		ApplicationTaskDto appTaskDto = new ApplicationTaskDto();
		appTaskDto.setTaskId(task.getTaskId().taskId());
		appTaskDto.setTaskName(task.getTaskName());	
		appTaskDto.setApplicationTaskName(task.getTaskName());
		List<ApplicationTypeDto> appTypes = applicationTypeService.findApplicationTypesOfJob(task.getJobId().jobId());
		if(!(appTypes.size() > 0)) {
			throw new RuntimeException("The_applicationType_may_not_found");
		}
		ApplicationTypeDto appType = appTypes.get(0);
		appTaskDto.setApplicationTypeId(appType.getApplicationTypeId());
		appTaskDto.setApplicationTypeName(appType.getApplicationTypeName());
		appTaskDto.setCompanyId(task.getCompanyId().id());
		applicationTaskService.defineApplicationTask(appTaskDto);
		
		return taskDto;
	}

	@Override
	public void updateTask(TaskDto taskDto) throws Exception {
		// TODO Auto-generated method stub
		
		Task task = taskRepository.TaskOfId(new TaskId(taskDto.getTaskId()));
		if(task == null) {
			throw new Exception("The task does not exist.");
		}
		if(taskDto.getTaskId() == null || taskDto.getTaskId().isEmpty()) {
			throw new Exception("The_taskId_may_not_be_set_to_null");
		}
		
		
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(taskDto.getFunctionId() != null && taskDto.getFunctionName() != null) {
			Function function = new Function(taskDto.getFunctionId(), taskDto.getFunctionName());
			task.setFunction(function);
		}
		
		if(taskDto.getTaskName() != null && !taskDto.getTaskName().isEmpty()) {
			task.setTaskName(taskDto.getTaskName());
		}
		//Descriptionの場合、ユースケースとしてNULLを設定したい場合がある
		//if(taskDto.getTaskDescription() != null) {
			task.setTaskDescription(taskDto.getTaskDescription());
		//}
		
		if(taskDto.getUrl() != null) {
			task.setUrl(new Url(taskDto.getUrl()));
		//ユースケースとしてURLを設定しない場合、明示的にNULLを設定する。
		}else {
			task.setUrl(null);
		}
		task.setAssociatedDataTypes(taskDto.getAssociatedDataTypes());
		task.setUpdatedAt(new Date());
		
		taskRepository.save(task);
	}

	@Override
	public void deleteTask(String taskId) throws Exception {
		// TODO Auto-generated method stub
		
		Task task = taskRepository.TaskOfId(new TaskId(taskId));
		if(task == null) {
			throw new Exception("The task does not exist.");
		}
		if(taskId == null || taskId.isEmpty()) {
			throw new RuntimeException("The_taskId_may_not_be_set_to_null");
		}
		
		
		taskRepository.remove(task);
	}

	@Override
	public TaskDto findTaskById(String taskId) throws Exception {
		// TODO Auto-generated method stub
		Task task = taskRepository.TaskOfId(new TaskId(taskId));
		if(task == null) {
			throw new Exception("The task does not exist.");
		}
		return this.convertTaskToTaskDto(task);
	}

	@Override
	public List<TaskDto> findTasksOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<Task> tasks = taskRepository.tasksOfCompany(new CompanyId(companyId));
		return this.convertTasksToTaskDtos(tasks);
	}

	@Override
	public List<TaskDto> findTasksOfJob(String jobId) throws Exception {
		// TODO Auto-generated method stub
		List<Task> tasks = taskRepository.tasksOfJob(new JobId(jobId));
		return this.convertTasksToTaskDtos(tasks);
	}
	
	/*
	 * 製品タスク
	 */

	@Override
	public ProductTaskDto addProductTask(ProductTaskDto productTaskDto) throws Exception {
		// TODO Auto-generated method stub
		ProductType productType = productTypeRepository.productTypeOfId(new ProductTypeId(productTaskDto.getProductTypeId()));
		if(productType == null) {
			throw new Exception("The productType does not exist.");
		}
		ProductTaskId productTaskId = productTaskRepository.nextIdentity();
		ProductTask productTask = productType.defineProductTask(productTaskId, productTaskDto.getProductTaskName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(productTaskDto.getProductTaskDescription() != null) {
			productTask.setProductTaskDescription(productTaskDto.getProductTaskDescription());
		}
		if(productTaskDto.getUrl() != null) {
			productTask.setUrl(new Url(productTaskDto.getUrl()));
		}
		productTask.setCreatedAt(new Date());		
		productTaskRepository.save(productTask);
		productTaskDto.setProductTaskId(productTask.getProductTaskId().getProductTaskId());
		return productTaskDto;
	}

	@Override
	public void updateProductTask(ProductTaskDto productTaskDto) throws Exception {
		// TODO Auto-generated method stub
		ProductTask productTask = productTaskRepository.productTaskOfId(new ProductTaskId(productTaskDto.getProductTaskId()));
		if(productTask == null) {
			throw new Exception("The productTask does not exist.");
		}
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		
		
		if(productTaskDto.getProductTaskName() != null && !productTaskDto.getProductTaskName().isEmpty()) {
			productTask.setProductTaskName(productTaskDto.getProductTaskName());
		}
		//Descriptionの場合、ユースケースとしてNULLを設定したい場合がある
		//if(taskDto.getTaskDescription() != null) {
		productTask.setProductTaskDescription(productTaskDto.getProductTaskDescription());
		//}
		
		if(productTaskDto.getUrl() != null) {
			productTask.setUrl(new Url(productTaskDto.getUrl()));
		//ユースケースとしてURLを設定しない場合、明示的にNULLを設定する。
		}else {
			productTask.setUrl(null);
		}
		productTask.setUpdatedAt(new Date());
		productTaskRepository.save(productTask);
		
	}

	@Override
	public void deleteProductTask(String productTaskId) throws Exception {
		// TODO Auto-generated method stub
		ProductTask productTask = productTaskRepository.productTaskOfId(new ProductTaskId(productTaskId));
		if(productTask == null) {
			throw new Exception("The productTask does not exist.");
		}
		productTaskRepository.remove(productTask);
		
	}

	@Override
	public ProductTaskDto findProductTaskById(String productTaskId) throws Exception {
		// TODO Auto-generated method stub
		ProductTask productTask = productTaskRepository.productTaskOfId(new ProductTaskId(productTaskId));
		if(productTask == null) {
			throw new Exception("The productTask does not exist.");
		}
		return this.convertProductTaskToProductTaskDto(productTask);
	}

	@Override
	public List<ProductTaskDto> findProductTasksOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<ProductTask> productTasks = productTaskRepository.productTasksOfCompany(new CompanyId(companyId));
		return this.convertProductTasksToProductTaskDtos(productTasks);
	}

	@Override
	public List<ProductTaskDto> findProductTasksOfProductType(String productTypeId) throws Exception {
		// TODO Auto-generated method stub
		List<ProductTask> productTasks = productTaskRepository.productTasksOfProductType(new ProductTypeId(productTypeId));
		return this.convertProductTasksToProductTaskDtos(productTasks);
	}

	
	
	
	
	
	/*
	 * 部門
	 */
	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) throws Exception {
		// TODO Auto-generated method stub
		Job job = jobRepository.jobOfId(new JobId(departmentDto.getJobId()));
		if(job == null) {
			throw new Exception("The job does not exist.");
		}
		DepartmentId departmentId = departmentRepository.nextIdentity();
		Department department = job.definDepartment(departmentId, departmentDto.getDepartmentType(), departmentDto.getDepartmentName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
        if(departmentDto.getBusinessUnitId() != null) {
        	department.setBusinessUnitId(new BusinessUnitId(departmentDto.getBusinessUnitId()));
        }
        if(departmentDto.getBusinessUnitName() != null) {
        	department.setBusinessUnitName(departmentDto.getBusinessUnitName());
        }
        if(departmentDto.getDepartmentDescription() != null) {
        	department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        }
        if(departmentDto.getUrl() != null) {
        	department.setUrl(new Url(departmentDto.getUrl()));
        }
        if(departmentDto.getGoals() != null) {
        	department.setGoals(departmentDto.getGoals());
        }
        if(departmentDto.getAchievements() != null) {
        	department.setAchievements(departmentDto.getAchievements());
        }
        department.setAssociatedApplicationCategories(departmentDto.getAssociatedApplicationCategories());
        department.setAssociatedPartnerCategories(departmentDto.getAssociatedPartnerCategories());
        
        department.setCreatedAt(new Date());
        departmentRepository.save(department);
		departmentDto.setDepartmentId(department.getDepartmentId().departmentId());
		return departmentDto;
	}

	@Override
	public void updateDepartment(DepartmentDto departmentDto) throws Exception {
		// TODO Auto-generated method stub
		Department department = departmentRepository.departmentOfId(new DepartmentId(departmentDto.getDepartmentId()));
		if(department == null) {
			throw new Exception("The department does not exist.");
		}
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(departmentDto.getDepartmentType() != null) {
			department.setDepartmentType(departmentDto.getDepartmentType());
		}
		if(departmentDto.getDepartmentName() != null) {
			department.setDepartmentName(departmentDto.getDepartmentName());
		}
		if(departmentDto.getBusinessUnitId() != null) {
        	department.setBusinessUnitId(new BusinessUnitId(departmentDto.getBusinessUnitId()));
        }
        if(departmentDto.getBusinessUnitName() != null) {
        	department.setBusinessUnitName(departmentDto.getBusinessUnitName());
        }
        if(departmentDto.getDepartmentDescription() != null) {
        	department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        }
        if(departmentDto.getUrl() != null) {
        	department.setUrl(new Url(departmentDto.getUrl()));
        }
        if(departmentDto.getGoals() != null) {
        	department.setGoals(departmentDto.getGoals());
        }
        if(departmentDto.getAchievements() != null) {
        	department.setAchievements(departmentDto.getAchievements());
        }
        //編集・削除チェック。
		if(!departmentChecker.avarable(departmentDto.getDepartmentId()).equals("OK")) {
			throw new Exception(departmentChecker.avarable(departmentDto.getDepartmentId()));
		}
        department.setAssociatedApplicationCategories(departmentDto.getAssociatedApplicationCategories());
        department.setAssociatedPartnerCategories(departmentDto.getAssociatedPartnerCategories());
       
        department.setUpdatedAt(new Date());        
        departmentRepository.save(department);
	}

	@Override
	public void deleteDepartment(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
		if(department == null) {
			throw new Exception("The department does not exist.");
		}
        //編集・削除チェック。
		if(!departmentChecker.avarable(departmentId).equals("OK")) {
			throw new Exception(departmentChecker.avarable(departmentId));
		}
		departmentRepository.remove(department);
		
	}
	
	public MessageDto assignApplicationToDepartment(String departmentId, AssociatedApplicationCategory application) throws Exception{
		MessageDto message = new MessageDto();
		try {
			if(application == null) {
				throw new IllegalArgumentException("The_application_may_not_be_set_to_null");
			}
			Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
			department.assignApplication(application);
			departmentRepository.save(department);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto assignPartnerToDepartment(String departmentId, AssociatedPartnerCategory partner) throws Exception{
		MessageDto message = new MessageDto();
		try {
			if(partner == null) {
				throw new IllegalArgumentException("The_partner_may_not_be_set_to_null");
			}
			Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
			department.assignPartner(partner);
			departmentRepository.save(department);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
			
		}
		return message;
	}
	
	public MessageDto releaseApplicationFromDepartment(String departmentId, AssociatedApplicationCategory application) throws Exception{
		MessageDto message = new MessageDto();
		try {
			if(application == null) {
				throw new IllegalArgumentException("The_application_may_not_be_set_to_null");
			}
			
			Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
			department.releaseApplication(application);
			departmentRepository.save(department);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto releasePartnerFromDepartment(String departmentId, AssociatedPartnerCategory partner) throws Exception{
		MessageDto message = new MessageDto();
		try {
			if(partner == null) {
				throw new IllegalArgumentException("The_partner_may_not_be_set_to_null");
			}
			Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
			department.releasePartner(partner);
			departmentRepository.save(department);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
			
		}
		return message;
	}


	@Override
	public DepartmentDto findDepartmentById(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
		if(department == null) {
			throw new Exception("The department does not exist.");
		}
		return this.convertDepartmentToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> findDepartmentsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<Department> departments = departmentRepository.departmentsOfCompany(new CompanyId(companyId));
		return this.convertDepartmentsToDepartmentDtos(departments);
	}

	@Override
	public List<DepartmentDto> findDepartmentsOfJob(String jobId) throws Exception {
		// TODO Auto-generated method stub
		List<Department> departments = departmentRepository.departmentsOfJob(new JobId(jobId));
		return this.convertDepartmentsToDepartmentDtos(departments);
	}

    public List<DepartmentDto> findDepartmentsOfBusinessUnit(String businessUnitId) throws Exception{
    	List<Department> departments = departmentRepository.departmentsOfBusinessUnit(new BusinessUnitId(businessUnitId));
		return this.convertDepartmentsToDepartmentDtos(departments);
    }
	
	public List<DepartmentDto> findDepartmentsOfNotBusinessUnit() throws Exception{
		List<Department> departments = departmentRepository.departmentsOfNotBusinessUnit();
		return this.convertDepartmentsToDepartmentDtos(departments);
	}
	public List<DepartmentDto> findDepartmentsOfDepartmentType(DepartmentType departmentType) throws Exception{
		List<Department> departments = departmentRepository.departmentsOfDepartmentType(departmentType);
		return this.convertDepartmentsToDepartmentDtos(departments);
	}
	
	
	
	
	/*
	 * アクション
	 */
	@Override
	public ActionDto addAction(ActionDto actionDto) throws Exception {
		// TODO Auto-generated method stub
		Department department = departmentRepository.departmentOfId(new DepartmentId(actionDto.getDepartmentId()));
		if(department == null) {
			throw new Exception("The department does not exist.");
		}
		ActionId actionId = actionRepository.nextIdentity();
		Action action = department.defineAction(actionId, new TaskId(actionDto.getTaskId()), actionDto.getTaskName(), actionDto.getActionName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
        if(actionDto.getActionDescription() != null) {
        	action.setActionDescription(actionDto.getActionDescription());
        }
        if(actionDto.getUrl() != null) {
        	action.setUrl(new Url(actionDto.getUrl()));
        }
        if(actionDto.getApplicationUrl() != null) {
        	action.setApplicationUrl(actionDto.getApplicationUrl());
        }
        if(actionDto.getPersonUrl() != null) {
        	action.setPersonUrl(actionDto.getPersonUrl());
        }
        if(actionDto.getApplicationProductId() != null) {
        	action.setApplicationProductId(actionDto.getApplicationProductId());
        }
        if(actionDto.getPersonProductId() != null) {
        	action.setPersonProductId(actionDto.getPersonProductId());
        }
        if(actionDto.getApplicationCategoryId() != null) {
        	action.setApplicationCategoryId(actionDto.getApplicationCategoryId());
        }
        if(actionDto.getPartnerCategoryId() != null) {
        	action.setPartnerCategoryId(actionDto.getPartnerCategoryId());
        }
		actionRepository.save(action);
		actionDto.setActionId(action.getActionId().actionId());
		return actionDto;
	}

	@Override
	public void updateAction(ActionDto actionDto) throws Exception {
		// TODO Auto-generated method stub
		Action action = actionRepository.actionOfId(new ActionId(actionDto.getActionId()));
		if(action == null) {
			throw new Exception("The action does not exist.");
		}
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(actionDto.getActionName() != null) {
			action.setActionName(actionDto.getActionName());
		}
		if(actionDto.getTaskId() != null) {
			action.setTaskId(new TaskId(actionDto.getTaskId()));
		}
		if(actionDto.getTaskName() != null) {
			action.setTaskName(actionDto.getTaskName());
		}
		if(actionDto.getActionDescription() != null) {
        	action.setActionDescription(actionDto.getActionDescription());
        }
        if(actionDto.getUrl() != null) {
        	action.setUrl(new Url(actionDto.getUrl()));
        }
        if(actionDto.getApplicationUrl() != null) {
        	action.setApplicationUrl(actionDto.getApplicationUrl());
        }else {
        	action.setApplicationUrl(null);
        }
        if(actionDto.getPersonUrl() != null) {
        	action.setPersonUrl(actionDto.getPersonUrl());
        }else {
        	action.setPersonUrl(null);
        }
        if(actionDto.getApplicationProductId() != null) {
        	action.setApplicationProductId(actionDto.getApplicationProductId());
        }
        if(actionDto.getPersonProductId() != null) {
        	action.setPersonProductId(actionDto.getPersonProductId());
        }
        if(actionDto.getApplicationCategoryId() != null) {
        	action.setApplicationCategoryId(actionDto.getApplicationCategoryId());
        }else {
        	action.setApplicationCategoryId(null);
        }
        if(actionDto.getPartnerCategoryId() != null) {
        	action.setPartnerCategoryId(actionDto.getPartnerCategoryId());
        }else {
        	action.setPartnerCategoryId(null);
        }
		actionRepository.save(action);
	}

	@Override
	public void deleteAction(String actionId) throws Exception {
		// TODO Auto-generated method stub
		Action action = actionRepository.actionOfId(new ActionId(actionId));
		if(action == null) {
			throw new Exception("The action does not exist.");
		}
		actionRepository.remove(action);
	}

	@Override
	public ActionDto findActionById(String actionId) throws Exception {
		// TODO Auto-generated method stub
		Action action = actionRepository.actionOfId(new ActionId(actionId));
		if(action == null) {
			throw new Exception("The action does not exist.");
		}
		return this.convertActionToActionDto(action);
	}

	@Override
	public List<ActionDto> findActionsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<Action> actions = actionRepository.actionsOfCompany(new CompanyId(companyId));
		return this.convertActionsToActionDtos(actions);
	}

	@Override
	public List<ActionDto> findActionsOfDepartment(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		List<Action> actions = actionRepository.actionsOfDepartment(new DepartmentId(departmentId));
		return this.convertActionsToActionDtos(actions);
	}
	
	

	@Override
	public BusinessProcessDto addBusinessProcess(BusinessProcessDto businessProcessDto) throws Exception {
		// TODO Auto-generated method stub
		Company company = companyRepository.companyOfId(new CompanyId(businessProcessDto.getCompanyId()));
		if(company == null) {
			throw new Exception("The company does not exist.");
		}
		BusinessProcessId businessProcessId = businessProcessRepository.nextIdentity();
		BusinessProcess businessProcess = company.defineBusinessProcess(businessProcessId, businessProcessDto.getBusinessProcessType(), businessProcessDto.getBusinessProcessName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
        if(businessProcessDto.getBusinessDomainId() != null) {
        	businessProcess.setBusinessDomainId(new BusinessDomainId(businessProcessDto.getBusinessDomainId()));
        }
        if(businessProcessDto.getBusinessDomainName() != null) {
        	businessProcess.setBusinessDomainName(businessProcessDto.getBusinessDomainName());
        }
        if(businessProcessDto.getAssociatedTasks() != null) {
        	businessProcess.setAssociatedTasks(businessProcessDto.getAssociatedTasks());
        }
        if(businessProcessDto.getBusinessProcessDescription() != null) {
        	businessProcess.setBusinessProcessDescription(businessProcessDto.getBusinessProcessDescription());
        }
        if(businessProcessDto.getBusinessProcessClass() != null) {
        	businessProcess.setBusinessProcessClass(businessProcessDto.getBusinessProcessClass());
        }
        if(businessProcessDto.getUrl() != null) {
        	businessProcess.setUrl(new Url(businessProcessDto.getUrl()));
        }
        if(businessProcessDto.getIndicators() != null) {
        	businessProcess.setIndicators(businessProcessDto.getIndicators());
        }
        businessProcess.setCreatedAt(new Date());
        businessProcessRepository.save(businessProcess);
		businessProcessDto.setBusinessProcessId(businessProcess.getBusinessProcessId().businessProcessId());
		return businessProcessDto;
	}

	@Override
	public void updateBusinessProcess(BusinessProcessDto businessProcessDto) throws Exception {
		// TODO Auto-generated method stub
		BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcessDto.getBusinessProcessId()));
		if(businessProcess == null) {
			throw new Exception("The businessProcess does not exist.");
		}
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(businessProcessDto.getBusinessProcessName() != null) {
			businessProcess.setBusinessProcessName(businessProcessDto.getBusinessProcessName());
		}
		if(businessProcessDto.getBusinessProcessType() != null) {
			businessProcess.setBusinessProcessType(businessProcessDto.getBusinessProcessType());
		}
		if(businessProcessDto.getBusinessDomainId() != null) {
        	businessProcess.setBusinessDomainId(new BusinessDomainId(businessProcessDto.getBusinessDomainId()));
        }else {
        	businessProcess.setBusinessDomainId(null);
        }
        if(businessProcessDto.getBusinessDomainName() != null) {
        	businessProcess.setBusinessDomainName(businessProcessDto.getBusinessDomainName());
        }else {
        	businessProcess.setBusinessDomainName(null);
        }
        if(businessProcessDto.getAssociatedTasks() != null) {
        	businessProcess.setAssociatedTasks(businessProcessDto.getAssociatedTasks());
        }
        if(businessProcessDto.getBusinessProcessDescription() != null) {
        	businessProcess.setBusinessProcessDescription(businessProcessDto.getBusinessProcessDescription());
        }
        if(businessProcessDto.getBusinessProcessClass() != null) {
        	businessProcess.setBusinessProcessClass(businessProcessDto.getBusinessProcessClass());
        }
        if(businessProcessDto.getUrl() != null) {
        	businessProcess.setUrl(new Url(businessProcessDto.getUrl()));
        }
        if(businessProcessDto.getIndicators() != null) {
        	businessProcess.setIndicators(businessProcessDto.getIndicators());
        }
        businessProcess.setUpdatedAt(new Date());
		businessProcessRepository.save(businessProcess);
	}

	@Override
	public void deleteBusinessProcess(String businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcessId));
		if(businessProcess == null) {
			throw new Exception("The businessProcess does not exist.");
		}
		businessProcessRepository.remove(businessProcess);
	}

	@Override
	public BusinessProcessDto findBusinessProcessById(String businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcessId));
		if(businessProcess == null) {
			throw new Exception("The businessProcess does not exist.");
		}
		return this.convertBusinessProcessToBusinessProcessDto(businessProcess);
	}

	@Override
	public List<BusinessProcessDto> findBusinessProcessesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<BusinessProcess> businessProcesses = businessProcessRepository.businessProcessesOfCompany(new CompanyId(companyId));
		return this.convertBusinessProcessesToBusinessProcessDtos(businessProcesses);
	}
	
	public List<BusinessProcessDto> findBusinessProcessesOfBusinessDomain(String businessDomainId) throws Exception{
		List<BusinessProcess> businessProcesses = businessProcessRepository.businessProcessesOfBusinessDomain(new BusinessDomainId(businessDomainId));
		return this.convertBusinessProcessesToBusinessProcessDtos(businessProcesses);
	}

	@Override
	public List<BusinessProcessDto> findBusinessProcessesOfTask(String taskId) throws Exception {
		// TODO Auto-generated method stub
		List<BusinessProcess> businessProcesses = businessProcessRepository.businessProcessesOfTask(taskId);
		return this.convertBusinessProcessesToBusinessProcessDtos(businessProcesses);
	}

	@Override
	public ActionPlanDto addActionPlan(ActionPlanDto actionPlanDto) throws Exception {
		// TODO Auto-generated method stub
		BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(new BusinessProcessId(actionPlanDto.getBusinessProcessId()));
		if(businessProcess == null) {
			throw new Exception("The businessProcess does not exist.");
		}
		ActionPlanId actionPlanId = actionPlanRepository.nextIdentity();
		ActionPlan actionPlan = businessProcess.defineActionPlan(actionPlanId, actionPlanDto.getActionPlanType(), actionPlanDto.getActionPlanName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(actionPlanDto.getBusinessUnitId() != null) {
			actionPlan.setBusinessUnitId(new BusinessUnitId(actionPlanDto.getBusinessUnitId()));
		}
		if(actionPlanDto.getBusinessUnitName() != null) {
			actionPlan.setBusinessUnitName(actionPlanDto.getBusinessUnitName());
		}
		if(actionPlanDto.getHypothesis() != null) {
			actionPlan.setHypothesis(actionPlanDto.getHypothesis());
		}
		if(actionPlanDto.getResult() != null) {
			actionPlan.setResult(actionPlanDto.getResult());
		}
		if(actionPlanDto.getCustomerCategoryId() != null) {
			actionPlan.setCustomerCategoryId(new CustomerCategoryId(actionPlanDto.getCustomerCategoryId()));
		}
		if(actionPlanDto.getCustomerCategoryName() != null) {
			actionPlan.setCustomerCategoryName(actionPlanDto.getCustomerCategoryName());	
		}
		if(actionPlanDto.getAssociatedProductCategories() != null) {
			actionPlan.setAssociatedProductCategories(actionPlanDto.getAssociatedProductCategories());
		}
		if(actionPlanDto.getAssociatedActions() != null) {
			actionPlan.setAssociatedActions(actionPlanDto.getAssociatedActions());
		}
		if(actionPlanDto.getActionPlanDescription() != null) {
			actionPlan.setActionPlanDescription(actionPlanDto.getActionPlanDescription());
		}
		if(actionPlanDto.getStartDate() != null) {
			actionPlan.setStartDate(actionPlanDto.getStartDate());
		}
		if(actionPlanDto.getEndDate() != null) {
			actionPlan.setEndDate(actionPlanDto.getEndDate());
		}
		if(actionPlanDto.getUrl() != null) {
			actionPlan.setUrl(new Url(actionPlanDto.getUrl()));
		}
		if(actionPlanDto.getProjectId() != null) {
			actionPlan.setProjectId(new ProjectId(actionPlanDto.getProjectId()));
		}
		if(actionPlanDto.getGoals() != null) {
			actionPlan.setGoals(actionPlanDto.getGoals());
		}
		if(actionPlanDto.getAchievements() != null) {
			actionPlan.setAchievements(actionPlanDto.getAchievements());
		}
		actionPlanRepository.save(actionPlan);
		actionPlanDto.setActionPlanId(actionPlan.getActionPlanId().actionPlanId());
		return actionPlanDto;
	}

	@Override
	public void updateActionPlan(ActionPlanDto actionPlanDto) throws Exception {
		// TODO Auto-generated method stub
		ActionPlan actionPlan = actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlanDto.getActionPlanId()));
		if(actionPlan == null) {
			throw new Exception("The actionPlan does not exist.");
		}
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(actionPlanDto.getBusinessUnitId() != null) {
			actionPlan.setBusinessUnitId(new BusinessUnitId(actionPlanDto.getBusinessUnitId()));
		}
		if(actionPlanDto.getBusinessUnitName() != null) {
			actionPlan.setBusinessUnitName(actionPlanDto.getBusinessUnitName());
		}
		if(actionPlanDto.getCustomerCategoryId() != null) {
			actionPlan.setCustomerCategoryId(new CustomerCategoryId(actionPlanDto.getCustomerCategoryId()));
		}
		if(actionPlanDto.getCustomerCategoryName() != null) {
			actionPlan.setCustomerCategoryName(actionPlanDto.getCustomerCategoryName());	
		}
		if(actionPlanDto.getAssociatedProductCategories() != null) {
			actionPlan.setAssociatedProductCategories(actionPlanDto.getAssociatedProductCategories());
		}
		if(actionPlanDto.getAssociatedActions() != null) {
			actionPlan.setAssociatedActions(actionPlanDto.getAssociatedActions());
		}
		if(actionPlanDto.getActionPlanDescription() != null) {
			actionPlan.setActionPlanDescription(actionPlanDto.getActionPlanDescription());
		}
		if(actionPlanDto.getUrl() != null) {
			actionPlan.setUrl(new Url(actionPlanDto.getUrl()));
		}
		if(actionPlanDto.getHypothesis() != null) {
			actionPlan.setHypothesis(actionPlanDto.getHypothesis());
		}
		if(actionPlanDto.getResult() != null) {
			actionPlan.setResult(actionPlanDto.getResult());
		}
		if(actionPlanDto.getProjectId() != null) {
			actionPlan.setProjectId(new ProjectId(actionPlanDto.getProjectId()));
		}
		if(actionPlanDto.getStartDate() != null) {
			actionPlan.setStartDate(actionPlanDto.getStartDate());
		}
		if(actionPlanDto.getEndDate() != null) {
			actionPlan.setEndDate(actionPlanDto.getEndDate());
		}
		if(actionPlanDto.getActionPlanName() != null) {
			actionPlan.setActionPlanName(actionPlanDto.getActionPlanName());
		}
		if(actionPlanDto.getActionPlanType() != null) {
			actionPlan.setActionPlanType(actionPlanDto.getActionPlanType());
		}
		if(actionPlanDto.getGoals() != null) {
			actionPlan.setGoals(actionPlanDto.getGoals());
		}
		if(actionPlanDto.getAchievements() != null) {
			actionPlan.setAchievements(actionPlanDto.getAchievements());
		}
		actionPlanRepository.save(actionPlan);
	}

	@Override
	public void deleteActionPlan(String actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		ActionPlan actionPlan = actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlanId));
		if(actionPlan == null) {
			throw new Exception("The actionPlan does not exist.");
		}
		actionPlanRepository.remove(actionPlan);
	}

	@Override
	public ActionPlanDto findActionPlanById(String actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		ActionPlan actionPlan = actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlanId));
		if(actionPlan == null) {
			throw new Exception("The actionPlan does not exist.");
		}
		return this.convertActionPlanToActionPlanDto(actionPlan);
	}

	@Override
	public List<ActionPlanDto> findActionPlansOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfCompany(new CompanyId(companyId));
		return this.convertActionPlansToActionPlanDtos(actionPlans);
	}
	
	@Override
	public List<ActionPlanDto> findActionPlansOfProject(String projectId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfProject(new ProjectId(projectId));
		return this.convertActionPlansToActionPlanDtos(actionPlans);
	}
	

	@Override
	public List<ActionPlanDto> findActionPlansOfBusinessProcess(String businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfBusinessProcess(new BusinessProcessId(businessProcessId));
		return this.convertActionPlansToActionPlanDtos(actionPlans);
	}
	
	@Override
	public List<ActionPlanDto> findActionPlansOfBusinessUnit(String businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfBusinessUnit(new BusinessUnitId(businessUnitId));
		return this.convertActionPlansToActionPlanDtos(actionPlans);
	}

	@Override
	public List<ActionPlanDto> findActionPlansOfAction(String actionId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionPlan> actionPlans = actionPlanRepository.actionPlansOfAction(actionId);
		return this.convertActionPlansToActionPlanDtos(actionPlans);
	}

	@Override
	public void assignTaskToBusinessProcess(TaskDto task, String businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		if(task == null) {
			throw new IllegalArgumentException("The task may not be set to null.");
		}
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		businessProcessService.assignTaskToBusinessProcess(task, new BusinessProcessId(businessProcessId));
	}
	
	
	
	public void releaseTaskFromBusinessProcess(TaskDto task, String businessProcessId) throws Exception{
		if(task == null) {
			throw new IllegalArgumentException("The task may not be set to null.");
		}
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		businessProcessService.releaseTaskFromBusinessProcess(task, new BusinessProcessId(businessProcessId));
	}

	@Override
	public void assignActionToActionPlan(ActionDto action, String actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		
		if(action == null) {
			throw new IllegalArgumentException("The action may not be set to null.");
		}
		if(actionPlanId == null) {
			throw new IllegalArgumentException("The actionPlanId may not be set to null.");
		}
		actionPlanService.assignActionToActionPlan(action, new ActionPlanId(actionPlanId));
		
	}
	
	public void releaseActionFromActionPlan(ActionDto action, String actionPlanId) throws Exception{
		if(action == null) {
			throw new IllegalArgumentException("The action may not be set to null.");
		}
		if(actionPlanId == null) {
			throw new IllegalArgumentException("The actionPlanId may not be set to null.");
		}
		actionPlanService.releaseActionFromActionPlan(action, new ActionPlanId(actionPlanId));
	}
	
	public List<ActionDto> findActionsOfActionPlan(String actionPlanId) throws Exception{
		if(actionPlanId == null) {
			throw new IllegalArgumentException("actionPlanId dose not exixt.");
		}
		List<Action> actions = actionPlanService.actionsOfActionPlan(new ActionPlanId(actionPlanId));
		return this.convertActionsToActionDtos(actions);
	}

	/*
	 * 製品アクション
	 */

	@Override
	public ProductFunctionDto addProductFunction(ProductFunctionDto productFunctionDto) throws Exception {
		// TODO Auto-generated method stub
		ProductCategory productCategory = productCategoryRepository.productCategoryOfId(new ProductCategoryId(productFunctionDto.getProductCategoryId()));
		if(productCategory == null) {
			throw new Exception("The productCategory does not exist.");
		}
		ProductFunctionId productFunctionId = productFunctionRepository.nextIdentity();
		ProductFunction productFunction = productCategory.defineProductFunction(productFunctionId, new ProductTaskId(productFunctionDto.getProductTaskId()), productFunctionDto.getProductTaskName(), productFunctionDto.getProductFunctionName());
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		if(productFunctionDto.getProductFunctionDescription() != null) {
			productFunction.setProductFunctionDescription(productFunctionDto.getProductFunctionDescription());
		}
		if(productFunctionDto.getUrl() != null && !productFunctionDto.getUrl().isEmpty()) {
			productFunction.setUrl(new Url(productFunctionDto.getUrl()));
		}
		if(productFunctionDto.getFunctionId() != null && productFunctionDto.getFunctionName() != null){
			productFunction.setFunction(new Function(productFunctionDto.getFunctionId(), productFunctionDto.getFunctionName()));
		}
		productFunctionRepository.save(productFunction);
		productFunctionDto.setProductFunctionId(productFunction.getProductFunctionId().getProductFunctionId());
		return productFunctionDto;
	}

	@Override
	public void updateProductFunction(ProductFunctionDto productFunctionDto) throws Exception {
		// TODO Auto-generated method stub
		ProductFunction productFunction = productFunctionRepository.productFunctionOfId(new ProductFunctionId(productFunctionDto.getProductFunctionId()));
		if(productFunction == null) {
			throw new Exception("The productFunction does not exist.");
		}
		//NULLのままだと値オブジェクトを生成するときNULLエラーになるので、任意項目については、アプリケーションでNULLを渡さないようにチェックする（ASの事前条件）
		
		
		if(productFunctionDto.getProductFunctionName() != null && !productFunctionDto.getProductFunctionName().isEmpty()) {
			productFunction.setProductFunctionName(productFunctionDto.getProductFunctionName());
		}
		//Descriptionの場合、ユースケースとしてNULLを設定したい場合がある
		//if(taskDto.getActionDescription() != null) {
		productFunction.setProductFunctionDescription(productFunctionDto.getProductFunctionDescription());
		//}
		productFunction.setProductTaskId(new ProductTaskId(productFunctionDto.getProductTaskId()));
		productFunction.setProductTaskName(productFunctionDto.getProductTaskName());	
		if(productFunctionDto.getUrl() != null) {
			productFunction.setUrl(new Url(productFunctionDto.getUrl()));
		//ユースケースとしてURLを設定しない場合、明示的にNULLを設定する。
		}else {
			productFunction.setUrl(null);
		}
		if(productFunctionDto.getFunctionId() != null && productFunctionDto.getFunctionName() != null){
			productFunction.setFunction(new Function(productFunctionDto.getFunctionId(), productFunctionDto.getFunctionName()));
		}else {
			productFunction.setFunction(null);	
		}
		productFunctionRepository.save(productFunction);
		
	}

	@Override
	public void deleteProductFunction(String productFunctionId) throws Exception {
		// TODO Auto-generated method stub
		ProductFunction productFunction = productFunctionRepository.productFunctionOfId(new ProductFunctionId(productFunctionId));
		if(productFunction == null) {
			throw new Exception("The productFunction does not exist.");
		}
		productFunctionRepository.remove(productFunction);
		
	}

	@Override
	public ProductFunctionDto findProductFunctionById(String productFunctionId) throws Exception {
		// TODO Auto-generated method stub
		ProductFunction productFunction = productFunctionRepository.productFunctionOfId(new ProductFunctionId(productFunctionId));
		if(productFunction == null) {
			throw new Exception("The productFunction does not exist.");
		}
		return this.convertProductFunctionToProductFunctionDto(productFunction);
	}

	@Override
	public List<ProductFunctionDto> findProductFunctionsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		List<ProductFunction> productFunctions = productFunctionRepository.productFunctionsOfCompany(new CompanyId(companyId));
		return this.convertProductFunctionsToProductFunctionDtos(productFunctions);
	}

	@Override
	public List<ProductFunctionDto> findProductFunctionsOfProductCategory(String productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		List<ProductFunction> productFunctions = productFunctionRepository.productFunctionsOfProductCategory(new ProductCategoryId(productCategoryId));
		return this.convertProductFunctionsToProductFunctionDtos(productFunctions);
	}

	@Override
	public boolean isProductTaskUsed(ProductTaskId productTypeId) throws Exception {
		// TODO Auto-generated method stub
		return this.productTaskService.isUsed(productTypeId);
	}

	@Override
	public boolean isProductIndicatorUsed(ProductTypeId productTypeId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return this.productIndicatorService.isUsed(productTypeId, indicator);
	}

	@Override
	public boolean isCustomerTypeUsed(CustomerTypeId customerTypeId) throws Exception {
		// TODO Auto-generated method stub
		return this.customerTypeService.isUsed(customerTypeId);
	}

	@Override
	public boolean isCustomerIndicatorUsed(CustomerTypeId customerTypeId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return this.customerTypeService.indicatorIsUsed(customerTypeId, indicator);
	}

	@Override
	public boolean isBusinessDomainUsed(BusinessDomainId businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return businessDomainService.isUsed(businessDomainId);
	}

	@Override
	public boolean isBusinessIndicatorUsed(BusinessDomainId businessDomainId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return businessDomainService.indicatorIsUsed(businessDomainId, indicator);
	}

	@Override
	public boolean isCustomerCategoryUsed(CustomerCategoryId customerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return customerCategoryService.isUsed(customerCategoryId);
	}

	@Override
	public boolean isCustomerGoalUsed(CustomerCategoryId customerCategoryId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return customerCategoryService.goalIsUsed(customerCategoryId, indicator);
	}

	@Override
	public boolean isProductCategoryUsed(ProductCategoryId productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return productCategoryService.isUsed(productCategoryId);
	}

	@Override
	public boolean isProductGoalUsed(ProductCategoryId productCategoryId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return productCategoryService.goalIsUsed(productCategoryId, indicator);
	}

	@Override
	public boolean isBusinessUnitUsed(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		return businessUnitService.isUsed(businessUnitId);
	}

	@Override
	public boolean isBusinessGoalUsed(BusinessUnitId businessUnitId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return businessUnitService.goalIsUsed(businessUnitId, indicator);
	}

	@Override
	public List<ProductCategoryDto> findProductCategoriesByBusinessDomain(String businessDomainId)
			throws Exception {
		// TODO Auto-generated method stub
		List<ProductCategory> pcs = businessDomainService.findProductCategoriesByBusinessDomain(new BusinessDomainId(businessDomainId));
		return convertProductCategoriesToProductCategoryDtos(pcs);
	}

	@Override
	public List<CustomerCategoryDto> findCustomerCategoriesByBusinessDomain(String businessDomainId)
			throws Exception {
		// TODO Auto-generated method stub
		return convertCustomerCategoriesToCustomerTypeDtos(businessDomainService.findCustomerCategoriesByBusinessDomain(new BusinessDomainId(businessDomainId)));
	}

	@Override
	public boolean isJobUsed(JobId jobId) throws Exception {
		// TODO Auto-generated method stub
		return jobService.isUsed(jobId);
	}
	
	@Override
	public boolean isJobUsedExceptApp(JobId jobId) throws Exception {
		// TODO Auto-generated method stub
		return jobService.isUsedExceptApp(jobId);
	}

	@Override
	public boolean isJobIndicatorUsed(JobId jobId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return jobService.indicatorIsUsed(jobId, indicator);
	}

	@Override
	public boolean isTaskUsed(TaskId taskId) throws Exception {
		// TODO Auto-generated method stub
		return taskService.isUsed(taskId);
	}

	@Override
	public boolean isDepartmentUsed(DepartmentId departmentId) throws Exception {
		// TODO Auto-generated method stub
		return departmentService.isUsed(departmentId);
	}

	@Override
	public boolean isDepartmentGoalUsed(DepartmentId departmentId, Indicator indicator) throws Exception {
		// TODO Auto-generated method stub
		return departmentService.goalIsUsed(departmentId, indicator);
	}

	@Override
	public boolean isBusinessProcessUsed(BusinessProcessId businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		return businessProcessService.isUsed(businessProcessId);
	}

	@Override
	public boolean isBusinessProcessIndicatorUsed(BusinessProcessId businessProcessId, Indicator indicator)
			throws Exception {
		// TODO Auto-generated method stub
		return businessProcessService.indicatorIsUsed(businessProcessId, indicator);
	}

	@Override
	public void replaceTaskOrder(TaskDto source, TaskDto target, BusinessProcessId businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		this.businessProcessService.replaceTaskOrder(source, target, businessProcessId);	
	}

	
	



	



	

}
