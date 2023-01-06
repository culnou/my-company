package com.culnou.mumu.company.application;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessUnitId;

import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentType;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobType;

import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTypeId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.compnay.controller.ActionDto;
import com.culnou.mumu.compnay.controller.ActionPlanDto;
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

public interface CompanyService {
	
	/*
	 * Company
	 */
	public CompanyDto addCompany(CompanyDto companyDto) throws Exception;
	
	public void updateCompany(CompanyDto companyDto) throws Exception;
	
	public void deleteCompany(String companyId) throws Exception;
	
	public CompanyDto findCompanyById(String companyId) throws Exception;
	
	public List<CompanyDto> findCompaniesOfDomainName(String domainName) throws Exception;
	
	/*
	 * BusinessDomain
	 */
	
	public BusinessDomainDto addBusinessDomain(BusinessDomainDto businessDomainDto) throws Exception;
	
	public void updateBusinessDomain(BusinessDomainDto businessDomainDto) throws Exception;
	
	public void deleteBusinessDomain(String businessDomainId) throws Exception;
	
	public BusinessDomainDto findBusinessDomainById(String businessDomainId) throws Exception;
	
	public List<BusinessDomainDto> findBusinessDomainsOfCompany(String companyId) throws Exception;
	
	public BusinessDomainDto findEnterpriseDomain(String companyId) throws Exception;
	
	public List<BusinessDomainDto> findBusinessDomainsOfCustomerType(String customerTypeId) throws Exception;
	
	public List<BusinessDomainDto> findBusinessDomainsOfProductType(String productTypeId) throws Exception;
	
    public boolean isBusinessDomainUsed(BusinessDomainId businessDomainId) throws Exception;
	
	public boolean isBusinessIndicatorUsed(BusinessDomainId businessDomainId, Indicator indicator) throws Exception;
	
	public List<ProductCategoryDto> findProductCategoriesByBusinessDomain(String businessDomainId) throws Exception;
	
	public List<CustomerCategoryDto> findCustomerCategoriesByBusinessDomain(String businessDomainId) throws Exception;
	
	/*
	 * BusinessUnit
	 */
	public BusinessUnitDto addBusinessUnit(BusinessUnitDto businessUnitDto) throws Exception;
	
	public void updateBusinessUnit(BusinessUnitDto businessUnitDto) throws Exception;
	
	public void deleteBusinessUnit(String businessUnitId) throws Exception;
	
	public BusinessUnitDto findBusinessUnitById(String businessUnitId) throws Exception;
	
	public List<BusinessUnitDto> findBusinessUnitsOfDomain(String businessDomainId) throws Exception;
	
	public List<BusinessUnitDto> findBusinessUnitsOfCompany(String companyId) throws Exception;
	
	public List<BusinessUnitDto> findBusinessUnitsOfCustomerCategory(String customerCategoryId) throws Exception;
	
	public List<BusinessUnitDto> findBusinessUnitsOfProductCategory(String productCategoryId) throws Exception;
	
	public boolean isBusinessUnitUsed(BusinessUnitId businessUnitId) throws Exception;
	
    public boolean isBusinessGoalUsed(BusinessUnitId businessUnitId, Indicator indicator) throws Exception;
    
	/*
	 * CustomerType
	 */
	public CustomerTypeDto addCustomerType(CustomerTypeDto customerTypeDto) throws Exception;
	
	public void updateCustomerType(CustomerTypeDto customerTypeDto) throws Exception;
	
	public void deleteCustomerType(String customerTypeId) throws Exception;
	
	public CustomerTypeDto findCustomerTypeById(String customerTypeId) throws Exception;
	
	public List<CustomerTypeDto> findCustomerTypesOfCompany(String companyId) throws Exception;
	
	public List<CustomerTypeDto> findCustomerTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public boolean isCustomerTypeUsed(CustomerTypeId customerTypeId) throws Exception;
	
	public boolean isCustomerIndicatorUsed(CustomerTypeId customerTypeId, Indicator indicator) throws Exception;
	
	/*
	 * ProductType
	 */
	public ProductTypeDto addProductType(ProductTypeDto productTypeDto) throws Exception;
	
	public void updateProductType(ProductTypeDto productTypeDto) throws Exception;
	
	public void deleteProductType(String productTypeId) throws Exception;
	
	public ProductTypeDto findProductTypeById(String productTypeId) throws Exception;
	
	public List<ProductTypeDto> findProductTypesOfCompany(String companyId) throws Exception;
	
	public List<ProductTypeDto> findProductTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	//製品タイプで使用されている場合、顧客タイプを編集、削除できないようにするために必要。
	public List<ProductTypeDto> findProductTypesOfCustomerType(String customerTypeId) throws Exception;
	
