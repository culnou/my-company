package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractBusinessProcessRepository;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.CompanyId;

@Service("businessProcessJpaRepository")
@Transactional
public class BusinessProcessJpaRepository extends AbstractBusinessProcessRepository {
	
	@Autowired
	private BusinessProcessJpaDataRepository repository;
	
	/*
	 * 変換処理
	 */
	private BusinessProcess convertEntityToPojo(BusinessProcessEntity entity) {
		BusinessProcess pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getBusinessDomainId() != null) {
			pojo.setBusinessDomainId(entity.getBusinessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			pojo.setBusinessDomainName(entity.getBusinessDomainName());
		}
		if(entity.getAssociatedTasks() != null) {
			pojo.setAssociatedTasks(entity.getAssociatedTasks());

		}
		if(entity.getBusinessProcessDescription() != null) {
			pojo.setBusinessProcessDescription(entity.getBusinessProcessDescription());
		}
		if(entity.getBusinessProcessClass() != null) {
			pojo.setBusinessProcessClass(entity.getBusinessProcessClass());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getIndicators() != null) {
			pojo.setIndicators(entity.getIndicators());
		}
		if(entity.getCreatedAt() != null) {
			pojo.setCreatedAt(entity.getCreatedAt());
	    }
	    if(entity.getUpdatedAt() != null) {
	    	pojo.setUpdatedAt(entity.getUpdatedAt());
	    }
		
		return pojo;
	}
	
	private BusinessProcessEntity convertPojoToEntity(BusinessProcess pojo) {
		BusinessProcessEntity entity = new BusinessProcessEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setBusinessProcessId(pojo.getBusinessProcessId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setBusinessProcessType(pojo.getBusinessProcessType());
		entity.setBusinessProcessName(pojo.getBusinessProcessName());
		//任意項目
		if(pojo.getBusinessDomainId() != null) {
			entity.setBusinessDomainId(pojo.getBusinessDomainId());
		}
		if(pojo.getBusinessDomainName() != null) {
			entity.setBusinessDomainName(pojo.getBusinessDomainName());
		}
		if(pojo.getAssociatedTasks() != null) {
			entity.setAssociatedTasks(pojo.getAssociatedTasks());
		}
		if(pojo.getBusinessProcessDescription() != null) {
			entity.setBusinessProcessDescription(pojo.getBusinessProcessDescription());
		}
		if(pojo.getBusinessProcessClass() != null) {
			entity.setBusinessProcessClass(pojo.getBusinessProcessClass());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getIndicators() != null) {
			entity.setIndicators(pojo.getIndicators());
		}
		if(pojo.getCreatedAt() != null) {
			entity.setCreatedAt(pojo.getCreatedAt());
		}
		if(pojo.getUpdatedAt() != null) {
			entity.setUpdatedAt(pojo.getUpdatedAt());
		}
		return entity;
	}
	
	private List<BusinessProcess> convertEntitiesToPojos(List<BusinessProcessEntity> entities){
		List<BusinessProcess> pojos = new ArrayList<>();
		for(BusinessProcessEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}

	@Override
	public BusinessProcessId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new BusinessProcessId(str);
	}

	@Override
	public void save(BusinessProcess businessProcess) throws Exception {
		// TODO Auto-generated method stub
		if(businessProcess == null) {
			throw new IllegalArgumentException("The businessProcess may not be set to null.");
		}
        repository.save(this.convertPojoToEntity(businessProcess));

	}

	@Override
	public void remove(BusinessProcess businessProcess) throws Exception {
		// TODO Auto-generated method stub
		if(businessProcess == null) {
			throw new IllegalArgumentException("The businessProcess may not be set to null.");
		}
        repository.delete(this.convertPojoToEntity(businessProcess));
	}

	@Override
	public BusinessProcess businessProcessOfId(BusinessProcessId businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		BusinessProcessEntity entity = repository.findByBusinessProcessId(businessProcessId);
		if(entity == null) {
			throw new IllegalArgumentException("The businessProcessEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<BusinessProcess> businessProcessesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The CompanyId may not be set to null.");
		}
		List<BusinessProcessEntity> entities = repository.findBusinessProcessesOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}
	
	public List<BusinessProcess> businessProcessesOfBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		if(businessDomainId == null) {
			throw new IllegalArgumentException("The businessDomainId may not be set to null.");
		}
		List<BusinessProcessEntity> entities = repository.findBusinessProcessesOfBusinessDomain(businessDomainId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<BusinessProcess> businessProcessesOfTask(String taskId) throws Exception {
		// TODO Auto-generated method stub
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		List<BusinessProcessEntity> entities = repository.findBusinessProcessesOfTask(taskId);
		return this.convertEntitiesToPojos(entities);
	}

}
