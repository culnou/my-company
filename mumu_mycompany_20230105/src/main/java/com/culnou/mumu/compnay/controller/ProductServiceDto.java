package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.ServiceType;
import com.culnou.mumu.company.domain.model.common.AssociatedRole;
import com.culnou.mumu.company.domain.model.common.Personality;
import com.culnou.mumu.company.domain.model.product.instance.AssociatedProductFunction;
import com.culnou.mumu.company.domain.model.service.ProductServiceId;
import com.culnou.mumu.company.domain.model.service.ProductServiceState;

import lombok.Data;
@Data
public class ProductServiceDto {
	
	private ProductServiceId productServiceId;
	private String companyId;
	private String productId;
	private ProductClass productClass;
	private ServiceType serviceType;
	private Personality personality;
	private String productName;
	private String productDescription;
	private String url;
	private String departmentId;
	private String industryId;
	private String serviceId;
	private List<AssociatedRole> associatedRoles = new ArrayList<>();
	private List<AssociatedProductFunction> associatedProductFunctions = new ArrayList<>();
	private ProductServiceState productSerivceState;

}
