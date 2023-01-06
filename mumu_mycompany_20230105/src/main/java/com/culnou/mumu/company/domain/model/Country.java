package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Country implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String countryCd;
	String countryName;
	
	protected Country() {}
	
	public Country(String countryCd, String countryName) {
		this.setCountryCd(countryCd);
		this.setCountryName(countryName);
	}
	protected void setCountryCd(String countryCd) {
		//セッターで一貫性制約を保持する。
		if(countryCd == null) {
			throw new IllegalArgumentException("The countryCd may not be set to null.");
		}
		this.countryCd = countryCd;
	}
	
	public String getCountryCd() {
		return this.countryCd;
	}
	
	protected void setCountryName(String countryName) {
		//セッターで一貫性制約を保持する。
		if(countryName == null) {
			throw new IllegalArgumentException("The countryName may not be set to null.");
		}

		this.countryName = countryName;
	}
	
	public String getCountryName() {
		return this.countryName;
	}
	
	@Override
	public Country clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Country(this.countryCd, this.countryName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Country country = (Country)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(country.getCountryCd().equals(this.getCountryCd()) && country.getCountryName().equals(this.getCountryName())){
				equality = true;
			}
		}
		return equality;
	}

}
