/** ArrayedStack implements array-based, bounded-sized stacks.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/10
 *  Usage: new ArrayedStack<>(Class<T[]> cls,size);
 */
package cs6301.g39;

import java.lang.reflect.Array;  

public class ArrayedStack<T> {
	private T[] gen_array;
	private int index = -1;

	public ArrayedStack(Class<T[]> cls, int size){
		gen_array = cls.cast(Array.newInstance(cls.getComponentType(), size));  
	}

	/**
	 * Pushes an item onto the top of this stack
	 * @param item : the item to be pushed onto this stack.
	 * @return the item argument.
	 * @throws Exception if stack is full.
	 */
	public T push(T item) throws Exception {
		if(index < gen_array.length){
			index++;
			gen_array[index] = item;
			return gen_array[index];

		}else {
			throw new Exception();
		}
	}

	/**
	 * Removes the item at the top of this stack and returns that item as the value of this function.
	 * @return  : The item at the top of this stack
	 */
	public T pop(){
		if(index > -1){
			return gen_array[index--];
		}
		return null;
	}

	/**
	 * Looks at the item at the top of this stack without removing it from the stack.
	 * @return : the item at the top of this stack
	 */
	public T peek(){
		return gen_array[index];
	}

	/**
	 * Tests if this stack is empty.
	 * @return : true if and only if this stack contains no items; false otherwise.
	 */
	public boolean empty(){
		if(index == -1)
			return true;
		else return false;
	}

	/**
	 * Returns the 1-based position where an item is on this stack. If the item  occurs in this stack, this method returns the distance from the top of the stack of the occurrence nearest the top of the stack; the topmost item on the stack is considered to be at distance 1. 
	 * @param item
	 * @return the 1-based position from the top of the stack where the item is located; the return value -1 indicates that the item is not on the stack.
	 */
	public int search(T item){
		for(int i=0;i<=index;i++){
			if(gen_array[i].equals(item)){
				return i+1;
			}
		}
		return -1;
	}

}
