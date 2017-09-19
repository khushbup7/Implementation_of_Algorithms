// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g39;


public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num(-65);
	Num y = new Num("000100");
	
	//System.out.println(Num.power(x,y).sign);
    //System.out.println(Num.power(x,y));
    
    System.out.println(y.toString());
    Num.removeLeadingZeros(y);
    
    System.out.println(y.toString());
	
	//Num.add(x, y).printList();
	//Num.subtract(x, y).printList();
    }
}

