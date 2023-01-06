package com.culnou.mumu.company.domain.model.activity.work;



import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkTest {
	
	
	@Autowired
	private WorkRegistry registry;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
    
	@Test
	public void test() throws Exception{
		List<Work> works = registry.findWorksOfWorkflow(new WorkflowId("111"), WorkType.Doing);
		System.out.println(works.size());
	}
	

}
