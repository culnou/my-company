package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;

import lombok.Data;

@Data
public class DataItemDto {
	
	private String dataItemId;
	private String companyId;
	private String dataTypeId;
	private String dataTypeName;
	private String dataDomainId;
	private String dataDomainName;
	private String dataItemName;
	private String dataItemDescription;
	private String dataItemType;
	private String dataItemDigit;
	private String dataItemConstraints;
	private String dataOwnerId;
	private String url;
	private Date createdAt;
	private Date updatedAt;
	
	private boolean primaryKey;
	private boolean foreignKey;
	//外部キーの場合、参照先の属性IDを指定する。
	private String referenceAttributeId;
	//データ機密レベル
	private String dataSensitivityLevel;
	//データ規制対象カテゴリ
	private String dataRestrictedCategory;
	private  Date auditDate;
	
	private List<Indicator> indicators = new ArrayList<>();

}
