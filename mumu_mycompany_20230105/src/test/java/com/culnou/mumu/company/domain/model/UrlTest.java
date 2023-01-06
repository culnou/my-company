package com.culnou.mumu.company.domain.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UrlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//URLの正規表現検証テスト
	@Test(expected = IllegalArgumentException.class)
	public void testVaridation() {
		Url url = new Url();
		url.setUrl("111");
		System.out.println(url.getUrl());
	}

}
