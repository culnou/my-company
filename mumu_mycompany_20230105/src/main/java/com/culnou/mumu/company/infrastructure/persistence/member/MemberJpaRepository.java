package com.culnou.mumu.company.infrastructure.persistence.member;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberId;
import com.culnou.mumu.company.domain.model.member.MemberRepository;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;



@Service("memberJpaRepository")
@Transactional
public class MemberJpaRepository implements MemberRepository {
	
	@Autowired
	private MemberJpaDataRepository repository;

	@Override
	public void save(Member member) throws Exception {
		// TODO Auto-generated method stub
		repository.save(member);
		
	}

	@Override
	public Member findByMemberId(MemberId memberId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByMemberId(memberId);
	}

	@Override
	public void remove(Member member) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(member);
		
	}
    /*
	@Override
	public List<Member> findMembersOfAssignable(String industryId, String roleId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersByIndustryAndRole(industryId, roleId);
		
	}
	*/

	@Override
	public List<Member> findMembersOfRole(String roleId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersOfRole(roleId);
	}

	@Override
	public List<Member> findMembersOfDepartment(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersOfDepartment(departmentId);
	}
	
	@Override
	public List<Member> findMembersOfProject(String projectId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersOfProject(projectId);
	}
	
	@Override
	public List<Member> findMembersOfProgram(String programId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersOfProgram(programId);
	}

	@Override
	public List<Member> findMembersOfMemberCategory(String memberCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersOfMemberCategory(new MemberCategoryId(memberCategoryId));
	}

	@Override
	public List<Member> findMembersOfEmail(String email, String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMembersOfEmail(new Email(email), companyId);
	}

	
	

}
