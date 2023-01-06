package com.culnou.mumu.company.domain.model.member;



import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.common.Email;







@Service
@Transactional
public class MemberRegistry {
	
	@Qualifier("memberJpaRepository")
	@Autowired
	private MemberRepository repository;

	
	public MemberId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new MemberId(str);
	}
	
	public void registerMember(Member member) throws Exception{
		//エンティティ整合性が保持できているかチェックする
		//Java Validationを使う
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Member>> violations = validator.validate(member);
		if (!violations.isEmpty()) {
			throw new Exception("entity validation error.");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(member);
	}

	public void assignMember(Member member) throws Exception{
		repository.save(member);
	}
	
	public void removeMember(Member member) throws Exception{
		repository.remove(member);
	}
	
	
	//未使用
	public void compensateMember(MemberId memberId) throws Exception{
		if(memberId == null) {
			throw new Exception("The memberId may not be set to null.");
		}
		Member member = repository.findByMemberId(memberId);
		if(member == null) {
			throw new Exception("The_memberId_may_be_wrong");
		}
		/*
		if(member.getMemberState().equals(MemberState.Registered)) {
			throw new Exception("The member is not assigned.");
		}
		*/
		if(member.getMemberState().equals(MemberState.Released)) {
			throw new Exception("The_member_is_already_released");
		}
		
		member.setMemberState(MemberState.Registered);
		repository.save(member);
	}
	//解雇する。
	public void releaseMember(MemberId memberId) throws Exception{
		if(memberId == null) {
			throw new Exception("The memberId may not be set to null.");
		}
		Member member = repository.findByMemberId(memberId);
		if(member == null) {
			throw new Exception("The_memberId_may_be_wrong");
		}
		/*
		if(member.getMemberState().equals(MemberState.Registered)) {
			throw new Exception("The member is not assigned.");
		}
		*/
		if(member.getMemberState().equals(MemberState.Released)) {
			throw new Exception("The_member_is_already_released");
		}
		//解約するときユニークなEmailアドレスを設定し、それまでのEmailアドレスを再度登録可能にする。
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        Email email = new Email(str + "@mumu.com");
		member.setEmail(email);
		member.setMemberState(MemberState.Released);
		repository.save(member);
	}
	/*
	public List<Member> findMembersOfIndustryAndRole(String industryId, String roleId) throws Exception{
		return repository.findMembersOfAssignable(industryId, roleId);
	}
	*/
	
	public List<Member> findMembersOfRole(String roleId) throws Exception{
		return  repository.findMembersOfRole(roleId);
	}
	
	public List<Member> findMembersOfDepartment(String departmentId) throws Exception{
		return  repository.findMembersOfDepartment(departmentId);
	}
	
	public List<Member> findMembersOfProject(String projectId) throws Exception{
		return  repository.findMembersOfProject(projectId);
	}
	
	public List<Member> findMembersOfProgram(String programId) throws Exception{
		return  repository.findMembersOfProgram(programId);
	}
	
	public List<Member> findMembersOfMemberCategory(String memberCategoryId) throws Exception{
		return  repository.findMembersOfMemberCategory(memberCategoryId);
	}
	
	public Member findByMemberId(String memberId) throws Exception{
		return repository.findByMemberId(new MemberId(memberId));
	}
	
	public List<Member> findMembersOfEmail(String email, String companyId) throws Exception{
		return  repository.findMembersOfEmail(email, companyId);
	}
	
}
