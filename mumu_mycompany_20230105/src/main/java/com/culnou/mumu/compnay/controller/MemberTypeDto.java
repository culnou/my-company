package com.culnou.mumu.compnay.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.member.type.AssociatedJob;
import com.culnou.mumu.company.domain.model.member.type.MemberClass;

import lombok.Data;

@Data
public class MemberTypeDto {
	
	private String companyId;
	private String businessDomainId;
	//private String memberTypeId;
	private String memberTypeName;
	private MemberClass memberClass;
	private JobId jobId;
	private String jobName;
	private List<AssociatedJob> associatedJobs = new ArrayList<>();
	private String values;
	private String issue;
	private String memberTypeDescription;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private String dataTypeId;
	/*
	private List<Indicator> indicators = new ArrayList<>();
	private List<Attribute> attributes = new ArrayList<>();
	//データ
	private String entityName;//エンティティ名
	private String entityEnglishName;//エンティティ英語名
	private String entityDescription;//エンティティ摘要
	private String dataOwner;//データオーナー
	private String address;//データオーナーのメールアドレス
	private String associatedConstraint;//関連制約
	private String existenceConstraint;//存在制約
	private String dataAmount;//データ量
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	*/

}
