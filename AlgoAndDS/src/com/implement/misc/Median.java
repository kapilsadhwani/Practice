package com.implement.misc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
public class Median 
{
     
//BSelect by Blum, Floyd, Pratt, Rivest, Tarjon (1972)
//@Author Kishore Ravindran
//@Date of Creating 21-Feb-2011
//Algorithm: DeterministicSelect
//Step 1: Break n elements into blocks of 5 each.
//Step 2: Compute the median of each 5-element block in constant time.
//Step 3: Collect together the n/5 medians and recursively compute  their median.
//Step 4: Chose the resulting element as a pivot
 
    public static void main (String[] args)
    {
        System.out.println("Please enter the size of the array");
        Scanner myscan = new Scanner(System.in);
        // Handle InputMismatchException exception here;
        int number = myscan.nextInt();
        Integer [] input = new Integer[number];
        System.out.println("Please enter the array elements");
        System.out.println("");
        for(int i=0;i<number;i++)
        {
            input[i] = myscan.nextInt();
        }
        System.out.println("Enter a number less than the size of the array");
        System.out.println("");
        // Check and InputMismatchException exception needs to be handled here;
        number = myscan.nextInt();
        Median m = new Median();
        System.out.println("The " + number + "th smallest element in the input is " + m.DeterministicSelect(input, number));
    }
    // simple module to find the median of a array
    int median(Integer[] array)
    {
        if (array.length == 1)
        {
            return array[0];
        }
        int smallerCount = 0;
        for (int i = 0 ; i < array.length ; i++)
        {
            for (int j = 0 ; j < array.length ; j++)
            {
                if (array[i] < array[j])
                {
                    smallerCount++;
                }
            }
            if (smallerCount == (array.length - 1)/2)
            {
                return array[i];
            }
            smallerCount = 0;
        }
        return -1; //should never happen
    }
    // finds pivot element of a given array recursively using DeterministicSelect
    int Pivot(Integer[] array)
    {       
        if (array.length == 1)
        {
            return array[0];
        }
        //Divide A into n/5 groups of 5 elements each
        int numGroups = array.length / 5;
        if (array.length % 5 > 0)
        {
            numGroups++;
        }
        Integer[] setOfMedians = new Integer[numGroups];
        for (int i = 0 ; i < numGroups ; i++)
        {
            Integer[] subset;
            if(array.length % 5 > 0)
            {
                if (i == numGroups - 1)
                {
                    subset = new Integer[array.length % 5];
                }
                else
                {
                    subset = new Integer[5];
                }
            }
            else
            {
                subset = new Integer[5];
            }
            for (int j = 0; j < subset.length ; j++)
            {
                subset[j] = array[5*i+j];
            }
            //Find the median of each group
            setOfMedians[i] = median(subset);
        }
        //Use DeterministicSelect to find the median, p, of the n/5 medians
        int goodPivot = DeterministicSelect(setOfMedians, setOfMedians.length/2 );
        return goodPivot;
    }
      
//The algorithm in words:
//1. Divide n elements into groups of 5 
//2. Find median of each group (How? How long?)
//3. Use Select() recursively to find median x of the n/5? medians
//4. Partition the n elements around x. Let k = rank(x)
//5. if (i == k) then return x else (i > k) use Select() recursively to find (i-k)th 
//smallest element in last partition
//source  
//Lecture PDF mentioned in the blog post
//and MIT Lecture 6 order statistics.
 
    int DeterministicSelect(Integer[] array, int k)
    {
        if (array.length == 1)
        {
            return array[0];
        }
        int pivot = Pivot(array);
        //set is used to ignore duplicate values
        HashSet<Integer> A1 = new HashSet<Integer>();
        HashSet<Integer> A2 = new HashSet<Integer>();
        for (int i = 0; i < array.length ; i++)
        {
            if (array[i] < pivot)
            {
                A1.add(array[i]);
            }
            else if (array[i] > pivot)
            {
                A2.add(array[i]);
            }
        }
        if (A1.size() >= k)
        {
            return DeterministicSelect(A1.toArray(new Integer[0]) ,k);
        }
        else if (A1.size() == k-1)
        {
            return pivot;
        }
        else
        {
            return DeterministicSelect(A2.toArray(new Integer[0]) , k - A1.size() - 1);
        }
    }
    
    // Deterministic another implementation
    
