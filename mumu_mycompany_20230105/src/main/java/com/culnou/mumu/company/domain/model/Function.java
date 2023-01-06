package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Function implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String functionId;
	private String functionName;
	
	protected Function() {}
	
	public Function(String functionId, String functionName) {
		this.setFunctionId(functionId);
		this.setFunctionName(functionName);
	}
	
	protected void setFunctionId(String functionId) {
		if(functionId == null) {
			throw new IllegalArgumentException("The functionId may not be set to null.");
		}
		this.functionId = functionId;
	}
	
	public String getFunctionId() {
		return this.functionId;
	}
	
	public void setFunctionName(String functionName) {
		if(functionName == null) {
			throw new IllegalArgumentException("The functionName may not be set to null.");
		}
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	@Override
	public Function clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Function(this.functionId, this.functionName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Function function = (Function)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(function.getFunctionId().equals(this.getFunctionId()) && function.getFunctionName().equals(this.getFunctionName())){
				equality = true;
			}
		}
		return equality;
	}

}
