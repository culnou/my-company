package com.culnou.mumu.company.domain.model.product.instance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionRepository;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentRepository;
import com.culnou.mumu.company.domain.model.Goods;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductFunction;
import com.culnou.mumu.company.domain.model.Role;
import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryRegistry;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunction;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionRegistry;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTask;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskRepository;
import com.culnou.mumu.company.domain.model.common.AssociatedRole;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.domain.model.common.DepartmentAdapter;
import com.culnou.mumu.company.domain.model.common.ProductCategoryAdapter;
import com.culnou.mumu.company.domain.model.common.ProductFunctionAdapter;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryRegistry;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunction;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionRegistry;
import com.culnou.mumu.compnay.controller.CompanyDto;

import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductCategoryDto;
import com.culnou.mumu.compnay.controller.ProductDto;
import com.culnou.mumu.compnay.controller.RoleDto;
//ドメイン単位のトランザクションを保証する
@Service
@Transactional
public class ProductService {
	//ドメイン間のコミュニケーションはアダプタ(commonパッケージに配置する）を介して行う。
	@Autowired
	private ProductCategoryAdapter productCategoryAdapter;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ProductFunctionAdapter productFunctionAdapter;
	@Autowired
	private ProductRegistry registry;
	@Autowired
	private MarketProductAdapter marketProductAdapter;
	@Autowired
	private DepartmentAdapter departmentAdapter;
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private ApplicationCategoryRegistry applicationCategoryRegistry;
	@Autowired
	private PartnerCategoryRegistry partnerCategoryRegistry;
	@Autowired
	private ApplicationFunctionRegistry applicationFunctionRegistry;
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	@Qualifier("applicationTaskJpaRepository")
	@Autowired
	private ApplicationTaskRepository applicationTaskRepository;
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private PartnerFunctionRegistry partnerFunctionRegistry;
	@Autowired
	private JmsTemplate jmsTemplate;
	

	
	
	/*
	 * 製品カテゴリの製品を探す
	 */
	//アプリケーションサービスからドメインサービスには、文字列で識別子を渡し、ドメイン側で、その識別子に対する実体が存在するか検証する
	public List<ProductDto> findProductsOfProductCategory(String productCategoryId) throws Exception{
		
		//事前条件
        //コントローラーからの入力値の妥当性を保証する
		if(productCategoryId == null){
			throw new Exception("The_productCategoryId_may_not_be_set_to_null");
		}
		if(productCategoryId.isEmpty()) {
			throw new Exception("Must_provide_a_productCategoryId");
		}
        //識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
		ProductCategoryDto productCategory = productCategoryAdapter.findProductCategoryOfId(productCategoryId);
		if(productCategory == null) {
			throw new Exception("The_productCategory_could_not_be_found");
		}
		
		
		return this.convertProducts(registry.findProductsOfProductCategory(productCategoryId));
	}
	
