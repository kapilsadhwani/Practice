package com.implement.multithreading;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {
	public ObservableSet(Set<E> set) {
		// TODO Auto-generated constructor stub
		super(set);
	}

	//private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();
	private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<SetObserver<E>>();
	
	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	public boolean removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			return observers.remove(observer);
		}
	}
	
/*
	// This method is the culprit
	private void notifyElementAdded(E element) {
		synchronized (observers) {
			for (SetObserver<E> observer : observers)
				observer.added(this, element);
		}
	}
*/

/*
	// Solution 1: Move foreign method outside any synchronized blocks
	private void notifyElementAdded(E element) {
		List<SetObserver<E>> snapshot = null;
		synchronized (observers) {
			snapshot = new ArrayList<SetObserver<E>>(observers);
		}
		
		for (SetObserver<E> observer : snapshot){
			observer.added(this, element);
		}
	}
*/
	
	// Solution 2: Thread-safe with CopyOnWriteArrayList
	private void notifyElementAdded(E element) {
		for (SetObserver<E> observer : observers){
			observer.added(this, element);
		}
	}
	
	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		boolean added = super.add(element);
		
		if(added){
			notifyElementAdded(element);
		}
		
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		for(E element : c){
			result |= add(element); // calls notifyElementAdded
		}
		
		return result;
	}

}