	public boolean isProductTypeUsed(ProductTypeId productTypeId) throws Exception;
	/*
	 * ProductTask
	 */
	public ProductTaskDto addProductTask(ProductTaskDto productTaskDto) throws Exception;
	
	public void updateProductTask(ProductTaskDto productTaskDto) throws Exception;
	
	public void deleteProductTask(String productTaskId) throws Exception;
	
	public ProductTaskDto findProductTaskById(String productTaskId) throws Exception;
	
	public List<ProductTaskDto> findProductTasksOfCompany(String companyId) throws Exception;
	
	public List<ProductTaskDto> findProductTasksOfProductType(String productTypeId) throws Exception;

	public boolean isProductTaskUsed(ProductTaskId productTypeId) throws Exception;
	
	public boolean isProductIndicatorUsed(ProductTypeId productTypeId, Indicator indicator) throws Exception;
	
	/*
	 * CustomerCategory
	 */
	public CustomerCategoryDto addCustomerCategory(CustomerCategoryDto customerCategoryDto) throws Exception;
	
	public void updateCustomerCategory(CustomerCategoryDto customerCategoryDto) throws Exception;
	
	public void deleteCustomerCategory(String customerCategoryId) throws Exception;
	
	public CustomerCategoryDto findCustomerCategoryById(String customerCategoryId) throws Exception;
	
	public List<CustomerCategoryDto> findCustomerCategoriesOfCompany(String companyId) throws Exception;
	
	public List<CustomerCategoryDto> findCustomerCategoriesOfBusinessUnit(String businessUnitId) throws Exception;

	public List<CustomerCategoryDto> findCustomerCategoriesOfCustomerType(String customerTypeId) throws Exception;
	
    public boolean isCustomerCategoryUsed(CustomerCategoryId customerCategoryId) throws Exception;
	
	public boolean isCustomerGoalUsed(CustomerCategoryId customerCategoryId, Indicator indicator) throws Exception;

	/*
	 * ProductCategory
	 */
	public ProductCategoryDto addProductCategory(ProductCategoryDto productCategoryDto) throws Exception;
	
	public void updateProductCategory(ProductCategoryDto productCategoryDto) throws Exception;
	
	public void deleteProductCategory(String productCategoryId) throws Exception;
	
	public ProductCategoryDto findProductCategoryById(String productCategoryId) throws Exception;
	
	public List<ProductCategoryDto> findProductCategoriesOfCompany(String companyId) throws Exception;
	
	public List<ProductCategoryDto> findProductCategoriesOfBusinessUnit(String businessUnitId) throws Exception;
	
	public List<ProductCategoryDto> findProductCategoriesOfProductType(String productTypeId) throws Exception;
	
	public List<ProductCategoryDto> findProductCategoriesOfCustomerCategory(String customerCategoryId) throws Exception;

	public boolean isProductCategoryUsed(ProductCategoryId productCategoryId) throws Exception;
		
    public boolean isProductGoalUsed(ProductCategoryId productCategoryId, Indicator indicator) throws Exception;
    
    public List<ProductCategoryDto> findProductCategoriesOfProductClass(String companyId, ProductClass productClass) throws Exception;
    	

	/*
	 * ProductFunction
	 */
	public ProductFunctionDto addProductFunction(ProductFunctionDto productFunctionDto) throws Exception;
	
	public void updateProductFunction(ProductFunctionDto productFunctionDto) throws Exception;
	
	public void deleteProductFunction(String productFunctionId) throws Exception;
	
	public ProductFunctionDto findProductFunctionById(String productFunctionId) throws Exception;
	
	public List<ProductFunctionDto> findProductFunctionsOfCompany(String companyId) throws Exception;
	
	public List<ProductFunctionDto> findProductFunctionsOfProductCategory(String productCategoryId) throws Exception;
	
	/*
	 * Job
	 */
	public JobDto addJob(JobDto jobDto) throws Exception;
	
	public void updateJob(JobDto jobDto) throws Exception;
	
	public void deleteJob(String jobId) throws Exception;
	
	public JobDto findJobById(String jobId) throws Exception;
	
	public List<JobDto> findJobsOfCompany(String companyId) throws Exception;
	
	public List<JobDto> findJobsOfBusinessDomain(String businessDomainId) throws Exception;
	
	public List<JobDto> findJobsOfJobType(JobType jobType) throws Exception;
	
	public boolean isJobUsed(JobId jobId) throws Exception;
	
	public boolean isJobUsedExceptApp(JobId jobId) throws Exception;
		
	public boolean isJobIndicatorUsed(JobId jobId, Indicator indicator) throws Exception;

	
	/*
	 * Task
	 */
	public TaskDto addTask(TaskDto taskDto) throws Exception;
	
	public void updateTask(TaskDto taskDto) throws Exception;
	
	public void deleteTask(String taskId) throws Exception;
	
	public TaskDto findTaskById(String taskId) throws Exception;
	
