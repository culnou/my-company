package com.culnou.mumu.company.domain.model.product.instance;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AssociatedGoods {
	
	private String goodsId;
	private String goodsName;

}
