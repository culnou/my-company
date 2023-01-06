package com.culnou.mumu.company.domain.model.member;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.application.CompanyService;
import com.culnou.mumu.company.application.MemberCategoryApplicationService;
import com.culnou.mumu.company.domain.model.Company;
import com.culnou.mumu.company.domain.model.CompanyRepository;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DomainName;
import com.culnou.mumu.company.domain.model.activity.action_plan.AssociatedActionPlan;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.common.FullName;
import com.culnou.mumu.company.domain.model.common.PersonId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;
import com.culnou.mumu.company.domain.model.program.Program;
import com.culnou.mumu.company.domain.model.project.Project;

import com.culnou.mumu.compnay.controller.ActionDto;
import com.culnou.mumu.compnay.controller.ActionPlanDto;
import com.culnou.mumu.compnay.controller.BusinessUnitDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.DepartmentDto;
import com.culnou.mumu.compnay.controller.MemberCategoryDto;
import com.culnou.mumu.compnay.controller.MemberDto;
import com.culnou.mumu.compnay.controller.MemberSignInDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.RoleDto;





@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberRegistry memberRegistry;
	@Autowired
	private MarketMemberAdapter adapter;
	@Autowired
	private DepartmentServiceAdapter departmentAdapter;
	@Autowired
	private MemberProjectAdapter projectAdapter;
	@Autowired
	private MemberProgramAdapter programAdapter;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private MemberCategoryApplicationService memberCategoryService;
	@Qualifier("companyJpaRepository")
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	//会社のメンバーカテゴリにメンバーを登録する。2022/9/22
	//会社のメンバーの場合、市場のメンバーと異なりIndustryとRoleは無関係。
	public MessageDto registerMember(MemberDto memberDto){
		MessageDto msg = new MessageDto();
		try {
			//事前条件の検証
			if(memberDto.getMemberCategoryId() == null || memberDto.getMemberCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
			}
			
			if(memberDto.getCompanyId() == null || memberDto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			CompanyDto company = companyService.findCompanyById(memberDto.getCompanyId());
			if(company == null) {
				throw new IllegalArgumentException("The_company_is_not_exist");
			}
			if(memberDto.getEmail() == null || memberDto.getEmail().isEmpty()) {
				throw new IllegalArgumentException("The_email_may_not_be_set_to_null");
			}
			if(memberDto.getFirstName() == null || memberDto.getFirstName().isEmpty()) {
				throw new IllegalArgumentException("The_firstName_may_not_be_set_to_null");
			}
			if(memberDto.getLastName() == null || memberDto.getLastName().isEmpty()) {
				throw new IllegalArgumentException("The_lastName_may_not_be_set_to_null");
			}
			if(memberDto.getPassword() == null || memberDto.getPassword().isEmpty()) {
				throw new IllegalArgumentException("The_password_may_not_be_set_to_null");
			}
			if(memberDto.getMemberClass() == null) {
				throw new IllegalArgumentException("The_memberClass_may_not_be_set_to_null");
			}
			List<Member> members = memberRegistry.findMembersOfEmail(memberDto.getEmail(), memberDto.getCompanyId());
			if(members.size() > 0) {
				throw new IllegalArgumentException("The_email_member_is_already_exist");
			}
			Member member = new Member();
			member.setMemberId(memberRegistry.nextIdentity());
			//PersonIdをユニークにするためにUUIDであるメンバーIDをPersonIdにする。
			member.setPersonId(new PersonId(member.getMemberId().getMemberId()));
			member.setCompanyId(memberDto.getCompanyId());
			member.setMemberName(new FullName(memberDto.getFirstName(), memberDto.getLastName()));
			member.setEmail(new Email(memberDto.getEmail()));
			member.setMemberCategoryId(new MemberCategoryId(memberDto.getMemberCategoryId()));
			//Industryは必須なので存在しない値を設定する。2022/9/22
			member.setIndustryId("9999");
			member.setMemberDescription(memberDto.getMemberDescription());
			member.setPassword(memberDto.getPassword());
			member.setMemberClass(memberDto.getMemberClass());
			member.setMemberState(MemberState.Registered);
			memberRegistry.registerMember(member);
			msg.setReturnValue(member.getMemberId().getMemberId());
			msg.setResult("OK");
		}catch(Exception ex) {
			msg.setResult("NG");
			msg.setErrorMessage(ex.getMessage());
			return msg;
		}
		return msg;
	}
	
	
	public MessageDto updateMember(MemberDto memberDto){
		MessageDto msg = new MessageDto();
		try {
			//事前条件の検証
			if(memberDto.getMemberId() == null || memberDto.getMemberId().isEmpty()) {
				throw new IllegalArgumentException("The_memberId_may_not_be_set_to_null");
			}
			Member member = memberRegistry.findByMemberId(memberDto.getMemberId());
			if(member == null) {
				throw new IllegalArgumentException("The_member_is_not_exist");
			}
			if(memberDto.getEmail() == null || memberDto.getEmail().isEmpty()) {
				throw new IllegalArgumentException("The_email_may_not_be_set_to_null");
			}
			if(memberDto.getFirstName() == null || memberDto.getFirstName().isEmpty()) {
				throw new IllegalArgumentException("The_firstName_may_not_be_set_to_null");
			}
			if(memberDto.getLastName() == null || memberDto.getLastName().isEmpty()) {
				throw new IllegalArgumentException("The_lastName_may_not_be_set_to_null");
			}
			if(memberDto.getPassword() == null || memberDto.getPassword().isEmpty()) {
				throw new IllegalArgumentException("The_password_may_not_be_set_to_null");
			}
			if(memberDto.getMemberClass() == null) {
				throw new IllegalArgumentException("The_memberClass_may_not_be_set_to_null");
			}
			
			
			if(!member.getMemberName().firstName().equals(memberDto.getFirstName())|| !member.getMemberName().lastName().equals(memberDto.getLastName())) {
				member.setMemberName(new FullName(memberDto.getFirstName(), memberDto.getLastName()));
			}
			if(!member.getEmail().address().equals(memberDto.getEmail())) {
				member.setEmail(new Email(memberDto.getEmail()));
			}
			if(!member.getMemberDescription().equals(memberDto.getMemberDescription())) {
				member.setMemberDescription(memberDto.getMemberDescription());
			}
			if(!member.getPassword().equals(memberDto.getPassword())) {
				member.setPassword(memberDto.getPassword());
			}
			if(!member.getMemberClass().equals(memberDto.getMemberClass())){
				member.setMemberClass(memberDto.getMemberClass());
			}
			memberRegistry.registerMember(member);
			msg.setResult("OK");
		}catch(Exception ex) {
			msg.setResult("NG");
			msg.setErrorMessage(ex.getMessage());
			return msg;
		}
		return msg;
	}
	

	
	
	
	
	//市場のメンバーを部門に割当てる。
	public MessageDto assignMemberToDepartment(MemberDto member, String departmentId, String companyId){
		MessageDto msg = new MessageDto();
		//事前条件の検証
		//部門が存在しているか検証する
		try {
			Department department = departmentAdapter.findDepartmentOfId(new DepartmentId(departmentId));
			if(department == null) {
				msg.setResult("NG");
				msg.setErrorMessage("The_department_may_not_exist");
			}else {
				//市場のメンバーを部門に割り当てる。
				//REST/HTTP
				/*
				MessageDto message = adapter.assignMemberToDepartment(member.getMemberId(), departmentId, companyId);
				//メンバーを会社の部門に割り当てて保存する。
				if(message.getResult().equals("OK")) {
					member.setDepartmentId(departmentId);
					member.setCompanyId(department.getCompanyId().id());
					member.setMemberState(MemberState.Assigned);
					member.setPassword(member.getEmail());
					memberRegistry.assignMember(this.convertMemberDto(member));
					msg.setResult("OK");
				}else {
					msg.setResult("NG");
					msg.setErrorMessage("You_could_not_assign_member_to_the_department");
				}
				*/
				//JMS非同期メッセージング
				//DBアクセス
				member.setDepartmentId(departmentId);
				member.setCompanyId(department.getCompanyId().id());
				member.setMemberState(MemberState.Assigned);
				member.setPassword(member.getEmail());
				memberRegistry.assignMember(this.convertMemberDto(member));
				
				//Topicアクセス
				//DBアクセスに失敗したら送信されない。
				MemberAssignedToDepartment memberState = new MemberAssignedToDepartment();
				memberState.setCompanyId(companyId);
				memberState.setDepartmentId(departmentId);
				memberState.setMemberId(member.getMemberId());
				jmsTemplate.convertAndSend("Member.AssignedToDepartment.Queue", memberState);
				
				msg.setResult("OK");
				
			}//if department null
		}catch(Exception ex) {
			/*
			//市場のメンバーを戻す。Compensation
			MessageDto message = adapter.compensateMember(member.getMemberId());
			try {
				memberRegistry.removeMember(this.convertMemberDto(member));
			}catch(Exception e){
				e.printStackTrace();
			}
			*/
			msg.setResult("NG");
			msg.setErrorMessage("You_could_not_assign_member_to_the_department");
		}//try
		return msg;
		
	}
	
	//会社のメンバーを部門に割当てる。
	public MessageDto assignMemberOfCompanyToDepartment(MemberDto member, String departmentId, String companyId) {
		MessageDto msg = new MessageDto();
		//事前条件の検証
		try {
			if(member == null) {
				throw new IllegalArgumentException("The_member_may_not_be_set_to_null");
			}
			if(departmentId == null || departmentId.isEmpty()) {
				throw new IllegalArgumentException("The_departmentId_may_not_be_set_to_null");
			}
			if(companyId == null || companyId.isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			Department department = departmentAdapter.findDepartmentOfId(new DepartmentId(departmentId));
			if(department == null) {
				throw new IllegalArgumentException("The_department_may_not_exist");
			}
			//会社のメンバーを部門に割り当てる。
			member.setDepartmentId(departmentId);
			member.setCompanyId(department.getCompanyId().id());
			member.setMemberState(MemberState.Assigned);
			memberRegistry.assignMember(this.convertMemberDto(member));
			msg.setResult("OK");
		}catch(Exception ex) {
			msg.setResult("NG");
			msg.setErrorMessage(ex.getMessage());
			return msg;
		}//try
		return msg;
		
	}
	
	/*
	 * メンバーを部門から解除する。
	 * memberStateをRegisterdにする。
	 */
	public MessageDto releaseDepartmentMember(String memberId) {
		MessageDto message = new MessageDto();
		try {
			if(memberId == null || memberId.isEmpty()){
				throw new IllegalArgumentException("The_memberId_may_not_be_set_to_null");
			}
			Member member = memberRegistry.findByMemberId(memberId);
			if(member == null) {
				throw new IllegalArgumentException("The_member_is_not_exist");
			}
			if(member.getProjectId() != null && !member.getProjectId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_already_assigned");
			}
			if(member.getProgramId() != null && !member.getProgramId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_already_assigned");
			}
			if(member.getDepartmentId() == null || member.getDepartmentId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_not_assigned");
			}
			member.setDepartmentId(null);
			member.setMemberState(MemberState.Registered);
			//会社のメンバーの場合、registerMemberでindustryが9999になっているので、それで識別する。
			MessageDto msg = new MessageDto();
			msg.setResult("OK");
			//市場のメンバーの場合
			if(!member.getIndustryId().equals("9999")){
				//REST/HTTP
				/*
				msg = adapter.releaseMember(memberId);
				//解雇状態にする。
				if(msg.getResult().equals("OK")) {
					memberRegistry.releaseMember(new MemberId(memberId));
				}
				*/
	            //JMS非同期メッセージング			
				//DBアクセス
				memberRegistry.releaseMember(new MemberId(memberId));
				//Topicアクセス
				//DBアクセスに失敗したら送信されない。
				MemberReleased memberState = new MemberReleased();
				memberState.setMemberId(memberId);
				jmsTemplate.convertAndSend("Member.Released.Queue", memberState);
			//会社のメンバーの場合
			}else {
			    if(msg.getResult().equals("OK")) {
			    	memberRegistry.registerMember(member);
				}
			}
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		message.setResult("OK");
		return message;
	}
	
	
	/*
	 * メンバーをプロジェクトから解除する
	 * プログラムの解除は別途開発する
	 */
	public MessageDto releaseProjectMember(String memberId) {
		MessageDto message = new MessageDto();
		try {
			
			if(memberId == null || memberId.isEmpty()){
				throw new IllegalArgumentException("The_memberId_may_not_be_set_to_null");
			}
			Member member = memberRegistry.findByMemberId(memberId);
			if(member == null) {
				throw new IllegalArgumentException("The_member_is_not_exist");
			}
			if(member.getDepartmentId() == null || member.getDepartmentId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_not_assigned");
			}
			if(member.getProjectId() == null || member.getProjectId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_not_assigned");
			}
			if(member.getProgramId() != null && member.getProgramId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_already_assigned");
			}
			member.setProjectId(null);
			member.setMemberState(MemberState.Registered);
			memberRegistry.registerMember(member);
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		message.setResult("OK");
		return message;
	}
	
	public MessageDto releaseProgramMember(String memberId) {
		MessageDto message = new MessageDto();
		try {
			if(memberId == null || memberId.isEmpty()){
				throw new IllegalArgumentException("The_memberId_may_not_be_set_to_null");
			}
			Member member = memberRegistry.findByMemberId(memberId);
			if(member == null) {
				throw new IllegalArgumentException("The_member_is_not_exist");
			}
			if(member.getDepartmentId() == null || member.getDepartmentId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_not_assigned");
			}
			if(member.getProgramId() == null || member.getProgramId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_not_assigned");
			}
			if(member.getProgramId() != null && member.getProgramId().isEmpty()) {
				throw new IllegalArgumentException("The_member_is_already_assigned");
			}
			member.setProgramId(null);
			member.setMemberState(MemberState.Registered);
			memberRegistry.registerMember(member);
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		message.setResult("OK");
		return message;
	}
	
	//メンバーの解約
	public MessageDto releaseMember(String memberId) {
		MessageDto message = new MessageDto();
		try {
			memberRegistry.releaseMember(new MemberId(memberId));
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		message.setResult("OK");
		return message;
	}
	/*
	public List<MemberDto> findMembersOfAssignable(String industryId, String roleId) throws Exception{
		List<Member> members = memberRegistry.findMembersOfIndustryAndRole(industryId, roleId);
		List<MemberDto> memberDtos = new ArrayList<>();
		for(Member member : members) {
			if(member.getMemberState().equals(MemberState.Registered)) {
				MemberDto dto = new MemberDto();
				dto.setMemberId(member.getMemberId().getMemberId());
				dto.setMemberName(member.getMemberName());
				dto.setPersonId(member.getPersonId());
				dto.setIndustryId(member.getIndustryId());
				//dto.setRoleId(member.getRoleId());
				dto.setMemberDescription(member.getMemberDescription());
				dto.setUrl(member.getUrl());
				dto.setMemerState(member.getMemberState());
				memberDtos.add(dto);
			}
		}
		return memberDtos;
	}
	*/
	
	public MessageDto memberSignIn(MemberSignInDto signInDto) {
		MessageDto message = new MessageDto();
		try {
			if(signInDto.getCompanyDomainName() == null) {
				throw new IllegalArgumentException("The_domain_name_may_not_be_set_to_null");
			}
			if(signInDto.getCompanyDomainName().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_domain_names");
			}
			if(signInDto.getCompanyPassword() == null) {
				throw new IllegalArgumentException("The_companyPassword_may_not_be_set_to_null");
			}
			if(signInDto.getCompanyPassword().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_company_password");
			}
			if(signInDto.getMemberAddress() == null || signInDto.getMemberAddress().isEmpty()) {
				throw new IllegalArgumentException("The_email_may_not_be_set_to_null");
			}
			if(signInDto.getMemberPassword() == null || signInDto.getMemberPassword().isEmpty()) {
				throw new IllegalArgumentException("The_password_may_not_be_set_to_null");
			}
			List<Company> companies = companyRepository.companiesOfDomainName(new DomainName(signInDto.getCompanyDomainName()));
            if(companies.size() != 1) {
            	throw new Exception("The_company_is_not_exist");
            }
            Company company = companies.get(0);
            if(!company.companyPassword().equals(signInDto.getCompanyPassword())) {
            	throw new Exception("The_companyPassword_is_wrong");
            }
            List<Member> members = memberRegistry.findMembersOfEmail(signInDto.getMemberAddress(), company.companyId().id());
            if(members.size() != 1) {
            	throw new Exception("The_member_is_not_exist");
            }
            Member member = members.get(0);
            if(!member.getPassword().equals(signInDto.getMemberPassword())) {
            	throw new Exception("wrong_employee_password_message");
            }
            message.setReturnValue(company.companyId().id());
            message.setLoginAddress(member.getEmail().address());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		message.setResult("OK");
		return message;
	}
	
	//部門にアサイン可能は会社のメンバーを探す。
	//部門と同じ事業単位のメンバーカテゴリーのメンバーを探す。
	public List<MemberDto> findAssignableMembersOfCompany(String departmentId){
		List<MemberDto> memberDtos = new ArrayList<>();
		try {
			DepartmentDto department = companyService.findDepartmentById(departmentId);
			BusinessUnitDto bu = companyService.findBusinessUnitById(department.getBusinessUnitId());
			List<MemberCategoryDto> memberCategoreis = memberCategoryService.findMemberCategoriesOfBusinessUnit(bu.getBusinessUnitId());
			for(MemberCategoryDto memberCategory : memberCategoreis) {
				//アサインされていないメンバーのみ選択する。
				List<MemberDto> members = this.findMembersOfMemberCategory(memberCategory.getMemberCategoryId());
				for(MemberDto member : members) {
					if(member.getMemberState().equals(MemberState.Registered)) {
						memberDtos.add(member);
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return memberDtos;
	}
	//部門にアサイン可能な市場のメンバーを探す。
	public List<MemberDto> findAssignableMembers(List<RoleDto> roles, String industryId) {
		
		List<MemberDto> memberDtos = new ArrayList<>();
		 
		try {
			memberDtos = adapter.findAssignableMembers(roles, industryId);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return memberDtos;
		//以下はmyMarket側の処理
		/*
		List<MemberDto> memberDtos = new ArrayList<>();
		for(RoleDto role : roles) {
			try {
				List<Member> members = memberRegistry.findMembersOfRole(Integer.valueOf(role.getId()).toString());
				for(Member member : members) {
					//該当産業のメンバーのみ対象とする。
					if(member.getIndustryId().equals(industryId)) {
						//アサインされていないメンバーのみ対象とする。
						if(member.getMemberState().equals(MemberState.Registered)) {
							MemberDto dto = new MemberDto();
							dto.setMemberId(member.getMemberId().getMemberId());
							dto.setPersonId(member.getPersonId().id());
							dto.setFirstName(member.getMemberName().firstName());
							dto.setLastName(member.getMemberName().lastName());
							dto.setEmail(member.getEmail().address());
							dto.setIndustryId(member.getIndustryId());
							dto.setDepartmentId(member.getDepartmentId());
							dto.setProjectId(member.getProjectId());
							dto.setProgramId(member.getProgramId());
							dto.setAssociatedRoles(member.getAssociatedRoles());
							dto.setMemberDescription(member.getMemberDescription());
							dto.setUrl(member.getUrl());
							dto.setMemerState(member.getMemberState());
							//すでに含まれているメンバーは除外する。
							if(!memberDtos.contains(dto)) {
								memberDtos.add(dto);
							}
						}//if
					}//if industry
				}//for
				
			}catch(Exception ex) {
				//Listを返す場合はトレースを残す。
				ex.printStackTrace();
			}
			
		}
		return memberDtos;
		*/
	}
	public MemberDto findMemberOfEmail(String email, String companyId) throws Exception{
		if(email == null) {
			throw new Exception("The_email_may_not_be_set_to_null");
		}
		if(companyId == null) {
			throw new Exception("The_companyId_may_not_be_set_to_null");
		}
		List<Member> members = memberRegistry.findMembersOfEmail(email, companyId);	
		if(members.size() != 1) {
        	throw new Exception("The_member_is_not_exist");
        }
        Member member = members.get(0);
        return this.convertMember(member);
	}
	
	public List<MemberDto> findMembersOfDepartment(String departmentId) throws Exception{
		if(departmentId == null) {
			throw new Exception("The_departmentId_may_not_be_set_to_null");
		}
		List<Member> members = memberRegistry.findMembersOfDepartment(departmentId);
		return this.convertMembers(members);
	}
	
	public MemberDto findMemberOfMemberId(String memberId) throws Exception{
		if(memberId == null) {
			throw new Exception("The_memberId_may_not_be_set_to_null");
		}
		Member member = memberRegistry.findByMemberId(memberId);
		return this.convertMember(member);
	}
	
	/*
	 * プロジェクトのメンバーを探す
	 */
	public List<MemberDto> findMembersOfProject(String projectId) throws Exception{
		List<Member> members = memberRegistry.findMembersOfProject(projectId);
		return this.convertMembers(members);
	}
	
	public List<MemberDto> findMembersOfProgram(String programId) throws Exception{
		List<Member> members = memberRegistry.findMembersOfProgram(programId);
		return this.convertMembers(members);
	}
	
	public List<MemberDto> findMembersOfMemberCategory(String memberCategoryId) throws Exception{
		List<Member> members = memberRegistry.findMembersOfMemberCategory(memberCategoryId);
		return this.convertMembers(members);
	}
	
	//2022/9/22次のメソッドに変更。
	/*
	 * プロジェクトに割当可能なメンバーを探す
	 * プロジェクトが所属する事業単位の部門に割当てられたメンバーを探す
	 * ①プロジェクトが所属する事業単位を探す
	 * ②その事業単位に所属する部門を探す
	 * ③その部門に所属するメンバーでプロジェクトに割り当てられていない者を探す
	 */
	/*
	public List<MemberDto> findProjectAssignableMembers(String projectId) throws Exception{
		List<MemberDto> members = new ArrayList<>();
		try {
			BusinessUnitId businessUnitId = projectAdapter.findBusinessUnitOfProject(projectId);
			List<Department> departments = departmentAdapter.findDepartmentsOfBusinessUnit(businessUnitId);
			for(Department department : departments) {
				List<MemberDto> memberDtos = new ArrayList<>();
				memberDtos = this.findMembersOfDepartment(department.getDepartmentId().departmentId());
				for(MemberDto dto : memberDtos) {
					if(dto.getProjectId() == null || dto.getProjectId().isEmpty()) {
						members.add(dto);
					}
				}
			}
		}catch(Exception ex) {
			throw ex;
		}
		return members;
	}
	*/
	
	/*
	 * プロジェクトに割当可能なメンバーを探す
	 * プロジェクトのアクションプランの部門に割当てられたメンバーを探す
	 * その部門に所属するメンバーでプロジェクトに割り当てられていない者を探す
	 */
	public List<MemberDto> findProjectAssignableMembers(String projectId) throws Exception{
		List<MemberDto> members = new ArrayList<>();
		try {
			Project prj = projectAdapter.findProjectOfId(projectId);
			//プロジェクトのアクションプランを探す。
			List<AssociatedActionPlan> actionPlans = prj.getAssociatedActionPlans();
			for(AssociatedActionPlan actionPlan : actionPlans) {
				ActionPlanDto actionPlanDto = companyService.findActionPlanById(actionPlan.getActionPlanId());
				//アクションプランのアクションを探す。
				List<ActionDto> actions = companyService.findActionsOfActionPlan(actionPlanDto.getActionPlanId());
				for(ActionDto action : actions) {
					List<MemberDto> memberDtos = new ArrayList<>();
					//アクションに対応する部門に属するメンバーを探す。
					memberDtos = this.findMembersOfDepartment(action.getDepartmentId());
					for(MemberDto dto : memberDtos) {
						//部門のメンバーのうちプロジェクトにアサインされていない者を対象にする。
						if(dto.getProjectId() == null || dto.getProjectId().isEmpty()) {
							//プログラムにアサインされてにない者を対象にする。
							if(dto.getProgramId() == null || dto.getProgramId().isEmpty()) {
								//すでに配列にあるメンバーは追加しない。
								if(!members.contains(dto)) {
									members.add(dto);
								}
							}
						}
					}
				}
			}
			
		}catch(Exception ex) {
			throw ex;
		}
		return members;
	}
	
	public List<MemberDto> findProgramAssignableMembers(String programId) throws Exception{
		List<MemberDto> members = new ArrayList<>();
		try {
			Program prj = programAdapter.findProgramOfId(programId);
			//プロジェクトのアクションプランを探す。
			List<AssociatedActionPlan> actionPlans = prj.getAssociatedActionPlans();
			for(AssociatedActionPlan actionPlan : actionPlans) {
				ActionPlanDto actionPlanDto = companyService.findActionPlanById(actionPlan.getActionPlanId());
				//アクションプランのアクションを探す。
				List<ActionDto> actions = companyService.findActionsOfActionPlan(actionPlanDto.getActionPlanId());
				for(ActionDto action : actions) {
					List<MemberDto> memberDtos = new ArrayList<>();
					//アクションに対応する部門に属するメンバーを探す。
					memberDtos = this.findMembersOfDepartment(action.getDepartmentId());
					for(MemberDto dto : memberDtos) {
						//部門のメンバーのうちプログラムにアサインされていない者を対象にする。
						if(dto.getProgramId() == null || dto.getProgramId().isEmpty()) {
							//プロジェクトにアサインされていない者を対象にする。
							if(dto.getProjectId() == null || dto.getProjectId().isEmpty()) {
								//すでに配列にあるメンバーは追加しない。
								if(!members.contains(dto)) {
									members.add(dto);
								}
							}
						}
					}
				}
			}
			
		}catch(Exception ex) {
			throw ex;
		}
		return members;
	}
	
	/*
	 * 割当可能な市場メンバーをプロジェクトに割り当てる
	 */
	public MessageDto assignMemberToProject(MemberDto member, String projectId, String companyId) {
		MessageDto msg = new MessageDto();
		//事前条件の検証
		//部門が存在しているか検証する
		try {
			Project project = projectAdapter.findProjectOfId(projectId);
			if(project == null) {
				msg.setResult("NG");
				msg.setErrorMessage("The_project_may_not_exist");
			}else {
				//市場のメンバーを部門に割り当てる。
				if(member.getMemberCategoryId() == null || member.getMemberCategoryId().isEmpty()) {
					//REST/HTTP
					/*
					MessageDto message = adapter.assignMemberToProject(member.getMemberId(), projectId, companyId);
					//メンバーを会社の部門に割り当てて保存する。
					if(message.getResult().equals("OK")) {
						member.setProjectId(projectId);
						member.setCompanyId(project.getCompanyId().id());
						member.setMemberState(MemberState.Assigned);
						memberRegistry.assignMember(this.convertMemberDto(member));
						msg.setResult("OK");
					}else {
						msg.setResult("NG");
						msg.setErrorMessage("You_could_not_assign_member_to_the_project");
					}
					*/
					
					//JMS非同期メッセージング
					//DBアクセス
					member.setProjectId(projectId);
					member.setCompanyId(project.getCompanyId().id());
					member.setMemberState(MemberState.Assigned);
					memberRegistry.assignMember(this.convertMemberDto(member));
					//Topicアクセス
					//DBアクセスに失敗したら送信されない。
					MemberAssignedToProject memberState = new MemberAssignedToProject();
					memberState.setCompanyId(companyId);
					memberState.setProjectId(projectId);
					memberState.setMemberId(member.getMemberId());
					jmsTemplate.convertAndSend("Member.AssignedToProject.Queue", memberState);
					
					msg.setResult("OK");
					
				//会社のメンバーを部門に割当てる。
				}else {
					member.setProjectId(projectId);
					member.setCompanyId(project.getCompanyId().id());
					member.setMemberState(MemberState.Assigned);
					memberRegistry.assignMember(this.convertMemberDto(member));
					msg.setResult("OK");
				}
				
				
			}//if department null
		}catch(Exception ex) {
			msg.setResult("NG");
			msg.setErrorMessage("You_could_not_assign_member_to_the_project");
		}//try
		return msg;
	}
	
	
	public MessageDto assignMemberToProgram(MemberDto member, String programId, String companyId) {
		MessageDto msg = new MessageDto();
		//事前条件の検証
		//部門が存在しているか検証する
		try {
			Program program = programAdapter.findProgramOfId(programId);
			if(program == null) {
				msg.setResult("NG");
				msg.setErrorMessage("The_program_may_not_exist");
			}else {
				//市場のメンバーを部門に割り当てる。
				if(member.getMemberCategoryId() == null || member.getMemberCategoryId().isEmpty()) {
					//REST/HTTP
					/*
					MessageDto message = adapter.assignMemberToProgram(member.getMemberId(), programId, companyId);
					//メンバーを会社の部門に割り当てて保存する。
					if(message.getResult().equals("OK")) {
						member.setProgramId(programId);
						member.setCompanyId(program.getCompanyId().id());
						member.setMemberState(MemberState.Assigned);
						memberRegistry.assignMember(this.convertMemberDto(member));
						msg.setResult("OK");
					}else {
						msg.setResult("NG");
						msg.setErrorMessage("You_could_not_assign_member_to_the_program");
					}
					*/
					
					//JMS非同期メッセージング
					//DBアクセス
					member.setProgramId(programId);
					member.setCompanyId(program.getCompanyId().id());
					member.setMemberState(MemberState.Assigned);
					memberRegistry.assignMember(this.convertMemberDto(member));
					//Topicアクセス
					//DBアクセスに失敗したら送信されない。
					MemberAssignedToProgram memberState = new MemberAssignedToProgram();
					memberState.setCompanyId(companyId);
					memberState.setProgramId(programId);
					memberState.setMemberId(member.getMemberId());
					jmsTemplate.convertAndSend("Member.AssignedToProgram.Queue", memberState);
					
					msg.setResult("OK");
					
				//会社のメンバーを部門に割当てる。
				}else {
					member.setProgramId(programId);
					member.setCompanyId(program.getCompanyId().id());
					member.setMemberState(MemberState.Assigned);
					memberRegistry.assignMember(this.convertMemberDto(member));
					msg.setResult("OK");
				}
				
				
			}//if department null
		}catch(Exception ex) {
			msg.setResult("NG");
			msg.setErrorMessage("You_could_not_assign_member_to_the_program");
		}//try
		return msg;
	}
	
	//割当可能な会社のメンバーをプロジェクトに割り当てる
	public MessageDto assignMemberOfCompanyToProject(MemberDto member, String projectId) {
		MessageDto msg = new MessageDto();
		try {
			if(member == null) {
				throw new IllegalArgumentException("The_member_may_not_be_set_to_null");
			}
			if(projectId == null || projectId.isEmpty()) {
				throw new IllegalArgumentException("The_projectId_may_not_be_set_to_null");
			}
			Project project = projectAdapter.findProjectOfId(projectId);
			member.setProjectId(projectId);
			member.setCompanyId(project.getCompanyId().id());
			member.setMemberState(MemberState.Assigned);
			memberRegistry.assignMember(this.convertMemberDto(member));
			msg.setResult("OK");
			
		}catch(Exception ex) {
			msg.setResult("NG");
			msg.setErrorMessage(ex.getMessage());
			return msg;
		}
		return msg;
	}
	
	private MemberDto convertMember(Member member) {
		MemberDto dto = new MemberDto();
		dto.setMemberId(member.getMemberId().getMemberId());
		dto.setPersonId(member.getPersonId().id());
		dto.setCompanyId(member.getCompanyId());
		dto.setFirstName(member.getMemberName().firstName());
		dto.setLastName(member.getMemberName().lastName());
		dto.setJaName(dto.getLastName() + " " + dto.getFirstName());
		dto.setEnName(dto.getFirstName() + " " + dto.getLastName());
		dto.setEmail(member.getEmail().address());
		dto.setIndustryId(member.getIndustryId());
		if(member.getMemberCategoryId() != null) {
			dto.setMemberCategoryId(member.getMemberCategoryId().getMemberCategoryId());
		}
		dto.setDepartmentId(member.getDepartmentId());
		dto.setProjectId(member.getProjectId());
		dto.setProgramId(member.getProgramId());
		dto.setAssociatedRoles(member.getAssociatedRoles());
		dto.setMemberDescription(member.getMemberDescription());
		//dto.setUrl(member.getUrl());
		//Memberにpasswordを追加した。2022/4/25
		dto.setPassword(member.getPassword());
		dto.setMemberState(member.getMemberState());
		//MemberにMemberClassを追加した。2022/4/25
		dto.setMemberClass(member.getMemberClass());
		return dto;
	}
	
	private List<MemberDto> convertMembers(List<Member> members){
		List<MemberDto> memberDtos = new ArrayList<>();
		for(Member member : members) {
			/*
			MemberDto dto = new MemberDto();
			dto.setMemberId(member.getMemberId().getMemberId());
			dto.setPersonId(member.getPersonId().id());
			dto.setCompanyId(member.getCompanyId());
			dto.setFirstName(member.getMemberName().firstName());
			dto.setLastName(member.getMemberName().lastName());
			dto.setJaName(dto.getLastName() + " " + dto.getFirstName());
			dto.setEnName(dto.getFirstName() + " " + dto.getLastName());
			dto.setEmail(member.getEmail().address());
			dto.setIndustryId(member.getIndustryId());
			dto.setDepartmentId(member.getDepartmentId());
			dto.setProjectId(member.getProjectId());
			dto.setProgramId(member.getProgramId());
			dto.setAssociatedRoles(member.getAssociatedRoles());
			dto.setMemberDescription(member.getMemberDescription());
			dto.setUrl(member.getUrl());
			//Memberにpasswordを追加した。2022/4/25
			dto.setPassword(member.getPassword());
			dto.setMemberState(member.getMemberState());
			//MemberにMemberClassを追加した。2022/4/25
			dto.setMemberClass(member.getMemberClass());
			*/
			memberDtos.add(this.convertMember(member));
		}
		return memberDtos;
	}
	
	private Member convertMemberDto(MemberDto memberDto) {
		Member member = new Member();
		member.setMemberId(new MemberId(memberDto.getMemberId()));
		FullName fullName = new FullName(memberDto.getFirstName(), memberDto.getLastName());
		member.setMemberName(fullName);
		member.setEmail(new Email(memberDto.getEmail()));
		member.setPersonId(new PersonId(memberDto.getPersonId()));
		member.setCompanyId(memberDto.getCompanyId());
		member.setIndustryId(memberDto.getIndustryId());
		member.setDepartmentId(memberDto.getDepartmentId());
		if(memberDto.getMemberCategoryId() != null && !memberDto.getMemberCategoryId().isEmpty()) {
			member.setMemberCategoryId(new MemberCategoryId(memberDto.getMemberCategoryId()));
		}
		member.setProjectId(memberDto.getProjectId());
		member.setProgramId(memberDto.getProgramId());
		member.setAssociatedRoles(memberDto.getAssociatedRoles());
		member.setMemberState(memberDto.getMemberState());
		//MemberにMemberClassを追加した。2022/4/25
		member.setMemberClass(memberDto.getMemberClass());
		member.setMemberDescription(memberDto.getMemberDescription());
		//member.setUrl(memberDto.getUrl());
		//Memberにpasswordを追加した。2022/4/25
		member.setPassword(memberDto.getPassword());
		return member;
	}
	/*
	private List<Member> convertMemberDtos(List<MemberDto> memberDtos){
		List<Member> members = new ArrayList<>();
		for(MemberDto memberDto : memberDtos) {
			Member member = this.convertMemberDto(memberDto);
			members.add(member);
		}
		return members;
	}
	*/

}
