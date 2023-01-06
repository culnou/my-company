package com.culnou.mumu.company.domain.model.financial.asset.type;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedFinancialAssetType {
	
	private String financialAssetTypeId;
	private String financialAssetTypeName;

}