	/*
	 * 製品を作成する
	 */
	//コマンドの場合、MessageDtoでフロントエンドに結果（OKかNG）を伝える
	public MessageDto createProduct(ProductDto product) {
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
			Product entity = this.convertProductDto(product);
			//識別子の設定
			entity.setProductId(registry.nextIdentity());
			//製品カテゴリの物財を製品に設定する。
			for(Goods goods : productCategory.getGoodses()) {
				AssociatedGoods associatedGoods = new AssociatedGoods();
				associatedGoods.setGoodsId(goods.getGoodsId());
				associatedGoods.setGoodsName(goods.getGoodsName());
				entity.getAssociatedGoods().add(associatedGoods);
			}
			//製品カテゴリの役割を製品に設定する。
			for(Role role : productCategory.getRoles()) {
				AssociatedRole associatedRole = new AssociatedRole();
				associatedRole.setRoleId(role.getRoleId());
				associatedRole.setRoleName(role.getRoleName());
				entity.getAssociatedRoles().add(associatedRole);
			}
			//製品カテゴリの製品機能を製品に設定する。
			List<ProductFunction> productFunctions = productFunctionAdapter.productFunctionsOfProductCategory(new ProductCategoryId(productCategory.getProductCategoryId()));
			for(ProductFunction productFunction : productFunctions) {
				AssociatedProductFunction associatedProductFunction = new AssociatedProductFunction();
				associatedProductFunction.setProductFunctionId(productFunction.getProductFunctionId().getProductFunctionId());
				associatedProductFunction.setProductFunctionName(productFunction.getProductFunctionName());
				if(productFunction.getUrl().getUrl() != null && !productFunction.getUrl().getUrl().isEmpty()) {
					associatedProductFunction.setProductFunctionUrl(productFunction.getUrl().getUrl());
						
				}
				associatedProductFunction.setFunctionId(productFunction.getFunction().getFunctionId());
				//製品ワークを作成するために、製品を作成するタイミングで、associatedProductFunctionにProductIdを設定する。
				associatedProductFunction.setParentProductId(entity.getProductId().getProductId());
				entity.getAssociatedProductFunctions().add(associatedProductFunction);
				
				
			}
			//製品カテゴリの製品クラスを製品に設定する。
			entity.setProductClass(productCategory.getProductClass());
			//製品カテゴリのサービスタイプを製品に設定する。
			entity.setServiceType(productCategory.getServiceType());
			//製品カテゴリのパーソナリティを製品に設定する。
			entity.setPersonality(productCategory.getPersonality());
			//製品カテゴリの産業を製品に設定する。
			entity.setIndustryId(productCategory.getIndustryId());
			//製品カテゴリのサービスを製品に設定する。
			entity.setServiceId(productCategory.getService().getServiceId());
			
			registry.createProduct(entity);
			
			message.setResult("OK");
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	
	
	public MessageDto updateProduct(ProductDto product) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(product == null) {
				throw new Exception("The_product_may_not_be_set_to_null");
			}
			
			if(product.getProductId() == null || product.getProductId().isEmpty()) {
				throw new Exception("The_productId_may_not_be_set_to_null");
			}
			if(product.getProductName() == null || product.getProductName().isEmpty()) {
				throw new Exception("The_productName_may_not_be_set_to_null");
			}
			Product prod = registry.findProductOfId(new ProductId(product.getProductId()));
			if(prod == null) {
				throw new Exception("The_product_dose_not_exist");
			}
			//市場に登録された製品は更新できない。
			if(prod.isMarket() == true) {
				throw new Exception("The_product_is_already_be_registered_to_market");
			}
			prod.setProductName(product.getProductName());
			prod.setProductDescription(product.getProductDescription());
			
			
			registry.createProduct(prod);
			
			message.setResult("OK");
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}

