package com.culnou.mumu.company.domain.model.partner.category;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedPartnerCategory {
	
	private String partnerCategoryId;
	private String partnerCategoryName;

}
