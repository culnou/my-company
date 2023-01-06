package com.culnou.mumu.company.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.member.MemberService;
import com.culnou.mumu.compnay.controller.MemberDto;

@Service
@Transactional
public class MemberChecker {
	@Autowired
	private MemberService service;
	
	public String releaseAvarable(String memberId) throws Exception{
		String message = "OK";
		MemberDto member = service.findMemberOfMemberId(memberId);
		if(member == null) {
			throw new IllegalArgumentException("The_memberId_may_not_be_set_to_null");
		}
		if(member.getProjectId() != null && !member.getProjectId().isEmpty()) {
			message = "already_assigned_to_projects";
		}
		if(member.getProgramId() != null && !member.getProgramId().isEmpty()) {
			message = "already_assigned_to_programs";
		}
		
		return message;
	}

}