	public MessageDto removeProduct(String productId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(productId == null || productId.isEmpty()) {
				throw new Exception("The_productId_may_not_be_set_to_null");
			}
			Product prod = registry.findProductOfId(new ProductId(productId));
			if(prod == null) {
				throw new Exception("The_product_dose_not_exist");
			}
			//市場に登録された製品は削除できない。
			if(prod.isMarket() == true) {
				throw new Exception("The_product_is_already_be_registered_to_market");
			}
			registry.removeProduct(prod);
			
			message.setResult("OK");
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	
	
	
	/*
	 * IDの製品を探す
	 */
	public ProductDto findProductOfId(String productId) throws Exception{
		//事前条件
        //コントローラーからの入力値の妥当性を保証する
		if(productId == null) {
			throw new Exception("The_productId_may_not_be_set_to_null");
		}
		return this.convertProduct(registry.findProductOfId(new ProductId(productId)));
	}
	
	/*
	 * 製品を市場に登録する
	 */
	public MessageDto registerProductToMarket(String productId) {
		MessageDto message = new MessageDto();
		try {
			Product product = registry.findProductOfId(new ProductId(productId));
			//割当済や解除済の製品は登録できない。
			if(product.getProductState() != null) {
				if(product.getProductState().equals(ProductState.Assigned)) {
					throw new Exception("The_product_is_already_assigned");
				}
				if(product.getProductState().equals(ProductState.Released)) {
					throw new Exception("The_product_is_already_released");
				}
			}
			//REST/HTTP
			/*
			message = this.marketProductAdapter.createProduct(this.convertProduct(product));
			if(message.getResult().equals("OK")) {
				registry.registerProduct(product);
			}
			*/
			//JMS非同期メッセージング
			//DBアクセス
			registry.registerProduct(product);
			//Topicアクセス
			jmsTemplate.convertAndSend("Product.Registered.Queue", product);
			
			message.setResult("OK");
	
			
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	/*
	 * 割り当て可能な市場の製品を探す
	 */
	public List<ProductDto> findAssignableProducts(List<RoleDto> roles, String industryId) {
		List<ProductDto> productDtos = new ArrayList<>();
		try {
			productDtos = marketProductAdapter.findAssignableProducts(roles, industryId);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productDtos;
	}
	/*
	 * 割当可能なアプリケーションサービスを探す
	 */
	public List<ProductDto> findAssignableApplicationServices(List<RoleDto> roles, String industryId) {
		List<ProductDto> productDtos = new ArrayList<>();
		List<ProductDto> applications = new ArrayList<>();
		try {
			productDtos = marketProductAdapter.findAssignableProducts(roles, industryId);
			for(ProductDto dto : productDtos) {
				if(dto.getProductClass().equals(ProductClass.ApplicationService)) {
					applications.add(dto);				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return applications;
	}
	
	/*
	 * 割当可能なパーソナルサービスを探す
	 */
	public List<ProductDto> findAssignablePersonalServices(List<RoleDto> roles, String industryId) {
		List<ProductDto> productDtos = new ArrayList<>();
		List<ProductDto> personalServices = new ArrayList<>();
		try {
			productDtos = marketProductAdapter.findAssignableProducts(roles, industryId);
			for(ProductDto dto : productDtos) {
				if(dto.getProductClass().equals(ProductClass.PersonalService)) {
					personalServices.add(dto);				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return personalServices;
	}
	
	private ProductDto convertProduct(Product product) {
		ProductDto dto = new ProductDto();
		dto.setProductId(product.getProductId().getProductId());
		dto.setCompanyId(product.getCompanyId());
		dto.setProductCategoryId(product.getProductCategoryId());
		dto.setProductClass(product.getProductClass());
		dto.setServiceType(product.getServiceType());
		dto.setPersonality(product.getPersonality());
		dto.setProductName(product.getProductName());
		dto.setProductDescription(product.getProductDescription());
		dto.setDepartmentId(product.getDepartmentId());
		dto.setUrl(product.getUrl());
		dto.setIndustryId(product.getIndustryId());
		dto.setServiceId(product.getServiceId());
		dto.setAssociatedRoles(product.getAssociatedRoles());
		dto.setAssociatedGoods(product.getAssociatedGoods());
		dto.setAssociatedProductFunctions(product.getAssociatedProductFunctions());
		dto.setMarket(product.isMarket());		
		return dto;
	}
	private List<ProductDto> convertProducts(List<Product> products) {
		List<ProductDto> dtos = new ArrayList<>();
		for(Product product : products) {
			dtos.add(this.convertProduct(product));
		}
		return dtos;
	}
	
	private Product convertProductDto(ProductDto dto) {
		Product product = new Product();
		if(dto.getProductId() != null) {
			product.setProductId(new ProductId(dto.getProductId()));
		}
		product.setCompanyId(dto.getCompanyId());
		product.setProductCategoryId(dto.getProductCategoryId());		
		product.setProductClass(dto.getProductClass());
		product.setServiceType(dto.getServiceType());
		product.setPersonality(dto.getPersonality());
		product.setProductName(dto.getProductName());
		product.setProductDescription(dto.getProductDescription());
		product.setDepartmentId(dto.getDepartmentId());
		product.setUrl(dto.getUrl());
		product.setIndustryId(dto.getIndustryId());
		product.setServiceId(dto.getServiceId());
		product.setAssociatedRoles(dto.getAssociatedRoles());
		product.setAssociatedGoods(dto.getAssociatedGoods());
		product.setAssociatedProductFunctions(dto.getAssociatedProductFunctions());
		product.setMarket(dto.isMarket());		
		return product;
	}
	
	/*
	 * アプリケーションサービスを部門のサービスに割当てる
	 */
	public MessageDto assignApplicationServiceToDepartment(ProductDto product, String departmentId, String companyId, String businessUnitId) {
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
			if(businessUnitId == null) {
				throw new Exception("The_businessUnitId_may_not_be_set_to_null");
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
			List<Action> actions = actionRepository.actionsOfDepartment(department.getDepartmentId());
			List<Task> tasks = new ArrayList<Task>();
			for(Action action : actions) {
				Task task = taskRepository.TaskOfId(action.getTaskId());
				tasks.add(task);			
			}
			//市場の製品を部門に割り当てる。
			//REST/HTTP
			/*
			message = marketProductAdapter.assignProductToDepartment(product.getProductId(), departmentId, companyId);
			if(message.getResult().equals("NG")) {
				throw new Exception("The_product_of_market_could_not_assign_to_department");
			}
			*/
			//JMS非同期メッセージング
			Product prd = this.convertProductDto(product);
			prd.setDepartmentId(departmentId);
			prd.setCompanyId(companyId);
			jmsTemplate.convertAndSend("Product.AssignedToDepartment.Queue", prd);
			message.setResult("OK");
			
			//ApplicationCategoryを作成する。
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(businessUnitId));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			ApplicationCategoryId applicationCategoryId = applicationCategoryRegistry.nextIdentity();
			ApplicationCategory applicationCategory = businessUnit.defineApplicationCategory(applicationCategoryId, product.getProductName());
			applicationCategory.setProductId(new ProductId(product.getProductId()));
			applicationCategory.setProductName(product.getProductName());
			applicationCategory.setApplicationCategoryDescription(product.getProductDescription());
			applicationCategory.setBusinessState(BusinessState.Executing);
			applicationCategory.setCreatedAt(new Date());
			applicationCategoryRegistry.save(applicationCategory);
			//アプリケーション機能の生成。アプリケーション機能にはアプリケーションタスクが必須なのでアプリケーションタスクを作成する必要がある。
			//製品機能→機能→機能と同じアプリケーションタスクがあるかチェック→あれば、どれでも一つ選択する。
			List<AssociatedProductFunction> productFunctions  = product.getAssociatedProductFunctions();
			for(AssociatedProductFunction productFunction : productFunctions) {
				List<ApplicationTask> appTasks = new ArrayList<>();
				for(Task task : tasks) {
					if(productFunction.getFunctionId().equals(task.getFunction().getFunctionId())) {
						appTasks = applicationTaskRepository.findApplicationTasksOfTask(task.getTaskId());
					}
				}
				//ApplicationFunction applicationFunction = new ApplicationFunction(applicationFunctionRegistry.nextIdentity(), new CompanyId(companyId), appTask.getApplicationTaskName(), applicationCategoryId, product.getProductName());
				ApplicationFunction applicationFunction = null;
				if(appTasks.size() == 0) {
					//throw new Exception("The_applicationTask_dose_not_exist");
					//部門のアクションにない製品機能がある場合、エラーになるのでダミーデータを設定する。
					applicationFunction = new ApplicationFunction(applicationFunctionRegistry.nextIdentity(), new CompanyId(companyId), "111", applicationCategoryId, product.getProductName());
					applicationFunction.setApplicationTaskId(new ApplicationTaskId("111"));
					applicationFunction.setApplicationTaskName("111");
				}else {
					ApplicationTask appTask = appTasks.get(0);
					applicationFunction = new ApplicationFunction(applicationFunctionRegistry.nextIdentity(), new CompanyId(companyId), appTask.getApplicationTaskName(), applicationCategoryId, product.getProductName());
					applicationFunction.setApplicationTaskId(appTask.getApplicationTaskId());
					applicationFunction.setApplicationTaskName(appTask.getApplicationTaskName());
				}
				applicationFunction.setUrl(new Url(productFunction.getProductFunctionUrl()));
				applicationFunctionRegistry.save(applicationFunction);
				
			}
			//部門への割当て
			AssociatedApplicationCategory ac = new AssociatedApplicationCategory();
			ac.setApplicationCategoryId(applicationCategory.getApplicationCategoryId().getApplicationCategoryId());
			ac.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
			department.getAssociatedApplicationCategories().add(ac);
			departmentRepository.save(department);
			//製品を割当済にする。
			Product prod = registry.findProductOfId(new ProductId(product.getProductId()));
			registry.assignProduct(prod);
			
			message.setResult("OK");
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto assignPersonalServiceToDepartment(ProductDto product, String departmentId, String companyId, String businessUnitId) {
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
			if(businessUnitId == null) {
				throw new Exception("The_businessUnitId_may_not_be_set_to_null");
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
			List<Action> actions = actionRepository.actionsOfDepartment(department.getDepartmentId());
			List<Task> tasks = new ArrayList<Task>();
			for(Action action : actions) {
				Task task = taskRepository.TaskOfId(action.getTaskId());
				tasks.add(task);			
			}
			//市場の製品を部門に割り当てる。
			//REST/HTTP
			/*
			message = marketProductAdapter.assignProductToDepartment(product.getProductId(), departmentId, companyId);
			if(message.getResult().equals("NG")) {
				throw new Exception("The_product_of_market_could_not_assign_to_department");
			}
			*/
			//JMS非同期メッセージング
			Product prd = this.convertProductDto(product);
			prd.setDepartmentId(departmentId);
			prd.setCompanyId(companyId);
			jmsTemplate.convertAndSend("Product.AssignedToDepartment.Queue", prd);
			message.setResult("OK");
			
			
			//PartnerCategoryを作成する。
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(businessUnitId));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			PartnerCategoryId partnerCategoryId = partnerCategoryRegistry.nextIdentity();
			PartnerCategory partnerCategory = businessUnit.definePartnerCategory(partnerCategoryId, product.getProductName());
			partnerCategory.setProductId(new ProductId(product.getProductId()));
			partnerCategory.setProductName(product.getProductName());
			partnerCategory.setPartnerCategoryDescription(product.getProductDescription());
			partnerCategory.setBusinessState(BusinessState.Executing);
			partnerCategory.setCreatedAt(new Date());
			partnerCategoryRegistry.save(partnerCategory);
			//パートナー機能の生成。
			List<AssociatedProductFunction> productFunctions  = product.getAssociatedProductFunctions();
			for(AssociatedProductFunction productFunction : productFunctions) {
				//プロダクト機能の機能と同じ機能を持つアクションのタスクを特定し、それをパートナー機能のタスクに設定する。
				for(Task task : tasks) {
					if(productFunction.getFunctionId().equals(task.getFunction().getFunctionId())) {
						//パートナー機能の名前はアクションのタスク名を設定する。
						PartnerFunction partnerFunction = new PartnerFunction(partnerFunctionRegistry.nextIdentity(), new CompanyId(companyId), task.getTaskName(), partnerCategoryId, product.getProductName());
						partnerFunction.setTaskId(task.getTaskId());
						partnerFunction.setTaskName(task.getTaskName());
						partnerFunction.setUrl(new Url(productFunction.getProductFunctionUrl()));
						partnerFunctionRegistry.save(partnerFunction);
					}
				}
				
			}
			//部門への割当て
			AssociatedPartnerCategory ac = new AssociatedPartnerCategory();
			ac.setPartnerCategoryId(partnerCategory.getPartnerCategoryId().getPartnerCategoryId());
			ac.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
			department.getAssociatedPartnerCategories().add(ac);
			departmentRepository.save(department);
			//製品を割当済にする。
			Product prod = registry.findProductOfId(new ProductId(product.getProductId()));
			registry.assignProduct(prod);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto releaseApplicationService(String departmentId, String applicationCategoryId) throws Exception{
		
		MessageDto message = new MessageDto();
		try {
			if(applicationCategoryId == null || applicationCategoryId.isEmpty()) {
				throw new Exception("The_applicationCategoryId_may_not_be_set_to_null");
			}
			if(departmentId == null || departmentId.isEmpty()) {
				throw new Exception("The_departmentId_may_not_be_set_to_null");
			}
			ApplicationCategory applicationCategory = applicationCategoryRegistry.findApplicationCategoryOfId(applicationCategoryId);
			if(applicationCategory == null) {
				throw new Exception("The_applicationCategory_dose_not_exist");
			}
			Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
			if(department == null) {
				throw new Exception("The_department_dose_not_exist");
			}
			
			//会社のアプリケーションカテゴリを割当てている場合
			if(applicationCategory.getProductId() == null) {
				//throw new Exception("The_applicationCategory_is_not_applicationService");
				AssociatedApplicationCategory assp = new AssociatedApplicationCategory();
				assp.setApplicationCategoryId(applicationCategoryId);
				assp.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
				department.releaseApplication(assp);
				departmentRepository.save(department);
			//市場のアプリケーションサービスを割当てている場合
			}else {
				List<ApplicationFunction> applicationFunctions = applicationFunctionRegistry.applicationFunctionsOfApplicationCategory(applicationCategory.getApplicationCategoryId());
				//アプリケーション機能がアクションに割当てられている場合解除できない。
				List<Action> actions = actionRepository.actionsOfDepartment(department.getDepartmentId());
				for(Action action : actions) {
					for(ApplicationFunction appFunction : applicationFunctions) {
						if(action.getApplicationCategoryId() != null) {
							if(action.getApplicationCategoryId().equals(appFunction.getApplicationFunctionId().getApplicationFunctionId())){
								throw new Exception("The_applicationFunction_is_already_assigned");
							}
						}
					}
				}
				Product prod = registry.findProductOfId(applicationCategory.getProductId());
				//市場の製品を解除済にする。
				this.releaseProduct(prod.getProductId().getProductId());
				//会社の製品を解除済にする。
				prod.setProductState(ProductState.Registered);
				registry.createProduct(prod);
				//アプリケーション機能を削除する。
				List<ApplicationFunction> partnerFuncs = applicationFunctionRegistry.applicationFunctionsOfApplicationCategory(applicationCategory.getApplicationCategoryId());
				for(ApplicationFunction func : partnerFuncs) {
					applicationFunctionRegistry.remove(func);
				}
				//アプリケーションカテゴリを削除する。
				applicationCategoryRegistry.remove(applicationCategory);
				AssociatedApplicationCategory assp = new AssociatedApplicationCategory();
				assp.setApplicationCategoryId(applicationCategoryId);
				assp.setApplicationCategoryName(applicationCategory.getApplicationCategoryName());
				department.releaseApplication(assp);
				departmentRepository.save(department);
			}
			
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	
	
	public MessageDto releasePersonalService(String departmentId, String partnerCategoryId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			if(partnerCategoryId == null || partnerCategoryId.isEmpty()) {
				throw new Exception("The_partnerCategoryId_may_not_be_set_to_null");
			}
			if(departmentId == null || departmentId.isEmpty()) {
				throw new Exception("The_departmentId_may_not_be_set_to_null");
			}
			PartnerCategory partnerCategory = partnerCategoryRegistry.findPartnerCategoryOfId(partnerCategoryId);
			if(partnerCategory == null) {
				throw new Exception("The_partnerCategory_dose_not_exist");
			}
			Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
			if(department == null) {
				throw new Exception("The_department_dose_not_exist");
			}
			
			//会社のアプリケーションカテゴリを割当てている場合
			//実質的にないので削除
			//if(partnerCategory.getProductId() == null) {
				//throw new Exception("The_partnerCategory_is_not_partnerService");
				//AssociatedPartnerCategory assp = new AssociatedPartnerCategory();
				//assp.setPartnerCategoryId(partnerCategoryId);
				//assp.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
				//department.releasePartner(assp);
				//departmentRepository.save(department);
			//市場のアプリケーションサービスを割当てている場合
			//}else {
				List<PartnerFunction> partnerFunctions = partnerFunctionRegistry.partnerFunctionsOfPartnerCategory(partnerCategory.getPartnerCategoryId());
				//アプリケーション機能がアクションに割当てられている場合解除できない。
				List<Action> actions = actionRepository.actionsOfDepartment(department.getDepartmentId());
				for(Action action : actions) {
					for(PartnerFunction appFunction : partnerFunctions) {
						if(action.getPartnerCategoryId() != null) {
							if(action.getPartnerCategoryId().equals(appFunction.getPartnerFunctionId().getPartnerFunctionId())){
								throw new Exception("The_partnerFunction_is_already_assigned");
							}
						}
					}
				}
				Product prod = registry.findProductOfId(partnerCategory.getProductId());
				//市場の製品を解除済にする。
				this.releaseProduct(prod.getProductId().getProductId());
				//会社の製品を解除済にする。
				prod.setProductState(ProductState.Registered);
				registry.createProduct(prod);
				//パートナー機能を削除する。
				List<PartnerFunction> partnerFuncs = partnerFunctionRegistry.partnerFunctionsOfPartnerCategory(partnerCategory.getPartnerCategoryId());
				for(PartnerFunction func : partnerFuncs) {
					partnerFunctionRegistry.remove(func);
				}
				//パートナーカテゴリを削除する。
				partnerCategoryRegistry.remove(partnerCategory);
				AssociatedPartnerCategory assp = new AssociatedPartnerCategory();
				assp.setPartnerCategoryId(partnerCategoryId);
				assp.setPartnerCategoryName(partnerCategory.getPartnerCategoryName());
				department.releasePartner(assp);
				departmentRepository.save(department);
				
				
			//}
			
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

	
	public MessageDto releaseProduct(String productId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			if(productId == null || productId.isEmpty()) {
				throw new Exception("The_productId_may_not_be_set_to_null");
			}
			//市場の製品を解除する。
			//REST/HTTP
			/*
			message = marketProductAdapter.releaseProduct(productId);
			if(message.getResult().equals("NG")) {
				throw new Exception("The_product_of_market_could_not_release");
			}
			*/
			//JMS非同期メッセージング
			Product product = new Product();
			product.setProductId(new ProductId(productId));
			jmsTemplate.convertAndSend("Product.Registered.Queue", product);
			//製品を解除する。
			registry.releaseProduct(new ProductId(productId));
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}


}
