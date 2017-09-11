package cs6301.g39;

public class TestArrayedStack {
	public static void main(String args[]) throws Exception{
		ArrayedStack<String> arstck = new ArrayedStack<>(String[].class,5);
		System.out.println("Is arrayed-stack empty: " + arstck.empty());
		System.out.println("Pushed: " + arstck.push("My"));
		System.out.println("Pushed: " + arstck.push("Group"));
		System.out.println("Pushed: " + arstck.push("Three"));
		System.out.println("Pushed: " + arstck.push("Nine"));
		System.out.println("Popped: " + arstck.pop());
		System.out.println("Pushed: " + arstck.push("Is"));
		System.out.println("Is arrayed-stack empty: " + arstck.empty());
		System.out.println("Peek: " + arstck.peek());
		System.out.println("Search: " + arstck.search("4"));
		System.out.println("Search: " + arstck.search("Group"));
		System.out.println("Popped: " + arstck.pop());
		System.out.println("Popped: " + arstck.pop());
		System.out.println("Popped: " + arstck.pop());
		System.out.println("Popped: " + arstck.pop());
		System.out.println("Pushed: " + arstck.push("My"));
		System.out.println("Pushed: " + arstck.push("Group"));
		System.out.println("Pushed: " + arstck.push("Is"));
		System.out.println("Pushed: " + arstck.push("Three"));
		System.out.println("Pushed: " + arstck.push("Nine"));
		System.out.println("Pushed: " + arstck.push("Done"));
	}
}
