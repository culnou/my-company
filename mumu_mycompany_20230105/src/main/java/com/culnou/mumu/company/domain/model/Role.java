package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Role implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	private String roleName;
	
	protected Role() {}
	
	protected Role(String roleId, String roleName) {
		this.setRoleId(roleId);
		this.setRoleName(roleName);
	}
	
	protected void setRoleId(String roleId) {
		if(roleId == null) {
			throw new IllegalArgumentException("The roleId may not be set to null.");
		}
		this.roleId = roleId;
	}
	
	public String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleName(String roleName) {
		if(roleName == null) {
			throw new IllegalArgumentException("The roleName may not be set to null.");
		}
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	@Override
	public Role clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Role(this.roleId, this.roleName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Role role = (Role)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(role.getRoleId().equals(this.getRoleId()) && role.getRoleName().equals(this.getRoleName())){
				equality = true;
			}
		}
		return equality;
	}

}
