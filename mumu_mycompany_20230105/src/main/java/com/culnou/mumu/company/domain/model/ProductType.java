package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.common.Attribute;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import lombok.Getter;
import lombok.Setter;

public class ProductType {
	
	private CompanyId companyId;
	private ProductTypeId productTypeId;
	@Getter
	@Setter
	private BusinessDomainId businessDomainId;
	private CustomerTypeId customerTypeId;
	private String customerTypeName;
	private String productTypeName;
	@Getter
	private ProductClass productClass;
	@Getter
	private String valueProposition;
	private String solution;
	private String problem;
	@Getter
	private Url url;
	@Getter
	@Setter
	private List<Indicator> indicators = new ArrayList<>();
	@Getter
	@Setter
	private List<Attribute> attributes = new ArrayList<>();
	@Setter
	@Getter
	private Date createdAt;
	@Setter
	@Getter
	private Date updatedAt;
	
	@Setter
	@Getter
	private DataTypeId dataTypeId;
	
	//データ
	@Setter
	@Getter
	private String entityName;//エンティティ名
	@Setter
	@Getter
	private String entityEnglishName;//エンティティ英語名
	@Setter
	@Getter
	private String entityDescription;//エンティティ摘要
	@Setter
	@Getter
	private String dataOwner;//データオーナー
	@Setter
	@Getter
	private Email address;//データオーナーのメールアドレス
	@Setter
	@Getter
	private String associatedConstraint;//関連制約
	@Setter
	@Getter
	private String existenceConstraint;//存在制約
	@Setter
	@Getter
	private String dataAmount;//データ量

	
	protected ProductType(CompanyId companyId, ProductTypeId productTypeId) {
		this.setCompanyId(companyId);
		this.setProductTypeId(productTypeId);
	}
	
	protected void setProductTypeId(ProductTypeId productTypeId) {
		//不変性の保持。
		if(this.productTypeId != null) {
				throw new IllegalArgumentException("The_productTypeId_is_already_exist");
		}
		if(productTypeId == null) {
			throw new IllegalArgumentException("The productTypeId may not be set to null.");
		}
		this.productTypeId = productTypeId;
	}
	
	public ProductTypeId productTypeId() {
		return this.productTypeId;
	}
	
	public void setCustomerTypeId(CustomerTypeId customerTypeId) {
		if(customerTypeId == null) {
			throw new IllegalArgumentException("The customerTypeId may not be set to null.");
		}
		this.customerTypeId = customerTypeId;
	}
	
	public CustomerTypeId customerTypeId() {
		return this.customerTypeId;
	}
	
	public void setCustomerTypeName(String customerTypeName) {
		if(customerTypeName == null) {
			throw new IllegalArgumentException("The customerTypeName may not be set to null.");
		}
		this.customerTypeName = customerTypeName;
	}
	
	public String customerTypeName() {
		return this.customerTypeName;
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
	
	public void setProductTypeName(String productTypeName) {
		if(productTypeName == null) {
			throw new IllegalArgumentException("The productTypeName may not be set to null.");
		}
		this.productTypeName = productTypeName;
	}
	
	public String productTypeName() {
		return this.productTypeName;
	}
	
	public void setSolution(String solution) {
		if(solution == null) {
			throw new IllegalArgumentException("The solution may not be set to null.");
		}
		this.solution = solution;
	}
	
	public String solution() {
		return this.solution;
	}
	
	public void setProblem(String problem) {
		if(problem == null) {
			throw new IllegalArgumentException("The problem may not be set to null.");
		}
		this.problem = problem;
	}
	
	public String problem() {
		return this.problem;
	}
	
	public void setProductClass(ProductClass productClass) {
		if(productClass == null) {
			throw new IllegalArgumentException("The productClass may not be set to null.");
		}
		this.productClass = productClass;
	}
	
	public void setValueProposition(String valueProposition) {
		if(valueProposition == null) {
			throw new IllegalArgumentException("The valueProposition may not be set to null.");
		}
		this.valueProposition = valueProposition;
	}
	
	public void  setUrl(Url url) {
		this.url = url;
	}
	
	public ProductCategory defineProductCategory(ProductCategoryId productCategoryId) {
		return new ProductCategory(this.companyId, this.productTypeId, productCategoryId);
	}
	
	
	   public ProductTask defineProductTask(ProductTaskId productTaskId, String productTaskName) {
	    	if(productTaskId == null) {
				throw new IllegalArgumentException("The productTaskId may not be set to null.");
			}
	    	
	    	if(productTaskName == null) {
				throw new IllegalArgumentException("The productTaskName may not be set to null.");
			}
	    	return new ProductTask(productTaskId, this.companyId, this.productTypeId, this.productTypeName, productTaskName);
	    }

	

}
