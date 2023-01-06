package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class Industry implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String industryId;
	private String industryName;
	
	protected Industry() {}
	
	public Industry(String id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	protected void setId(String industryId) {
		//セッターで一貫性制約を保持する。
		if(industryId == null) {
			throw new IllegalArgumentException("The industryId may not be set to null.");
		}
		//産業をなしに設定する場合もあるので以下を削除。2021/12/14
		/*
		if(industryId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a industryId.");
		}
		*/
		this.industryId= industryId;
	}
	
	protected void setName(String industryName) {
		//セッターで一貫性制約を保持する。
		if(industryName == null) {
			throw new IllegalArgumentException("The industryName may not be set to null.");
		}
		//産業をなしに設定する場合もあるので以下を削除。2021/12/14
		/*
		if(industryName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a industryName.");
		}
		*/
		this.industryName = industryName;
	}
	
	public String id() {
		return this.industryId;
	}
	
	public String name() {
		return this.industryName;
	}
	@Override
	public Industry clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Industry(this.industryId, this.industryName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Industry industry = (Industry)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(industry.id().equals(this.id()) && industry.name().equals(this.name())){
				equality = true;
			}
		}
		return equality;
	}

}
