package com.culnou.mumu.company.domain.model.common;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.ActionRepository;
import com.culnou.mumu.company.domain.model.DepartmentId;


@Service
@Transactional
public class ActionAdapter {
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository repository;
	
	public List<Action> findActionsOfDepartment(DepartmentId departmentId) throws Exception{
		return repository.actionsOfDepartment(departmentId);
	}
	
	public Action findActionOfId(ActionId actionId) throws Exception{
		return repository.actionOfId(actionId);
	}
	
	public void updateAction(Action action) throws Exception{
		repository.save(action);
	}

}
