package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractActionPlanRepository;
import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.project.ProjectId;

@Service("actionPlanJpaRepository")
@Transactional
public class ActionPlanJpaRepository extends AbstractActionPlanRepository {

	@Autowired
	private ActionPlanJpaDataRepository repository;
	
	/*
	 * 変換処理
	 */
	private ActionPlan convertEntityToPojo(ActionPlanEntity entity) {
		ActionPlan pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getBusinessUnitId() != null) {
			pojo.setBusinessUnitId(entity.getBusinessUnitId());
		}
		if(entity.getBusinessUnitName() != null) {
			pojo.setBusinessUnitName(entity.getBusinessUnitName());
		}
		if(entity.getCustomerCategoryId() != null) {
			pojo.setCustomerCategoryId(entity.getCustomerCategoryId());
		}
		if(entity.getCustomerCategoryName() != null) {
			pojo.setCustomerCategoryName(entity.getCustomerCategoryName());
		}
		if(entity.getHypothesis() != null) {
			pojo.setHypothesis(entity.getHypothesis());
		}
		if(entity.getResult() != null) {
			pojo.setResult(entity.getResult());
		}
		if(entity.getAssociatedProductCategories() != null) {
			pojo.setAssociatedProductCategories(entity.getAssociatedProductCategories());
		}
		if(entity.getAssociatedActions() != null) {
			pojo.setAssociatedActions(entity.getAssociatedActions());
		}
		if(entity.getActionPlanDescription() != null) {
			pojo.setActionPlanDescription(entity.getActionPlanDescription());
		}
		if(entity.getStartDate() != null) {
			pojo.setStartDate(entity.getStartDate());
		}
		if(entity.getEndDate() != null) {
			pojo.setEndDate(entity.getEndDate());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		
		
		
		if(entity.getProjectId() != null) {
			pojo.setProjectId(entity.getProjectId());
		}
		if(entity.getGoals() != null) {
			pojo.setGoals(entity.getGoals());
		}
		if(entity.getAchievements() != null) {
			pojo.setAchievements(entity.getAchievements());
		}
		
		return pojo;
	}
	
	private ActionPlanEntity convertPojoToEntity(ActionPlan pojo) {
		ActionPlanEntity entity = new ActionPlanEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setActionPlanId(pojo.getActionPlanId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setBusinessProcessId(pojo.getBusinessProcessId());
		entity.setBusinessProcessName(pojo.getBusinessProcessName());
		entity.setActionPlanType(pojo.getActionPlanType());
		entity.setActionPlanName(pojo.getActionPlanName());
		//任意項目
		if(pojo.getBusinessUnitId() != null) {
			entity.setBusinessUnitId(pojo.getBusinessUnitId());
		}
		if(pojo.getBusinessUnitName() != null) {
			entity.setBusinessUnitName(pojo.getBusinessUnitName());
		}
		if(pojo.getHypothesis() != null) {
			entity.setHypothesis(pojo.getHypothesis());
		}
		if(pojo.getResult() != null) {
			entity.setResult(pojo.getResult());
		}
		if(pojo.getCustomerCategoryId() != null) {
			entity.setCustomerCategoryId(pojo.getCustomerCategoryId());
		}
		if(pojo.getCustomerCategoryName() != null) {
			entity.setCustomerCategoryName(pojo.getCustomerCategoryName());
		}
		if(pojo.getAssociatedProductCategories() != null) {
			entity.setAssociatedProductCategories(pojo.getAssociatedProductCategories());
		}
		if(pojo.getAssociatedActions() != null) {
			entity.setAssociatedActions(pojo.getAssociatedActions());
		}
		if(pojo.getActionPlanDescription() != null) {
			entity.setActionPlanDescription(pojo.getActionPlanDescription());
		}
		if(pojo.getStartDate() != null) {
			entity.setStartDate(pojo.getStartDate());
		}
		if(pojo.getEndDate() != null) {
			entity.setEndDate(pojo.getEndDate());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getProjectId() != null) {
			entity.setProjectId(pojo.getProjectId());
		}
		if(pojo.getGoals() != null) {
			entity.setGoals(pojo.getGoals());
		}
		if(pojo.getAchievements() != null) {
			entity.setAchievements(pojo.getAchievements());
		}
		return entity;
	}
	
	private List<ActionPlan> convertEntitiesToPojos(List<ActionPlanEntity> entities){
		List<ActionPlan> pojos = new ArrayList<>();
		for(ActionPlanEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}
	
	@Override
	public ActionPlanId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new ActionPlanId(str);
	}

	@Override
	public void save(ActionPlan actionPlan) throws Exception {
		// TODO Auto-generated method stub
		if(actionPlan == null) {
			throw new IllegalArgumentException("The actionPlan may not be set to null.");
		}
        repository.save(this.convertPojoToEntity(actionPlan));
	}

	@Override
	public void remove(ActionPlan actionPlan) throws Exception {
		// TODO Auto-generated method stub
		if(actionPlan == null) {
			throw new IllegalArgumentException("The actionPlan may not be set to null.");
		}
        repository.delete(this.convertPojoToEntity(actionPlan));

	}

	@Override
	public ActionPlan actionPlanOfId(ActionPlanId actionPlanId) throws Exception {
		// TODO Auto-generated method stub
		if(actionPlanId == null) {
			throw new IllegalArgumentException("The actionPlanId may not be set to null.");
		}
		ActionPlanEntity entity = repository.findByActionPlanId(actionPlanId);
		if(entity == null) {
			throw new IllegalArgumentException("The actionPlanEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<ActionPlan> actionPlansOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ActionPlanEntity> entities = repository.findActionPlansOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<ActionPlan> actionPlansOfBusinessProcess(BusinessProcessId businessProcessId) throws Exception {
		// TODO Auto-generated method stub
		if(businessProcessId == null) {
			throw new IllegalArgumentException("The businessProcessId may not be set to null.");
		}
		List<ActionPlanEntity> entities = repository.findActionPlansOfBusinessProcess(businessProcessId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<ActionPlan> actionPlansOfAction(String actionId) throws Exception {
		// TODO Auto-generated method stub
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		List<ActionPlanEntity> entities = repository.findActionPlansOfAction(actionId);
		return this.convertEntitiesToPojos(entities);
	}
	
	@Override
	public List<ActionPlan> actionPlansOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception {
		// TODO Auto-generated method stub
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		List<ActionPlanEntity> entities = repository.findActionPlansOfBusinessUnit(businessUnitId);
		return this.convertEntitiesToPojos(entities);
	}
	
	@Override
	public List<ActionPlan> actionPlansOfProject(ProjectId projectId) throws Exception {
		// TODO Auto-generated method stub
		if(projectId == null) {
			throw new IllegalArgumentException("The projectId may not be set to null.");
		}
		List<ActionPlanEntity> entities = repository.findActionPlansOfProject(projectId);
		return this.convertEntitiesToPojos(entities);
	}

}
