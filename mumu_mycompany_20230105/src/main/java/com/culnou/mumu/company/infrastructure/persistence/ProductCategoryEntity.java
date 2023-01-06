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
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Goods;
import com.culnou.mumu.company.domain.model.Industry;
import com.culnou.mumu.company.domain.model.IndustryGroup;
import com.culnou.mumu.company.domain.model.IndustrySubGroup;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ProductTypeId;
import com.culnou.mumu.company.domain.model.Role;
import com.culnou.mumu.company.domain.model.Service;
import com.culnou.mumu.company.domain.model.ServiceType;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Data;


@Entity
@Table(name = "product_categories")
@XmlRootElement
@Data
public class ProductCategoryEntity {
	@Id
	@Embedded
	private ProductCategoryId productCategoryId;
	@Column(name = "product_category_name")
	private String productCategoryName;
	@Column(name = "product_description")
	private String productCategoryDescription;
	@Column(name = "product_category_position")
	private String productCategoryPosition;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private ProductTypeId productTypeId;
	@Column(name = "product_type_name")
	private String productTypeName;
	@Embedded
	private CustomerCategoryId customerCategoryId;
	@Column(name = "customer_category_name")
	private String customerCategoryName;
	@Embedded
	private IndustryGroup industryGroup;
	@Embedded
	private Industry industry;
	@Embedded
	private IndustrySubGroup industrySubGroup;
	@ElementCollection
	private List<Role> roles = new ArrayList<>();
	@ElementCollection
	private List<Goods> goodses = new ArrayList<>();
	@ElementCollection
	private List<Service> services = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Column(name = "prodct_class")
	private ProductClass productClass;
	@Enumerated(EnumType.STRING)
	@Column(name = "service_type")
	private ServiceType serviceType;
	@Enumerated(EnumType.STRING)
	@Column(name = "personality")
	private Personality personality;
	@Embedded
	private Service service;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@Embedded
	private Url url;
	@Column(name = "customer_journey")
	private String customerJourney;
	@Column(name = "service_scenario")
	private String serviceScenario;
	@Column(name = "domain_model")
	private String domainModel;
	@Column(name = "demo")
	private String demo;
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "enddate")
	private Date endDate;
	
	@Column(name = "business_state")
	private BusinessState businessState;

}
