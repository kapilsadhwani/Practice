package com.implement.twoPointer;

class SortColors {
	/* Function to put all 0s on left, all 1s in the middle and all 2s on right */
	void sortColors(int nums[]) {
		/* Initialize left and right indexes */
		int left = 0, current = 0, right = nums.length - 1;
		int temp;

		while (current < right) {
			/* Increment left index while we see 0 at left */
			while (nums[left] == 0 && left < right) {
				left++;
				current++;
			}

			/* Decrement right index while we see 2 at right */
			while (nums[right] == 2 && current < right)
				right--;

			while (current < right) {
				switch (nums[current]) {
				case 0:
					if(left != current){
						temp = nums[left];
						nums[left] = nums[current];
						nums[current] = temp;
					}

					left++;
					current++;
					break;
				case 1:
					current++;
					break;
				case 2:
					if(current != right){
						temp = nums[right];
						nums[right] = nums[current];
						nums[current] = temp;
					}

					right--; // current is not incremented here as we don't know
								// if right element was 0,1or2
					break;
				}
			}

			/*
			 * If left is smaller than right then there is a 1 at left and a 0
			 * at right. Exchange arr[left] and arr[right]
			 */
			if (left < right) {
				if(nums[left] > nums[right]){
					temp = nums[left];
					nums[left] = nums[right];
					nums[right] = temp;
				}
				left++;
				right--;
			}
		}
	}

	/* Utility function to print array arr[] */
	static void printArray(int arr[], int arr_size) {
		int i;
		for (i = 0; i < arr_size; i++)
			System.out.print(arr[i] + " ");
		System.out.println("");
	}

	/* Driver Program to test above functions */
	public static void main(String[] args) {
		SortColors seg = new SortColors();
		int arr[];
		int arr_size;
		
		arr = new int[]{ 0, 2, 0, 2, 2, 2, 1, 2, 1, 2, 0, 2, 1, 2 };
		arr_size = arr.length;
		seg.sortColors(arr);
		System.out.println("Array after seggregation ");
		printArray(arr, arr_size);
		
		arr = new int[]{2,0,2,1,1,0};
		arr_size = arr.length;
		seg.sortColors(arr);
		System.out.println("Array after seggregation ");
		printArray(arr, arr_size);
		
		arr = new int[]{2,0,1};
		arr_size = arr.length;
		seg.sortColors(arr);
		System.out.println("Array after seggregation ");
		printArray(arr, arr_size);
		
		arr = new int[]{0,1,2};
		arr_size = arr.length;
		seg.sortColors(arr);
		System.out.println("Array after seggregation ");
		printArray(arr, arr_size);
		
		arr = new int[]{2,1,0};
		arr_size = arr.length;
		seg.sortColors(arr);
		System.out.println("Array after seggregation ");
		printArray(arr, arr_size);
		
		arr = new int[]{1,0,0};
		arr_size = arr.length;
		seg.sortColors(arr);
		System.out.println("Array after seggregation ");
		printArray(arr, arr_size);
	}
}