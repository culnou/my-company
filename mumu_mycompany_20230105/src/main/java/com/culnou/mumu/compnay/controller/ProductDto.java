package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ServiceType;
import com.culnou.mumu.company.domain.model.common.AssociatedRole;
import com.culnou.mumu.company.domain.model.common.Personality;
import com.culnou.mumu.company.domain.model.product.instance.AssociatedGoods;
import com.culnou.mumu.company.domain.model.product.instance.AssociatedProductFunction;

import lombok.Data;

@Data
public class ProductDto {
	
	private String productId;
	private String companyId;
	private String productCategoryId;
	private ProductClass productClass;
	private ServiceType serviceType;
	private Personality personality;
	private String productName;
	private String productDescription;
	private String departmentId;
	private String url;
	private String industryId;
	private String serviceId;
	private boolean market;
	private List<AssociatedRole> associatedRoles = new ArrayList<>();
	private List<AssociatedGoods> associatedGoods = new ArrayList<>();
	private List<AssociatedProductFunction> associatedProductFunctions = new ArrayList<>();

}
