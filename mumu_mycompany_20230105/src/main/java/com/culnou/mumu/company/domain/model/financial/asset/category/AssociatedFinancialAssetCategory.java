package com.culnou.mumu.company.domain.model.financial.asset.category;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedFinancialAssetCategory {
	
	private String financialAssetCategoryId;
	private String financialAssetCategoryName;

}
