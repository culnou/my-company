package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class DomainName implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DomainName() {}
	
	public DomainName(String name) {
		this.setName(name);
	}
	
	protected void setName(String name) {
		//セッターで一貫性制約を保持する。
		if(name == null) {
			throw new IllegalArgumentException("The_domain_name_may_not_be_set_to_null");
		}
		if(name.isEmpty()) {
			throw new IllegalArgumentException("Must provide a domain name.");
		}
		//ドメイン制約
		if(!java.util.regex.Pattern.matches("^([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", name)) {
			throw new IllegalArgumentException("The_domain_names_format_is_invalid");
		}
		this.name = name;
	}
	public String name() {
		return this.name;
	}
	@Override
	public DomainName clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DomainName(this.name);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DomainName name = (DomainName)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(name.name().equals(this.name())){
				equality = true;
			}
		}
		return equality;
	}
	

}
