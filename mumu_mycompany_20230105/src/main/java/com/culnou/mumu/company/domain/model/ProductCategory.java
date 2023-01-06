package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Getter;
import lombok.Setter;

public class ProductCategory {
	
	private ProductCategoryId productCategoryId;
	private String productCategoryName;
	private String productCategoryDescription;
	private String productCategoryPosition;
	private CompanyId companyId;
	@Getter
	@Setter
	private BusinessUnitId businessUnitId;
	private ProductTypeId productTypeId;
	private String productTypeName;
	private CustomerCategoryId customerCategoryId;
	private String customerCategoryName;
	private IndustryGroup industryGroup;
	private Industry industry;
	private IndustrySubGroup industrySubGroup;
	private List<Role> roles = new ArrayList<>();
	private List<Goods> goodses = new ArrayList<>();
	private List<Service> services = new ArrayList<>();
	private ProductClass productClass;
	private Service service;
	private ServiceType serviceType;
	private Personality personality;
	@Getter
	@Setter
	private List<Goal> goals = new ArrayList<>();
	@Getter
	@Setter
	private List<Achievement> achievements = new ArrayList<>();
	@Getter
	@Setter
	private Url url;
	@Getter
	@Setter
	private String customerJourney;
	@Getter
	@Setter
	private String serviceScenario;
	@Getter
	@Setter
	private String domainModel;
	@Getter
	@Setter
	private String demo;
	@Getter
	@Setter
	private Date startDate;
	@Getter
	@Setter
	private Date endDate;
	@Getter
	@Setter
	private BusinessState businessState;


	
	protected ProductCategory(CompanyId companyId, ProductTypeId productTypeId, ProductCategoryId productCategoryId) {
		this.setCompanyId(companyId);
		this.setProductTypeId(productTypeId);
		this.setProductCategoryId(productCategoryId);
	}
	
	protected void setProductCategoryId(ProductCategoryId productCategoryId) {
		if(productCategoryId == null) {
			throw new IllegalArgumentException("The productCategoryId may not be set to null.");
		}
		this.productCategoryId = productCategoryId;
	}
	public ProductCategoryId productCategoryId() {
		return this.productCategoryId;
	}
	

