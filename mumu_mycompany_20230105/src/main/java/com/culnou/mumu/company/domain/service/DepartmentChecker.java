package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionRepository;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentRepository;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberRegistry;



@Service
@Transactional
public class DepartmentChecker {
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository repository;
	@Autowired
	private MemberRegistry memberRegistry;
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	public String avarable(String departmentId) throws Exception{
		String message = "OK";
		Department department = repository.departmentOfId(new DepartmentId(departmentId));
		if(department.getAssociatedApplicationCategories().size() > 0) {
			message = "already_assigned_to_applicationCategory";
		}
		if(department.getAssociatedPartnerCategories().size() > 0) {
			message = "already_assigned_to_partnerCategory";
		}
		if(department.getGoals().size() > 0) {
			message = "already_assigned_to_goal";
		}
		if(department.getAchievements().size() > 0) {
			message = "already_assigned_to_achievement";
		}
		List<Member> members = memberRegistry.findMembersOfDepartment(departmentId);
		if(members.size() > 0) {
			message = "already_assigned_to_member";
		}
		List<Action> actions = actionRepository.actionsOfDepartment(new DepartmentId(departmentId));
		if(actions.size() > 0) {
			message = "already_assigned_to_action";
		}
		
		return message;
	}

}
