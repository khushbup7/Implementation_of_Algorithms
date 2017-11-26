
package cs6301.g39;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MDS {

	static TreeMap<Long, ItemDetails> itemMap;
	static TreeMap<Long, Float> supplierMap;
	static TreeMap<Long, HashSet<Long>> desciptionItemMapping;

	public MDS() {
		itemMap = new TreeMap<Long, ItemDetails>();
		supplierMap = new TreeMap<Long, Float>();
		desciptionItemMapping = new TreeMap<>();

	}

	public static class Pair {
		long id;
		int price;

		public Pair(long id, int price) {
			this.id = id;
			this.price = price;
		}
	}

	/*
	 * add a new item. If an entry with the same id already exists, the new
	 * description is merged with the existing description of the item. Returns true
	 * if the item is new, and false otherwise.
	 */
	public boolean add(Long id, Long[] description) {

		ItemDetails thisItemDetails = itemMap.get(id);
		if (thisItemDetails == null) {
			thisItemDetails = new ItemDetails(id);
			thisItemDetails.mergeDescription(description, desciptionItemMapping);
			itemMap.put(id, thisItemDetails);
			return true;
		} else {
			thisItemDetails.mergeDescription(description, desciptionItemMapping);
			itemMap.put(id, thisItemDetails);
			return false;
		}
	}

	/*
	 * add a new supplier (Long) and their reputation (float in [0.0-5.0], single
	 * decimal place). If the supplier exists, their reputation is replaced by the
	 * new value. Return true if the supplier is new, and false otherwise.
	 */
	public boolean add(Long supplier, float reputation) {
		Float supplierRep = supplierMap.get(supplier);
		if (supplierRep != null) {
			supplierMap.put(supplier, reputation);
			return false;
		}
		supplierMap.put(supplier, reputation);
		return true;
	}

	/*
	 * add products and their prices at which the supplier sells the product. If
	 * there is an entry for the price of an id by the same supplier, then the price
	 * is replaced by the new price. Returns the number of new entries created.
	 */
	public int add(Long supplier, Pair[] idPrice) {
		int count = 0;
		for (Pair p : idPrice) {
			ItemDetails itemDetails = itemMap.get(p.id);
			if (itemDetails.priceMap.get(supplier) == null) {
				count++;
			}
			itemDetails.priceMap.put(supplier, p.price);
			itemDetails.minPrice = itemDetails.minPrice > p.price ? p.price : itemDetails.minPrice;
		}
		return count;
	}

	/*
	 * return an array with the description of id. Return null if there is no item
	 * with this id.
	 */
	public Long[] description(Long id) {
		ItemDetails itemDesc = itemMap.get(id);
		if (itemDesc == null)
			return null;

		Long[] result = new Long[itemDesc.descMap.size()];
		int i = 0;
		for (Long key : itemDesc.descMap.keySet()) {
			result[i] = key;
			i++;
		}
		return result;
	}

	/*
	 * given an array of Longs, return an array of items whose description contains
	 * one or more elements of the array, sorted by the number of elements of the
	 * array that are in the item's description (non-increasing order).
	 */
	public Long[] findItem(Long[] arr) {
		Long[] result;
		HashMap<Long, Integer> findItemsMap = new HashMap<>();
		for (Long descId : arr) {
			HashSet<Long> itemsWithThisDesc = desciptionItemMapping.get(descId);
			if (itemsWithThisDesc != null) {
				for (Long item : itemsWithThisDesc) {
					findItemsMap.put(item, findItemsMap.getOrDefault(item, 0) + 1);
				}
			}
		}
		PriorityQueue<Map.Entry<Long, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Long, Integer>>() {
			@Override
			public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
				if (o1.getValue() > o2.getValue())
					return -1;
				else if (o1.getValue() < o2.getValue())
					return 1;
				else return 0;
			}
		});
		pq.addAll(findItemsMap.entrySet());
		result = new Long[pq.size()];
		int i = 0;
		while(i < result.length) {
			result[i] = pq.remove().getKey();
			i++;
		}
		return result;
	}

	/*
	 * given a Long n, return an array of items whose description contains n, which
	 * have one or more suppliers whose reputation meets or exceeds the given
	 * minimum reputation, that sell that item at a price that falls within the
	 * price range [minPrice, maxPrice] given. Items should be sorted in order of
	 * their minimum price charged by a supplier for that item (non-decreasing
	 * order).
	 */
	public Long[] findItem(Long n, int minPrice, int maxPrice, float minReputation) {
		HashSet<Long> itemsWithThisDesc = desciptionItemMapping.get(n);
		PriorityQueue<ItemDetails> pq = new PriorityQueue<>(new Comparator<ItemDetails>() {

			@Override
			public int compare(ItemDetails o1, ItemDetails o2) {
				if (o1.minPrice < o2.minPrice)
					return -1;
				else
					return 1;
			}
		});
		out: for (Long item : itemsWithThisDesc) {
			ItemDetails details = itemMap.get(item);
			for (Map.Entry<Long, Integer> priceEntry : details.priceMap.entrySet()) {
				Long supplier = priceEntry.getKey();
				Integer price = priceEntry.getValue();
				if (price < maxPrice && price > minPrice && supplierMap.get(supplier) > minReputation) {
					pq.add(details);
					continue out;
				}
			}
		}

		Long[] result = new Long[pq.size()];
		int i = 0;
		while(i < result.length) {
			result[i] = pq.remove().id;
			i++;
		}
		return result;
	}

	/*
	 * given an id, return an array of suppliers who sell that item, ordered by the
	 * price at which they sell the item (non-decreasing order).
	 */
	public Long[] findSupplier(Long id) {
		TreeMap<Long, Integer> allSuppliers = itemMap.get(id).priceMap;
		PriorityQueue<Map.Entry<Long, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Long, Integer>>() {
			@Override
			public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
				if (o1.getValue() < o2.getValue())
					return -1;
				return 1;
			}
		});

		pq.addAll(allSuppliers.entrySet());
		Long[] result = new Long[pq.size()];
		int i = 0;
		while(i < result.length) {
			result[i] = pq.remove().getKey();
			i++;
		}

		return result;
	}

	/*
	 * given an id and a minimum reputation, return an array of suppliers who sell
	 * that item, whose reputation meets or exceeds the given reputation. The array
	 * should be ordered by the price at which they sell the item (non-decreasing
	 * order).
	 */
	public Long[] findSupplier(Long id, float minReputation) {
		TreeMap<Long, Integer> allSuppliers = itemMap.get(id).priceMap;
		PriorityQueue<Map.Entry<Long, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Long, Integer>>() {
			@Override
			public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
				if (o1.getValue() < o2.getValue())
					return -1;
				return 1;
			}
		});

		for (Map.Entry<Long, Integer> entry : allSuppliers.entrySet()) {
			if (supplierMap.get(entry.getKey()) >= minReputation)
				pq.add(entry);
		}
		Long[] result = new Long[pq.size()];
		int i = 0;
		while(i< result.length) {
			result[i] = pq.remove().getKey();
			i++;
		}

		return result;

	}

	/*
	 * find suppliers selling 5 or more products, who have the same identical
	 * profile as another supplier: same reputation, and, sell the same set of
	 * products, at identical prices. This is a rare operation, so do not do
	 * additional work in the other operations so that this operation is fast.
	 * Creative solutions that are elegant and efficient will be awarded excellence
	 * credit. Return array of suppliers satisfying above condition. Make sure that
	 * each supplier appears only once in the returned array.
	 */
	public Long[] identical() {
		return null;
	}

	/*
	 * given an array of ids, find the total price of those items, if those items
	 * were purchased at the lowest prices, but only from sellers meeting or
	 * exceeding the given minimum reputation. Each item can be purchased from a
	 * different seller.
	 */
	public int invoice(Long[] arr, float minReputation) {
		int totalPrice = 0;
		for (Long item : arr) {
			Integer minPrice = Integer.MAX_VALUE;
			ItemDetails itemDetails = itemMap.get(item);
			for (Map.Entry<Long, Integer> priceEntry : itemDetails.priceMap.entrySet()) {
				Long supplier = priceEntry.getKey();
				Float reputation = supplierMap.get(supplier);
				Integer supplierPrice = priceEntry.getValue();
				if (reputation >= minReputation && supplierPrice < minPrice) {
					minPrice = supplierPrice;
				}
			}
			if (minPrice != Integer.MAX_VALUE)
				totalPrice += minPrice;
		}
		return totalPrice;
	}

	/*
	 * remove all items, all of whose suppliers have a reputation that is equal or
	 * lower than the given maximum reputation. Returns an array with the items
	 * removed.
	 */
	public Long[] purge(float maxReputation) {
		return null;
	}

	/*
	 * remove item from storage. Returns the sum of the Longs that are in the
	 * description of the item deleted (or 0, if such an id did not exist).
	 */
	public Long remove(Long id) {
		ItemDetails itemDetails = itemMap.remove(id);
		if(itemDetails == null)
			return 0L;
		
		Long result = 0L;
		for(Long descId : itemDetails.descMap.keySet()) {
			result += descId;
			desciptionItemMapping.get(descId).remove(id);
		}
		return result;
	}

	/*
	 * remove from the given id's description those elements that are in the given
	 * array. It is possible that some elements of the array are not part of the
	 * item's description. Return the number of elements that were actually removed
	 * from the description.
	 */
	public int remove(Long id, Long[] arr) {
		ItemDetails itemDetails = itemMap.get(id);
		int count = 0;
		for(Long desc : arr) {
			if(itemDetails.descMap.remove(desc) != null) {
				desciptionItemMapping.get(desc).remove(id);
				count++;
			}
		}
		return count;
	}

	/*
	 * remove the elements of the array from the description of all items. Return
	 * the number of items that lost one or more terms from their descriptions.
	 */
	public int removeAll(Long[] arr) {
		HashSet<Long> itemsModified = new HashSet<Long>();
		for(Long desc : arr) {
			HashSet<Long> itemsWithDesc = desciptionItemMapping.get(desc);
			for(Long item : itemsWithDesc) {
				itemsModified.add(item);
				ItemDetails itemDetails = itemMap.get(item);
				itemDetails.descMap.remove(desc);
			}
			desciptionItemMapping.remove(desc);
		}
		return itemsModified.size();
	}

	static class ItemDetails {

		Long id;
		TreeMap<Long, Boolean> descMap;
		TreeMap<Long, Integer> priceMap;
		int minPrice;

		ItemDetails(Long id) {
			this.id = id;
			descMap = new TreeMap<>();
			priceMap = new TreeMap<>();
			minPrice = Integer.MAX_VALUE;
		}

		void mergeDescription(Long[] description, TreeMap<Long, HashSet<Long>> desciptionItemMapping) {
			for (Long l : description) {
				descMap.put(l, true);
				HashSet<Long> descItems = desciptionItemMapping.get(l);
				if (descItems != null)
					descItems.add(id);
				else
					desciptionItemMapping.put(l, new HashSet<Long>(Arrays.asList(id)));
			}
		}
	}
}