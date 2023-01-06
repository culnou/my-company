package com.culnou.mumu.company.domain.model.activity.workflow;

import java.io.Serializable;


import lombok.Getter;
@Getter
public class WorkflowId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String workflowId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected WorkflowId() {}
	
	public WorkflowId(String workflowId) {
		this.setWorkflowId(workflowId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setWorkflowId(String workflowId) {
		//セッターで一貫性制約を保持する。
		if(workflowId == null) {
			throw new IllegalArgumentException("The workflowId may not be set to null.");
		}
		if(workflowId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a workflowId.");
		}

		this.workflowId = workflowId;
	}
	
	@Override
	public WorkflowId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new WorkflowId(this.getWorkflowId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			WorkflowId workflowId = (WorkflowId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(workflowId.getWorkflowId().equals(this.getWorkflowId())){
				equality = true;
			}
		}
		return equality;
	}

}
