package com.prep.implement.queue;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class FirstUniqueNumber {
	LinkedHashSet<Integer> unique;
	HashSet<Integer> all;
	
	public FirstUniqueNumber(int[] nums) {
		unique = new LinkedHashSet<Integer>();
		all = new HashSet<Integer>();
		
		for(int n: nums){
			if(all.add(n))
	    		unique.add(n);
	    	else{
	    		unique.remove(n);
	    	}
        }
    }
    
    public int showFirstUnique() {
    	return unique.isEmpty() ? -1 : unique.iterator().next();
    }
    
    public void add(int value) {
    	if(all.add(value))
    		unique.add(value);
    	else{
    		unique.remove(value);
    	}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {2,3,5};
		
		FirstUniqueNumber fun = new FirstUniqueNumber(nums);
		System.out.println(fun.showFirstUnique());
		fun.add(5);
		System.out.println(fun.showFirstUnique());
		fun.add(2);
		System.out.println(fun.showFirstUnique());
		fun.add(3);
		System.out.println(fun.showFirstUnique());
		
		nums = new int[]{7,7,7,7,7,7};
		
		System.out.println("=====================================");
		
		fun = new FirstUniqueNumber(nums);
		System.out.println(fun.showFirstUnique());
		fun.add(7);
		fun.add(3);
		fun.add(3);
		fun.add(7);
		fun.add(17);
		System.out.println(fun.showFirstUnique());
		
		System.out.println("=====================================");
		
		fun = new FirstUniqueNumber(new int[]{100000000});
		System.out.println(fun.showFirstUnique());
	}

}
