package com.implement.arrays;

public class MedianTwoSortedArrays {
    // The least significant bit of any odd number is 1.
    private static boolean isOdd(int x) {
    	return (x & 1) == 1;
    }
	
    public static double findMedianHelper(int[] nums1, int[] nums2) {
        int aLen = nums1.length;
        int bLen = nums2.length;

        int leftHalfLen = (aLen + bLen + 1) / 2;

        // Since A is guaranteed to be the shorter array, 
        // we know it can contribute 0 or all of its values.
        int aMinCount = 0;
        int aMaxCount = aLen;

        while (aMinCount <= aMaxCount){
            int aCount = aMinCount + ((aMaxCount - aMinCount) / 2);
            int bCount = leftHalfLen - aCount;

            //
            // Make sure aCount is greater than 0 (because A can contribute 0 values;
            // remember that A is either shorter or of the same length as B). This also 
            // implies bCount will be less than B.Length since it won't be possible 
            // for B to contribute all of its values if A has contributed at least 1
            // value.
            //
            if (aCount > 0 && nums1[aCount - 1] > nums2[bCount]){
                // Decrease A's contribution size; x lies in the right half.
                aMaxCount = aCount - 1;
            }
            
            //
            // Make sure aCount is less than A.Length since A can actually contribute
            // all of its values (remember that A is either shorter or of the same 
            // length as B). This also implies bCount > 0 because B has to contribute 
            // at least 1 value if aCount < A.Length.
            //
            else if (aCount < aLen && nums2[bCount - 1] > nums1[aCount]){
                // Decrease B's contribution size, i.e. increase A's contribution size; 
                // y lies in the right half.
                aMinCount = aCount + 1;
            }else{
                //
                // Neither x nor y lie beyond the left half. We found the right aCount.
                // We don't know how x and y compare to each other yet though.
                //

                //
                // If aLen + bLen is odd, the median is the greater of x and y.
                //
                int leftHalfEnd = 
                    (aCount == 0)             // A not contributing?
                        ? nums2[bCount - 1]       // aCount = 0 implies bCount > 0
                        : (bCount == 0)       // B is not contributing?
                            ? nums1[aCount - 1]   // bCount = 0 implies aCount > 0
                            : Math.max(nums1[aCount - 1], nums2[bCount - 1]); 

                if (isOdd(aLen + bLen)){
                    return leftHalfEnd;
                }

                //
                // aLen + bLen is even. To compute the median, we need to find 
                // the first element in the right half, which will be the smaller 
                // of A[aCount] and B[bCount]. Remember that aCount could be equal
                // to A.Length, bCount could be equal to B.Length (if all the values 
                // of A or B are in the left half).
                //
                int rightHalfStart = 
                    (aCount == aLen)          // A is all in the left half?
                        ? nums2[bCount]           // aCount = aLen implies bCount < B.Length 
                        : (bCount == bLen)    // B is all in the left half?
                            ? nums1[aCount]       // bCount = B.Length implies aCount < A.Length 
                            : Math.min(nums1[aCount], nums2[bCount]);
                return (leftHalfEnd + rightHalfStart) / 2.0;
            }
        }

        throw new IllegalArgumentException("Unexpected code path reached");
    }
    
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if ((nums1 == null && nums1.length == 0) || (nums2 == null && nums2.length == 0)){
            throw new IllegalArgumentException("Inputs are NULL");
        }
		
		if(nums1.length < nums2.length){
			return findMedianHelper(nums1, nums2);
		}
		
		return findMedianHelper(nums2, nums1);
	}

    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int[] nums1 = {1, 2};
    	int[] nums2 = {3, 4};
    	
    	System.out.println(findMedianSortedArrays(nums1, nums2));
	}

}
