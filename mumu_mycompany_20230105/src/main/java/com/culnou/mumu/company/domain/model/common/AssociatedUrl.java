package com.culnou.mumu.company.domain.model.common;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.culnou.mumu.company.domain.model.Url;

import lombok.Data;

@Data
@Embeddable
public class AssociatedUrl {
	
	private String urlTitle;
	@Embedded
	private Url url;

}
