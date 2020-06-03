package com.implement.mergeIntervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class EmployeeFreeTime {
	public static class Interval{
		int start;
		int end;
		
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> rst = new ArrayList<>();
        if (schedule == null || schedule.isEmpty()) return rst;
        
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2) {
                Interval t1 = schedule.get(i1[0]).get(i1[1]);
                Interval t2 = schedule.get(i2[0]).get(i2[1]);
                return t1.start != t2.start ? Integer.compare(t1.start, t2.start) : 
                	Integer.compare(t1.end, t2.end);
            }
        });
        
        int n = schedule.size();
        for (int i = 0; i < n; i++) {
            List<Interval> times = schedule.get(i);
            if (times.isEmpty()) continue;
            pq.offer(new int[]{i, 0});
        }
        
        int end = -1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            Interval t = schedule.get(curr[0]).get(curr[1]);
            if (end == -1 || t.start <= end) end = Math.max(end, t.end);
            else {
                rst.add(new Interval(end, t.start));
                end = t.end;
            }
            
            if (curr[1] < schedule.get(curr[0]).size() - 1) {
                curr[1]++;
                pq.offer(curr);
            }
        }
        
        return rst;
    }
}