package com.culnou.mumu.company.domain.model.activity.work;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberId;

import com.culnou.mumu.company.infrastructure.persistence.member.MemberJpaRepository;

@Service
@Transactional
public class WorkMemberAdapter {
	
	@Autowired
	private MemberJpaRepository memberService;
	
	protected Member findMemberOfId(String memberId) throws Exception{
		return memberService.findByMemberId(new MemberId(memberId));
	}
	
	

}