    public static int findKthLargestUsingMedian(Integer[] array, int k) {
        // Step 1: Divide the list into n/5 lists of 5 element each.
        int noOfRequiredLists = (int) Math.ceil(array.length / 5.0);
        // Step 2: Find pivotal element aka median of medians.
        int medianOfMedian =  findMedianOfMedians(array, noOfRequiredLists);
        //Now we need two lists split using medianOfMedian as pivot. All elements in list listOne will be grater than medianOfMedian and listTwo will have elements lesser than medianOfMedian.
        List<Integer> listWithGreaterNumbers = new ArrayList<>(); // elements greater than medianOfMedian
        List<Integer> listWithSmallerNumbers = new ArrayList<>(); // elements less than medianOfMedian
        for (Integer element : array) {
            if (element < medianOfMedian) {
                listWithSmallerNumbers.add(element);
            } else if (element > medianOfMedian) {
                listWithGreaterNumbers.add(element);
            }
        }
        // Next step.
        if (k <= listWithGreaterNumbers.size()) return findKthLargestUsingMedian((Integer[]) listWithGreaterNumbers.toArray(new Integer[listWithGreaterNumbers.size()]), k);
        else if ((k - 1) == listWithGreaterNumbers.size()) return medianOfMedian;
        else if (k > (listWithGreaterNumbers.size() + 1)) return findKthLargestUsingMedian((Integer[]) listWithSmallerNumbers.toArray(new Integer[listWithSmallerNumbers.size()]), k-listWithGreaterNumbers.size()-1);
        return -1;
    }

    public static int findMedianOfMedians(Integer[] mainList, int noOfRequiredLists) {
        int[] medians = new int[noOfRequiredLists];
        for (int count = 0; count < noOfRequiredLists; count++) {
            int startOfPartialArray = 5 * count;
            int endOfPartialArray = startOfPartialArray + 5;
            Integer[] partialArray = Arrays.copyOfRange((Integer[]) mainList, startOfPartialArray, endOfPartialArray);
            // Step 2: Find median of each of these sublists.
            int medianIndex = partialArray.length/2;
            medians[count] = partialArray[medianIndex];
        }
        // Step 3: Find median of the medians.
        return medians[medians.length / 2];
    }
    
    public static int findKthLargestUsingPriorityQueue(Integer[] nums, int k) {
        int p = 0;
        int numElements = nums.length;
        // create priority queue where all the elements of nums will be stored
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        // place all the elements of the array to this priority queue
        for (int n : nums) {
            pq.add(n);
        }

        // extract the kth largest element
        while (numElements - k + 1 > 0) {
            p = pq.poll();
            k++;
        }

        return p;
    }
    
   /* public static void main(String[] args) throws IOException {
        Integer[] numbers = new Integer[]{2, 3, 5, 4, 1, 12, 11, 13, 16, 7, 8, 6, 10, 9, 17, 15, 19, 20, 18, 23, 21, 22, 25, 24, 14};
        System.out.println(findKthLargestUsingMedian(numbers, 8));
        System.out.println(findKthLargestUsingPriorityQueue(numbers, 8));
    } */
    
    ////////////// Randomised Select //////////////////////
    public int quickSelect(ArrayList<Integer>list, int nthSmallest){
        //Choose random number in range of 0 to array length
        Random random =  new Random();
        //This will give random number which is not greater than length - 1
        int pivotIndex = random.nextInt(list.size() - 1); 

        int pivot = list.get(pivotIndex);

        ArrayList<Integer> smallerNumberList = new ArrayList<Integer>();
        ArrayList<Integer> greaterNumberList = new ArrayList<Integer>();

        //Split list into two. 
        //Value smaller than pivot should go to smallerNumberList
        //Value greater than pivot should go to greaterNumberList
        //Do nothing for value which is equal to pivot
        for(int i=0; i<list.size(); i++){
            if(list.get(i)<pivot){
                smallerNumberList.add(list.get(i));
            }
            else if(list.get(i)>pivot){
                greaterNumberList.add(list.get(i));
            }
            else{
                //Do nothing
            }
        }

        //If smallerNumberList size is greater than nthSmallest value, nthSmallest number must be in this list 
        if(nthSmallest < smallerNumberList.size()){
            return quickSelect(smallerNumberList, nthSmallest);
        }
        //If nthSmallest is greater than [ list.size() - greaterNumberList.size() ], nthSmallest number must be in this list
        //The step is bit tricky. If confusing, please see the above loop once again for clarification.
        else if(nthSmallest > (list.size() - greaterNumberList.size())){
            //nthSmallest will have to be changed here. [ list.size() - greaterNumberList.size() ] elements are already in 
            //smallerNumberList
            nthSmallest = nthSmallest - (list.size() - greaterNumberList.size());
            return quickSelect(greaterNumberList,nthSmallest);
        }
        else{
            return pivot;
        }
    }
}