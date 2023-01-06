package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.data.type.DataClass;

import lombok.Data;
@Data
public class DataTypeDto {
	
	private String dataTypeId;
	private String companyId;
	private String dataTypeName;
	private String dataTypeDescription;
	private String businessDomainId;
	private String businessDomainName;
	private DataClass dataClass;
	private String dataOwnerId;
	private String url;
	private List<Indicator> indicators = new ArrayList<>();
	private String dataOwner;//データオーナー
	private String address;//データオーナーのメールアドレス
	private String associatedConstraint;//関連制約
	private String existenceConstraint;//存在制約
	private String dataAmount;//データ量
	private String parentId;
	private Date createdAt;
	private Date updatedAt;

}
