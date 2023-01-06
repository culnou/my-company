package com.culnou.mumu.company.domain.model;



import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;

public class Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@org.junit.Test
	public void test() {
		//fail("Not yet implemented");
		List<Indicator> inds = new ArrayList<>();
		Indicator id1 = new Indicator("111", "111", "111");
		Indicator id2 = new Indicator("577", "222", "333");
		Indicator id3 = new Indicator("111", "222", "222");
		inds.add(id1);
		inds.add(id2);
		inds.add(id3);
		
		Indicator id4 = new Indicator("111", "222", "222");
		
		int cd = inds.indexOf(id4);
		Indicator id5 = new Indicator("おきかえ111", "おきかえ222", "おきかえ222");
		inds.set(cd, id5);
		System.out.println("*** in5 size " + inds.size() + "***" + inds.get(2).getDescription());
		Indicator id6 = new Indicator("おきかえ222", "おきかえ222", "おきかえ222");
		System.out.println("*** in6  " + inds.indexOf(id6));
		inds.remove(0);
		inds.forEach(i -> System.out.println(i.getIndicatorName()));
		
		AssociatedApplicationCategory app1 = new AssociatedApplicationCategory();
		app1.setApplicationCategoryId("111");
		app1.setApplicationCategoryName("app1");
		AssociatedApplicationCategory app2 = new AssociatedApplicationCategory();
		app2.setApplicationCategoryId("111");
		app2.setApplicationCategoryName("app1");
		Department dp = new Department(new DepartmentId("111"), new CompanyId("111"), DepartmentType.Business, new JobId("111"), "111", "111");
		dp.getAssociatedApplicationCategories().add(app1);
		System.out.println("***** contain test ***** " + dp.getAssociatedApplicationCategories().contains(app2));
		
		
		
		
		
	}

}
