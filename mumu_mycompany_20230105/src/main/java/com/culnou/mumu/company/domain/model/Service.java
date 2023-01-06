package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Service implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serviceId;
	private String serviceName;
	
	protected Service() {}
	
	protected Service(String serviceId, String serviceName) {
		this.setServiceId(serviceId);
		this.setServiceName(serviceName);
	}
	
	protected void setServiceId(String serviceId) {
		if(serviceId == null) {
			throw new IllegalArgumentException("The serviceId may not be set to null.");
		}
		this.serviceId = serviceId;
	}
	
	public String getServiceId() {
		return this.serviceId;
	}
	
	public void setServiceName(String serviceName) {
		if(serviceName == null) {
			throw new IllegalArgumentException("The serviceName may not be set to null.");
		}
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return this.serviceName;
	}
	
	@Override
	public Service clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Service(this.serviceId, this.serviceName);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Service service = (Service)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(service.getServiceId().equals(this.getServiceId()) && service.getServiceName().equals(this.getServiceName())){
				equality = true;
			}
		}
		return equality;
	}

}
