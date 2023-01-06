package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.common.Attribute;

import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Data;


@Data
public class CustomerTypeDto {
	
	private String customerTypeId;
	private String companyId;
	private String businessDomainId;
	private String customerTypeName;
	private Personality personality;
	private String values;
	private String cause;
	private String issue;
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
