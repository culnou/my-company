package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.List;


import com.culnou.mumu.company.domain.model.common.AssociatedRole;

import com.culnou.mumu.company.domain.model.member.MemberState;

import com.culnou.mumu.company.domain.model.member.type.MemberClass;

import lombok.Data;

@Data
public class MemberDto {
	
	private String memberId;
	//private String memberName;
	private String firstName;
	private String lastName;
	private String jaName;
	private String enName;
	private String email;
	private String password;
	private String personId;
	private String companyId;
	private String memberCategoryId;
	private String industryId;
	private String departmentId;
	private String projectId;
	private String programId;
	private List<AssociatedRole> associatedRoles = new ArrayList<>();
	private String memberDescription;
	private String url;
	private MemberState memberState;
	private MemberClass memberClass;
	

}
