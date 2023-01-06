package com.culnou.mumu.company.domain.model.member.type;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MemberTypeIdTest {

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
		MemberTypeId memberTypeId = new MemberTypeId(id);
		//実行されない。
		memberTypeId.getMemberTypeId();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		MemberTypeId memberTypeId = new MemberTypeId("111");
		MemberTypeId clone = memberTypeId.clone();
		assertEquals(memberTypeId, clone);
		//副作用のないメソッドの実行
		System.out.println(memberTypeId.getMemberTypeId());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(memberTypeId, clone);
	}
	//値オブジェクトの等価性検証。
	@Test
	public void testEquality() {
		MemberTypeId id1 = new MemberTypeId("111");
		MemberTypeId id2 = new MemberTypeId("111");
		assertEquals(id1, id2);
	}
	//値オブジェクトの交換可能性検証。
	@Test
	public void testExchangeability() {
		MemberTypeId id1 = new MemberTypeId("111");
		MemberTypeId id2 = new MemberTypeId("222");
		MemberTypeId id3 = new MemberTypeId("222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}



}
