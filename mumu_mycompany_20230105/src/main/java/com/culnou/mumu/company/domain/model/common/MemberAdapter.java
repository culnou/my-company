package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberId;
import com.culnou.mumu.company.domain.model.member.MemberRepository;

@Service
@Transactional
public class MemberAdapter {
	
	@Qualifier("memberJpaRepository")
	@Autowired
	private MemberRepository repository;
	
	public Member findMemberOfMemberId(MemberId memberId) throws Exception{
		return repository.findByMemberId(memberId);
	}

}
