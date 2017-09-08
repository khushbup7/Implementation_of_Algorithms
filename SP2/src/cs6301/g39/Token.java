package cs6301.g39;

public class Token<T> {
	
	T element;
	boolean isOperator;
	
	
	Token(T c){
		element = c;
	}
	
    public String toString() {
    	return this.element.toString();
    	}
}
