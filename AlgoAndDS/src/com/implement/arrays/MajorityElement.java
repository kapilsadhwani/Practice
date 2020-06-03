package com.implement.arrays;
/* Program for finding out majority element in an array */
 
class MajorityElement {
    /* Function to print Majority Element */
    void printMajority(int a[], int size){
        /* Find the candidate for Majority*/
        int cand = findCandidate(a, size);
        
        System.out.print("Candidate is : " + cand);
 
        /* Print the candidate if it is Majority*/
        if (isMajority(a, size, cand))
            System.out.println(", Majority: " + cand + " ");
        else
            System.out.println(", No Majority Element");
    }
 
    /* Function to find the candidate for Majority */
    int findCandidate(int a[], int size){	//1, 2, 2, 4, 1, 1, 3, 3
        int maj_index = 0, count = 1;

        for (int i = 1; i < size; i++) {
            if (a[maj_index] == a[i])
                count++;
            else
                count--;
            if (count == 0)
            {
                maj_index = i;
                count = 1;
            }
        }
        return a[maj_index];
    }
 
    /* Function to check if the candidate occurs more
       than n/2 times */
    boolean isMajority(int a[], int size, int cand){
        int count = 0;
        for (int i = 0; i < size; i++) 
        {
            if (a[i] == cand)
                count++;
        }
        return (count > size / 2);
    }
 
    /* Driver program to test the above functions */
    public static void main(String[] args){
        MajorityElement majorelement = new MajorityElement();
        int arr[] = new int[]{1, 3, 3, 2, 3, 3, 4};
        majorelement.printMajority(arr, arr.length);
        
        int arr1[] = new int[]{1, 2, 2, 4, 1, 1, 3, 3};
        majorelement.printMajority(arr1, arr1.length);
    }
}