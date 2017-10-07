/** Class to implement huffman coding algorithm
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/05
*/

package cs6301.g39;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
	
	/* a function to build huffman code for given string s */
	public static HuffmanNode buildHuffmanCode(String s) {
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();   //a map to store characters and their frequencies in string s
		
		//calculating the frequencies of characters in a string and putting them into map
		for(char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		//creating a priority queue with the characters of s, the priority is their frequencies
		PriorityQueue<HuffmanNode> pq = getPriorityQueue(map);
		
		//Iteratively combine characters with lowest frequencies to create huffman tree
		while(pq.size() > 1) {
			HuffmanNode x = pq.remove();
			HuffmanNode y = pq.remove();
			HuffmanNode z = new HuffmanNode(x ,y);
			pq.add(z);
		}
		
		if(!pq.isEmpty())
			return pq.remove();
		else return null;
	}
	
	//function that takes a map and builds a priority queue out of it
	public static PriorityQueue<HuffmanNode> getPriorityQueue(Map<Character, Integer> map) {
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			HuffmanCoding.HuffmanNode hn = new HuffmanNode(entry.getValue(), entry.getKey());
			pq.add(hn);
		}
		
		return pq;
	}
	
	//class representing a node in huffman coding tree
	static class HuffmanNode implements Comparable<HuffmanNode> {
		char c; //a character in the string 
		HuffmanNode left;
		HuffmanNode right;
		int count; //frequency count of a character
		
		HuffmanNode(int x, char c) {
			this.count = x;
			this.c = c;
		}
		
		public HuffmanNode(HuffmanNode x, HuffmanNode y) {
			this.left = x;
			this.right = y;
			count = x.count + y.count;
		}

		@Override
		public int compareTo(HuffmanNode o) {
			if(this.count < o.count)
				return -1;
			else if(this.count > o.count)
				return 1;
			else return 0;
		}
	}
	
	//Main function : taking a string from console and building huffman codes out of it
	public static void main(String[] args) {
		HuffmanNode h = buildHuffmanCode("aaabccdddddeeee");
		
		printCodes(h , "");
	}

	/* function to print the codes by concatenating the labels along the edges from the root of the tree to the leaf node */
	private static void printCodes(HuffmanNode h, String s) {
		if (h == null)
	        return;
	 
	    if (h.c != '\0')
	        System.out.println(h.c + " : " + s + "\n");
	 
	    printCodes(h.left, s + "0");
	    printCodes(h.right, s + "1");
		
	}
}
