package com.culnou.mumu.company.domain.model.partner.type;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedPartnerType {
	
	private String partnerTypeId;
	private String partnerTypeName;

}
