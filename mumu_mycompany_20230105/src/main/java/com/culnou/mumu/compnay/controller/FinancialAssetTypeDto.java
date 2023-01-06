package com.culnou.mumu.compnay.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetClass;


import lombok.Data;

@Data
public class FinancialAssetTypeDto {
	
	private String financialAssetTypeId;
	private String companyId;
	private String financialAssetTypeName;
	private FinancialAssetClass financialClass;
	private String financialAssetTypeDescription;
	private String businessDomainId;
	private String businessDomainName;
	private String url;
	private String dataTypeId;
	private List<Indicator> indicators = new ArrayList<>();
	private Date createdAt;
	private Date updatedAt;

}
