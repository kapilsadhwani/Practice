package com.implement.mergeIntervals;

public class VideoStitching {
	class Interval{
		int start;
		int end;
		public Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
	
	public int videoStitching(int[][] clips, int T) {
		Interval array[] = new Interval[clips.length];
		
		for(int i = 0; i < clips.length; i++) 
			array[i] = new Interval(clips[i][0], clips[i][1]);
		
		int start = 0, result = 0;
		while( start < T ) {
			int maxEnd = 0;
			for(int i = 0; i < array.length; i++)
				if( array[i].start <= start )
					maxEnd = Math.max(array[i].end, maxEnd);
			if( maxEnd <= start )
				return -1;
			start = maxEnd;
			result++;
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VideoStitching vs = new VideoStitching();
		
		int[][] clips;
		int T;
		
		clips = new int[][]{{0,2},{4,6},{0,1},{8,10},{1,9},{1,5},{5,9},{1,3}};
		T = 10;
		System.out.println(vs.videoStitching(clips, T));
		
		clips = new int[][]{{0,1},{1,2}};
		T = 5;
		System.out.println(vs.videoStitching(clips, T));
		
		clips = new int[][]{
				{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}
		};
		T = 9;
		System.out.println(vs.videoStitching(clips, T));
		
		clips = new int[][]{{0,4},{2,8}};
		T = 5;
		System.out.println(vs.videoStitching(clips, T));
	}

}
