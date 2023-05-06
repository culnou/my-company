package com.culnou.mumu.domain.model;



import java.io.Serializable;

import javax.persistence.Embeddable;


import lombok.Getter;
@Getter
@Embeddable
public class AssociatedCode implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codeId;
	private String code;
	private String codeName;
	private String codeCategoryId;
	private String codeCategoryName;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected AssociatedCode() {}
	
	public AssociatedCode(String codeId, String code, String codeName, String codeCategoryId, String codeCategoryName) {
		this.setCodeId(codeId);
		this.setCode(code);
		this.setCodeName(codeName);
		this.setCodeCategoryId(codeCategoryId);
		this.setCodeCategoryName(codeCategoryName);
	}
	
	protected void setCodeId(String codeId) {
		if(codeId == null) {
			throw new IllegalArgumentException("The_codeId_may_not_be_set_to_null");
		}
		if(codeId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_codeId");
		}
		this.codeId = codeId;
	}
	
	protected void setCode(String code) {
		if(code == null) {
			throw new IllegalArgumentException("The_code_may_not_be_set_to_null");
		}
		if(code.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_code");
		}
		this.code = code;
	}
	
	protected void setCodeName(String codeName) {
		if(codeName == null) {
			throw new IllegalArgumentException("The_codeName_may_not_be_set_to_null");
		}
		if(codeName.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_codeName");
		}
		this.codeName = codeName;
	}
	
	protected void setCodeCategoryId(String codeCategoryId) {
		if(codeCategoryId == null) {
			throw new IllegalArgumentException("The_codeCategoryId_may_not_be_set_to_null");
		}
		if(codeCategoryId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_codeCategoryId");
		}
		this.codeCategoryId = codeCategoryId;
	}
	
	protected void setCodeCategoryName(String codeCategoryName) {
		if(codeCategoryName == null) {
			throw new IllegalArgumentException("The_codeCategoryName_may_not_be_set_to_null");
		}
		if(codeCategoryName.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_codeCategoryName");
		}
		this.codeCategoryName = codeCategoryName;
	}
	
	@Override
	public AssociatedCode clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new AssociatedCode(this.codeId, this.code, this.codeName, this.codeCategoryId, this.codeCategoryName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			AssociatedCode associatedCode = (AssociatedCode)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(associatedCode.getCodeId().equals(this.getCodeId()) && associatedCode.getCode().equals(this.getCode()) && associatedCode.getCodeName().equals(this.getCodeName()) && associatedCode.getCodeCategoryId().equals(this.getCodeCategoryId()) && associatedCode.getCodeCategoryName().equals(this.getCodeCategoryName())){
				equality = true;
			}
		}
		return equality;
	}
	
	
}
