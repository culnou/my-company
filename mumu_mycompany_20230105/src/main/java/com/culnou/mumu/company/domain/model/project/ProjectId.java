package com.culnou.mumu.company.domain.model.project;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class ProjectId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String projectId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ProjectId() {}
	
	public ProjectId(String projectId) {
		this.setProjectId(projectId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setProjectId(String projectId) {
		//セッターで一貫性制約を保持する。
		if(projectId == null) {
			throw new IllegalArgumentException("The projectId may not be set to null.");
		}
		if(projectId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a projectId.");
		}

		this.projectId = projectId;
	}
	
	@Override
	public ProjectId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProjectId(this.getProjectId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProjectId projectId = (ProjectId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(projectId.getProjectId().equals(this.getProjectId())){
				equality = true;
			}
		}
		return equality;
	}

}
