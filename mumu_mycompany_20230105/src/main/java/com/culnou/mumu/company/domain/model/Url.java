package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class Url implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	
	protected Url() {}
	
	public Url(String url) {
		this.setUrl(url);
	}
	
	public void setUrl(String url) {
		
		if(url == null) {
			throw new IllegalArgumentException("The url may not be set to null.");
		}
		
		//ドメイン制約
		if(!java.util.regex.Pattern.matches("^https?://[a-zA-Z0-9/:%#&~=_!'\\\\$\\\\?\\\\(\\\\)\\\\.\\\\+\\\\*\\\\-]+$", url)) {
			throw new IllegalArgumentException("The_url_format_is_invalid");
		}
		
		
		
		
		
		
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	@Override
	public Url clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Url(this.url);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Url url = (Url)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(url.getUrl().equals(this.getUrl())){
				equality = true;
			}
		}
		return equality;
	}

}
