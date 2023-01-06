package com.culnou.mumu.company.domain.model.member.category;

import javax.persistence.Embeddable;


import lombok.Data;

@Data
@Embeddable
public class AssociatedMemberCategory {
	
	private String memberCategoryId;
	private String memberCategoryName;

}
