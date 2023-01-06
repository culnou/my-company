package com.culnou.mumu.company.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class CompanyIdUnitTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//自己カプセル化のセッターの例外テスト：NULLを防いで正しく初期化できる
	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		String id = null;
		CompanyId companyId = new CompanyId(id);
		//実行されない。
		companyId.id();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		CompanyId companyId = new CompanyId("111");
		CompanyId clone = companyId.clone();
		assertEquals(companyId, clone);
		//副作用のないメソッドの実行
		System.out.println(companyId.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(companyId, clone);
	}
	@Test
	public void testEquality() {
		CompanyId id1 = new CompanyId("111");
		CompanyId id2 = new CompanyId("111");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		CompanyId id1 = new CompanyId("111");
		CompanyId id2 = new CompanyId("222");
		CompanyId id3 = new CompanyId("222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}


}
