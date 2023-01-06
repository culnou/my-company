package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class Address implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String zipcode;
	private String country;
	private String state;
	private String city;
	private String street;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected Address() {}
	
	public Address(String zipcode, String country, String state, String city, String street) {
		this.setZipcode(zipcode);
		this.setCountry(country);
		this.setState(state);
		this.setCity(city);
		this.setStreet(street);
	}
	
	protected void setZipcode(String zipcode) {
		if(zipcode == null) {
			throw new IllegalArgumentException("The zipcode may not be set to null.");
		}
		if(zipcode.isEmpty()) {
			throw new IllegalArgumentException("Must provide a zipcode.");
		}
		this.zipcode = zipcode;
	}
	
	protected void setState(String state) {
		if(state == null) {
			throw new IllegalArgumentException("The state may not be set to null.");
		}
		if(state.isEmpty()) {
			throw new IllegalArgumentException("Must provide a state.");
		}
		this.state = state;
	}
	
	protected void setCountry(String country) {
		if(country == null) {
			throw new IllegalArgumentException("The country may not be set to null.");
		}
		if(country.isEmpty()) {
			throw new IllegalArgumentException("Must provide a country.");
		}
		this.country = country;
	}
	
	protected void setCity(String city) {
		if(city == null) {
			throw new IllegalArgumentException("The city may not be set to null.");
		}
		if(city.isEmpty()) {
			throw new IllegalArgumentException("Must provide a city.");
		}
		this.city = city;
	}
	
	protected void setStreet(String street) {
		if(street == null) {
			throw new IllegalArgumentException("The street may not be set to null.");
		}
		if(street.isEmpty()) {
			throw new IllegalArgumentException("Must provide a street.");
		}
		this.street = street;
	}
	
	public String zipcode() {
		return this.zipcode;
	}
	
	public String country() {
		return this.country;
	}
	
	public String state() {
		return this.state;
	}
	
	public String city() {
		return this.city;
	}
	
	public String street() {
		return this.street;
	}
	
	@Override
	public Address clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Address(this.zipcode, this.country, this.state, this.city, this.street);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Address address = (Address)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(address.zipcode.equals(this.zipcode()) && address.country.equals(this.country) && address.state.equals(this.state) && address.city.equals(this.city) && address.street.equals(this.street)){
				equality = true;
			}
		}
		return equality;
	}

	

}
