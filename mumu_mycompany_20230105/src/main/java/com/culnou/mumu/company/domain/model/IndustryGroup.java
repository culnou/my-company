package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class IndustryGroup implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//DB側ではキャメルケースをindustry_group_idというカラム名に自動解釈する。
	private String industryGroupId;
	private String industryGroupName;
	
	protected IndustryGroup(){}
	
	public IndustryGroup(String id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	protected void setId(String industryGroupId) {
		//セッターで一貫性制約を保持する。
		if(industryGroupId == null) {
			throw new IllegalArgumentException("The industryGroupId may not be set to null.");
		}
		//産業をなしに設定する場合もあるので以下を削除。2021/12/14
		/*
		if(industryGroupId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a industryGroupId.");
		}
		*/
		this.industryGroupId= industryGroupId;
	}
	
	protected void setName(String industryGroupName) {
		//セッターで一貫性制約を保持する。
		if(industryGroupName == null) {
			throw new IllegalArgumentException("The industryGroupName may not be set to null.");
		}
		//産業をなしに設定する場合もあるので以下を削除。2021/12/14
		/*
		if(industryGroupName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a industryGroupName.");
		}
		*/
		this.industryGroupName = industryGroupName;
	}
	
	public String industryGroupId() {
		return this.industryGroupId;
	}
	
	public String industryGroupName() {
		return this.industryGroupName;
	}
	@Override
	public IndustryGroup clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new IndustryGroup(this.industryGroupId, this.industryGroupName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			IndustryGroup industryGroup = (IndustryGroup)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(industryGroup.industryGroupId().equals(this.industryGroupId()) && industryGroup.industryGroupName().equals(this.industryGroupName())){
				equality = true;
			}
		}
		return equality;
	}
}
