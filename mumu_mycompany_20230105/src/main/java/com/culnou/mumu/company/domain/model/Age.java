package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Age implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String ageCd;
	String ageName;
	
	public Age() {}
	
	public Age(String ageCd, String ageName) {
		this.setAgeCd(ageCd);
		this.setAgeName(ageName);
	}
	
	protected void setAgeCd(String ageCd) {
		if(ageCd == null) {
			throw new IllegalArgumentException("The ageCd may not be set to null.");
		}

		this.ageCd = ageCd;
	}
	
	public String getAgeCd() {
		return this.ageCd;
	}
	
	protected void setAgeName(String ageName) {
		if(ageName == null) {
			throw new IllegalArgumentException("The ageName may not be set to null.");
		}
		this.ageName = ageName;
	}
	
	public String getAgeName() {
		return this.ageName;
	}
	@Override
	public Age clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Age(this.ageCd, this.ageName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Age age = (Age)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(age.getAgeCd().equals(this.getAgeCd()) && age.getAgeName().equals(this.getAgeName())){
				equality = true;
			}
		}
		return equality;
	}

}
