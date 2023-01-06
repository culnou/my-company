package com.culnou.mumu.company.domain.model.member.type;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.company.application.MemberTypeApplicationService;
import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Indicator;

import com.culnou.mumu.compnay.controller.MemberTypeDto;
import com.culnou.mumu.compnay.controller.MessageDto;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTypeApplicationServiceTest {
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	
	@Autowired
	private MemberTypeApplicationService service;
	

	private BusinessDomain bd = null;

	@Before
	public void setUp() throws Exception {
		/*
		bd = new BusinessDomain(new CompanyId("MemberTypeApplicationServiceTest"), new BusinessDomainId("MemberTypeApplicationServiceTest"));
		bd.setBusinessDomainName("MemberTypeApplicationServiceTest");
		businessDomainRepository.save(bd);
		*/
		
	}

	@After
	public void tearDown() throws Exception {
		/*
		BusinessDomain bd = businessDomainRepository.businessDomainOfId(new BusinessDomainId("MemberTypeApplicationServiceTest"));
		businessDomainRepository.remove(bd);
		*/
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		/*
		MemberTypeDto dto = new MemberTypeDto();
		dto.setCompanyId("MemberTypeApplicationServiceTest");
		dto.setBusinessDomainId("MemberTypeApplicationServiceTest");
		dto.setMemberTypeName("MemberTypeApplicationServiceTest");
		MessageDto message = service.defineMemberType("MemberTypeApplicationServiceTest", dto);
		
		Indicator id = new Indicator("33","33","33");
		
		
		
		MessageDto msg = service.defineMemberTypeIndicator(message.getReturnValue(), id);
		System.out.println(msg.getResult());
		
		//service.removeMemberTypeInfo(message.getReturnValue());
		 * */
		 
		
		
	}

}
