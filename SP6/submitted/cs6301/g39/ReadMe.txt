ReadMe SP6 

Group : g39
Authors: Khushbu Patil, Vatsal Patel, Shruti Shetye

Questions solved:
1. Question-2
   Implement Huffman Coding algorithm.  Create a class for representing coding trees.  Use a priority queue to hold the trees.  In each    step, the algorithm removes two trees with the smallest frequencies, merges them, and inserts it back into the priority queue.  At      the end, there is a single coding tree. Traverse the tree and output the binary codes for each symbol.
   
   Related files:
   HuffmanCoding.java - Contains algorithm implemmentation and main function as well
   Input: A string s. Default value of s is "aaabccdddddeeee".
   
2. Question-4
   Implement Prim's algorithm for MST using priority queue of edges (Prim1). Starter code is provided.

   
   Related files: 
   PrimMST.java - Contains algorithm implemmentations of prim1 and prim2
   TestPrimMST.java - Driver class for PrimMST.java
   PrimTest.txt - Sample graph with nodes and edges values
   
   Input: The size of the array n as a input parameter and k to TestSelection.java. 
			Default value of 20000 will be used. k = 10. 

3. Question-5 
   Implement binary heap and heap sort.  Starter code is provided. Include in your submission, a driver that uses heap sort to sort an     array in ascending order, and then in descending order.

   Related files:
   BinaryHeap.java - contains code for building a binary heap (min or max) and also for basic operations on heap
   MaxHeapComparator.java - contains code for comparator to create max heap
   MinHeapComparator.java - contains code for comparator to create min heap
   BinaryHeapTest.java - Driver class for BinaryHeap.java

4. Question-6
   Extend binary heaps to indexed binary heaps.  Implement Prim's algorithm for MST using indexed heaps of vertices (Prim2).  Starter      code is provided.

   Related files:
   IndexedHeap.java - contains code to build an indexed heap (base class is BinaryHeap.java)
   Index.java - index interface
   PrimMST.java - Contains algorithm implemmentations of prim1 and prim2
   TestPrimMST.java - Driver class for PrimMST.java 

5. Question-8
   Compare the running times of the following two algorithms for the problem of finding the k largest elements of a stream:
    (a) Use Java's priority queue to keep track of the k largest elements seen
    (b) Use your priority queue implementation (problem 5) using the replace() operation in that implementation, instead of delete+add 	to update PQ.
   Try large inputs that are randomly ordered and other inputs in increasing order.

   Related files:
   SelectkLargest.java - contains algorithm implementation
   ComparekLargest.java - Driver Class

   Input: The size of the array n and value of k (number of largest elements to be chosen) as input parameters to ComparekLargest.java. 
			Default value of n = 1000000 and k = 2000 will be used. 