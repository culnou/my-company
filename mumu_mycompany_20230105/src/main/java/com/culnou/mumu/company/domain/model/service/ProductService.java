package com.culnou.mumu.company.domain.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ServiceType;
import com.culnou.mumu.company.domain.model.common.AssociatedRole;
import com.culnou.mumu.company.domain.model.common.Personality;

import com.culnou.mumu.company.domain.model.product.instance.AssociatedProductFunction;


import lombok.Data;
@Entity
@Table(name = "product_services")
@XmlRootElement
@Data
public class ProductService {
	@Id
	@Embedded
	@NotNull
	private ProductServiceId productServiceId;
	@Column(name = "company_id")
	@NotNull
	@NotEmpty
	private String companyId;
	@Column(name = "product_id")
	@NotNull
	@NotEmpty
	private String productId;
	@Enumerated(EnumType.STRING)
	@Column(name = "product_class")
	private ProductClass productClass;
	@Enumerated(EnumType.STRING)
	@Column(name = "service_type")
	private ServiceType serviceType;
	@Enumerated(EnumType.STRING)
	@Column(name = "personality")
	private Personality personality;
	@Column(name = "product_name")
	@NotNull
	@NotEmpty
	private String productName;
	@Column(name = "product_description")
	private String productDescription;
	@Column(name = "url")
	private String url;
	@Column(name = "department_id")
	private String departmentId;
	@Column(name = "industry_id")
	private String industryId;
	@Column(name = "service_id")
	private String serviceId;
	@ElementCollection
	private List<AssociatedRole> associatedRoles = new ArrayList<>();
	@ElementCollection
	private List<AssociatedProductFunction> associatedProductFunctions = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Column(name = "product_service_state")
	private ProductServiceState productSerivceState;

}
