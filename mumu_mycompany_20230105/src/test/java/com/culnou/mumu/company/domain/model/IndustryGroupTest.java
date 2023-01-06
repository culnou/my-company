package com.culnou.mumu.company.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class IndustryGroupTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//自己カプセル化のセッターの例外テスト：NULLを防いで正しく初期化できる
	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		String industryGroupId = null;
		String name = null;
		IndustryGroup industryGroup = new IndustryGroup(industryGroupId, name);
		//実行されない。
		industryGroup.industryGroupId();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		IndustryGroup industryGroup = new IndustryGroup("111", "111");
		IndustryGroup clone = industryGroup.clone();
		assertEquals(industryGroup, clone);
		//副作用のないメソッドの実行
		System.out.println(industryGroup.industryGroupId());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(industryGroup, clone);
	}
	@Test
	public void testEquality() {
		IndustryGroup id1 = new IndustryGroup("111", "111");
		IndustryGroup id2 = new IndustryGroup("111", "111");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		IndustryGroup id1 = new IndustryGroup("111", "111");
		IndustryGroup id2 = new IndustryGroup("222", "222");
		IndustryGroup id3 = new IndustryGroup("222", "222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
