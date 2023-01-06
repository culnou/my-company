package com.culnou.mumu.company.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class IndustryTest {

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
		String name = null;
		Industry industry = new Industry(id, name);
		//実行されない。
		industry.id();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		Industry industry = new Industry("111", "111");
		Industry clone = industry.clone();
		assertEquals(industry, clone);
		//副作用のないメソッドの実行
		System.out.println(industry.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(industry, clone);
	}
	@Test
	public void testEquality() {
		Industry id1 = new Industry("111", "111");
		Industry id2 = new Industry("111", "111");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		Industry id1 = new Industry("111", "111");
		Industry id2 = new Industry("222", "222");
		Industry id3 = new Industry("222", "222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
