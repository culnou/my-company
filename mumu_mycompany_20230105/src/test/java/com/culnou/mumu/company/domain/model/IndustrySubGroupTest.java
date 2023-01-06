package com.culnou.mumu.company.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class IndustrySubGroupTest {

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
		IndustrySubGroup industryGroup = new IndustrySubGroup(id, name);
		//実行されない。
		industryGroup.id();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		IndustrySubGroup industryGroup = new IndustrySubGroup("111", "111");
		IndustrySubGroup clone = industryGroup.clone();
		assertEquals(industryGroup, clone);
		//副作用のないメソッドの実行
		System.out.println(industryGroup.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(industryGroup, clone);
	}
	@Test
	public void testEquality() {
		IndustrySubGroup id1 = new IndustrySubGroup("111", "111");
		IndustrySubGroup id2 = new IndustrySubGroup("111", "111");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		IndustrySubGroup id1 = new IndustrySubGroup("111", "111");
		IndustrySubGroup id2 = new IndustrySubGroup("222", "222");
		IndustrySubGroup id3 = new IndustrySubGroup("222", "222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
