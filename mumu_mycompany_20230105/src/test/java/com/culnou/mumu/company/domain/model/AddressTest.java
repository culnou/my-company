package com.culnou.mumu.company.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class AddressTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//自己カプセル化のセッターの例外テスト：NULLを防いで正しく初期化できる
	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		String zipcode = null;
		String country = null;
		String state = null;
		String city = null;
		String street = null;
		Address adddress = new Address(zipcode, country, state, city, street);
		//実行されない。
		adddress.zipcode();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		Address address = new Address("111", "111", "111", "111", "111");
		Address clone = address.clone();
		assertEquals(address, clone);
		//副作用のないメソッドの実行
		System.out.println(address.city());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(address, clone);
	}
	@Test
	public void testEquality() {
		Address id1 = new Address("111", "111", "111", "111", "111");
		Address id2 = new Address("111", "111", "111", "111", "111");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		Address id1 = new Address("111", "111", "111", "111", "111");
		Address id2 = new Address("222", "222", "222", "222", "222");
		Address id3 = new Address("222", "222", "222", "222", "222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}


}
