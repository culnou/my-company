package com.culnou.mumu.compnay.controller;

import java.util.Date;



import lombok.Data;

@Data
public class MemberTypeMetaDataDto {
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

}
