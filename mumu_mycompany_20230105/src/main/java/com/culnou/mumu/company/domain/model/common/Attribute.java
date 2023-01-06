package com.culnou.mumu.company.domain.model.common;



import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.culnou.mumu.company.domain.model.Indicator;

import lombok.Data;

@Embeddable
@Data
public class Attribute {
	
	private String attributeId;
	private String attributeName;
	private String attributeEnglishName;
	@Embedded
	private AttributeDomain attributeDomain;
	@Embedded
	private Indicator indicator;
	private String attributeDescription;
	private boolean primaryKey;
	private boolean foreignKey;
	//外部キーの場合、参照先の属性IDを指定する。
	private String referenceAttributeId;
	//データ機密レベル
	private String dataSensitivityLevel;
	//データ規制対象カテゴリ
	private String dataRestrictedCategory;
	private  Date auditDate;
	
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Attribute attribute = (Attribute)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(attribute.getAttributeId().equals(this.getAttributeId())){
				equality = true;
			}
		}
		return equality;
	}
	@Override
    public int hashCode() {
        
        int result = this.attributeId.hashCode();
        
        return result;
    }
	
	
	

}
