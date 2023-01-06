package com.culnou.mumu.company.domain.model.data.item;



import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.culnou.mumu.company.domain.model.Indicator;

public class DataItemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		//fail("Not yet implemented");
		List<Indicator> inds = new ArrayList<>();
		Indicator id1 = new Indicator("111", "111", "111");
		Indicator id2 = new Indicator("222", "222", "111");
		Indicator id3 = new Indicator("333", "222", "111");
		Indicator id4 = new Indicator("444", "222", "111");
		Indicator id5 = new Indicator("555", "222", "111");
		Indicator id6 = new Indicator("111", "222", "111");
		inds.add(id1);
		inds.add(id2);
		inds.add(id3);
		inds.add(id4);
		inds.add(id5);
		inds.add(id6);
		DataItem item = new DataItem();
		item.setIndicators(inds);
	}

}
