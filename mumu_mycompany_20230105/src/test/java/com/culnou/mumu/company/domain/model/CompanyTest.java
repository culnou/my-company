package com.culnou.mumu.company.domain.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCompanyId() {	
		Company company = new Company(new CompanyId("111"),new DomainName("culnou.com"));
		//Companyの不変性により、すでにIDがある場合は設定されない。
		company.setCompanyId(new CompanyId("222"));
	}
	
	

}
