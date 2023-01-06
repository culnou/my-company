package com.culnou.mumu.company.domain.model.common;

import java.io.Serializable;

public class Email implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String address;
	
	protected Email() {}
	
	public Email(String address) {
		super();
		this.setAddress(address);
	}
	
	private void setAddress(String address) {
		if(address == null) {
			throw new IllegalArgumentException("The address may not be set to null.");
		}
		if(address.length() == 0) {
			throw new IllegalArgumentException("The email address is required.");
		}
		if(!java.util.regex.Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", address)) {
			throw new IllegalArgumentException("The email address and/or its format is invalid.");
		}
		this.address = address;
	}
	
	public String address() {
		return this.address;
	}
	
	@Override
	public Email clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Email(this.address());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Email email = (Email)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(email.address().equals(this.address())){
				equality = true;
			}
		}
		return equality;
	}

}
