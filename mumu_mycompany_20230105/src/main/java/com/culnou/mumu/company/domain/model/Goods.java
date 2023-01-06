package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Goods implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String goodsId;
	private String goodsName;
	
	protected Goods() {}
	
	protected Goods(String roleId, String roleName) {
		this.setGoodsId(roleId);
		this.setGoodsName(roleName);
	}
	
	protected void setGoodsId(String goodsId) {
		if(goodsId == null) {
			throw new IllegalArgumentException("The goodsId may not be set to null.");
		}
		this.goodsId = goodsId;
	}
	
	public String getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsName(String goodsName) {
		if(goodsName == null) {
			throw new IllegalArgumentException("The goodsName may not be set to null.");
		}
		this.goodsName = goodsName;
	}
	
	public String getGoodsName() {
		return this.goodsName;
	}
	
	@Override
	public Goods clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Goods(this.goodsId, this.goodsName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Goods goods = (Goods)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(goods.getGoodsId().equals(this.getGoodsId()) && goods.getGoodsName().equals(this.getGoodsName())){
				equality = true;
			}
		}
		return equality;
	}

}
