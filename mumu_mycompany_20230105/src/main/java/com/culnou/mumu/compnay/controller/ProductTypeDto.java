package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.common.Attribute;

import lombok.Data;

@Data
public class ProductTypeDto {
	private String productTypeId;
	private String customerTypeId;
	private String customerTypeName;
	private String companyId;
	private String businessDomainId;
	private String productTypeName;
	private ProductClass productClass;
	private String valueProposition;
	private String solution;
	private String problem;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();
	private List<Attribute> attributes = new ArrayList<>();
	private String dataTypeId;
	//データ
	private String entityName;//エンティティ名
	private String entityEnglishName;//エンティティ英語名
	private String entityDescription;//エンティティ摘要
	private String dataOwner;//データオーナー
	private String address;//データオーナーのメールアドレス
	private String associatedConstraint;//関連制約
	private String existenceConstraint;//存在制約
	private String dataAmount;//データ量
}
