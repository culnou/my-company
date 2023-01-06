package com.culnou.mumu.company.domain.model.activity.work;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class WorkId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String workId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected WorkId() {}
	
	public WorkId(String workId) {
		this.setWorkId(workId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setWorkId(String workId) {
		//セッターで一貫性制約を保持する。
		if(workId == null) {
			throw new IllegalArgumentException("The workId may not be set to null.");
		}
		if(workId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a workId.");
		}

		this.workId = workId;
	}
	
	@Override
	public WorkId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new WorkId(this.getWorkId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			WorkId workId = (WorkId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(workId.getWorkId().equals(this.getWorkId())){
				equality = true;
			}
		}
		return equality;
	}

}
