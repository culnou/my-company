package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.AssociatedCustomerType;
import com.culnou.mumu.company.domain.model.AssociatedProductType;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.common.AssociatedUrl;

import lombok.Data;

@Data
public class BusinessDomainDto {
	
	private String businessDomainId;
	private String companyId;
	private boolean enterprise;
	private String industryGroupId;
	private String industryGroupName;
	private String industryId;
	private String industryName;
	private String industrySubGroupId;
	private String industrySubGroupName;
	private String businessDomainName;
	private String purpose;
	private String businessModel;
	private String customerType;
	private String productType;
	private String url;
	private String customerTypeId;
	private String customerTypeName;
	private String productTypeId;
	private String productTypeName;
	private List<Indicator> indicators = new ArrayList<>();
	private List<AssociatedUrl> associatedUrls = new ArrayList<>();
	private List<AssociatedProductType> associatedProductTypes = new ArrayList<>();
	private List<AssociatedCustomerType> associatedCustomerTypes = new ArrayList<>();

}
