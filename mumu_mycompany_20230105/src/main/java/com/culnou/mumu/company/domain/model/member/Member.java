package com.culnou.mumu.company.domain.model.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.common.AssociatedRole;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.common.FullName;
import com.culnou.mumu.company.domain.model.common.PersonId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;
import com.culnou.mumu.company.domain.model.member.type.MemberClass;

import lombok.Data;




@Entity
@Table(name = "members")
@XmlRootElement
@Data
public class Member {
	
	@Id
	@Embedded
	@NotNull
	private MemberId memberId;
	
	@Embedded
	@NotNull
	private FullName memberName;
	
	//ログインのアカウントになります。
	@Embedded
	@NotNull
	private Email email;
	
	//Memberにpasswordを追加した。2022/4/25
	@Column(name = "password")
	private String password;
	
	@Embedded
	@NotNull
	private PersonId personId;
	
	@Column(name = "company_id")
	@NotNull
	@NotEmpty
	private String companyId;
	
	@Column(name = "industry_id")
	@NotNull
	@NotEmpty
	private String industryId;
	
	@Embedded
	private MemberCategoryId memberCategoryId;
	
	@Column(name = "department_id")
	private String departmentId;
	
	@Column(name = "project_id")
	private String projectId;
	
	@Column(name = "program_id")
	private String programId;
	
	@ElementCollection
	private List<AssociatedRole> associatedRoles = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "member_state")
	private MemberState memberState;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "member_class")
	private MemberClass memberClass;
	
	@Column(name = "member_description")
	private String memberDescription;
	
	@Column(name = "url")
	private String url;
	
	
	
	

}
