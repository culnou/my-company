package com.culnou.mumu.company.domain.model.service;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.ProductClass;

import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.common.ActionAdapter;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;

import com.culnou.mumu.company.domain.model.common.TaskAdapter;
import com.culnou.mumu.company.domain.model.product.instance.AssociatedProductFunction;
import com.culnou.mumu.company.domain.model.product.instance.MarketProductAdapter;
import com.culnou.mumu.company.domain.model.common.DepartmentAdapter;
import com.culnou.mumu.compnay.controller.CompanyDto;

import com.culnou.mumu.compnay.controller.MessageDto;

import com.culnou.mumu.compnay.controller.ProductDto;
import com.culnou.mumu.compnay.controller.ProductServiceDto;

@Service
@Transactional
public class ProductServiceService {
	@Autowired
	private CompanyAdapter companyAdapter;
	
	@Autowired
	private ProductServiceRegistry registry;
	@Autowired
	private DepartmentAdapter departmentAdapter;
	@Autowired
	private MarketProductAdapter productAdapter;
	@Autowired
	private ActionAdapter actionAdapter;
	@Autowired
	private TaskAdapter taskAdapter;
	
	/*
	 * 製品を部門のサービスに割当てる
	 */
	public MessageDto assignProductToDepartment(ProductDto product, String departmentId, String companyId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(product == null) {
				throw new Exception("The_product_may_not_be_set_to_null");
			}
			if(departmentId == null) {
				throw new Exception("The_departmentId_may_not_be_set_to_null");
			}
			if(departmentId.isEmpty()) {
				throw new Exception("Must_provide_a_departmentId");
			}
			if(companyId == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(companyId.isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(product.getProductId() == null) {
				throw new Exception("The_productId_may_not_be_set_to_null");
			}
			if(product.getProductId().isEmpty()) {
				throw new Exception("Must_provide_a_productId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Department department = departmentAdapter.findDepartmentOfId(new DepartmentId(departmentId));
			if(department == null) {
				message.setErrorMessage("The_department_may_not_exist");
			}
			CompanyDto company = companyAdapter.findCompayOfId(product.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//ビジネスロジック
			//市場の製品を部門に割り当てる。
			message = productAdapter.assignProductToDepartment(product.getProductId(), departmentId, companyId);
			if(message.getResult().equals("NG")) {
				throw new Exception("The_product_of_market_could_not_assign_to_department");
			}
			//ProductDtoをProductServiceに展開しProductServiceを新規作成する。
			ProductService service = new ProductService();
			service.setProductServiceId(registry.nextIdentity());
			service.setCompanyId(product.getCompanyId());
			service.setProductId(product.getProductId());
			service.setProductClass(product.getProductClass());
			service.setServiceType(product.getServiceType());
			service.setPersonality(product.getPersonality());
			service.setProductName(product.getProductName());
			service.setProductDescription(product.getProductDescription());
			service.setUrl(product.getUrl());
			service.setDepartmentId(departmentId);
			service.setIndustryId(product.getIndustryId());
			service.setServiceId(product.getServiceId());
			service.setAssociatedProductFunctions(product.getAssociatedProductFunctions());
			service.setAssociatedRoles(product.getAssociatedRoles());
			registry.createProductService(service);
			//同じファンクションの部門のアクションにサービスのURLを設定する。
			/*
			List<Action> actions = actionAdapter.findActionsOfDepartment(new DepartmentId(departmentId));
			List<AssociatedProductFunction> productFunctions = product.getAssociatedProductFunctions();
			for(AssociatedProductFunction productFunction : productFunctions) {
				String functinId = productFunction.getFunctionId();
				for(Action action : actions) {
					Task task = taskAdapter.findTaskOfId(action.getTaskId());
					if(task.getFunction().getFunctionId().equals(functinId)) {
						if(product.getProductClass().equals(ProductClass.ApplicationService)) {
							action.setApplicationUrl(productFunction.getProductFunctionUrl());
						}else if(product.getProductClass().equals(ProductClass.PersonalService)) {
							action.setPersonUrl(productFunction.getProductFunctionUrl());
						}
						actionAdapter.updateAction(action);
					}//if
				}//for
			}//for
			*/
			
			message.setResult("OK");
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * アクションに対応するアプリケーションサービスの製品ファンクションを探す
	 */
	public List<AssociatedProductFunction> findApplitacationServiceFunctionOfAction(String actionId) throws Exception{
		//事前条件
        //コントローラーからの入力値の妥当性を保証する
		if(actionId == null) {
			throw new Exception("The_actionId_may_not_be_set_to_null");
		}
		//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
		Action action = actionAdapter.findActionOfId(new ActionId(actionId));
		if(action == null) {
			throw new Exception("The_action_could_not_be_found");
		}
		//ビジネスロジック
		//アクションに対応するタスクを探す
		Task task = taskAdapter.findTaskOfId(action.getTaskId());
		//アクションの部門に関連する製品サービスを探す
		List<ProductServiceDto> procutServices = this.findProductsOfDepartment(action.getDepartmentId().departmentId());
		//アクションと同じ機能に対応するアプリケーションサービスの製品機能を探す
		List<AssociatedProductFunction> productFunctions = new ArrayList<>();
		for(ProductServiceDto service : procutServices) {
			if(service.getProductClass().equals(ProductClass.ApplicationService)) {
				List<AssociatedProductFunction> functions = service.getAssociatedProductFunctions();
				for(AssociatedProductFunction function : functions) {
					if(function.getFunctionId().equals(task.getFunction().getFunctionId())){
						productFunctions.add(function);
					}
				}
			}
			
		}
		return productFunctions;
	}
	
	
	/*
	public MessageDto createProductServiceFromProduct(ProductDto product) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(product == null) {
				throw new Exception("The_product_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(product.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ProductCategoryDto productCategory = productCategoryAdapter.findProductCategoryOfId(product.getProductCategoryId());
			if(productCategory == null) {
				throw new Exception("The_productCategory_could_not_be_found");
			}
			//ビジネスロジック
			//ProductDtoをProductServiceに展開する。
			ProductService service = new ProductService();
			service.setProductServiceId(registry.nextIdentity());
			service.setCompanyId(product.getCompanyId());
			service.setProductId(product.getProductId());
			service.setProductClass(product.getProductClass());
			service.setServiceType(product.getServiceType());
			service.setPersonality(product.getPersonality());
			service.setProductName(product.getProductName());
			service.setProductDescription(product.getProductDescription());
			service.setUrl(product.getUrl());
			service.setDepartmentId(product.getDepartmentId());
			service.setIndustryId(product.getIndustryId());
			service.setServiceId(product.getServiceId());
			service.setAssociatedProductFunctions(product.getAssociatedProductFunctions());
			service.setAssociatedRoles(product.getAssociatedRoles());
			registry.createProductService(service);
			
			//アクションのURLを設定する。
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	*/
	
	
	public List<ProductServiceDto> findProductsOfDepartment(String departmentId) throws Exception{
		List<ProductService> productServices = registry.findProductServicesOfDepartment(departmentId);
		return this.convertProductServices(productServices);
	}
	
	public List<ProductServiceDto> findProductsOfProduct(String productId) throws Exception{
		List<ProductService> productServices = registry.findProductServicesOfProduct(productId);
		return this.convertProductServices(productServices);
	}
	
	private ProductServiceDto convertProductService(ProductService entity) {
		ProductServiceDto dto = new ProductServiceDto();
		dto.setProductServiceId(entity.getProductServiceId());
		dto.setCompanyId(entity.getCompanyId());
		dto.setProductId(entity.getProductId());
		dto.setProductClass(entity.getProductClass());
		dto.setServiceType(entity.getServiceType());
		dto.setPersonality(entity.getPersonality());
		dto.setProductName(entity.getProductName());
		dto.setProductDescription(entity.getProductDescription());
		dto.setUrl(entity.getUrl());
		dto.setIndustryId(entity.getIndustryId());
		dto.setDepartmentId(entity.getDepartmentId());
		dto.setServiceId(entity.getServiceId());
		dto.setAssociatedRoles(entity.getAssociatedRoles());
		dto.setAssociatedProductFunctions(entity.getAssociatedProductFunctions());
		dto.setProductSerivceState(entity.getProductSerivceState());	
		
		return dto;
	}
	
	private List<ProductServiceDto> convertProductServices(List<ProductService> services){
		List<ProductServiceDto> dtos = new ArrayList<>();
		for(ProductService service : services) {
			dtos.add(this.convertProductService(service));
		}
		return dtos;
	}

}
