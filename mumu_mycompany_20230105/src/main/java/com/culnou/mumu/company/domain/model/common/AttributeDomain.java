package com.culnou.mumu.company.domain.model.common;

import java.io.Serializable;



import lombok.Getter;

@Getter
public class AttributeDomain implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String attributeDomainId;
	private String attributeDomainName;
	private String attributeDomainDescription;
	
    protected AttributeDomain() {}
	
	public AttributeDomain(String attributeDomainId, String attributeDomainName, String attributeDomainDescription) {
		this.setAttributeDomainId(attributeDomainId);
		this.setAttributeDomainName(attributeDomainName);
		this.setAttributeDomainDescription(attributeDomainDescription);
	}
	
	protected void setAttributeDomainId(String attributeDomainId) {
		if(attributeDomainId == null) {
			throw new IllegalArgumentException("The attributeDomainId may not be set to null.");
		}
		if(attributeDomainId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a attributeDomainId.");
		}
		this.attributeDomainId = attributeDomainId;
	}
	
	protected void setAttributeDomainName(String attributeDomainName) {
		if(attributeDomainName == null) {
			throw new IllegalArgumentException("The attributeDomainName may not be set to null.");
		}
		if(attributeDomainName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a attributeDomainName.");
		}
		this.attributeDomainName = attributeDomainName;
	}
	
	protected void setAttributeDomainDescription(String attributeDomainDescription) {
		if(attributeDomainDescription == null) {
			throw new IllegalArgumentException("The attributeDomainDescription may not be set to null.");
		}
		if(attributeDomainDescription.isEmpty()) {
			throw new IllegalArgumentException("Must provide a attributeDomainDescription.");
		}
		this.attributeDomainDescription = attributeDomainDescription;
	}
	
	@Override
	public AttributeDomain clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new AttributeDomain(this.getAttributeDomainId(), this.getAttributeDomainName(), this.getAttributeDomainDescription());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			AttributeDomain attributeDomain = (AttributeDomain)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(attributeDomain.getAttributeDomainId().equals(this.getAttributeDomainId()) && attributeDomain.getAttributeDomainName().equals(this.getAttributeDomainDescription()) && attributeDomain.getAttributeDomainDescription().equals(this.getAttributeDomainDescription())){
				equality = true;
			}
		}
		return equality;
	}

}
