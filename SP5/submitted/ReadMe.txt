ReadMe SP5 

Group : g39
Authors: Khushbu Patil, Vatsal Patel, Shruti Shetye

Questions solved:
1. Compare the performance of the two versions of partition discussed in class
   on the running time of Quick sort, on arrays with distinct elements.
   Try arrays that are randomly ordered (by shuffle) and arrays in
   descending order. 
   
   Related files:
   QuickSort.java - Contains algorithm implemmentations
   TestQuickSort.java - Driver class for QuickSort.java
   
   Input: The size of the array n as a input parameter to TestQuickSort.java. Default value of 20000 will be used.
   
3. Implement 3 versions of the Select algorithm (finding k largest elements)
   and empirically evaluate their performance:
   (a) Create a priority queue (max heap) of the n elements, and use remove() k times.
   (b) Use a priority queue (min heap) of size k to keep track of the
       k largest elements seen so far, as you iterate over the array.
   (c) Implement the O(n) algorithm for Select discussed in class.
   
   Related files: 
   SelectkLargest.java - Contains algorithm implemmentations
   TestSelection.java - Driver class for QuickSort.java
   
   Input: The size of the array n as a input parameter and k to TestSelection.java. 
			Default value of 20000 will be used. k = 10. 