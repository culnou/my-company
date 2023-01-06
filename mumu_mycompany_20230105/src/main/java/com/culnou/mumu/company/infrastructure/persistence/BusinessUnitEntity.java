package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.AssociatedCustomerCategory;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.BusinessDomainId;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Industry;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;


@Entity
@Table(name = "business_units")
@XmlRootElement
@Data
public class BusinessUnitEntity {
	
	@Id
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private BusinessDomainId businessDomainId;
	@Embedded
	private CompanyId companyId;
	@Column(name = "business_domain_name")
	private String businessDomainName;
	@Column(name = "business_unit_name")
	private String businessUnitName;
	@Column(name = "vision")
	private String vision;
	@Column(name = "slogan")
	private String slogan;
	@Column(name = "business_strategy")
	private String businessStrategy;
	@Column(name = "business_unit_customer_category")
	private String customerCategory;
	@Column(name = "business_unit_product_category")
	private String productCategory;
	@Embedded
	private Url url;
	@Embedded
	private Industry industry;
	@Embedded
	private CustomerCategoryId customerCategoryId;
	@Column(name = "customer_category_name")
	private String customerCategoryName;
	@Embedded
	private ProductCategoryId productCategoryId;
	@Column(name = "product_category_name")
	private String productCategoryName;
	@ElementCollection
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	@ElementCollection
	private List<AssociatedCustomerCategory> associatedCustomerCategories = new ArrayList<>();
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@Column(name = "hypothesis")
	private String hypothesis;
	@Column(name = "result")
	private String result;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

}
