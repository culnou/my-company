package com.culnou.mumu.company.domain.model.program;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class ProgramId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String programId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected ProgramId() {}
	
	public ProgramId(String programId) {
		this.setProgramId(programId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setProgramId(String programId) {
		//セッターで一貫性制約を保持する。
		if(programId == null) {
			throw new IllegalArgumentException("The programId may not be set to null.");
		}
		if(programId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a programId.");
		}

		this.programId = programId;
	}
	
	@Override
	public ProgramId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProgramId(this.getProgramId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProgramId programtId = (ProgramId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(programtId.getProgramId().equals(this.getProgramId())){
				equality = true;
			}
		}
		return equality;
	}

}
