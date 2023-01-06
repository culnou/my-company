package com.culnou.mumu.company.infrastructure.persistence.member;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;




public interface MemberJpaDataRepository extends JpaRepository<Member, MemberId> {
	
	public Member findByMemberId(MemberId memberId);
	/*
	@Query(value = "select member1 from Member member1 where member1.industryId = :IndustryID AND member1.roleId = :RoleID")
	List<Member> findMembersByIndustryAndRole(@Param("IndustryID") String industryId, @Param("RoleID") String roleId);
    */
	@Query(value = "select member1 from Member member1 join member1.associatedRoles role where role.roleId = :RoleId")
	public List<Member> findMembersOfRole(@Param("RoleId") String roleId);
	
	@Query(value = "select member1 from Member member1 where member1.departmentId = :DepartmentId")
	public List<Member> findMembersOfDepartment(@Param("DepartmentId") String departmentId);
	
	@Query(value = "select member1 from Member member1 where member1.projectId = :ProjectId")
	public List<Member> findMembersOfProject(@Param("ProjectId") String projectId);
	
	@Query(value = "select member1 from Member member1 where member1.programId = :ProgramId")
	public List<Member> findMembersOfProgram(@Param("ProgramId") String programId);
	
	@Query(value = "select member1 from Member member1 where member1.memberCategoryId = :MemberCategoryId")
	public List<Member> findMembersOfMemberCategory(@Param("MemberCategoryId") MemberCategoryId memberCategoryId);
	
	@Query(value = "select member1 from Member member1 where member1.email = :Email and member1.companyId = :CompanyId")
	public List<Member> findMembersOfEmail(@Param("Email") Email email, @Param("CompanyId") String companyId);
	
}