	public void setProductCategoryName(String productCategoryName) {
		if(productCategoryName == null) {
			throw new IllegalArgumentException("The productCategoryName may not be set to null.");
		}
		this.productCategoryName = productCategoryName;
	}
	public String productCategoryName() {
		return this.productCategoryName;
	}
	public void setProductCategoryDescription(String productCategoryDescription) {
		//任意にする。2022/9/25
		/*
		if(productCategoryDescription == null) {
			throw new IllegalArgumentException("The productCategoryDescription may not be set to null.");
		}
		*/
		this.productCategoryDescription = productCategoryDescription;
	}
	public String productCategoryDescription() {
		return this.productCategoryDescription;
	}
	public void setProductCategoryPosition(String productCategoryPosition) {
		//任意にする。2022/9/25
		/*
		if(productCategoryPosition == null) {
			throw new IllegalArgumentException("The productCategoryPosition may not be set to null.");
		}
		*/
		this.productCategoryPosition = productCategoryPosition;
	}
	public String productCategoryPosition() {
		return this.productCategoryPosition;
	}
	public void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		this.companyId =companyId;
	}
	public CompanyId companyId() {
		return this.companyId;
	}
	public void setProductTypeId(ProductTypeId productTypeId) {
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		this.productTypeId = productTypeId;
	}
	
	public ProductTypeId productTypeId() {
		return this.productTypeId;
	}

	
	public void setProductTypeName(String productTypeName) {
		if(productTypeName == null) {
			throw new IllegalArgumentException("The productTypeName may not be set to null.");
		}
		this.productTypeName = productTypeName;
	}
	public String productTypeName() {
		return this.productTypeName;
	}
	
	public void setCustomerCategoryId(CustomerCategoryId customerCategoryId) {
		//任意にする。2022/9/25
		/*
		if(customerCategoryId == null) {
			throw new IllegalArgumentException("The customerCategoryId may not be set to null.");
		}
		*/
		this.customerCategoryId = customerCategoryId;
	}
	public CustomerCategoryId customerCategoryId() {
		return this.customerCategoryId;
	}
	

	public void setCustomerCategoryName(String customerCategoryName) {
		if(customerCategoryName == null) {
			throw new IllegalArgumentException("The customerCategoryName may not be set to null.");
		}
		this.customerCategoryName = customerCategoryName;
	}
	public String customerCategoryName() {
		return this.customerCategoryName;
	}
	

	public void setIndustryGroup(IndustryGroup industryGroup) {
		//任意にする。2022/9/25
		/*
		if(industryGroup == null) {
			throw new IllegalArgumentException("The industryGroup may not be set to null.");
		}
		*/
		this.industryGroup = industryGroup;
	}
	public IndustryGroup industryGroup() {
		return this.industryGroup;
	}
	
	public void setIndustry(Industry industry) {
		//任意にする。2022/9/25
		/*
		if(industry == null) {
			throw new IllegalArgumentException("The industry may not be set to null.");
		}
		*/
		this.industry = industry;
	}
	
	public Industry industry() {
		return this.industry;
	}
	
	public void setIndustrySubGroup(IndustrySubGroup industrySubGroup) {
		//任意にする。2022/9/25
		/*
		if(industrySubGroup == null) {
			throw new IllegalArgumentException("The industrySubGroup may not be set to null.");
		}
		*/
		this.industrySubGroup = industrySubGroup;
	}
	
	public IndustrySubGroup industrySubGroup() {
		return this.industrySubGroup;
	}
	public void setRoles(List<Role> roles) {
		//サービスから商品に更新する場合、DBからサービスデータを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		/*
		if(roles == null) {
			throw new IllegalArgumentException("The roles may not be set to null.");
		}
		*/
		this.roles = roles;
	}
	public List<Role> roles(){
		return this.roles;
	}
	public void setProductClass(ProductClass productClass) {
		if(productClass == null) {
			throw new IllegalArgumentException("The productClass may not be set to null.");
		}
		this.productClass = productClass;
	}
	
	public ProductClass productClass() {
		return this.productClass;
	}
	
	public void setGoodses(List<Goods> goodses) {
		//商品からサービスに更新する場合、DBから商品データを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		/*
		if(goodses == null) {
			throw new IllegalArgumentException("The goodses may not be set to null.");
		}
		*/
		this.goodses = goodses;
	}
	public List<Goods> goodses(){
		return this.goodses;
	}
	
	public void setServices(List<Service> services) {
		if(services == null) {
			throw new IllegalArgumentException("The services may not be set to null.");
		}
		this.services = services;
	}
	public List<Service> services(){
		return this.services;
	}
	public void setPersonality(Personality personality) {
		//任意にする。2022/9/25
		/*
		if(personality == null) {
			throw new IllegalArgumentException("The personality may not be set to null.");
		}
		*/
		this.personality = personality;
	}
	public Personality personality() {
		return this.personality;
	}
	public void setServiceType(ServiceType serviceType) {
		//サービスから商品に更新する場合、DBからサービスタイプデータを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		/*
		if(serviceType == null) {
			throw new IllegalArgumentException("The serviceType may not be set to null.");
		}
		*/
		this.serviceType = serviceType;
	}
	
	public ServiceType serviceType() {
		return this.serviceType;
	}
	public void setService(Service service) {
		//サービスから商品に更新する場合、DBからサービスデータを削除するために商品をNULLにするのでNullチェックを外します。2021/12/23
		/*
		if(service == null) {
			throw new IllegalArgumentException("The service may not be set to null.");
		}
		*/
		this.service = service;
	}
	
	public Service service() {
		return this.service;
	}
	
	   public ProductFunction defineProductFunction(ProductFunctionId productActionId, ProductTaskId productTaskId, String productTaskName, String productActionName) {
	    	if(productActionId == null) {
				throw new IllegalArgumentException("The productActionId may not be set to null.");
			}
	    	
	    	if(productActionName == null) {
				throw new IllegalArgumentException("The productActionName may not be set to null.");
			}
	    	return new ProductFunction(productActionId, this.companyId, this.productCategoryId, this.productCategoryName, productTaskId, productTaskName, productActionName);
	    }
	



}
