package com.culnou.mumu.company.domain.model.member;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.compnay.controller.MemberDto;

import com.culnou.mumu.compnay.controller.RoleDto;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketMemberAdapterTest {
	
	@Autowired
	MarketMemberAdapter adapter;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception{
		
		RoleDto role1 = new RoleDto();
		role1.setId(1);
		RoleDto role2 = new RoleDto();
		role2.setId(3);
		List<RoleDto> roles = new ArrayList<>();
		roles.add(role1);
		roles.add(role2);
		List<MemberDto> members = adapter.findAssignableMembers(roles, "78");
		for(MemberDto member : members) {
			System.out.println("********* member name " + member.getFirstName() + " " + member.getLastName());;
		}
		
		
		
	}
	/*
	@Test
	public void testAssignMemberToDepartment() throws Exception{
		MessageDto message = adapter.assignMemberToDepartment("d619d44d-794f-404f-bf64-acfaa908c74a", "dep001");
		System.out.println(message.getErrorMessage());
	}
	*/

}
