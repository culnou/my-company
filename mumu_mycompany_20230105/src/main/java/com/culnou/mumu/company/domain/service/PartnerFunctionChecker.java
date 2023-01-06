package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionRepository;

@Service
@Transactional
public class PartnerFunctionChecker {
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	public String avarable(String partnerFunctionId) throws Exception{
		String message = "OK";
		List<Action> actions = actionRepository.findActionsOfPartnerFunction(partnerFunctionId);
		if(actions.size() > 0) {
			message = "already_assigned_to_action";
		}
		return message;
	}

}
