package com.culnou.mumu.company.domain.model;





import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.company.domain.model.common.AssociatedUrl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociatedUrlTest {
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		/*
		BusinessDomain bd = businessDomainRepository.businessDomainOfId(new BusinessDomainId("associatedUrlTestBD2"));
		businessDomainRepository.remove(bd);
		*/
	}

	@Test
	public void test() throws Exception{
		/*
		//fail("Not yet implemented");
		BusinessDomain bd = new BusinessDomain(new CompanyId("associatedUrlTestCompany1"), new BusinessDomainId("associatedUrlTestBD2"));
		bd.setBusinessDomainName("associatedUrlTestBDName1");
		AssociatedUrl au1 = new AssociatedUrl();
		Url url1 = new Url();
		url1.setUrl("https://www.associatedUrlTest1.com");
		au1.setUrlTitle("UrlTest1");
		au1.setUrl(url1);
		
		AssociatedUrl au2 = new AssociatedUrl();
		Url url2 = new Url();
		url2.setUrl("https://www.associatedUrlTest2.com");
		au2.setUrlTitle("UrlTest2");
		au2.setUrl(url2);
		
		bd.getAssociatedUrls().add(au1);
		bd.getAssociatedUrls().add(au2);
		
		businessDomainRepository.save(bd);
		*/
		
		
		
	}

}
