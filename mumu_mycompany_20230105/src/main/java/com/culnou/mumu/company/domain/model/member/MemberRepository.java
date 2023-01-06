package com.culnou.mumu.company.domain.model.member;

import java.util.List;

public interface MemberRepository {

	public void save(Member member) throws Exception;
	
	
	
	public Member findByMemberId(MemberId memberId) throws Exception;
	
	public void remove(Member member) throws Exception;
	
	//public List<Member> findMembersOfAssignable(String industryId, String roleId) throws Exception;
	
	public List<Member> findMembersOfRole(String roleId) throws Exception;
	
	public List<Member> findMembersOfDepartment(String departmentId) throws Exception;
	
	public List<Member> findMembersOfProject(String projectId) throws Exception;
	
	public List<Member> findMembersOfProgram(String programId) throws Exception;
	
	public List<Member> findMembersOfMemberCategory(String memberCategoryId) throws Exception;
	
	public List<Member> findMembersOfEmail(String email, String companyId) throws Exception;
		
}
