package com.culnou.mumu.company.domain.model.member.type;

import javax.persistence.Embeddable;



import lombok.Data;

@Data
@Embeddable
public class AssociatedMemberType {
	
	private String memberTypeId;
	private String memberTypeName;

}
