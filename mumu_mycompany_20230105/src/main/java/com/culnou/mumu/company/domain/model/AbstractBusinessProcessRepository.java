package com.culnou.mumu.company.domain.model;


import com.culnou.mumu.company.infrastructure.persistence.BusinessProcessEntity;

public abstract class AbstractBusinessProcessRepository implements BusinessProcessRepository {
	
	//コンストラクターは外部から呼び出せないようにProtectedにしているため本メソッドが必要。
	public BusinessProcess convertFromEntity(BusinessProcessEntity entity) {
		BusinessProcessId businessProcessId = entity.getBusinessProcessId();
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId of BusinessProcessEntity may not be set to null.");
		}
		CompanyId companyId = entity.getCompanyId();
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId of BusinessProcessEntity may not be set to null.");
		}
		BusinessProcessType businessProcessType = entity.getBusinessProcessType();
		if(businessProcessType == null) {
			throw new IllegalArgumentException("The businessProcessType of BusinessProcessEntity may not be set to null.");
		}
		String businessProcessName = entity.getBusinessProcessName();
		if(businessProcessName == null) {
			throw new IllegalArgumentException("The businessProcessName of BusinessProcessEntity may not be set to null.");
		}
		return new BusinessProcess(businessProcessId, companyId, businessProcessType, businessProcessName);
	}

}
