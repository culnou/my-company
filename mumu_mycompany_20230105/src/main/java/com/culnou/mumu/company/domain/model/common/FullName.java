package com.culnou.mumu.company.domain.model.common;



import java.io.Serializable;

public class FullName implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String middle;
	private String lastName;
	/*
	@SuppressWarnings("unused")
	private FullName() {
	}
	*/
	
	protected FullName() {}
	public FullName(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	private FullName(String firstName, String middle, String lastName) {
		this.setFirstName(firstName);
		this.middle = middle;
		this.setLastName(lastName);
	}
	
	protected void setFirstName(String firstName) {
		if(firstName == null) {
			throw new IllegalArgumentException("The firstName may not be set to null.");
		}
		if(firstName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a firstName.");
		}
		this.firstName = firstName;
	}
	
	public String firstName() {
		return this.firstName;
	}
	
	protected void setLastName(String lastName) {
		if(lastName == null) {
			throw new IllegalArgumentException("The lastName may not be set to null.");
		}
		if(lastName.isEmpty()) {
			throw new IllegalArgumentException("Must provide a lastName.");
		}
		this.lastName = lastName;
	}
	
	public String lastName() {
		return this.lastName;
	}
	
	public FullName withMiddleInitial(String middleNameOrInitial) {
		if(middleNameOrInitial == null) {
			throw new IllegalArgumentException("Must provide a middle name or initial.");
		}
		//文字列の前後の空白を除去する。
		String middle = middleNameOrInitial.trim();
		//文字列が空文字（"")かどうかを判定する。
		if(middle.isEmpty()) {
			throw new IllegalArgumentException("Must provide a middle name or initial.");
		}
		//substringで1文字を取得する。
		return new FullName(this.firstName, middle.substring(0,1).toUpperCase(), this.lastName);
	}
	
	public String middle() {
		return this.middle;
	}
	
	@Override
	public FullName clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		if(this.middle != null) {
			return new FullName(this.firstName(), this.middle(), this.lastName());
		}else {
			return new FullName(this.firstName(), this.lastName());
		}
		
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			FullName fullName = (FullName)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(this.middle != null) {
				if(fullName.firstName().equals(this.firstName()) && fullName.middle().equals(this.middle()) && fullName.lastName().equals(this.lastName())){
					equality = true;
				}
			}else {
				if(fullName.firstName().equals(this.firstName()) && fullName.lastName().equals(this.lastName())){
					equality = true;
				}
			}
			
		}
		return equality;
	}

}
