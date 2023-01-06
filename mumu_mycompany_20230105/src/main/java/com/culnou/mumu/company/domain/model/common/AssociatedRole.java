package com.culnou.mumu.company.domain.model.common;

import javax.persistence.Embeddable;


import lombok.Data;

@Data
@Embeddable
public class AssociatedRole {
	
	private String roleId;
	private String roleName;

}
