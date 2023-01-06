package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractActionRepository;
import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.TaskId;

@Service("actionJpaRepository")
@Transactional
public class ActionJpaRepository extends AbstractActionRepository {
	
	@Autowired
	private ActionJpaDataRepository repository;

	/*
	 * 変換処理
	 */
	private Action convertEntityToPojo(ActionEntity entity) {
		Action pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getActionDescription() != null) {
			pojo.setActionDescription(entity.getActionDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getApplicationUrl() != null) {
			pojo.setApplicationUrl(entity.getApplicationUrl());
		}
		if(entity.getApplicationProductId() != null) {
			pojo.setApplicationProductId(entity.getApplicationProductId());
		}
		if(entity.getPersonUrl() != null) {
			pojo.setPersonUrl(entity.getPersonUrl());
		}
		if(entity.getPersonProductId() != null) {
			pojo.setPersonProductId(entity.getPersonProductId());
		}
		if(entity.getApplicationCategoryId() != null) {
			pojo.setApplicationCategoryId(entity.getApplicationCategoryId());
		}
		if(entity.getPartnerCategoryId() != null) {
			pojo.setPartnerCategoryId(entity.getPartnerCategoryId());
		}
		
		return pojo;
	}
	
	private ActionEntity convertPojoToEntity(Action pojo) {
		ActionEntity entity = new ActionEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setActionId(pojo.getActionId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setTaskId(pojo.getTaskId());
		entity.setTaskName(pojo.getTaskName());
		entity.setDepartmentId(pojo.getDepartmentId());
		entity.setDepartmentName(pojo.getDepartmentName());
		entity.setActionName(pojo.getActionName());
		//任意項目
		if(pojo.getActionDescription() != null) {
			entity.setActionDescription(pojo.getActionDescription());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getApplicationUrl() != null) {
			entity.setApplicationUrl(pojo.getApplicationUrl());
		}
		if(pojo.getApplicationProductId() != null) {
			entity.setApplicationProductId(pojo.getApplicationProductId());
		}
		if(pojo.getPersonUrl() != null) {
			entity.setPersonUrl(pojo.getPersonUrl());
		}
		if(pojo.getPersonProductId() != null) {
			entity.setPersonProductId(pojo.getPersonProductId());
		}
		if(pojo.getApplicationCategoryId() != null) {
			entity.setApplicationCategoryId(pojo.getApplicationCategoryId());
		}
		if(pojo.getPartnerCategoryId() != null) {
			entity.setPartnerCategoryId(pojo.getPartnerCategoryId());
		}
		return entity;
	}
	
	private List<Action> convertEntitiesToPojos(List<ActionEntity> entities){
		List<Action> pojos = new ArrayList<>();
		for(ActionEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}
	
	@Override
	public ActionId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ActionId(str);
	}

	@Override
	public void save(Action action) throws Exception {
		// TODO Auto-generated method stub
		if(action == null) {
			throw new IllegalArgumentException("The action may not be set to null.");
		}
        repository.save(this.convertPojoToEntity(action));

	}

	@Override
	public void remove(Action action) throws Exception {
		// TODO Auto-generated method stub
		if(action == null) {
			throw new IllegalArgumentException("The action may not be set to null.");
		}
        repository.delete(this.convertPojoToEntity(action));
	}

	@Override
	public Action actionOfId(ActionId actionId) throws Exception {
		// TODO Auto-generated method stub
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		ActionEntity entity = repository.findByActionId(actionId);
		if(entity == null) {
			throw new IllegalArgumentException("The actionEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<Action> actionsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ActionEntity> entities = repository.findActionsOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Action> actionsOfDepartment(DepartmentId departmentId) throws Exception {
		// TODO Auto-generated method stub
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId may not be set to null.");
		}
		List<ActionEntity> entities = repository.findActionsOfDepartment(departmentId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Action> findActionsOfTask(TaskId taskId) {
		// TODO Auto-generated method stub
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		List<ActionEntity> entities = repository.findActionsOfTask(taskId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Action> findActionsOfApplicationFunction(String applicationFunctionId) throws Exception {
		// TODO Auto-generated method stub
		if(applicationFunctionId == null) {
			throw new IllegalArgumentException("The applicationFunctionId may not be set to null.");
		}
		List<ActionEntity> entities = repository.findActionsOfApplicationFunction(applicationFunctionId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Action> findActionsOfPartnerFunction(String partnerFunctionId) throws Exception {
		// TODO Auto-generated method stub
		if(partnerFunctionId == null) {
			throw new IllegalArgumentException("The partnerFunctionId may not be set to null.");
		}
		List<ActionEntity> entities = repository.findActionsOfPartnerFunction(partnerFunctionId);
		return this.convertEntitiesToPojos(entities);
	}

}
