package com.prep.implement.arrays;

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode (int x) {val = x;}
}

public class SortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] nums){
		if(nums==null || nums.length==0){
			return null;
		}
		
		return constructBSTRecusrive(nums, 0, nums.length-1);
	}

	private TreeNode constructBSTRecusrive(int[] nums, int low, int high) {
		// TODO Auto-generated method stub
		if(high < low){
			return null;
		}
		
		int mid = (low + high) / 2;
		TreeNode node = new TreeNode(nums[mid]);
		node.left = constructBSTRecusrive(nums, low, mid-1);
		node.right = constructBSTRecusrive(nums, mid+1, high);
		
		return node;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
