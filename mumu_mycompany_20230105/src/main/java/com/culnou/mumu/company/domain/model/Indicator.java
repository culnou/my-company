package com.culnou.mumu.company.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;


@Embeddable
public class Indicator implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String indicatorName;
	@Getter
	private String unit;
	//測定方法
	@Getter
	@Setter
	private String measuringMethod;
	//評価基準
	@Getter
	@Setter
	private String evaluationStandard;
	@Getter
	@Setter
	private String description;
	@Getter
	@Setter
	private Date createdAt;
	@Getter
	@Setter
	private Date updatedAt;
	
	
	protected Indicator() {}
	
	public Indicator(String indicatorName, String unit, String description) {
		this.setIndicatorName(indicatorName);
		this.setUnit(unit);
		this.setDescription(description);
	}
	
	
	protected void setIndicatorName(String indicatorName) {
		//セッターで一貫性制約を保持する。
		if(indicatorName == null) {
			throw new IllegalArgumentException("The indicatorName may not be set to null.");
		}
		if(indicatorName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a indicatorName.");
		}
		this.indicatorName = indicatorName;
	}
	
	public void setUnit(String unit) {
		
		if(unit == null) {
			throw new IllegalArgumentException("The unit may not be set to null.");
		}
		if(unit.isEmpty()) {
			throw new IllegalArgumentException("Must provide a unit.");
		}
		
		this.unit = unit;
	}
	/*
	protected void setDescription(String description) {
		//セッターで一貫性制約を保持する。
		if(description == null) {
			throw new IllegalArgumentException("The description may not be set to null.");
		}
		if(description.isEmpty()) {
			throw new IllegalArgumentException("Must provide a description.");
		}
		this.description = description;
	}
	*/
	
	
	
	
	@Override
	public Indicator clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Indicator(this.indicatorName, this.unit, this.description);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Indicator indicator = (Indicator)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(indicator.getIndicatorName().equals(this.getIndicatorName()) && indicator.getUnit().equals(this.getUnit()) && indicator.getDescription().equals(this.getDescription())){
				equality = true;
			}
		}
		return equality;
	}

}
