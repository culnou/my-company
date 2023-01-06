package com.culnou.mumu.company.domain.model.product.instance;

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


import lombok.Data;
@Entity
@Table(name = "products")
@XmlRootElement
@Data
public class Product {
	
	@Id
	@Embedded
	@NotNull
	private ProductId productId;
	@Column(name = "company_id")
	@NotNull
	@NotEmpty
	private String companyId;
	@Column(name = "product_category_id")
	@NotNull
	@NotEmpty
	private String productCategoryId;
	@Column(name = "department_id")
	private String departmentId;
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
	//リスティングページのURL
	@Column(name = "url")
	private String url;
	@Column(name = "industry_id")
	private String industryId;
	@Column(name = "service_id")
	private String serviceId;
	@ElementCollection
	private List<AssociatedRole> associatedRoles = new ArrayList<>();
	@ElementCollection
	private List<AssociatedGoods> associatedGoods = new ArrayList<>();
	@ElementCollection
	private List<AssociatedProductFunction> associatedProductFunctions = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Column(name = "product_state")
	private ProductState productState;
	@Column(name = "email")
	private String email;
	//割当てられた市場の製品かどうか識別する。
	@Column(name = "market")
	private boolean market;
	

}
