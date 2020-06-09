package com.implement.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

class TimeMap {
    Map<String, List<Pair<Integer, String>>> map;

    public TimeMap() {
    	map = new HashMap<String, List<Pair<Integer, String>>>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key))
        	map.put(key, new ArrayList<Pair<Integer, String>>());

        map.get(key).add(new Pair<Integer, String>(timestamp, value));
    }

	public String get(String key, int timestamp) {
		if (!map.containsKey(key))
			return "";

		List<Pair<Integer, String>> values = map.get(key);
		
		/*
		int index = Collections.binarySearch(values, 
						new Pair<Integer, String>(timestamp, "{"), 			// Here getKey is on Pair
						(a, b) -> Integer.compare(a.getKey(), b.getKey())); // i.e timestamp

		*/
		
		int index = Collections.binarySearch(values, 
				new Pair<Integer, String>(timestamp, "{"), 	// Here getKey is on Pair
				new Comparator<Pair<Integer, String>>(){	// i.e timestamp
					public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2){
						return o1.getKey() - o2.getKey();
					}
				});
		
		if (index >= 0)
			return values.get(index).getValue();
		else if (index == -1)
			return "";
		else
			return values.get(-index - 2).getValue();
	}

    public static void main(String[] args) {
    	TimeMap cache = new TimeMap();

		cache.set("foo","bar",1);
		
		System.out.println(cache.get("foo",1));
		System.out.println(cache.get("foo",3));
		
		cache.set("foo","bar2",4);
		
		System.out.println(cache.get("foo",4));
		System.out.println(cache.get("foo",5));
		
		TimeMap cache1 = new TimeMap();

		cache1.set("love","high",10);
		cache1.set("love","low",20);
		
		System.out.println(cache1.get("love",5));
		System.out.println(cache1.get("love",10));
		System.out.println(cache1.get("love",15));
		System.out.println(cache1.get("love",20));
		System.out.println(cache1.get("love",25));
	}
}