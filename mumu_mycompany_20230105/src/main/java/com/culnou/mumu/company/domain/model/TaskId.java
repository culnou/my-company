package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class TaskId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4075684911682405050L;
	
	String taskId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected TaskId() {}
	
	public TaskId(String taskId) {
		this.setTaskId(taskId);
	}
	
	protected void setTaskId(String taskId){
		//セッターで一貫性制約を保持する。
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		if(taskId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a taskId.");
		}

		this.taskId = taskId;
	}
	
	public String taskId() {
		return this.taskId;
	}
	
	@Override
	public TaskId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new TaskId(this.taskId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			TaskId taskId = (TaskId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(taskId.taskId().equals(this.taskId())){
				equality = true;
			}
		}
		return equality;
	}

}
