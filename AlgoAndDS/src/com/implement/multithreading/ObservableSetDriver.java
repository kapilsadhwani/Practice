package com.implement.multithreading;

import java.util.HashSet;

public class ObservableSetDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			
			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				// TODO Auto-generated method stub
				System.out.println(element);	// Action !!!
				
				if(element > 25){	// too many to observe!
					set.removeObserver(this);
				}
			}
		});
		
		for(int i = 0; i < 100; i++){
			set.add(i);
		}
	}

}