	public List<TaskDto> findTasksOfCompany(String companyId) throws Exception;
	
	public List<TaskDto> findTasksOfJob(String jobId) throws Exception;
	
	public boolean isTaskUsed(TaskId taskId) throws Exception;
	
	/*
	 * Department
	 */
	public DepartmentDto addDepartment(DepartmentDto departmentDto) throws Exception;
	
	public void updateDepartment(DepartmentDto departmentDto) throws Exception;
	
	public void deleteDepartment(String departmentId) throws Exception;
	
	public DepartmentDto findDepartmentById(String departmentId) throws Exception;
	
	public List<DepartmentDto> findDepartmentsOfCompany(String companyId) throws Exception;
	
	public List<DepartmentDto> findDepartmentsOfJob(String jobId) throws Exception;
	
	public List<DepartmentDto> findDepartmentsOfBusinessUnit(String businessUnitId) throws Exception;
	
	public List<DepartmentDto> findDepartmentsOfDepartmentType(DepartmentType departmentType) throws Exception;
	
	public List<DepartmentDto> findDepartmentsOfNotBusinessUnit() throws Exception;
	
    public boolean isDepartmentUsed(DepartmentId departmentId) throws Exception;
	
	public boolean isDepartmentGoalUsed(DepartmentId departmentId, Indicator indicator) throws Exception;
	
	public MessageDto assignApplicationToDepartment(String departmentId, AssociatedApplicationCategory application) throws Exception;

	public MessageDto assignPartnerToDepartment(String departmentId, AssociatedPartnerCategory partner) throws Exception;
	
	public MessageDto releaseApplicationFromDepartment(String departmentId, AssociatedApplicationCategory application) throws Exception;

	public MessageDto releasePartnerFromDepartment(String departmentId, AssociatedPartnerCategory partner) throws Exception;

	/*
	 * Action
	 */
	public ActionDto addAction(ActionDto actionDto) throws Exception;
	
	public void updateAction(ActionDto actionDto) throws Exception;
	
	public void deleteAction(String actionId) throws Exception;
	
	public ActionDto findActionById(String actionId) throws Exception;
	
	public List<ActionDto> findActionsOfCompany(String companyId) throws Exception;
	
	public List<ActionDto> findActionsOfDepartment(String departmentId) throws Exception;
	
	public List<ActionDto> findActionsOfActionPlan(String actionPlanId) throws Exception;
	
	/*
	 * BusinessProcess
	 */
	public BusinessProcessDto addBusinessProcess(BusinessProcessDto businessProcessDto) throws Exception;
	
	public void updateBusinessProcess(BusinessProcessDto businessProcessDto) throws Exception;
	
	public void deleteBusinessProcess(String businessProcessId) throws Exception;
	
	public void assignTaskToBusinessProcess(TaskDto task, String businessProcessId) throws Exception;
	
	public void releaseTaskFromBusinessProcess(TaskDto task, String businessProcessId) throws Exception;
	
	public void replaceTaskOrder(TaskDto source, TaskDto target, BusinessProcessId businessProcessId) throws Exception;
	
	public BusinessProcessDto findBusinessProcessById(String businessProcessId) throws Exception;
	
	public List<BusinessProcessDto> findBusinessProcessesOfCompany(String companyId) throws Exception;
	
	public List<BusinessProcessDto> findBusinessProcessesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public List<BusinessProcessDto> findBusinessProcessesOfTask(String taskId) throws Exception;
	
    public boolean isBusinessProcessUsed(BusinessProcessId businessProcessId) throws Exception;
	
	public boolean isBusinessProcessIndicatorUsed(BusinessProcessId businessProcessId, Indicator indicator) throws Exception;

	/*
	 * ActionPlan
	 */
	public ActionPlanDto addActionPlan(ActionPlanDto actionPlanDto) throws Exception;
	
	public void updateActionPlan(ActionPlanDto actionPlanDto) throws Exception;
	
	public void deleteActionPlan(String actionPlanId) throws Exception;
	
	public void assignActionToActionPlan(ActionDto action, String actionPlanId) throws Exception;
	
	public void releaseActionFromActionPlan(ActionDto action, String actionPlanId) throws Exception;
	
	public ActionPlanDto findActionPlanById(String actionPlanId) throws Exception;
	
	public List<ActionPlanDto> findActionPlansOfCompany(String companyId) throws Exception;
	
	public List<ActionPlanDto> findActionPlansOfBusinessProcess(String businessProcessId) throws Exception;
	
	public List<ActionPlanDto> findActionPlansOfBusinessUnit(String businessUnitId) throws Exception;

	public List<ActionPlanDto> findActionPlansOfAction(String actionId) throws Exception;
	
	public List<ActionPlanDto> findActionPlansOfProject(String projectId) throws Exception;
}
