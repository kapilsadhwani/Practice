package com.implement.design;

import java.util.Deque;
import java.util.LinkedList;

class HitCounter {
	class TimeSlot {
		int timestamp;
		int count;

		public TimeSlot(int timestamp) {
			this.timestamp = timestamp;
			count = 1;
		}
	}

	Deque<TimeSlot> dq;
	int totalHits = 0, duration = 300;

	/** Initialize your data structure here. */
	public HitCounter() {
		dq = new LinkedList<TimeSlot>();
	}

	/**
	 * Record a hit.
	 * 
	 * @param timestamp
	 *            - The current timestamp (in seconds granularity).
	 */
	public void hit(int timestamp) {
		totalHits++;
		TimeSlot last = dq.peekLast();
		if (last != null && last.timestamp == timestamp)
			last.count++;
		else
			dq.offerLast(new TimeSlot(timestamp));
		
		adjustSlots(timestamp);
	}

	/**
	 * Return the number of hits in the past 5 minutes.
	 * 
	 * @param timestamp
	 *            - The current timestamp (in seconds granularity).
	 */
	public int getHits(int timestamp) {
		adjustSlots(timestamp);
		return totalHits;
	}

	private void adjustSlots(int timestamp) {
		TimeSlot first;
		
		while (!dq.isEmpty()) {
			first = dq.peekFirst();
			if (timestamp - first.timestamp >= duration) {
				dq.pollFirst();
				totalHits = totalHits - first.count;
			} else {
				break;
			}
		}
	}
}