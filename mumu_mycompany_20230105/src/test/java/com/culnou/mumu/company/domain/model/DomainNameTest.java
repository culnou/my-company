package com.culnou.mumu.company.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DomainNameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//自己カプセル化のセッターの例外テスト：NULLを防いで正しく初期化できる
	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		String name = null;
		DomainName domainName = new DomainName(name);
		//実行されない。
		domainName.name();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		DomainName domainName = new DomainName("culnou.com");
		DomainName clone = domainName.clone();
		assertEquals(domainName, clone);
		//副作用のないメソッドの実行
		System.out.println(domainName.name());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(domainName, clone);
	}
	@Test
	public void testEquality() {
		DomainName id1 = new DomainName("culnou.com");
		DomainName id2 = new DomainName("culnou.com");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		DomainName id1 = new DomainName("culnou.com");
		DomainName id2 = new DomainName("example.com");
		DomainName id3 = new DomainName("example.com");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}
	
	
	//ドメイン名の正規表現検証テスト
	@Test(expected = IllegalArgumentException.class)
	public void testValidation() {
		DomainName name = new DomainName("111");
		System.out.println(name.name);
	}



}
