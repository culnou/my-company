package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class DepartmentId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String departmentId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected DepartmentId() {}
	
	public DepartmentId(String departmentId) {
		this.setDepartmentId(departmentId);
	}
	
	protected void setDepartmentId(String departmentId){
		//セッターで一貫性制約を保持する。
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId may not be set to null.");
		}
		if(departmentId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a departmentId.");
		}

		this.departmentId = departmentId;
	}
	
	public String departmentId() {
		return this.departmentId;
	}
	
	@Override
	public DepartmentId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new DepartmentId(this.departmentId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			DepartmentId departmentId = (DepartmentId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(departmentId.departmentId().equals(this.departmentId())){
				equality = true;
			}
		}
		return equality;
	}

}
