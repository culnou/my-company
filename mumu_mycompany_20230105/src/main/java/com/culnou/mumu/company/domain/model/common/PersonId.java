package com.culnou.mumu.company.domain.model.common;

import java.io.Serializable;


//値オブジェクトの実装①：finalでserializableでCloneableなクラスにする。
public class PersonId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	protected PersonId() {}
	//値オブジェクトの実装②：プライマリーコンストラクタ
	//privateなセッターで自己カプセル化して属性を設定して初期化することで不変性を担保する。
	public PersonId(String id) {
		//自己カプセル化する。
		this.setId(id);
	}
	
	protected void setId(String id) {
		//セッターで一貫性制約を保持する。
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	//副作用のないメソッド。
	public String id() {
		return this.id;
	}
	
	//値オブジェクトの実装③：クローンの生成
	//単体テストで使用する。
	@Override
	public PersonId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new PersonId(this.id());
	}
	
	//値オブジェクトの実装④：値の等価性を実現する。
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			PersonId personId = (PersonId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(personId.id().equals(this.id())){
				equality = true;
			}
		}
		return equality;
	}
	

}
