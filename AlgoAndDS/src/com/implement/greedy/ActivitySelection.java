package com.implement.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// The following implementation assumes that the activities 
// are already sorted according to their finish time 

class ActivitySelection { 
	// Prints a maximum set of activities that can be done by a single 
	// person, one at a time. 
	//  n   -->  Total number of activities 
	//  s[] -->  An array that contains start time of all activities 
	//  f[] -->  An array that contains finish time of all activities 
	public static void printMaxActivities(int s[], int f[], int n){ 
		int i, j; 

		System.out.print("Following activities are selected : "); 

		// The first activity always gets selected 
		i = 0; 
		System.out.print("("+s[i]+","+f[i]+")");  

		// Consider rest of the activities 
		for (j = 1; j < n; j++){ 
			// If this activity has start time greater than or 
			// equal to the finish time of previously selected 
			// activity, then select it 
			if (s[j] >= f[i]) { 
				System.out.print(", ("+s[j]+","+f[j]+")");
				i = j; 
			} 
		} 
	} 
	
	public static void printMaxActivities(List<Activity> activities, int n){ 
		int i, j; 
	
		// Sort jobs according to finish time 
	    Collections.sort(activities, new ActivityComparator()); 
	    
	    
		System.out.print("\nFollowing activities are selected : "); 

		// The first activity always gets selected 
		i = 0; 
		System.out.print("("+activities.get(i).getStart()+","+activities.get(i).getFinish()+")"); 

		// Consider rest of the activities 
		for (j = 1; j < n; j++){ 
			// If this activity has start time greater than or 
			// equal to the finish time of previously selected 
			// activity, then select it 
			if (activities.get(j).getStart() >= activities.get(i).getFinish()) { 
				System.out.print(", ("+activities.get(j).getStart()+","+activities.get(j).getFinish()+")");
				i = j; 
			} 
		} 
	} 

	// driver program to test above function 
	public static void main(String[] args) { 
		int s[] =  {1, 3, 0, 5, 8, 5}; 
		int f[] =  {2, 4, 6, 7, 9, 9}; 
		int n = s.length; 

		printMaxActivities(s, f, n); 
		
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity(5,9));
		activities.add(new Activity(1,2));
		activities.add(new Activity(3,4));
		activities.add(new Activity(0,6));
		activities.add(new Activity(5,7));
		activities.add(new Activity(8,9));
		
		printMaxActivities(activities, activities.size()); 
	}
	
	static class Activity{
		private int start;
		private int finish;
		
		public Activity(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		
		public int getStart() {
			return start;
		}

		public int getFinish() {
			return finish;
		}
	}
	
	static class ActivityComparator implements Comparator<Activity>{
		@Override
		public int compare(Activity act0, Activity act1) {
			if(act0 != null && act1 != null){
				return act0.getFinish() - act1.getFinish();
			}
			return 0;
		}
	}

} 