package com.culnou.mumu.company.domain.model;

import java.io.Serializable;

public class JobId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jobId;
	
	protected JobId() {}
	
	public JobId(String jobId) {
		this.setJobId(jobId);
	}
	
	protected void setJobId(String jobId) {
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		if(jobId.isEmpty()) {
			throw new IllegalArgumentException("Must provide a jobId.");
		}
		this.jobId = jobId;
	}
	
	public String jobId() {
		return jobId;
	}
	
	@Override
	public JobId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new JobId(this.jobId);
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			JobId jobId = (JobId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(jobId.jobId().equals(this.jobId())){
				equality = true;
			}
		}
		return equality;
	}

}
