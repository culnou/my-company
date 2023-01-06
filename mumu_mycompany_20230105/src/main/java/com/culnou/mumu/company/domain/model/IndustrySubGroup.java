package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class IndustrySubGroup implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String industrySubGroupId;
	private String industrySubGroupName;
	
	protected IndustrySubGroup() {}
	public IndustrySubGroup(String id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	protected void setId(String industrySubGroupId) {
		//セッターで一貫性制約を保持する。
		if(industrySubGroupId == null) {
			throw new IllegalArgumentException("The industrySubGroupId may not be set to null.");
		}
		//産業をなしに設定する場合もあるので以下を削除。2021/12/14
		/*
		if(industrySubGroupId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a industrySubGroupId.");
		}
		*/
		this.industrySubGroupId= industrySubGroupId;
	}
	
	protected void setName(String industrySubGroupName) {
		//セッターで一貫性制約を保持する。
		if(industrySubGroupName == null) {
			throw new IllegalArgumentException("The industrySubGroupName may not be set to null.");
		}
		//産業をなしに設定する場合もあるので以下を削除。2021/12/14
		/*
		if(industrySubGroupName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a industrySubGroupName.");
		}
		*/
		this.industrySubGroupName = industrySubGroupName;
	}
	
	public String id() {
		return this.industrySubGroupId;
	}
	
	public String name() {
		return this.industrySubGroupName;
	}
	@Override
	public IndustrySubGroup clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new IndustrySubGroup(this.industrySubGroupId, this.industrySubGroupName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			IndustrySubGroup industryGroup = (IndustrySubGroup)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(industryGroup.id().equals(this.id()) && industryGroup.name().equals(this.name())){
				equality = true;
			}
		}
		return equality;
	}

}
