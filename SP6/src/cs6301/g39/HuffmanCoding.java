package cs6301.g39;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
	public static HuffmanNode buildHuffmanCode(String s) {
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		PriorityQueue<HuffmanNode> pq = getPriorityQueue(map);
		
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
	
	public static PriorityQueue<HuffmanNode> getPriorityQueue(Map<Character, Integer> map) {
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			HuffmanCoding.HuffmanNode hn = new HuffmanNode(entry.getValue(), entry.getKey());
			pq.add(hn);
		}
		
		return pq;
	}
	
	static class HuffmanNode implements Comparable<HuffmanNode> {
		char c;
		HuffmanNode left;
		HuffmanNode right;
		int count;
		
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
	
	
	public static void main(String[] args) {
		HuffmanNode h = buildHuffmanCode("aaabccdddddeeee");
		
		printCodes(h , "");
	}

	private static void printCodes(HuffmanNode h, String s) {
		if (h == null)
	        return;
	 
	    if (h.c != '\0')
	        System.out.println(h.c + " : " + s + "\n");
	 
	    printCodes(h.left, s + "0");
	    printCodes(h.right, s + "1");
		
	}
}
