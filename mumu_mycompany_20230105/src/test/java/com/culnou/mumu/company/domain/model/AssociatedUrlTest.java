package com.culnou.mumu.company.domain.model;





import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
		
		
		
	}

}
