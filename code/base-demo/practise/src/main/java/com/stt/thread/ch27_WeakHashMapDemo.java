package com.stt.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class ch27_WeakHashMapDemo {
	public static void main(String[] args) {
		hashMapTest();
		weakHashMapTest();
	}

	static void hashMapTest(){
		Map<Integer,String> map = new HashMap();
		Integer key = new Integer(1);
		String value = "value";
		map.put(key,value);
		System.out.println(map);

		key = null;
		System.out.println("======");
		System.out.println(map);

		System.gc();
		System.out.println("======");
		System.out.println(map);
	}

	static void weakHashMapTest(){
		Map<Integer,String> map = new WeakHashMap<>();
		Integer key = new Integer(2);
		String value = "value";
		map.put(key,value);
		System.out.println(map);

		key = null;
		System.out.println("======");
		System.out.println(map);

		System.gc();
		System.out.println("======");
		System.out.println(map);
	}
}
