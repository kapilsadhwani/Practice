package com.prep.implement.dp;

import java.util.Arrays;

public class CutRope {
	static int recursion(int ropeLength, int initialRopeLength) {
		if (ropeLength <= 1){
			return initialRopeLength == ropeLength ? 0 : 1;
		}
		
		int max = initialRopeLength == ropeLength ? 0 : ropeLength;
		for (int c = 1; c < ropeLength; ++c)
			max = Math.max(max, 
					c * recursion(ropeLength - c, initialRopeLength));

		return max;
	}
	
	static int getMaxScoreMemo(int l, int len, int[] score){
		if(score[l] != -1) return score[l];
		
		int bestScore;
		
		if(l == 1){
			bestScore = l == len ? 0 : 1;
		}else{
			bestScore = l == len ? 0 : l;
			
			for(int c=1; c<l; c++){			// c = cut
				bestScore = Math.max(bestScore,
						c * getMaxScoreMemo(l-c,len,score));
			}
		}
		
		score[l] = bestScore;
		return bestScore;
	}
	
	// Top-down approach - DP: Memoization
	static int getMaxScore(int len){
		int[] score = new int[len+1];
		
		Arrays.fill(score, -1);
		
		return getMaxScoreMemo(len, len, score);
	}
	
	 // Bottom-up approach - DP: Iterative
	static int getMaxScoreDP(int len, boolean printPath){
		int[] score = new int[len+1];
		int[] path = new int[len+1];
		
		if(len <= 1){
            return 0;
		}
		
		if(printPath){
			getMaxScoreDPWithPath(len, score, path);
			System.out.print("Path is : ");
			
			int l = len;
			while(l > 0){
				System.out.print(path[l] + " --> ");
				l = l - path[l];
			}
			System.out.print("NULL");
		}else{
			score[1] = len == 1 ? 0 : 1;	// Will always get set to 1
				
			for(int l=2; l<=len; l++){
				int bestScore = l == len ? 0 : l;	// Force a cut
		
				for(int c=1; c<l; c++){			// c = cut
					bestScore = Math.max(bestScore,
							c * score[l-c]);
				}
				score[l] = bestScore;			
			}
		}
		return score[len];
	}
	
	static void getMaxScoreDPWithPath(int len, int[] score, int[] path){
		score[1] = 1;
		path[1] = 1;
		
		for(int l=2; l<=len; l++){
			int bestScore = l == len ? 0 : l;	// Force a cut
			path[l] = l == len ? 0 : l;
	
			for(int c=1; c<l; c++){			// c = cut
				if(bestScore < c * score[l-c]){
					bestScore = c * score[l-c];
					path[l] = c;
				}
			}
			score[l] = bestScore;			
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Rope of length 1 - Memo : " + getMaxScore(1) + ", Iter : " + getMaxScoreDP(1,false));
		System.out.println("Rope of length 2 - Memo : " + getMaxScore(2) + ", Iter : " + getMaxScoreDP(2,false));
		System.out.println("Rope of length 3 - Memo : " + getMaxScore(3) + ", Iter : " + getMaxScoreDP(3,false));
		System.out.println("Rope of length 4 - Memo : " + getMaxScore(4) + ", Iter : " + getMaxScoreDP(4,false));
		System.out.println("Rope of length 5 - Memo : " + getMaxScore(5) + ", Iter : " + getMaxScoreDP(5,false));
		System.out.println("Rope of length 6 - Memo : " + getMaxScore(6) + ", Iter : " + getMaxScoreDP(6,false));
		System.out.println("Rope of length 7 - Memo : " + getMaxScore(7) + ", Iter : " + getMaxScoreDP(7,false));
		System.out.println("Rope of length 8 - Memo : " + getMaxScore(8) + ", Iter : " + getMaxScoreDP(8,false));
		System.out.println("Rope of length 9 - Memo : " + getMaxScore(9) + ", Iter : " + getMaxScoreDP(9,false));
		System.out.println("Rope of length 10 - Memo : " + getMaxScore(10) + ", Iter : " + getMaxScoreDP(10,false));
		
		System.out.println();
		System.out.println("With Path of length 7 ");
		int bestScore = getMaxScoreDP(7, true);
		
		System.out.println("\nAnd Best Score is : " + bestScore);
	}

}
